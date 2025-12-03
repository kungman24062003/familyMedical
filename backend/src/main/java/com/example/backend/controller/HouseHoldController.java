package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.example.backend.entity.HouseHold;
import com.example.backend.service.HouseHoldService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/households")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class HouseHoldController {

    private final HouseHoldService service;

    @GetMapping
    public List<HouseHold> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public HouseHold getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public HouseHold create(@RequestBody HouseHold household) {
        return service.create(household);
    }

    @PutMapping("/{id}")
    public HouseHold update(@PathVariable Integer id, @RequestBody HouseHold household) {
        return service.update(id, household);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    
    @GetMapping("/by-user/{userId}")
    public HouseHold getByHouseHead(@PathVariable Integer userId) {
        return service.getByHouseHeadId(userId);
    }

}
