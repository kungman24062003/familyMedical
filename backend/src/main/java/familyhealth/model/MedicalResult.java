package familyhealth.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_results")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // e.g., "Blood Test", "X-Ray"

    @Column(nullable = false, length = 200)
    private String note; // doctor's notes

    @Column(nullable = false, length = 200)
    private String diagnose; // diagnosis

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
