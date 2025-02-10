package com.example.ProfileManagementSystem.repository;


import com.example.ProfileManagementSystem.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
