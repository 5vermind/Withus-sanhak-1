package com.example.webservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memoryinfo")
public class MemoryInfo {
	@Id
	int INFO_NO;
	long AMOUNT;

	public void setAll(int num, long am){
		setAMOUNT(am);
		setINFO_NO(num);
	}

	public int getINFO_NO() {
		return INFO_NO;
	}

	public long getAMOUNT() {
		return AMOUNT;
	}

	public void setINFO_NO(int INFO_NO) {
		this.INFO_NO = INFO_NO;
	}

	public void setAMOUNT(long AMOUNT) {
		this.AMOUNT = AMOUNT;
	}
}
