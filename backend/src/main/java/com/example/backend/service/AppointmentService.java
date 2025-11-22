package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.entity.User;
import com.example.backend.entity.Appointment;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.AppointmentRepository;
import com.example.backend.repository.MedicalRecordRepository;
import com.example.backend.dto.AppointmentResponse;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final MedicalRecordRepository recordRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            UserRepository userRepository,
            MedicalRecordRepository recordRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Integer patientId) {
        var patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getAppointmentsByDoctor(Integer doctorId) {
        var doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return appointmentRepository.findByDoctor(doctor);
    }

    public Appointment updateStatus(Integer id, Appointment.Status status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    // ====== Mapping entity -> DTO ======
    public AppointmentResponse toResponse(Appointment appointment) {
        AppointmentResponse res = new AppointmentResponse();
        res.setId(appointment.getId());
        res.setPatientId(appointment.getPatient().getId());
        res.setPatientName(appointment.getPatient().getName());
        if (appointment.getDoctor() != null) {
            res.setDoctorId(appointment.getDoctor().getId());
            res.setDoctorName(appointment.getDoctor().getName());
        }
        if (appointment.getRecord() != null) {
            res.setRecordId(appointment.getRecord().getId());
        }
        res.setAppointmentTime(appointment.getAppointmentTime());
        res.setReason(appointment.getReason());
        res.setStatus(appointment.getStatus().name());
        res.setCreatedAt(appointment.getCreatedAt());
        return res;
    }

    public List<AppointmentResponse> toResponseList(List<Appointment> appointments) {
        return appointments.stream().map(this::toResponse).toList();
    }
}
