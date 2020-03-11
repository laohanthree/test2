package com.Map;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistemHash {
	private static TreeMap<Integer,String> virtualNoders = new TreeMap<>(); 
	private static final int VIRTUAL_NOOES = 160;
	
	static {
		//对每个真实节点添加虚拟节点，虚拟节点会根据哈希算法进行散列
		for(String ip:ServerIps.LIST) {
			for(int i=0;i<VIRTUAL_NOOES;i++) {
				int hash = getHash(ip+"VN"+i);
				virtualNoders.put(hash, ip);
			}
		}
	}
	
	private static String getServer(String client) {
		int hash = getHash(client);
		//得到大于该hash值得排好序的Map
		SortedMap<Integer, String> subMap = virtualNoders.tailMap(hash);
		//大于该hash值第一个元素的位置
		Integer nodeIndex = subMap.firstKey();
		//如果不存在大于该hash值得元素，则返回根节点
		if(nodeIndex==null) {
			nodeIndex = virtualNoders.firstKey();
		}
		//返回对应的虚拟节点名称
		return subMap.get(nodeIndex);
	}
	
	private static int getHash(String str) {
		final int p = 16777619;
		int hash = (int)2166136261L;
		for(int i=0;i<str.length();i++)
			hash = (hash^str.charAt(i)*p);
		hash += hash <<13;
		hash ^= hash >>7;
		hash += hash <<3;
		hash ^= hash >>17;
		hash += hash <<5;
		//如果算出来的值为负数，则取其绝对值
		    if(hash<0) 
			hash=Math.abs(hash);
			return hash;
	}
	
	
	
	public static void main(String[] args) {
		//连续调用十次，随机10个client
		for(int i=0;i<10;i++) {
			System.out.println(getServer("client"+i));
		}
	}

}
