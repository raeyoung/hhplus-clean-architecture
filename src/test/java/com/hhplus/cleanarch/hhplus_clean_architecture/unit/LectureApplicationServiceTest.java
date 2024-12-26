package com.hhplus.cleanarch.hhplus_clean_architecture.unit;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplication;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplicationService;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.User;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.user.UserRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureApplicationServiceTest {

    @Mock
    LectureRepository lectureRepository;

    @Mock
    LectureApplicationRepository lectureApplicationRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LectureApplicationService lectureApplicationService;

    @Test
    void 특강_신청에_성공한다() {
        // Given
        Long userId = 1L;
        Long lectureId = 1L;

        User user = new User();
        user.setId(userId);

        Lecture lecture = new Lecture();
        lecture.setId(lectureId);
        lecture.setCapacity(10);

        LectureApplication application = new LectureApplication();
        application.setUserId(userId);
        application.setLectureId(lectureId);
        application.setAppliedAt(LocalDateTime.now());
        application.setApplicationStatus(ApplicationStatus.SUCCESS);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lectureRepository.findById(lectureId)).thenReturn(Optional.of(lecture));
        when(lectureApplicationRepository.save(any(LectureApplication.class))).thenReturn(application);

        LectureRequest request = new LectureRequest(userId, lectureId);

        // When
        LectureApplication apply = lectureApplicationService.apply(request);

        // Then
        assertThat(apply).isNotNull();
        assertThat(apply.getLectureId()).isEqualTo(lectureId);
        assertThat(apply.getUserId()).isEqualTo(userId);
        assertThat(apply.getApplicationStatus()).isEqualTo(ApplicationStatus.SUCCESS);
    }

    @Test
    void 정원이_가득_찼을때_특강신청을_실패한다() {
        // Given
        Long userId = 1L;
        Long lectureId = 1L;

        User user = new User();
        user.setId(userId);

        Lecture lecture = new Lecture();
        lecture.setId(lectureId);
        lecture.setCapacity(30);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lectureRepository.findById(lectureId)).thenReturn(Optional.of(lecture));

        LectureRequest request = new LectureRequest(userId, lectureId);

        // When
        LectureApplication apply = lectureApplicationService.apply(request);

        // Then
        assertThat(apply).isNotNull();
        assertThat(apply.getLectureId()).isEqualTo(lectureId);
        assertThat(apply.getUserId()).isEqualTo(userId);
        assertThat(apply.getApplicationStatus()).isEqualTo(ApplicationStatus.FAIL);
    }
}
