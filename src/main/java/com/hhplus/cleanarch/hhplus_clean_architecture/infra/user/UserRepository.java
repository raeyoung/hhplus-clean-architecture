package com.hhplus.cleanarch.hhplus_clean_architecture.infra.user;

import com.hhplus.cleanarch.hhplus_clean_architecture.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}