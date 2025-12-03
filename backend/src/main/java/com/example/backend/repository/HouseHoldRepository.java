
package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.HouseHold;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer> {
    // Lấy ID lớn nhất hiện tại
    @Query("SELECT COALESCE(MAX(h.id), 0) FROM HouseHold h")
    Integer getMaxId();

    @Query("SELECT h FROM HouseHold h WHERE h.househead.id = :userId")
    HouseHold findByHouseHeadId(@Param("userId") Integer userId);

}
