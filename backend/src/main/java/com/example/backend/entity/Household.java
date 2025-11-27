package com.example.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "households")
public class Household {

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

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
