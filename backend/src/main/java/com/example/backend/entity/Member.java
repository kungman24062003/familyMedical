package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String fullname;

    @Column(length = 12, nullable = false)
    private String idCard;

    @Column(length = 200)
    private String address = "";

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Column(length = 12)
    private String bhyt = "";

    @Column(length = 100, nullable = false)
    private String relation;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "household_id", nullable = false)
    private HouseHold household;

    // Enums for gender and relation
    public enum Gender {
        MALE, FEMALE, OTHER
    }


}
