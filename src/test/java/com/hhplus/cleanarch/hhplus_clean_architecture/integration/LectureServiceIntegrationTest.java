package com.hhplus.cleanarch.hhplus_clean_architecture.integration;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureService;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureDateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class LectureServiceIntegrationTest {

    @Autowired
    LectureService lectureService;

    @Test
    void 특강_목록_조회에_성공한다() {
        // Given
        LectureDateRequest request = new LectureDateRequest();
        request.setStartAt(LocalDate.of(2024, 12, 1));
        request.setEndAt(LocalDate.of(2024, 12, 31));

        // When
        List<Lecture> lectures = lectureService.getLectures(request);

        // Then
        assertThat(lectures, is(notNullValue()));
        assertThat(lectures.isEmpty(), is(false));
    }
}
