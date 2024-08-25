package com.sparta.upgradeschedule.repository;

import com.sparta.upgradeschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
