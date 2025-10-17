package com.example.demo;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.services.AuthService;            
import com.example.demo.models.LoginPrincipal;         
import jakarta.servlet.http.HttpSession;  

import jakarta.validation.Valid;

/**
 * Handles authentication-related web flows for Rainier Auto Service, including:
 * <ul>
 *   <li>Rendering the login and registration pages</li>
 *   <li>Processing login attempts against the configured {@link AuthService}</li>
 *   <li>Processing user registration via {@link UserService}</li>
 *   <li>Logging out by invalidating the current HTTP session</li>
 * </ul>
 * <p>
 * Notes:
 * <ul>
 *   <li>This milestone emulates authentication (no database persistence required yet).</li>
 *   <li>A lightweight principal is stored in the session to drive UI/menu state.</li>
 *   <li>Registration validation relies on Bean Validation constraints declared on {@link User}.</li>
 * </ul>
 */
@Controller
public class AuthController {
	
	private final UserService userService;
	private final AuthService authService;

	/**
	 * Constructs the controller with required collaborators.
	 *
	 * @param userService service responsible for user registration and username checks
	 * @param authService authentication boundary used to verify credentials and manage logout
	 */
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService; 
    }
    
    /**
     * Supplies a default {@link User} instance for views that bind to "user"
     * (e.g., the registration page). This ensures the form always has a model.
     *
     * @return an empty {@link User} for data binding
     */
    @ModelAttribute("user")
    public User userModel() {
        return new User();
    }

    /**
     * Renders the login page and provides a form-backing DTO for credentials.
     *
     * @param model view model used to pass the page title and loginRequest
     * @return the logical view name {@code "login"}
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Employee Login - Rainier Auto Service");
        model.addAttribute("loginRequest", new com.example.demo.models.LoginRequest());
        return "login"; // loads login.html
    }
    
    /**
     * Renders the registration page.
     *
     * @param model view model used to pass the page title
     * @return the logical view name {@code "register"}
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register - Rainier Auto Service");
        return "register"; // loads register.html
    }
    
    /**
     * Processes a registration submission. Validates the {@link User} payload,
     * checks for username uniqueness, and delegates to {@link UserService} for creation.
     *
     * @param user   the submitted registration model bound to "user"
     * @param result binding/validation outcome
     * @param model  view model for returning validation messages if needed
     * @return {@code "register"} when validation fails; otherwise a redirect with success flag
     */
    @PostMapping("/register")
	public String processRegistration(@Valid @ModelAttribute("user") User user,
	                                  BindingResult result,
	                                  Model model) {
		// Server-side uniqueness check for the username.
		if (userService.usernameTaken(user.getUsername())) {
			result.rejectValue("username", "username.taken", "Username already in use");
		}
		
		// If any field-level or custom validation failed, return to the form.
		if (result.hasErrors()) {
			return "register";
		}
		
		// Persist the new user record (implementation detail depends on milestone).
		userService.register(user);
		
		// Redirect back to the register page with a success indicator.
		return "redirect:/register?success";
	}
    
    /**
     * Processes a login submission. Validates the login DTO, attempts authentication,
     * and, on success, stores a minimal {@link LoginPrincipal} in the session before
     * redirecting to the home page.
     *
     * @param form    the submitted login form model (username/password)
     * @param result  binding/validation outcome for the login form
     * @param model   view model used to pass a global auth error when credentials are invalid
     * @param session current HTTP session used to store the authenticated principal
     * @return {@code "login"} when invalid; {@code "redirect:/"} on success
     */
    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("loginRequest") com.example.demo.models.LoginRequest form,
            BindingResult result,
            Model model,
            jakarta.servlet.http.HttpSession session) {

        // If any field-level constraints failed (e.g., @NotBlank, @Size), re-render the login view.
        if (result.hasErrors()) {
            return "login";
        }

        // Delegate credential verification to the authentication service.
        com.example.demo.models.LoginPrincipal principal =
                authService.authenticate(form.getUsername(), form.getPassword());

        // Invalid credentials: show a global message and stay on the login page.
        if (principal == null) {
            model.addAttribute("authError", "Invalid username or password.");
            return "login";
        }

        // Successful authentication: store minimal identity in the session for UI/menu toggling.
        session.setAttribute("principal", principal);

        // Redirect away from the login page (destination can be adjusted as needed).
        return "redirect:/";  
    }
    
    /**
     * Logs out the current user by invalidating the HTTP session and redirects home.
     *
     * @param session the active HTTP session to invalidate
     * @return {@code "redirect:/"} after logout
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        authService.logout(session);
        return "redirect:/";
    }
}

