package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LectureResponse {
    private Long id;
    private String title;
    private String instructorName;
    private Integer capacity;
    private LocalDate startAt;
    private LocalDate endAt;
    private LectureStatus lectureStatus;
}