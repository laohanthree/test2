package com.Map;

import java.util.ArrayList;
import java.util.List;

public class WeightRandom2 {
	public static String getServer() {
		
		int totalWeight = 0;
		boolean sameWeight = true;
		Object[] weights = ServerIps.WEIGHT_LIST.values().toArray();
		for(int i=0;i<weights.length;i++) {
			Integer weight = (Integer) weights[i];
			totalWeight+=weight;
			
			if(sameWeight &&i >0 && !weight.equals(weights[i-1])) {
				sameWeight = false;
			}
		}	
		
		List<String> ips = new ArrayList<String>();
		for(String ip:ServerIps.WEIGHT_LIST.keySet()) {
			Integer weight = ServerIps.WEIGHT_LIST.get(ip);
			for(int i=0;i<weight;i++) {
				ips.add(ip);
			}
		}
		java.util.Random random = new java.util.Random();
		int randomPos = random.nextInt(totalWeight);
		
		if(!sameWeight) {
			for(String ip:ServerIps.WEIGHT_LIST.keySet()) {
				Integer value = ServerIps.WEIGHT_LIST.get(ip);
				if(randomPos<value) {
					return ip;
				}
				randomPos = randomPos - value;
			}			
		}
	
		return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[new java.util.Random().nextInt(ServerIps.WEIGHT_LIST.size())];
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			System.out.println(getServer());
		}
	}

}
