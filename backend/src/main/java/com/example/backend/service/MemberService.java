package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member getById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public List<Member> getByHouseholdId(Integer householdId) {
        return memberRepository.findByHouseholdId(householdId);
    }

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public Member update(Integer id, Member updated) {
        Member existing = getById(id);
        existing.setFullname(updated.getFullname());
        existing.setIdCard(updated.getIdCard());
        existing.setAddress(updated.getAddress());
        existing.setGender(updated.getGender());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setBhyt(updated.getBhyt());
        existing.setRelation(updated.getRelation());
        existing.setHousehold(updated.getHousehold());
        return memberRepository.save(existing);
    }

    public void delete(Integer id) {
        memberRepository.deleteById(id);
    }
}
