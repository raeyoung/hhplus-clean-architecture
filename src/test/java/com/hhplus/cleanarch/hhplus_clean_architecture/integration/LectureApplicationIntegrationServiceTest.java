package com.hhplus.cleanarch.hhplus_clean_architecture.integration;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplication;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplicationService;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@Transactional
public class LectureApplicationIntegrationServiceTest {

    @Autowired
    private LectureApplicationService lectureApplicationService;

    @Test
    void 특강_신청에_성공한다() {
        // Given
        LectureRequest request = new LectureRequest(1L, 20L);

        // When
        LectureApplication application = lectureApplicationService.apply(request);

        // Then
        assertThat(application, is(notNullValue()));
    }
}
