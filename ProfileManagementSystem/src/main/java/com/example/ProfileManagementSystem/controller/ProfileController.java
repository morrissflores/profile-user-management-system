package com.example.ProfileManagementSystem.controller;

import com.example.ProfileManagementSystem.model.Profile;
import com.example.ProfileManagementSystem.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profile = profileService.getProfileById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@Valid @RequestBody Profile profile) {
        if (profileService.existsByFirstAndLastName(profile.getFirstName(), profile.getLastName())) {
            return ResponseEntity.badRequest().body("Profile with this First Name and Last Name already exists.");
        }
        Profile savedProfile = profileService.saveProfile(profile);
        return ResponseEntity.ok(savedProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @Valid @RequestBody Profile updateProfile) {
        Optional<Profile> existingProfile = profileService.getProfileById(id);
        if (existingProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (profileService.existsByFirstAndLastName(updateProfile.getFirstName(), updateProfile.getLastName()) &&
                !existingProfile.get().getId().equals(id)) {
            return ResponseEntity.badRequest().body("Profile with this First Name and Last Name already exists.");
        }
        updateProfile.setId(id);
        return ResponseEntity.ok(profileService.saveProfile(updateProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfile(@PathVariable Long id) {
        if (!profileService.getProfileById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        profileService.deleteProfileById(id);
        return ResponseEntity.ok("Profile deleted successfully.");
    }
}
