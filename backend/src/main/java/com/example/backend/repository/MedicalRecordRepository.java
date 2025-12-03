package com.example.backend.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.backend.entity.MedicalRecord;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findByUser(User user);
    List<MedicalRecord> findByDoctor(User doctor);
    @Query(value = """
        SELECT mr.*
        FROM users_households uh
        JOIN members m ON m.household_id = uh.household_id
        JOIN medical_records mr ON mr.user_id = m.id
        WHERE uh.user_id = :userId
        """, nativeQuery = true)
    List<MedicalRecord> findByHouseholdUserId(@Param("userId") Integer userId);
}
