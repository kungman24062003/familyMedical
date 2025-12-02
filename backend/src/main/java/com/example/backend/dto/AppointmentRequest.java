package com.example.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private Integer patientId;
    private Integer doctorId;
    private Integer recordId; // có thể null
    private LocalDateTime appointmentTime;
    private String reason;
}
