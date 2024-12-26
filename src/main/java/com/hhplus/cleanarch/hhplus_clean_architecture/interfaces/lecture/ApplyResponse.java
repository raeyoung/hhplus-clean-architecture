package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResponse {
    private Long lectureId;                         // 신청한 특강의 ID
    private Long userId;                            // 사용자 ID
    private LocalDateTime appliedAt;                // 신청 일시
    private ApplicationStatus applicationStatus;    // 신청 상태
}