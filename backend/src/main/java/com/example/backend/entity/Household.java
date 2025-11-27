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
    private User househead; // references users table

    @Column(length = 200, nullable = false)
    private String address = "";

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false)
    private Boolean isActive = true;

    // One household has many members
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Member> members;

}
