package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureRequest {
    private Long lectureId;
    private Long userId;
}
