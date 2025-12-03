package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.UserHouseHold;

public interface UserHouseHoldRepository extends JpaRepository<UserHouseHold, Integer> {
    List<UserHouseHold> findByUserId(Integer userId);
    List<UserHouseHold> findByHouseholdId(Integer householdId);
}
