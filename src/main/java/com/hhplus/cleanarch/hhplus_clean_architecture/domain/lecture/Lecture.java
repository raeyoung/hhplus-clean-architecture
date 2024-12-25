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

    private static final int MAX_CAPACITY = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "instructor_name", nullable = false)
    private String instructorName;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @Column(name = "lecture_status", nullable = false)
    @Enumerated(EnumType.STRING) // Enum 타입으로 저장하기 위해 추가
    private LectureStatus lectureStatus;

    public boolean isCapacityFull() {
        return this.capacity >= MAX_CAPACITY;
    }

    // 수강 인원을 추가하는 메서드
    public void addStudent() {
        if (isCapacityFull()) {
            throw new LimitExceededException("수강 인원이 가득 찼습니다.");
        }

        this.capacity++;

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