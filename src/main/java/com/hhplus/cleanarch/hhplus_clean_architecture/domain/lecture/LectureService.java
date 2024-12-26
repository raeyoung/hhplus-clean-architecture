package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.NotFoundException;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureDateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LectureService {

    private LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    /**
     * 날짜별로 현재 신청 가능한 특강 목록을 조회
     * @return
     */
    public List<Lecture> getLectures(LectureDateRequest request) {
        LocalDate startAt = request.getStartAt();
        LocalDate endAt = request.getEndAt();

        // 강의 목록 조회 (사용자는 각 특강에 신청하기 전 목록을 조회해볼 수 있어야 한다)
        List<Lecture> lectures = lectureRepository.findLecturesByIdAndDateAndStatus(LectureStatus.OPENED, startAt, endAt);

        // 신청 가능한 목록이 없는 경우 예외 처리
        if (lectures == null || lectures.isEmpty()) {
            throw new NotFoundException("Lecture not found");
        }
        return lectures;
    }
}
