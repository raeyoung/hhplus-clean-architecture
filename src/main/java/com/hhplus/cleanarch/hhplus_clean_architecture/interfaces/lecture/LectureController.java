package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureService;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.UserService;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.user.UserHistoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class LectureController {

    private final LectureService lectureService;
    private final UserService userService;

    public LectureController(LectureService lectureService, UserService userService) {
        this.lectureService = lectureService;
        this.userService = userService;
    }

    /**
     * 날짜별로 현재 신청 가능한 특강 목록 조회
     * @param request
     * @return
     */
    @PostMapping("/lectures")
    public ResponseEntity<List<LectureResponse>> getLectures(LectureDateRequest request) {
        List<Lecture> lectures = lectureService.getLectures(request);

        List<LectureResponse> lectureResponses = lectures.stream()
                .map(Lecture::toLectureResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lectureResponses);
    }

    /**
     * 특정 아이디로 특강 신청 완료 목록 조회
     * @param userId
     * @return
     */
    @GetMapping("/lectures/{userId}")
    public ResponseEntity<List<UserHistoryResponse>> getApplyStatus(@PathVariable Long userId) {
        List<LectureApplicationAndLecture> histories = userService.getApplyStatus(userId);

        List<UserHistoryResponse> userHistoryResponses = histories.stream()
                .map(LectureApplicationAndLecture::toUserHistoryResponse)
                .toList();

        return ResponseEntity.ok(userHistoryResponses);
    }
}
