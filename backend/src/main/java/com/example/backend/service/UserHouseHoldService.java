package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.UserHouseHold;
import com.example.backend.repository.UserHouseHoldRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHouseHoldService {

    private final UserHouseHoldRepository repository;

    public List<UserHouseHold> getAll() {
        return repository.findAll();
    }

    public UserHouseHold getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHouseHold not found"));
    }

    public List<UserHouseHold> getByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    public List<UserHouseHold> getByHouseholdId(Integer householdId) {
        return repository.findByHouseholdId(householdId);
    }

    public UserHouseHold create(UserHouseHold userHouseHold) {
        return repository.save(userHouseHold);
    }

    public UserHouseHold update(Integer id, UserHouseHold updated) {
        UserHouseHold existing = getById(id);
        existing.setUser(updated.getUser());
        existing.setHousehold(updated.getHousehold());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
