package familyhealth.repository;

import familyhealth.model.MedicalResult;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicalResultRepository extends JpaRepository<MedicalResult, Long> {
    List<MedicalResult> findByMemberId(Long memberId);

    // 2. Optional: find all results for members of a specific household
    @Query("SELECT mr FROM MedicalResult mr WHERE mr.member.household.id = :householdId")
    List<MedicalResult> findByHouseholdId(@Param("householdId") Long householdId);

    // 3. Optional: find all results for members of a doctor (through household)
    @Query("SELECT mr FROM MedicalResult mr WHERE mr.member.household.doctor.id = :doctorId")
    List<MedicalResult> findAllByDoctorId(@Param("doctorId") Long doctorId);
}
