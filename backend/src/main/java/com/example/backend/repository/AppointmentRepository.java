package com.example.backend.repository;

import com.example.backend.entity.Appointment;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatient(User patient);
    List<Appointment> findByDoctor(User doctor);
}
