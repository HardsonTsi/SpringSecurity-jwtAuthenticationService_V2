package com.hartech.securityservice.Security.Repositories;

import com.hartech.securityservice.Security.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUserByUsername(String username);

}
