package com.entry.entrydsm.tempuser.model;

import com.entry.entrydsm.tempuser.model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempUserRepository extends JpaRepository<TempUser, String> {
    boolean existsTempUserByEmail(String email);

    Optional<TempUser> findByCode(String code);
}
