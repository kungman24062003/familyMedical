package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.Member;
import com.example.backend.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // nếu frontend chạy ở port này
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAll();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Integer id) {
        return memberService.getById(id);
    }

    @GetMapping("/household/{householdId}")
    public List<Member> getMembersByHousehold(@PathVariable Integer householdId) {
        return memberService.getByHouseholdId(householdId);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.create(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Integer id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.delete(id);
    }
}
