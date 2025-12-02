package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.HouseHold;
import com.example.backend.entity.Member;
import com.example.backend.repository.HouseHoldRepository;
import com.example.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final HouseHoldRepository householdRepo;
    private final MemberRepository memberRepo;

    public List<Member> getAllMembers(Integer householdId) {
        return memberRepo.findByHouseholdId(householdId);
    }

    public Member getMember(Integer householdId, Integer memberId) {
        return memberRepo.findById(memberId)
                .filter(m -> m.getHousehold().getId().equals(householdId))
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member createMember(Integer householdId, Member member) {
        HouseHold household = householdRepo.findById(householdId)
                .orElseThrow(() -> new RuntimeException("Household not found"));

        member.setHousehold(household);
        return memberRepo.save(member);
    }

    public Member updateMember(Integer householdId, Integer memberId, Member data) {
        Member member = getMember(householdId, memberId);
        member.setFullname(data.getFullname());
        member.setRelation(data.getRelation());
        return memberRepo.save(member);
    }

    public void deleteMember(Integer householdId, Integer memberId) {
        Member member = getMember(householdId, memberId);
        memberRepo.delete(member);
    }
}
