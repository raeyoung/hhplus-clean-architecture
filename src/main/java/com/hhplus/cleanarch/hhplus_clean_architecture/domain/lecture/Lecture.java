package com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture;

import com.hhplus.cleanarch.hhplus_clean_architecture.global.exception.LimitExceededException;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "lecture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    private static final int MAX_CAPACITY = 30;     // 선착순 30명

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;           // 강의 이름

    @Column(name = "instructor_name", nullable = false)
    private String instructorName;  // 강사 이름

    @Column(nullable = false)
    private Integer capacity;       // 현재 정원

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;      // 신청 시작일

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;        // 신청 마감일

    @Column(name = "lecture_status", nullable = false)
    @Enumerated(EnumType.STRING)            // Enum 타입으로 저장
    private LectureStatus lectureStatus;    // 특강 상태 (OPENED, CLOSED)

    // 특강은 선착순 30명만 신청 가능
    public boolean isCapacityFull() {
        return this.capacity >= MAX_CAPACITY;
    }

    // 수강 인원을 추가
    public void addStudent() {
        // 선착순 30명 검증
        if (isCapacityFull()) {
            throw new LimitExceededException("수강 인원이 가득 찼습니다.");
        }

        this.capacity++;

        // 정원이 가득 찼을 경우 특강 상태 변경
        if (capacity == MAX_CAPACITY) {
            this.lectureStatus = LectureStatus.CLOSED;
        }
    }

    public LectureResponse toLectureResponse() {
        return new LectureResponse(
                this.id,
                this.title,
                this.instructorName,
                this.capacity,
                this.startAt,
                this.endAt,
                this.lectureStatus
        );
    }
}