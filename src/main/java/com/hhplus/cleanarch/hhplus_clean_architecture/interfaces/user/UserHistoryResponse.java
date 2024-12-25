package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserHistoryResponse {
    private Long id;
    private Long userId;
    private Long lectureId;
    private LocalDateTime appliedAt;
    private String lectureTitle;
    private String lectureName;
    private String historyStatus;
}