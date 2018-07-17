package com.example.webservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="systeminfo")
public class SystemInfo {
	@Id
	long idx;
	int memory;
	double cpu;

	public void setAll(long idx, int memory, double cpu){
		setCpu(cpu);
		setMemory(memory);
		setIdx(idx);
	}

	public double getCpu() {
		return cpu;
	}

	public int getMemory() {
		return memory;
	}

	public long getIdx() {
		return idx;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}
}
