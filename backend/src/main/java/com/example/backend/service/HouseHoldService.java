package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.HouseHold;
import com.example.backend.repository.HouseHoldRepository;

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

    public Integer getNextId() {
        // Lấy Household có ID lớn nhất
        return householdRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .findFirst()
                .map(h -> h.getId() + 1) // ID tiếp theo
                .orElse(1); // Nếu chưa có dữ liệu, trả về 1
    }
    @GetMapping("/next-id")
    public Integer getNextId() {
        return householdRepo.getMaxId() + 1;
    }
}
