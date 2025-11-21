package com.example.backend.controller;

import com.example.backend.entity.Appointment;
import com.example.backend.entity.User;
import com.example.backend.service.AppointmentService;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.MedicalRecordRepository;
import com.example.backend.dto.AppointmentRequest;
import com.example.backend.dto.AppointmentResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserRepository userRepository;
    private final MedicalRecordRepository recordRepository;

    public AppointmentController(
            AppointmentService appointmentService,
            UserRepository userRepository,
            MedicalRecordRepository recordRepository
    ) {
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    // GET /appointments?patient=&doctor=
    @GetMapping
    public ResponseEntity<?> getAppointments(
            @RequestParam(required = false) Integer patient,
            @RequestParam(required = false) Integer doctor
    ) {
        if (patient != null && doctor != null) {
            return ResponseEntity.badRequest().body("Cannot filter by both patient and doctor.");
        }

        if (patient != null) {
            return ResponseEntity.ok(appointmentService.toResponseList(appointmentService.getAppointmentsByPatient(patient)));
        }

        if (doctor != null) {
            return ResponseEntity.ok(appointmentService.toResponseList(appointmentService.getAppointmentsByDoctor(doctor)));
        }

        return ResponseEntity.ok(appointmentService.toResponseList(appointmentService.getAllAppointments()));
    }

    // GET /appointments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Integer id) {
        return appointmentService.getAppointmentById(id)
                .map(appointmentService::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /appointments
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest request) {
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        if (request.getRecordId() != null) {
            appointment.setRecord(recordRepository.findById(request.getRecordId())
                    .orElseThrow(() -> new RuntimeException("Medical record not found")));
        }
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setReason(request.getReason());
        appointment.setStatus(Appointment.Status.PENDING);
        appointment.setCreatedAt(LocalDateTime.now());

        Appointment created = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.toResponse(created));
    }

    // PUT /appointments/{id}/status?value=CONFIRMED
    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentResponse> updateStatus(
            @PathVariable Integer id,
            @RequestParam("value") Appointment.Status status
    ) {
        Appointment updated = appointmentService.updateStatus(id, status);
        return ResponseEntity.ok(appointmentService.toResponse(updated));
    }
}
