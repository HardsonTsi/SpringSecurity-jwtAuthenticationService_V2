package com.hardtech.securityservice.Security.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class AppRole {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String roleName;

    //Constructeur utilisé juste pour le test
    public AppRole(String roleName) {
        this.roleName = roleName;
    }
}
