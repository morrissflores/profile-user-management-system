package com.example.ProfileManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName; // Middle name is optional, so no validation

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Birthdate is required")
    private LocalDate birthDate;

    private String gender; // Optional: values can be "Male" or "Female"
}
