package com.hhplus.cleanarch.hhplus_clean_architecture.unit;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.UserService;
import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.NotFoundException;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.user.UserRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    LectureApplicationRepository lectureApplicationRepository;

    @InjectMocks
    UserService userService;

    @Test
    void 특강_신청_완료_목록_조회를_성공한다() {
        // Given
        Long userId = 1L;

        LectureApplicationAndLecture mockData1 = new LectureApplicationAndLecture();
        mockData1.setId(1L);
        mockData1.setUserId(userId);
        mockData1.setLectureId(1L);
        mockData1.setAppliedAt(LocalDateTime.of(2024,9,20,15,30));
        mockData1.setLectureTitle("자바");
        mockData1.setInstructorName("하헌우");
        mockData1.setApplicationStatus(ApplicationStatus.SUCCESS);

        LectureApplicationAndLecture mockData2 = new LectureApplicationAndLecture();
        mockData2.setId(1L);
        mockData2.setUserId(userId);
        mockData2.setLectureId(1L);
        mockData2.setAppliedAt(LocalDateTime.of(2024,9,20,15,30));
        mockData2.setLectureTitle("스프링");
        mockData2.setInstructorName("하헌우");
        mockData2.setApplicationStatus(ApplicationStatus.SUCCESS);

        when(lectureApplicationRepository.findCompletedLecturesByUserId(userId)).thenReturn(
                Arrays.asList(mockData1, mockData2)
        );

        // When
        List<LectureApplicationAndLecture> appplication = userService.getApplyStatus(userId);

        // Then
        assertNotNull(appplication);
        assertFalse(appplication.isEmpty());
    }

    @Test
    void 특강_신청_완료_목록이_없을경우_NotFoundException을_반환한다() {
        // Given
        Long userId = 1L;
        when(lectureApplicationRepository.findCompletedLecturesByUserId(userId)).thenReturn(Collections.emptyList()); // 빈 리스트로 반환

        // When
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getApplyStatus(userId));

        // Then
        assertEquals("Lecture not found", exception.getMessage());
    }

}