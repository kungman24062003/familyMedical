package com.example.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicalRecordRequest {
    private Integer userId;   // patient
    private Integer doctorId;
    private String diagnosis;
    private String symptoms;
    private String medications;
    private String allergies;
    private LocalDateTime visitDate;
    private String notes;
}
