package com.zidio.syncwork.repository;

import com.zidio.syncwork.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // Sabse naye notice sabse upar dikhane ke liye
    List<Notice> findAllByOrderByIdDesc();
}