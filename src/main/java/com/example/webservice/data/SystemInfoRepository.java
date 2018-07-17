package com.example.webservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface SystemInfoRepository extends JpaRepository<SystemInfo, Long> {
	List<SystemInfo> findAllByOrderByIdxAsc();
}
