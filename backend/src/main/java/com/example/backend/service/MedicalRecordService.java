package com.example.backend.service;

import com.example.backend.dto.MedicalRecordResponse;
import com.example.backend.entity.MedicalRecord;
import com.example.backend.entity.User;
import com.example.backend.repository.MedicalRecordRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository recordRepository;
    private final UserRepository userRepository;

    public MedicalRecordService(MedicalRecordRepository recordRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }

    public MedicalRecord createMedicalRecord(MedicalRecord record) {
        return recordRepository.save(record);
    }

    public Optional<MedicalRecord> getRecordById(Integer id) {
        return recordRepository.findById(id);
    }

    public List<MedicalRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    public List<MedicalRecord> getRecordsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return recordRepository.findByUser(user);
    }

    public List<MedicalRecord> getRecordsByDoctor(Integer doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return recordRepository.findByDoctor(doctor);
    }

    public MedicalRecord updateStatus(Integer id, MedicalRecord.Status status) {
        MedicalRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
        record.setStatus(status);
        return recordRepository.save(record);
    }

    // ===================== Mapping entity -> DTO =====================
    public MedicalRecordResponse toResponse(MedicalRecord record) {
        MedicalRecordResponse res = new MedicalRecordResponse();
        res.setId(record.getId());
        res.setUserId(record.getUser().getId());
        res.setUserName(record.getUser().getName());
        if (record.getDoctor() != null) {
            res.setDoctorId(record.getDoctor().getId());
            res.setDoctorName(record.getDoctor().getName());
        }
        res.setDiagnosis(record.getDiagnosis());
        res.setSymptoms(record.getSymptoms());
        res.setMedications(record.getMedications());
        res.setAllergies(record.getAllergies());
        res.setVisitDate(record.getVisitDate());
        res.setNotes(record.getNotes());
        res.setStatus(record.getStatus().name());
        res.setCreatedAt(record.getCreatedAt());
        return res;
    }

    public List<MedicalRecordResponse> toResponseList(List<MedicalRecord> records) {
        return records.stream().map(this::toResponse).toList();
    }
}
