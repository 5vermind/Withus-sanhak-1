package com.example.webservice.data;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;

@Component
public class ScheduledTasks {
	@Autowired
	SystemInfoRepository systemInfoRepository;

	final int maxIdx = 24 * 12;
	final int maxRepositSize = maxIdx + 10;
	long currentIdx = 0;

	@Scheduled(fixedRate = 60*5*1000)
	public void renewalData(){
		if(systemInfoRepository.count() >= maxRepositSize){
			systemInfoRepository.deleteById(currentIdx - maxRepositSize);
		}

		SystemInfo c = new SystemInfo();
		c.setAll(currentIdx, memoryData(), cpuData());
		systemInfoRepository.save(c);
		currentIdx++;
	}

	double cpuData(){
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		return osBean.getSystemCpuLoad() * 100;
	}

	int memoryData(){
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		int MB = 1024*1024;
		long used = osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize();
		long ret = used / MB;
		return (int)ret;
	}
}
