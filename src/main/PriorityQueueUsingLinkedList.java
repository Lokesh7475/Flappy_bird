package main;

public class PriorityQueueUsingLinkedList {
	class Node{
		Image_Object imgObj;
		Node next;
		
		Node(Image_Object imgObj){
			this.imgObj = imgObj;
		}
	}
	
	Node head;
	void add(Image_Object img) {
		if(head == null)
		{
			head = new Node(img);
			return ;
		}
		
		Node n = new Node(img);
		if(head.imgObj.depth < img.depth) {
			n.next = head;
			head = n;
			return ;
		}
		
		Node current = head;
		while(current.next != null) {
			if(current.next.imgObj.depth < img.depth)
			{
				n.next = current.next;
				current.next = n;
				return;
			}
			current = current.next;
		}
		
		current.next = n;
	}
	
	void remove(Image_Object img) {
		if(head == null)
			return ;
		if(head.imgObj.equals(img))
		{
			head = head.next;
			return ;
		}
		
		Node current = head;
		while(current.next != null)
		{
			if(current.next.imgObj.equals(img))
			{
				current.next = current.next.next;
				return ;
			}
		}
	}
	
	Iterator_pq getIterator() {
		return new Iterator_pq(this);
	}
}
