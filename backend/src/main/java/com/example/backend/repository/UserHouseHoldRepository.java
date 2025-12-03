package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.backend.entity.UserHouseHold;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserHouseHoldRepository extends JpaRepository<UserHouseHold, Integer> {
    List<UserHouseHold> findByUserId(Integer userId);
    List<UserHouseHold> findByHouseholdId(Integer householdId);
}
