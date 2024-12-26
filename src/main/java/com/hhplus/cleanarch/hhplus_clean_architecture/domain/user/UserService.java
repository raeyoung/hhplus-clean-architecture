package com.hhplus.cleanarch.hhplus_clean_architecture.domain.user;

import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.NotFoundException;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final LectureApplicationRepository lectureApplicationRepository;

    public UserService(LectureApplicationRepository lectureApplicationRepository) {
        this.lectureApplicationRepository = lectureApplicationRepository;
    }

    /**
     * 특정 아이디로 특강 신청 완료 목록 조회
     * @param userId
     * @return
     */
    public List<LectureApplicationAndLecture> getApplyStatus(Long userId) {
        // 특강 신청 완료 목록을 조회
        List<LectureApplicationAndLecture> lectures = lectureApplicationRepository.findCompletedLecturesByUserId(userId);

        // 신청 완료한 목록이 없는 경우 예외 처리
        if (lectures == null || lectures.isEmpty()) {
            throw new NotFoundException("Lecture not found");
        }
        return lectures;
    }
}
