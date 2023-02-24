package com.hardtech.securityservice;

import com.hardtech.securityservice.Security.Entities.AppRole;
import com.hardtech.securityservice.Security.Entities.AppUser;
import com.hardtech.securityservice.Security.Services.AccountService;
import com.hardtech.securityservice.tasks.entities.Task;
import com.hardtech.securityservice.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@SpringBootApplication
@RequiredArgsConstructor
public class SecurityServiceV2Application {

    static int i = 0;

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceV2Application.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    CommandLineRunner run(AccountService accountService, TaskService taskService) {
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

            //Ajouts de tâches
            String[] descriptions = {"Shopping de quelques produits", "Quelques exercices de musculatures",
                    "Mathématiques et Structures de données", "Sauce Gombo"};
            accountService.listUsers()
                    .forEach(user -> {
                        i = 0;
                        List.of("Shopping", "Sport", "Exercices d'école", "Cuisine")
                                .forEach(task -> {
                                    Task task1 = new Task();
                                    task1.setName(task);
                                    task1.setDescription(descriptions[i]);
                                    task1.setStatus(false);
                                    task1.setUsername(user.getUsername());
                                    taskService.saveTask(task1);
                                    i++;
                                });
                    });
        };


    }

}
