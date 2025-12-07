package familyhealth.controller;

import familyhealth.model.MedicalResult;
import familyhealth.model.dto.MedicalResultDTO;
import familyhealth.model.dto.response.ApiResponse;
import familyhealth.service.IMedicalResultService;
import familyhealth.service.impl.MedicalResultService;
import familyhealth.utils.MessageKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medical_results")
public class MedicalResultController {
    private final IMedicalResultService medicalResultService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMedicalResult(@PathVariable Long id) {
        try {
            MedicalResult medicalResult = medicalResultService.getMedicalResult(id);
            return ResponseEntity.ok(medicalResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMedicalResult(@RequestBody MedicalResultDTO medicalResultDTO) {
        System.out.println("Received DTO: " + medicalResultDTO);
        try {
            MedicalResult medicalResult = medicalResultService.createMedicalResult(medicalResultDTO);
            return ResponseEntity.ok(ApiResponse.builder()
                    .code(CREATED.value())
                    .message(MessageKey.CREATED_MEDICAL_RESULT_SUCCESS)
                    .data(medicalResult.getId())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateMedicalResult(@PathVariable Long id,
            @RequestBody MedicalResultDTO medicalResultDTO) {
        try {
            MedicalResult medicalResult = medicalResultService.updateMedicalResult(id, medicalResultDTO);
            return ResponseEntity.ok("Update MedicalResult : " + medicalResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMedicalResult(@PathVariable Long id) {
        try {
            medicalResultService.deleteMedicalResult(id);
            return ResponseEntity.ok("Delete MedicalResult : " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/members/{id}")

    public ResponseEntity<?> getMedicalResultsByMemberId(@PathVariable Long id) {
        try {
            var medicalResults = medicalResultService.getMedicalResultByMemberId(id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .code(200)
                    .message(MessageKey.GET_MEDICAL_RESULTS_BY_MEMBER_ID_SUCCESS)
                    .data(medicalResults)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
