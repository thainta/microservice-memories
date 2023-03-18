package com.example.main.controller.admin;

import com.example.main.builder.AuthenticationResponse;
import com.example.main.exeption.AccountNotFoundException;
import com.example.main.exeption.UserNotFoundException;
import com.example.main.model.Accounts;
import com.example.main.service.interfaces.AccountService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.example.main.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/pages")
public class LoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("account",new Accounts());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken)        {
            return "pages/login";
        }

        return "redirect:/pages/dashboard";
    }
    @PostMapping("/doLogin")
    public String doLogin(Model model, @ModelAttribute Accounts account, BindingResult bindingResult) throws AccountNotFoundException, UserNotFoundException {
        System.out.println("login");
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            System.out.println("Person is: "+ account.getEmail());
            return "pages/login";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("account",account);
        AuthenticationResponse authenticationResponse = accountService.authenticate(account);
        if(!authenticationResponse.getResult().getAuthorities().equals(List.of(new SimpleGrantedAuthority("ADMIN")))){
            return "redirect:/pages/login";
        };
        Cookie cookie = new Cookie("Authorization", authenticationResponse.getToken());
        response.addCookie(cookie);

        System.out.println(authenticationResponse);
        return "redirect:/pages/dashboard";
    }
}
