package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.UserHouseHold;
import com.example.backend.service.UserHouseHoldService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-households")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserHouseHoldController {

    private final UserHouseHoldService service;

    @GetMapping
    public List<UserHouseHold> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserHouseHold getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserHouseHold> getByUserId(@PathVariable Integer userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/household/{householdId}")
    public List<UserHouseHold> getByHouseholdId(@PathVariable Integer householdId) {
        return service.getByHouseholdId(householdId);
    }

    @PostMapping
    public UserHouseHold create(@RequestBody UserHouseHold userHouseHold) {
        return service.create(userHouseHold);
    }

    @PutMapping("/{id}")
    public UserHouseHold update(@PathVariable Integer id, @RequestBody UserHouseHold userHouseHold) {
        return service.update(id, userHouseHold);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
