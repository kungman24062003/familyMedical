package com.example.backend.repository;

import com.example.backend.entity.HealthStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthStatRepository extends JpaRepository<HealthStat, Integer> {
}
