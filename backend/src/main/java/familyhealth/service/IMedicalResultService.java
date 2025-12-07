package familyhealth.service;

import java.util.List;

import familyhealth.model.MedicalResult;
import familyhealth.model.dto.MedicalResultDTO;

public interface IMedicalResultService {
    MedicalResult getMedicalResult(Long id);

    MedicalResult createMedicalResult(MedicalResultDTO MedicalResultDTO);

    MedicalResult updateMedicalResult(Long id, MedicalResultDTO MedicalResultDTO);

    List<MedicalResult> getMedicalResultByMemberId(Long id);

    void deleteMedicalResult(Long id);
}
