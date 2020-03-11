package com.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMaptest {

	public static void main(String[] args) {
		TreeMap treeMap = new TreeMap();
		treeMap.put("1","1");
		treeMap.put("2","2");
		treeMap.put("3","3");
		treeMap.put("4","4");
		treeMap.put("5","5");
		treeMap.put("6","6");
		treeMap.put("7","7");
		SortedMap sortedMap = treeMap.tailMap("4");//返回子树（大于等于4）
		System.out.println(sortedMap.firstKey());
		System.out.println(treeMap.firstKey());
	}

}
