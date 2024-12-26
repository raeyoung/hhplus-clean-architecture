package com.hhplus.cleanarch.hhplus_clean_architecture.integration;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.UserService;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    void 특강_신청_완료_목록_조회를_성공한다() {
        // Given
        Long userId = 1L;

        // When
        List<LectureApplicationAndLecture> applyStatus = userService.getApplyStatus(userId);

        // Then
        assertThat(applyStatus, is(notNullValue()));
        assertThat(applyStatus.isEmpty(), is(false));
    }
}
