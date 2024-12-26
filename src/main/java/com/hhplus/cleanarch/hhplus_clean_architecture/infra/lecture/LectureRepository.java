package com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.Lecture;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Lecture> findById(Long lectureId);

    @Query("SELECT l FROM Lecture l WHERE l.lectureStatus = :lectureStatus AND l.startAt = :startAt AND l.endAt = :endAt")
    List<Lecture> findLecturesByIdAndDateAndStatus(@Param("lectureStatus") LectureStatus lectureStatus,
                                                   @Param("startAt") LocalDate startAt,
                                                   @Param("startAt") LocalDate endAt);
}