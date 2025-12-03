package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(User.Role role);

    // Lấy ID lớn nhất
    @Query("SELECT COALESCE(MAX(u.id), 0) FROM User u")
    Integer findMaxId();
}
