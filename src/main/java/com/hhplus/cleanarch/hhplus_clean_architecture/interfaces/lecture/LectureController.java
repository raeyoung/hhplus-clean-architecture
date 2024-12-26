package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplication;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplicationService;
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
    private final LectureApplicationService lectureApplicationService;
    private final UserService userService;

    public LectureController(LectureService lectureService, LectureApplicationService lectureApplicationService, UserService userService) {
        this.lectureService = lectureService;
        this.lectureApplicationService = lectureApplicationService;
        this.userService = userService;
    }

    /**
     * 특강 신청
     * 특정 userId 로 선착순으로 제공되는 특강을 신청한다.
     * @param request
     * @return
     */
    @PostMapping("/apply")
    public ResponseEntity<ApplyResponse> apply(@RequestBody LectureRequest request) {
        LectureApplication lectureHistory = lectureApplicationService.apply(request);
        ApplyResponse response = lectureHistory.toApplyResponse();
        return ResponseEntity.ok(response);
    }

    /**
     * 특강 신청 가능 목록 조회
     * 날짜별로 현재 신청 가능한 특강 목록을 조회한다.
     * @param request
     * @return
     */
    @GetMapping("/lectures")
    public ResponseEntity<List<LectureResponse>> lectures(LectureDateRequest request) {
        List<Lecture> lectures = lectureService.getLectures(request);

        List<LectureResponse> lectureResponses = lectures.stream()
                .map(Lecture::toLectureResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lectureResponses);
    }

    /**
     * 특강 신청 완료 목록 조회
     * 특정 userId 로 신청 완료된 특강 목록을 조회한다.
     * @param userId
     * @return
     */
    @GetMapping("/lectures/{userId}")
    public ResponseEntity<List<UserHistoryResponse>> applyStatus(@PathVariable Long userId) {
        List<LectureApplicationAndLecture> applicationAndLectures = userService.getApplyStatus(userId);

        List<UserHistoryResponse> userHistoryResponses = applicationAndLectures.stream()
                .map(LectureApplicationAndLecture::toUserHistoryResponse)
                .toList();

        return ResponseEntity.ok(userHistoryResponses);
    }
}
