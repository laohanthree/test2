package com.Map;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistemHash {
	private static TreeMap<Integer,String> virtualNoders = new TreeMap<>(); 
	private static final int VIRTUAL_NOOES = 160;
	
	static {
		//��ÿ����ʵ�ڵ��������ڵ㣬����ڵ����ݹ�ϣ�㷨����ɢ��
		for(String ip:ServerIps.LIST) {
			for(int i=0;i<VIRTUAL_NOOES;i++) {
				int hash = getHash(ip+"VN"+i);
				virtualNoders.put(hash, ip);
			}
		}
	}
	
	private static String getServer(String client) {
		int hash = getHash(client);
		//�õ����ڸ�hashֵ���ź����Map
		SortedMap<Integer, String> subMap = virtualNoders.tailMap(hash);
		//���ڸ�hashֵ��һ��Ԫ�ص�λ��
		Integer nodeIndex = subMap.firstKey();
		//��������ڴ��ڸ�hashֵ��Ԫ�أ��򷵻ظ��ڵ�
		if(nodeIndex==null) {
			nodeIndex = virtualNoders.firstKey();
		}
		//���ض�Ӧ������ڵ�����
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
		//����������ֵΪ��������ȡ�����ֵ
		    if(hash<0) 
			hash=Math.abs(hash);
			return hash;
	}
	
	
	
	public static void main(String[] args) {
		//��������ʮ�Σ����10��client
		for(int i=0;i<10;i++) {
			System.out.println(getServer("client"+i));
		}
	}

}
