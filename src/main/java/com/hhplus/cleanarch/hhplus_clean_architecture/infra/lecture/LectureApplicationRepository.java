package com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplication;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureApplicationRepository extends JpaRepository<LectureApplication, Long> {

    @Query("SELECT new com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureApplicationAndLecture(" +
            "lh.id, lh.userId, lh.lectureId, lh.appliedAt, l.title, l.instructorName, lh.applicationStatus) " +
            "FROM LectureApplication lh JOIN Lecture l ON lh.lectureId = l.id " +
            "WHERE lh.userId = :userId AND lh.applicationStatus = com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus.SUCCESS")
    List<LectureApplicationAndLecture> findCompletedLecturesByUserId(@Param("userId") Long userId);

    Optional<LectureApplication> findByUserIdAndLectureIdAndApplicationStatus(Long userId, Long lectureId, ApplicationStatus applicationStatus);
}