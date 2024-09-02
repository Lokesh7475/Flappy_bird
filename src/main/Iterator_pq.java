package main;

public class Iterator_pq {
	PriorityQueueUsingLinkedList pq;
	PriorityQueueUsingLinkedList.Node current;
	Iterator_pq(PriorityQueueUsingLinkedList pq){
		this.pq = pq;
		current = pq.head;
	}
	
	public boolean hasNext() {
		return current != null;
	}
	
	public Image_Object next() {
		Image_Object ob = current.imgObj;
		current = current.next;
		return ob;
	}
}
