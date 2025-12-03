package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.HouseHold;
import com.example.backend.repository.HouseHoldRepository;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseHoldService {

    private final HouseHoldRepository householdRepo;

    public List<HouseHold> getAll() {
        return householdRepo.findAll();
    }

    public HouseHold getById(Integer id) {
        return householdRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Household not found"));
    }

    public HouseHold create(HouseHold household) {
        return householdRepo.save(household);
    }

    public HouseHold update(Integer id, HouseHold updated) {
        HouseHold existing = getById(id);
        existing.setAddress(updated.getAddress());
        return householdRepo.save(existing);
    }

    public void delete(Integer id) {
        householdRepo.deleteById(id);
    }

    @GetMapping("/next-id")
    public Integer getNextId() {
        return householdRepo.getMaxId() + 1;
    }

    public HouseHold getByHouseHeadId(Integer userId) {
        HouseHold house = householdRepo.findByHouseHeadId(userId);
        if (house == null) {
            throw new RuntimeException("Không tìm thấy hộ gia đình cho userId = " + userId);
        }
        return house;
    }
}
