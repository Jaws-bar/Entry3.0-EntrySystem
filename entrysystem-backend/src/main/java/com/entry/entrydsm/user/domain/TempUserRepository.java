package com.entry.entrydsm.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TempUserRepository extends JpaRepository<TempUser, String> {
    @Transactional
    void deleteByEmail(String email);
}
