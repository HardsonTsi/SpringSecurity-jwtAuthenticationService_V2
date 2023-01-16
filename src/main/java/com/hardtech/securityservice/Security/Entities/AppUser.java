package com.hardtech.securityservice.Security.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DynamicUpdate
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @OneToMany
//    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appUserRoles = new ArrayList<>();

    //Constructeur utilisé juste pour le test
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
