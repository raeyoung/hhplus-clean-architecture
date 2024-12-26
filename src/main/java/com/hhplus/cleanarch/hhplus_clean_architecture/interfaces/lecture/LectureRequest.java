package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureRequest {
    private Long lectureId;
    private Long userId;
}
