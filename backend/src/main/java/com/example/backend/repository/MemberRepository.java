package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByHouseholdId(Integer householdId);
}
