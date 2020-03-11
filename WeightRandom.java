package com.Map;

import java.util.ArrayList;
import java.util.List;

public class WeightRandom {
	public static String getServer() {
		List<String> ips = new ArrayList<String>();
		for(String ip:ServerIps.WEIGHT_LIST.keySet()) {
			Integer weight = ServerIps.WEIGHT_LIST.get(ip);
			for(int i=0;i<weight;i++) {
				ips.add(ip);
			}
		}
		java.util.Random random = new java.util.Random();
		int randomPos = random.nextInt(ips.size());
		
		return ips.get(randomPos);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			System.out.println(getServer());
		}
	}

}
