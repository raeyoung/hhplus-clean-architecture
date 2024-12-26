package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.user.UserHistoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureApplicationAndLecture {
    private Long id;
    private Long userId;
    private Long lectureId;
    private LocalDateTime appliedAt;
    private String lectureTitle;
    private String instructorName;
    private ApplicationStatus applicationStatus;

    public UserHistoryResponse toUserHistoryResponse() {
        return new UserHistoryResponse(
                this.id,
                this.userId,
                this.lectureId,
                this.appliedAt,
                this.lectureTitle,
                this.instructorName,
                this.applicationStatus.toString()
        );
    }
}