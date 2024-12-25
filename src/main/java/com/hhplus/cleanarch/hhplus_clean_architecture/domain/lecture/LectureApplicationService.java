package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.user.UserRepository;
import org.springframework.stereotype.Service;

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

}