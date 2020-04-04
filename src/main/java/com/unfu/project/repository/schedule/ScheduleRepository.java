package com.unfu.project.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ScheduleRepository<T> extends JpaRepository<T, Long> {
}
