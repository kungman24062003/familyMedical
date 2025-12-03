package com.example.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicalRecordResponse {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer doctorId;
    private String doctorName;
    private String diagnosis;
    private String medications;
    private LocalDateTime visitDate;
    private String notes;
    private String status;
    private LocalDateTime createdAt;
}
