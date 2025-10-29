package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.patient;

    private String phone;
    private LocalDate dateOfBirth;
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "doctor")
    private List<MedicalRecord> doctorRecords;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> patientAppointments;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "sender")
    private List<Chat> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Chat> receivedMessages;

    @OneToMany(mappedBy = "user")
    private List<HealthStat> healthStats;

    @OneToMany(mappedBy = "user")
    private List<Log> logs;

    // Enum
    public enum Role {
        patient,
        doctor,
        admin
    }

}
