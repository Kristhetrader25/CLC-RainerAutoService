package com.example.demo;

import com.example.demo.models.User;
import com.example.demo.models.LoginPrincipal;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Only slice the web layer for AuthController
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    // Mock collaborators the controller depends on
    @MockBean private AuthService authService;
    @MockBean private UserService userService;

    @Test
    @DisplayName("GET /login renders login view with model")
    void getLogin() throws Exception {
        mvc.perform(get("/login"))
           .andExpect(status().isOk())
           .andExpect(view().name("login"))
           .andExpect(model().attributeExists("user")); // from @ModelAttribute("user")
    }

    @Test
    @DisplayName("POST /login bad credentials returns login with authError")
    void postLogin_badCreds() throws Exception {
        when(authService.authenticate("demo", "wrong")).thenReturn(null);

        mvc.perform(post("/login")
                .param("username", "demo")
                .param("password", "wrong"))
           .andExpect(status().isOk())
           .andExpect(view().name("login"))
           .andExpect(model().attributeExists("authError"));
    }

    @Test
    @DisplayName("POST /login success redirects to home and sets session principal")
    void postLogin_success() throws Exception {
        when(authService.authenticate("demo", "demo"))
                .thenReturn(new LoginPrincipal("demo", "Demo User", "USER"));

        mvc.perform(post("/login")
                .param("username", "demo")
                .param("password", "demo"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("POST /logout redirects to home")
    void postLogout() throws Exception {
        mvc.perform(post("/logout"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/"));
        verify(authService).logout(any()); // session invalidation called
    }
}