package com.hhplus.cleanarch.hhplus_clean_architecture.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.*;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.UserService;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.ApplyResponse;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureDateRequest;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureRequest;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.user.UserHistoryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService lectureService;

    @MockBean
    private LectureApplicationService lectureApplicationService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper; // JSON 변환용 ObjectMapper, 데이터 전달 테스트 시 사용

    @Test
    @DisplayName("GET - /api/v1/lectures 200K")
    void lectures() throws Exception {
        // Given
        List<Lecture> mockLectures = Arrays.asList(
                Lecture.builder()
                        .id(1L)
                        .title("Java")
                        .instructorName("하헌우")
                        .capacity(25)
                        .startAt(LocalDate.of(2024, 12, 1))
                        .endAt(LocalDate.of(2024, 12, 31))
                        .lectureStatus(LectureStatus.OPENED)
                        .build(),
                Lecture.builder()
                        .id(2L)
                        .title("Spring")
                        .instructorName("하헌우")
                        .capacity(25)
                        .startAt(LocalDate.of(2024, 12, 1))
                        .endAt(LocalDate.of(2024, 12, 31))
                        .lectureStatus(LectureStatus.OPENED)
                        .build()
        );
        LectureDateRequest request = new LectureDateRequest();
        String startAt = "2024-12-01";
        String endAt = "2024-12-20";
        request.setStartAt(LocalDate.parse(startAt));
        request.setEndAt(LocalDate.parse(endAt));

        // When
        given(lectureService.getLectures(request)).willReturn(mockLectures);

        // Then
        mockMvc.perform(get("/api/v1/lectures")
                        .param("startAt", startAt)
                        .param("endAt", endAt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Java"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Spring"));
    }

    @Test
    @DisplayName("GET - /api/v1/lectures/{userId} 200K")
    void applyStatus() throws Exception {
        // Given
        Long userId = 1L;
        List<LectureApplicationAndLecture> mockApplications = Arrays.asList(
                LectureApplicationAndLecture.builder()
                        .id(100L)
                        .lectureId(1L)
                        .lectureTitle("Java Basics")
                        .applicationStatus(ApplicationStatus.SUCCESS)
                        .build(),
                LectureApplicationAndLecture.builder()
                        .id(101L)
                        .lectureId(2L)
                        .lectureTitle("Spring Framework")
                        .applicationStatus(ApplicationStatus.SUCCESS)
                        .build()
        );
        List<UserHistoryResponse> mockResponses = mockApplications.stream()
                .map(LectureApplicationAndLecture::toUserHistoryResponse)
                .toList();

        // When
        given(userService.getApplyStatus(userId)).willReturn(mockApplications);

        // Then
        mockMvc.perform(get("/api/v1/lectures/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(100))
                .andExpect(jsonPath("$[0].lectureId").value(1))
                .andExpect(jsonPath("$[0].lectureTitle").value("Java Basics"))
                .andExpect(jsonPath("$[0].applicationStatus").value("SUCCESS"))
                .andExpect(jsonPath("$[1].id").value(101))
                .andExpect(jsonPath("$[1].lectureId").value(2))
                .andExpect(jsonPath("$[1].lectureTitle").value("Spring Framework"))
                .andExpect(jsonPath("$[1].applicationStatus").value("SUCCESS"));
    }

    @Test
    @DisplayName("POST - /api/v1/apply 200K")
    void apply() throws Exception {
        // Given
        LectureRequest mockRequest = LectureRequest.builder()
                .lectureId(1L)
                .userId(1L)
                .build();

        LectureApplication mockLectureApplication = LectureApplication.builder()
                .id(100L)
                .userId(1L)
                .lectureId(1L)
                .applicationStatus(ApplicationStatus.SUCCESS)
                .build();

        ApplyResponse mockResponse = mockLectureApplication.toApplyResponse();

        // When
        given(lectureApplicationService.apply(any(LectureRequest.class)))
                .willReturn(mockLectureApplication);

        // Then
        mockMvc.perform(post("/api/v1/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "lectureId": 1,
                                    "userId": 1
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.lectureId").value(1))
                .andExpect(jsonPath("$.applicationStatus").value("SUCCESS"));
    }
}
