package com.example.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Integer id;
    private Integer patientId;
    private String patientName;
    private Integer doctorId;
    private String doctorName;
    private Integer recordId;
    private LocalDateTime appointmentTime;
    private String reason;
    private String status;
    private LocalDateTime createdAt;
}
