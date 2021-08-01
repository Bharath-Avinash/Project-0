package com.example;

import java.util.LinkedList;

public class practice {

	public static void main(String[] args) {

		LinkedList<String> list = new LinkedList<>();
		// LinkedList< String> list1 = new LinkedList<>();
		list.add("1");

		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

//		for(int i=list.size()-1; i>=0;i-- ) {
//			
//			
//			list1.add(list.get(i));
//		}
//		
//		
//		System.out.print(list1);

		for (int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {

			String sc = list.get(i);

			list.set(i, list.get(j));
			list.set(j, sc);

		}

		System.out.print(list);
		
		
		
		
		

	}
	
	
	public static void recursive () {
		
		
		
		
	}

}
