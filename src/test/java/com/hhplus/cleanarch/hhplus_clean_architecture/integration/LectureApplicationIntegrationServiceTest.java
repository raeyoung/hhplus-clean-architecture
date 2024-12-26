package com.hhplus.cleanarch.hhplus_clean_architecture.integration;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.ApplicationStatus;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplication;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.lecture.LectureApplicationService;
import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.User;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureApplicationRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.lecture.LectureRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.infra.user.UserRepository;
import com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture.LectureRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@Transactional
public class LectureApplicationIntegrationServiceTest {

    @Autowired
    private LectureApplicationService lectureApplicationService;

    @Autowired
    private LectureApplicationRepository lectureApplicationRepository;

    private List<User> users;

    @BeforeEach
    public void setUp() {
        // 사용자 40명 생성
        users = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> {
                    User user = new User();
                    user.setId(Long.valueOf(i));
                    user.setUserId("user" + i);
                    user.setPassword("password");
                    return user;
                })
                .collect(Collectors.toList());
    }

    @Test
    void 특강_신청에_성공한다() {
        // Given
        LectureRequest request = new LectureRequest(1L, 20L);

        // When
        LectureApplication application = lectureApplicationService.apply(request);

        // Then
        assertThat(application, is(notNullValue()));
    }

    @Test
    void 동시에_동일한_특강에_대해_40명이_신청했을_때_30명만_성공한다() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(40);    // 40개의 스레드를 동시에 처리할 수 있는 스레드 풀 생성
        CountDownLatch latch = new CountDownLatch(40);   // 40명 특강 신청 대기

        // When
        for (int i = 0; i < 40; i++) {  // 40번 반복 실행
            final int userIndex = i;
            executor.submit(() -> {
                try {
                    Long id = users.get(userIndex).getId();
                    lectureApplicationService.apply(new LectureRequest(1L, id));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();  // 특강신청 완료까지 대기
        executor.shutdown();

        List<LectureApplication> lectureApplications = lectureApplicationRepository.findByLectureId(1L);
        long successCount = lectureApplications.stream()
                .filter(history -> history.getApplicationStatus() == ApplicationStatus.SUCCESS) // 성공한 사용자
                .count();
        long failCount = lectureApplications.stream()
                .filter(history -> history.getApplicationStatus() == ApplicationStatus.FAIL)    // 실패한 사용자
                .count();

        // Then
        assertThat(successCount).isEqualTo(30); // 성공한 특강 신청자가 30명인지 확인
        assertThat(failCount).isEqualTo(10);    // 실패한 특강 신청자가 10명인지 확인
    }
}
