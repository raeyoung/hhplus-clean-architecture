package com.hhplus.cleanarch.hhplus_clean_architecture.unit;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureService;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureDateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    LectureRepository lectureRepository;

    @InjectMocks
    LectureService lectureService;

    @Test
    void 날짜별_특강_목록_조회에_성공한다() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 10);
        LectureDateRequest request = new LectureDateRequest();
        request.setStartAt(startDate);
        request.setEndAt(endDate);

        Lecture lecture = Lecture.builder()
                .instructorName("하헌우")
                .capacity(30)
                .startAt(startDate)
                .endAt(endDate)
                .lectureStatus(LectureStatus.OPENED)
                .build();


        when(lectureRepository.findLecturesByIdAndDateAndStatus(LectureStatus.OPENED, startDate, endDate))
                .thenReturn(List.of(lecture));

        // When
        List<Lecture> lectures = lectureService.getLectures(request);

        // Then
        Assertions.assertThat(lectures).hasSize(1);
        verify(lectureRepository, times(1)).findLecturesByIdAndDateAndStatus(LectureStatus.OPENED, startDate, endDate);
    }

}