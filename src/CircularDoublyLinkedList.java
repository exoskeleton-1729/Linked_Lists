
public class CircularDoublyLinkedList {
	// Implements a circular doubly-linked list.

	private ListNode2 head;
	private int nodeCount;
	private ListNode2 sentinel;

	// Constructor: creates an empty list
	public CircularDoublyLinkedList() {
		head = null;
		sentinel = new ListNode2("sentinel");
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public CircularDoublyLinkedList(Object[] values) {
		if(values.length > 0)
		{
			ListNode2 node = new ListNode2(values[0]);
			head = node;
			sentinel = new ListNode2("sentinel");
			head.setNext(sentinel);
			sentinel.setPrevious(head);
			sentinel.setNext(head);
			head.setPrevious(sentinel);
			nodeCount = 1;
			for(int i = 1; i < values.length; i++)
			{
				add(values[i]);
			}
		}
		else
		{
			ListNode2 node = new ListNode2("sentinel");
			head = null;
			sentinel = node;
			nodeCount = 0;
		}
		
	}
	
	public ListNode2 getHead() {
		return head;
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Object obj) {
		if(obj == null)
		{
			for(ListNode2 node = head; node != null; node = node.getNext())
			{
				if(node.getValue() == null)
				{
					return true;
				}
				node = node.getNext();
			}
		}
		else
		{
			for(ListNode2 node = head; node != null; node = node.getNext())
			{
				if(node.getValue().equals(obj))
				{
					return true;
				}
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Object obj) {
		if(obj == null)
		{
			int counter = 0;
			for(ListNode2 node = head; node != null; node = node.getNext())
			{
				if(node.getValue() == null)
				{
					return counter;
				}
				counter++;
			}
		}
		else
		{
			int counter = 0;
			for(ListNode2 node = head; node != null; node = node.getNext())
			{
				if(node.getValue().equals(obj))
				{
					return counter;
				}
				counter++;
			}
		}
		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(Object obj) {
		ListNode2 node = new ListNode2(obj);
		if(nodeCount == 0)
		{
			head = node;
			head.setNext(sentinel);
			head.setPrevious(sentinel);
			sentinel.setNext(head);
			sentinel.setPrevious(head);
			nodeCount++;
			return true;
		}
		else
		{
			node.setPrevious(sentinel.getPrevious());
			sentinel.getPrevious().setNext(node);
			node.setNext(sentinel);
			sentinel.setPrevious(node);
			nodeCount++;
			return true;
		}
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Object obj) {
		if(head == null)
		{
			return false;
		}
		else if(head.getValue().equals(obj))
		{
			sentinel.setNext(head.getNext());
			head.getNext().setPrevious(sentinel);
			head = head.getNext();
			nodeCount--;
			return true;
		}
		else if(obj == null)
		{
			for(ListNode2 node = head; node != sentinel; node = node.getNext())
			{
				if(node.getValue() == null)
				{
					node.getPrevious().setNext(node.getNext());
					node.getNext().setPrevious(node.getPrevious());
					nodeCount--;
					return true;
				}
			}
		}
		else
		{
			for(ListNode2 node = head; node != sentinel; node = node.getNext())
			{
				if(node.getValue().equals(obj))
				{
					node.getPrevious().setNext(node.getNext());
					node.getNext().setPrevious(node.getPrevious());
					nodeCount--;
					return true;
				}
			}
		}
		return false;
	}

	// Returns the i-th element.               
	public Object get(int i) {
		int counter  = 0;
		for(ListNode2 node = head; !node.equals(sentinel); node = node.getNext())
		{
			if(counter == i)
			{
				return node.getValue();
			}
			counter++;
		}
		throw new IndexOutOfBoundsException();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Object set(int i, Object obj) {
		Object replaced;
		int counter = 0;
		for(ListNode2 node = head; !node.equals(sentinel); node = node.getNext())
		{
			if(counter == i)
			{
				replaced = node.getValue();
				node.setValue(obj);
				return replaced;
			}
			counter++;
		}
		if(i == nodeCount)
		{
			add(obj);
		}
		throw new IndexOutOfBoundsException();
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		ListNode2 addIn = new ListNode2(obj);
		if(i > nodeCount)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(nodeCount == 0)
		{
			add(obj);
		}
		else if(i == 0)
		{
			addIn.setNext(head);
			addIn.setPrevious(sentinel);
			sentinel.setNext(addIn);
			head.setPrevious(addIn);
			head = addIn;
			
		}
		else if(i == nodeCount)
		{
			add(obj);
		}
		else
		{
			ListNode2 node = head;
			for(int j = 1; j < i; j++)
			{
				node = node.getNext();
			}
			addIn.setNext(node.getNext());
			node.getNext().setPrevious(addIn);
			node.setNext(addIn);
			addIn.setPrevious(node);
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Object remove(int i) {
		ListNode2 toRemove = head;
		if(i >= nodeCount)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(nodeCount == 1)
		{
			head = null;
		}
		else if(i == 0)
		{
			sentinel.setNext(head.getNext());
			head.getNext().setPrevious(sentinel);
			head = head.getNext();
		}
		else
		{
			ListNode2 node = head;
			for(int j = 1; j < i; j++)
			{
				node = node.getNext();
			}
			
			toRemove = node.getNext();
			node.setNext(node.getNext().getNext());
			node.getNext().setPrevious(node);
		}
		nodeCount--;
		return toRemove.getValue();
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(ListNode2 node = head; node != sentinel; node = node.getNext())
		{
			if(node == sentinel.getPrevious())
			{
				sb.append(node.getValue());
			}
			else
			{
				sb.append(node.getValue() + ", ");
			}	
		}
		sb.append("]");
		return sb.toString();	
	}
	
	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(CircularDoublyLinkedList seg) {
		for(int i = 0; i < seg.size(); i++)
		{
			add(seg.get(i));
		}
		
	}
	
	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2 nodeBefore) {
		for(int i = 1; i <= 16; i++)
		{
			remove(indexOf(nodeBefore.getValue()) + i);
		}
		
	}
	
	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(CircularDoublyLinkedList seg) {
		return false;
	}
	
	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		return false;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		
	}

}
