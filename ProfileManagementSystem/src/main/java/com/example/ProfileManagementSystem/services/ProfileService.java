package com.example.ProfileManagementSystem.services;

import com.example.ProfileManagementSystem.model.Profile;
import com.example.ProfileManagementSystem.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    //get all profiles
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    //get profile by ID
    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);

    }

    //save create and update
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    //delete
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }

    //check if firstname + lastname is unique
    public boolean existsByFirstAndLastName(String firstName, String lastName) {
        return profileRepository.existsByFirstNameAndLastName(firstName, lastName);
    }
}
