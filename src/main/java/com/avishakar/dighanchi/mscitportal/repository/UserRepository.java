package com.avishakar.dighanchi.mscitportal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.avishakar.dighanchi.mscitportal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
