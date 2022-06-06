package com.hartech.securityservice;

import com.hartech.securityservice.Security.Entities.AppRole;
import com.hartech.securityservice.Security.Entities.AppUser;
import com.hartech.securityservice.Security.Services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Controller
public class SecurityServiceV2Application {


    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceV2Application.class, args);
    }

    @GetMapping("/")
    public String welcome(){
        return "Welcome to my security app";
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(AccountService accountService) {
        return args -> {
            // Ajout d'utilisateurs sans rôles
            List.of("Tessi", "Agossou", "Saizonou", "Gouthon", "Avahouin")
                    .forEach(appUser -> accountService.addNewUser(new AppUser(appUser, appUser)));
            System.out.println("Users enregistres");

            //Ajout de rôles
            List.of("ADMIN", "USER", "CUSTOMER")
                    .forEach(appRole -> accountService.addNewRole(new AppRole(appRole)));
            System.out.println("Roles enregistres");

            //Ajout de rôles aux utilisateurs
            accountService.listUsers()
                    .forEach(appUser -> {
                        double random = Math.random() * 10;
                        String roleName = (random >= 0 && random <= 4) ? "ADMIN" : (random >= 5 && random <= 8) ? "CUSTOMER" : "USER";
                        accountService.addRoleToUser(appUser.getUsername(), roleName);
                        System.out.println(roleName + " ==> " + appUser.getUsername());
                    });
        };
    }

}
