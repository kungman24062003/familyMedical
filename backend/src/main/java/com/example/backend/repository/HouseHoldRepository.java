
package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.HouseHold;

public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer> {
    // Lấy ID lớn nhất hiện tại
    @Query("SELECT COALESCE(MAX(h.id), 0) FROM HouseHold h")
    Integer getMaxId();
}
