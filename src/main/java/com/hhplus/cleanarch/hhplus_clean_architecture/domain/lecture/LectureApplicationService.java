package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.AlreadyAppliedException;
import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.LimitExceededException;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.user.UserRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LectureApplicationService {

    private final LectureApplicationRepository lectureApplicationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public LectureApplicationService(LectureApplicationRepository lectureApplicationRepository, UserRepository userRepository, LectureRepository lectureRepository) {
        this.lectureApplicationRepository = lectureApplicationRepository;
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    /**
     * 특강 신청
     * @param request
     * @return
     */
    @Transactional
    public LectureApplication apply(LectureRequest request) {
        Long userId = request.getUserId();
        Long lectureId = request.getLectureId();

        // 사용자 검증
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        // 특강 검증
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("특강을 찾을 수 없습니다."));

        LectureApplication lectureApplication = null;

        try {
            // 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공
            Optional<LectureApplication> existingHistory = lectureApplicationRepository.findByUserIdAndLectureIdAndApplicationStatus(userId, lectureId, ApplicationStatus.SUCCESS);
            if (existingHistory.isPresent()) {
                throw new AlreadyAppliedException("해당 특강은 이미 신청했습니다.");
            }

            lecture.addStudent();
            lectureApplication = LectureApplication.create(userId, lectureId, ApplicationStatus.SUCCESS);
        } catch (LimitExceededException | AlreadyAppliedException e) {
            // 이미 신청자가 30명이 초과 되면 이후 신청자는 요청을 실패
            lectureApplication = LectureApplication.create(userId, lectureId, ApplicationStatus.FAIL);
        }

        lectureApplicationRepository.save(lectureApplication);

        return lectureApplication;
    }
}