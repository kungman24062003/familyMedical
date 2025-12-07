package familyhealth.mapper;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicalResultResponse {

    private Long id;
    private String name;
    private String note;
    private String diagnose;
    private LocalDateTime createdAt;

    private Member member; // Nested member info

    @Data
    public static class Member {
        private Long id;
        private String fullname;
        private String gender;
        private String relation;
    }
}
