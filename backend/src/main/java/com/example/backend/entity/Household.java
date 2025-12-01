package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "households")
public class HouseHold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "househead_id")
    private User househead; // head of the household

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor; // family doctor

    @Column(length = 200, nullable = false)
    private String address = "";

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Member> members;
}
