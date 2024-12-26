package com.hhplus.cleanarch.hhplus_clean_architecture.domain.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userId;      // 사용자 아이디

    @Column(nullable = false)
    private String name;        // 사용자 이름

    @Column(nullable = false)
    private String password;    // 비밀번호

}
