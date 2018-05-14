package com.zdcf.mum;

import java.util.*;

public class Item {
	String iname;
	Double iprice;
	Date pdate;
	
	public Item(String iname, Double iprice, Date pdate){
		this.iname = iname;
		this.iprice = iprice;
		this.pdate = pdate;
	}

	public String getIname() {
		return iname;
	}

	public Double getIprice() {
		return iprice;
	}

	public Date getPdate() {
		return pdate;
	}

	@Override 
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;
		Item item = (Item) obj;
		return this.getIname().equals(item.getIname()) && 
				this.getIprice().equals(item.getIprice()) &&
				this.getPdate().equals(item.getPdate());
	}
	
	@Override 
	public int hashCode(){
		int result = 17;
		int hashIname = iname.hashCode();
		int hashIprice = iprice.hashCode();
		int hashPdate = pdate.hashCode();
		result += 31 * result + hashIname;
		result += 31 * result + hashIprice;
		result += 31 * result + hashPdate;
		return result;
		
	}

	@Override
	public String toString() {
		return "Item: " + iname + " Price: " + iprice + " Date: " + pdate;
	}
}

class ItemComparator implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		
		if(!o1.getIname().equals(o2.getIname())){
			return o1.getIname().compareTo(o2.getIname());
			
	
		}
		else if(!o1.getIprice().equals(o2.getIprice())){
			return o1.getIprice().compareTo(o2.getIprice());
		}
		else 
			return o1.getPdate().compareTo(o2.getPdate());
	}
	
}
