package com.example.webservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cpuinfo")
public class CPUInfo {
	@Id
	int INFO_NO;
	double PERCENTAGE;

	public void setAll(int num, double per){
		setINFO_NO(num);
		setPERCENTAGE(per);
	}

	public double getPERCENTAGE() {
		return PERCENTAGE;
	}

	public int getINFO_NO() {
		return INFO_NO;
	}

	public void setINFO_NO(int INFO_NO) {
		this.INFO_NO = INFO_NO;
	}

	public void setPERCENTAGE(double PERCENTAGE) {
		this.PERCENTAGE = PERCENTAGE;
	}
}
