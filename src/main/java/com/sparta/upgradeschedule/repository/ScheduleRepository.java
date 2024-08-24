package com.sparta.upgradeschedule.repository;

import com.sparta.upgradeschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
