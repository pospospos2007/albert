package com.zdcf.test;

public class LinkList<T> { 
	
	private Node<T> head; //链表的头节点
	private Node<T> tail; //链表的尾节点
	
	/**
	 * 构造一个空链表
	 */
	public LinkList() { 
		head = tail = null;    
	}  
	
	/**
	 * 链表内部的节点类
	 */
	private static class Node<T> {  
		T data;//节点的数据
		Node<T> next;//该节点的指向下一个节点的指针
		
		Node(T data) { 
		    this.data = data;  
		    this.next = null;   
		}  
  
	}  
	
	public void addHead(T point) {//为空链表增加头结点    
		this.head = new Node<T>(point);  
		if(tail == null) {  
		    tail = head;  
		}  
	}  
	
	public void addTail(T point){//为链表增加尾节点  
	    tail = new Node<T>(point);  
	    head.next = tail;  
	}  
	
	public void insert(T point) {
		if (this.head == null) {
			addHead(point);
			
		} else if (this.tail == this.head) {
			addTail(point);
			
		} else {
	    	Node<T> preNext = head.next;  
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Node<T> newNode = new Node(point);  
	        preNext = head.next;  
	        this.head.next = newNode;  
	        newNode.next = preNext;  
		}
	     
	}  
	
	public void printLinkList() {    //打印链表
		Node<T> curr = this.head;  
		if (isEmpty()) {  
			try {
				throw new Exception("linklist is empty");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		while(curr != null){  
			System.out.print(curr.data+" ");      
			curr = curr.next;  
		}  	
	}  
	
	public void delete(T data){//删除某一节点
		Node<T> curr = head, prev = null;
		boolean suc = false;//是否删除成功标志
		while (curr != null) {
			if (curr.data.equals(data)) {
				//判断是什么节点
				if (curr == head) {   //如果删除的是头节点
					System.out.println('\n'+"delete head node");
					head = curr.next;
					suc = true;
					return;
				}
				if (curr == tail) { //如果删除的是尾节点
					System.out.println('\n'+"delete tail node");
					tail = prev;
					prev.next = null;
					suc = true;
					return;
				} else {//如果删除的是中间节点（即非头节点或非尾节点）
					System.out.println('\n'+"delete center node");
					prev.next = curr.next;
					suc = true;
					return;
				}
			}

			prev = curr;
			curr = curr.next;	
		}
		
		if(suc == false) {
			System.out.println('\n'+"没有这个数据");
		}	
	
	}
	
	public boolean isEmpty(){//判断链表是否为空
		return this.head == null || this.tail == null;
    } 

    public static void main(String[] args) {  
		LinkList<Integer> mylist = new LinkList<Integer>();//构造一个空链表  
		mylist.insert(5);  
		mylist.insert(6);  
		mylist.insert(7);  
		mylist.insert(3);  
		mylist.printLinkList();  
		mylist.delete(1);
		mylist.printLinkList();  
		mylist.delete(5);
		mylist.printLinkList();
		mylist.delete(6);
		mylist.printLinkList();  
    }  
  
} 