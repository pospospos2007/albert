package com.zdcf.mum;

import java.util.*;

public class MainItemTesst {

	public static void main(String[] args) {
		Item t1 = new Item("Chilli", 100.0, new Date(2016, 12, 24));
		Item t2 = new Item("Rice", 50.0, new Date(2015, 12, 24));
		Item t3 = new Item("Beef", 80.0, new Date(2016, 10, 24));
		Item t4 = new Item("Rice", 50.0, new Date(2016, 12, 24));
		Item t5 = new Item("Chilli", 50.0, new Date(2016, 12, 24));
		
		List<Item> itemList = new ArrayList<>();
		itemList.add(t1);
		itemList.add(t2);
		itemList.add(t3);
		itemList.add(t4);
		itemList.add(t5);
		
		Collections.sort(itemList, new ItemComparator());
		for(Item ls: itemList){
			System.out.println(ls);
		}
		
	}

}
