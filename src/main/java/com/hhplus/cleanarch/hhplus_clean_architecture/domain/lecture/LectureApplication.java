package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.ApplyResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lecture_application")
public class LectureApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long lectureId;

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    // ApplyResponse 생성 메서드 추가
    public ApplyResponse toApplyResponse() {
        ApplyResponse response = new ApplyResponse();
        response.setLectureId(this.lectureId);
        response.setUserId(this.userId);
        response.setAppliedAt(this.appliedAt);
        response.setStatus(this.status);
        return response;
    }

    // 정적 팩토리 메서드
    public static LectureApplication create(Long userId, Long lectureId, ApplicationStatus status) {
        LectureApplication application = new LectureApplication();
        application.setUserId(userId);
        application.setLectureId(lectureId);
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus(status);
        return application;
    }

}