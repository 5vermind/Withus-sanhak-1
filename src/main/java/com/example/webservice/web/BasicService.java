package com.example.webservice.web;

import com.example.webservice.data.ScheduledTasks;
import com.example.webservice.data.SystemInfo;
import com.example.webservice.data.SystemInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicService {
	@Autowired
	SystemInfoRepository systemInfoRepository;

	final int maxIdx = 24 * 12;

	String ip, hostname, mac;

	public Map basicInfo() {
		try {
			getSystemInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map ret = new HashMap();
		ret.put("ip", ip);
		ret.put("hostname", hostname);
		ret.put("mac", mac);

		return ret;
	}

	public List<Float> cpuGraphInfo(){
		List<SystemInfo> temp = systemInfoRepository.findAllByOrderByIdxAsc();
		int sp = temp.size() - maxIdx;
		if(sp < 0) sp = 0;
		List<Float> ret = new ArrayList<Float>();

		for(int i=sp; i<temp.size(); i++){
			ret.add((float)temp.get(i).getCpu());
		}
		return ret;
	}

	public List<Float> memoryGraphInfo(){
		List<SystemInfo> temp = systemInfoRepository.findAllByOrderByIdxAsc();
		int sp = temp.size() - maxIdx;
		if(sp < 0) sp = 0;
		List<Float> ret = new ArrayList<Float>();

		for(int i=sp; i<temp.size(); i++){
			ret.add((float)temp.get(i).getMemory());
		}
		return ret;
	}

	void getSystemInfo() throws IOException {
		ip = hostname = mac = "";
		InetAddress ip = null;
		ip = InetAddress.getLocalHost();
		this.ip = ip.getHostAddress();
		this.hostname = ip.getHostName();

		NetworkInterface netif = null;
		netif = NetworkInterface.getByInetAddress(ip);

		byte[] mac = netif.getHardwareAddress();
		for (byte b : mac) {
			this.mac += String.format("[%02X]", b);
		}
	}
}
