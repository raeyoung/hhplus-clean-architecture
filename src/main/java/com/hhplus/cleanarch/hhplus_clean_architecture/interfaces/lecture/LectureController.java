package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    /**
     * 날짜별로 현재 신청 가능한 특강 목록을 조회
     * @param request
     * @return
     */
    @PostMapping("/lectures")
    public ResponseEntity<List<LectureResponse>> getLectures(@RequestBody LectureDateRequest request) {
        List<Lecture> lectures = lectureService.getLectures(request);

        List<LectureResponse> lectureResponses = lectures.stream()
                .map(Lecture::toLectureResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lectureResponses);
    }
}
