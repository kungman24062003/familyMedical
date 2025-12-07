package familyhealth.mapper;

import familyhealth.model.MedicalResult;
import familyhealth.model.Member;
import familyhealth.model.dto.MedicalResultDTO;
import familyhealth.mapper.MedicalResultResponse;

public class MedicalResultMapper {

    // NEW: convert DTO → MedicalResult using Member
    public static MedicalResult convertToMedicalResult(MedicalResultDTO dto, Member member) {
        if (dto == null)
            return null;

        return MedicalResult.builder()
                .name(dto.getName())
                .note(dto.getNote())
                .diagnose(dto.getDiagnose())
                .member(member)
                .build();
    }

    // Existing mapping to response
    public static MedicalResultResponse toResponse(MedicalResult mr) {
        if (mr == null)
            return null;

        MedicalResultResponse dto = new MedicalResultResponse();
        dto.setId(mr.getId());
        dto.setName(mr.getName());
        dto.setDiagnose(mr.getDiagnose());
        dto.setNote(mr.getNote());
        dto.setCreatedAt(mr.getCreatedAt());

        // Map member info instead of appointment
        dto.setMember(toMemberInner(mr.getMember()));

        return dto;
    }

    private static MedicalResultResponse.Member toMemberInner(Member m) {
        if (m == null)
            return null;

        MedicalResultResponse.Member dto = new MedicalResultResponse.Member();
        dto.setId(m.getId());
        dto.setFullname(m.getFullname());
        dto.setGender(m.getGender().name());
        dto.setRelation(m.getRelation().name());
        return dto;
    }
}
