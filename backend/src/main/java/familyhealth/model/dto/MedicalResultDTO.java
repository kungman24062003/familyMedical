package familyhealth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicalResultDTO {

    @NotBlank(message = "Tên kết quả không được để trống")
    private String name; // e.g., "Blood Test", "X-Ray"

    @NotBlank(message = "Ghi chú không được để trống")
    private String note; // Doctor's notes

    @NotBlank(message = "Chẩn đoán không được để trống")
    private String diagnose; // Diagnosis

    @NotNull(message = "ID bệnh nhân không được để trống")
    private Long memberId; // Reference to the member
}
