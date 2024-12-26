package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.ApplyResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lecture_application")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;        // 사용자 아이디

    @Column(nullable = false)
    private Long lectureId;     // 특강 아이디

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;    // 신청일

    @Column(name = "application_status", nullable = false)
    @Enumerated(EnumType.STRING)                    // Enum 타입으로 저장
    private ApplicationStatus applicationStatus;    // 신청 상태 (SUCCESS, FAIL)

    // ApplyResponse 생성 메서드 추가
    public ApplyResponse toApplyResponse() {
        ApplyResponse response = new ApplyResponse();
        response.setLectureId(this.lectureId);
        response.setUserId(this.userId);
        response.setAppliedAt(this.appliedAt);
        response.setApplicationStatus(this.applicationStatus);
        return response;
    }

    // 정적 팩토리 메서드 (객체 생성 로직을 캡슐화)
    public static LectureApplication create(Long userId, Long lectureId, ApplicationStatus applicationStatus) {
        LectureApplication application = new LectureApplication();
        application.setUserId(userId);
        application.setLectureId(lectureId);
        application.setAppliedAt(LocalDateTime.now());
        application.setApplicationStatus(applicationStatus);
        return application;
    }
}