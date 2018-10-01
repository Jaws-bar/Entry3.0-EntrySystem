package com.entry.entrydsm.school.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, String> {

    List<School> findByGovernmentContainingAndNameContaining(String government, String name);
}
