package com.example.backend.controller;

import com.example.backend.dto.MedicalRecordRequest;
import com.example.backend.dto.MedicalRecordResponse;
import com.example.backend.entity.MedicalRecord;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicalRecordController {

    private final MedicalRecordService recordService;
    private final UserRepository userRepository;

    public MedicalRecordController(MedicalRecordService recordService, UserRepository userRepository) {
        this.recordService = recordService;
        this.userRepository = userRepository;
    }

    // GET /medical-records?user=&doctor=
    @GetMapping
    public ResponseEntity<?> getRecords(
            @RequestParam(required = false) Integer user,
            @RequestParam(required = false) Integer doctor
    ) {
        if (user != null && doctor != null) {
            return ResponseEntity.badRequest().body("Cannot filter by both user and doctor.");
        }

        if (user != null) {
            List<MedicalRecordResponse> res = recordService.toResponseList(recordService.getRecordsByUser(user));
            return ResponseEntity.ok(res);
        }

        if (doctor != null) {
            List<MedicalRecordResponse> res = recordService.toResponseList(recordService.getRecordsByDoctor(doctor));
            return ResponseEntity.ok(res);
        }

        List<MedicalRecordResponse> res = recordService.toResponseList(recordService.getAllRecords());
        return ResponseEntity.ok(res);
    }

    // GET /medical-records/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponse> getRecordById(@PathVariable Integer id) {
        return recordService.getRecordById(id)
                .map(recordService::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /medical-records
    @PostMapping
    public ResponseEntity<MedicalRecordResponse> createRecord(@RequestBody MedicalRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        User doctor = null;
        if (request.getDoctorId() != null) {
            doctor = userRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
        }

        MedicalRecord record = new MedicalRecord();
        record.setUser(user);
        record.setDoctor(doctor);
        record.setDiagnosis(request.getDiagnosis());
        record.setMedications(request.getMedications());
        record.setVisitDate(request.getVisitDate());
        record.setNotes(request.getNotes());
        record.setStatus(MedicalRecord.Status.PENDING);
        record.setCreatedAt(java.time.LocalDateTime.now());

        MedicalRecord created = recordService.createMedicalRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.toResponse(created));
    }

    // PUT /medical-records/{id}/status?value=REVIEWED
    @PutMapping("/{id}/status")
    public ResponseEntity<MedicalRecordResponse> updateStatus(
            @PathVariable Integer id,
            @RequestParam("value") MedicalRecord.Status status
    ) {
        MedicalRecord updated = recordService.updateStatus(id, status);
        return ResponseEntity.ok(recordService.toResponse(updated));
    }

    @GetMapping("/household/{userId}")
    public ResponseEntity<List<MedicalRecordResponse>> getHouseholdRecords(@PathVariable Integer userId) {
        List<MedicalRecordResponse> res = recordService.getRecordsByHouseholdUserResponse(userId);
        return ResponseEntity.ok(res);
    }

}
