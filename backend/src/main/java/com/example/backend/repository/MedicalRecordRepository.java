package com.example.backend.repository;

import com.example.backend.entity.MedicalRecord;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findByUser(User user);
    List<MedicalRecord> findByDoctor(User doctor);
}
