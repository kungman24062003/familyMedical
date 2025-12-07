package familyhealth.service.impl;

import familyhealth.exception.AppException;
import familyhealth.exception.ErrorCode;
import familyhealth.mapper.MedicalResultMapper;
import familyhealth.model.MedicalResult;
import familyhealth.model.Member;
import familyhealth.model.dto.MedicalResultDTO;
import familyhealth.repository.MedicalResultRepository;
import familyhealth.repository.MemberRepository;
import familyhealth.service.IEmailService;
import familyhealth.service.IMedicalResultService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MedicalResultService implements IMedicalResultService {

    private final MedicalResultRepository medicalResultRepository;
    private final MemberRepository memberRepository; // new
    private final IEmailService emailService;

    @Override
    public MedicalResult getMedicalResult(Long id) {
        return medicalResultRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_RESULT_NOT_EXISTED));
    }

    @Override
    @Transactional
    public MedicalResult createMedicalResult(MedicalResultDTO medicalResultDTO) {

        // Get member
        Member member = memberRepository.findById(medicalResultDTO.getMemberId())
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        // Map DTO to entity
        MedicalResult medicalResult = MedicalResultMapper.convertToMedicalResult(medicalResultDTO, member);

        MedicalResult savedResult = medicalResultRepository.save(medicalResult);

        // Optionally send email
        log.info("Sending medical result email to member: {}", member.getFullname());
        emailService.sendMedicalResultEmail(savedResult);

        return savedResult;
    }

    @Override
    @Transactional
    public MedicalResult updateMedicalResult(Long id, MedicalResultDTO medicalResultDTO) {
        MedicalResult existingResult = getMedicalResult(id);

        Member member = existingResult.getMember();

        MedicalResult updatedResult = MedicalResultMapper.convertToMedicalResult(medicalResultDTO, member);

        updatedResult.setId(existingResult.getId()); // keep the same ID

        return medicalResultRepository.save(updatedResult);
    }

    @Override
    public void deleteMedicalResult(Long id) {
        MedicalResult medicalResult = getMedicalResult(id);
        medicalResultRepository.delete(medicalResult);
    }

    @Override
    public List<MedicalResult> getMedicalResultByMemberId(Long id) {
        List<MedicalResult> results = medicalResultRepository.findByMemberId(id);
        if (results.isEmpty()) {
            throw new AppException(ErrorCode.MEDICAL_RESULT_NOT_EXISTED);
        }
        return results;
    }
}
