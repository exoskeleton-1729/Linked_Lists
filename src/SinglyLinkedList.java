// Implements a singly-linked list.


public class SinglyLinkedList {
	private ListNode head;
	private ListNode tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
		ListNode node = new ListNode(values[0]);
		head = node;
		tail = node;
		nodeCount = 1;
		for(int i = 1; i < values.length; i++)
		{
			add(values[i]);
		}
	}
	
	public ListNode getHead() {
		return head;
	}
	
	public ListNode getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return(nodeCount == 0);
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
			for(ListNode node = head; node != null; node = node.getNext())
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
			for(ListNode node = head; node != null; node = node.getNext())
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
			for(ListNode node = head; node != null; node = node.getNext())
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
			for(ListNode node = head; node != null; node = node.getNext())
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
		ListNode toAdd = new ListNode(obj);
	    if(nodeCount == 0)
	    {
	        head = toAdd;
	        tail = toAdd;
	        nodeCount++;
	        return true;
	    }
	    toAdd.setNext(null);
	    tail.setNext(toAdd);
	    tail = toAdd;
	    nodeCount++;
	    return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Object obj) {
		if(obj == null)
		{
			if(head.getValue() == null)
			{
				head = head.getNext();
				nodeCount--;
				return true;
			}
			else
			{
				ListNode prevNode = head;
				for(ListNode node = head.getNext(); node != null; node = node.getNext())
				{
					if(node.getValue() == null)
					{
						prevNode.setNext(node.getNext());
						nodeCount--;
						return true;
					}
					prevNode = prevNode.getNext();
				}
			}
		}
		else if(head.getValue().equals(obj))
		{
			head = head.getNext();
			nodeCount--;
			return true;
		}
		else
		{
			ListNode prevNode = head;
			for(ListNode node = head.getNext(); node != null; node = node.getNext())
			{
				if(node.getValue().equals(obj))
				{
					prevNode.setNext(node.getNext());
					nodeCount--;
					return true;
				}
				prevNode = prevNode.getNext();
			}
		}
		return false;
	}

	// Returns the i-th element.               
	public Object get(int i) {
		int counter  = 0;
		for(ListNode node = head; node != null; node = node.getNext())
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
		for(ListNode node = head; node != null; node = node.getNext())
		{
			if(counter == i)
			{
				replaced = node.getValue();
				node.setValue(obj);
				return replaced;
			}
			counter++;
		}
		throw new IndexOutOfBoundsException();
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		ListNode addIn = new ListNode(obj);
		if(i > nodeCount)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(nodeCount == 0)
		{
			head = addIn;
			tail = addIn;
		}
		else if(i == 0)
		{
			addIn.setNext(head);
			head = addIn;
		}
		else if(i == nodeCount)
		{
			add(obj);
		}
		else
		{
			ListNode node = head;
			for(int j = 1; j < i; j++)
			{
				node = node.getNext();
			}
			addIn.setNext(node.getNext());
			node.setNext(addIn);
		}
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Object remove(int i) {
		ListNode toRemove = head;
		if(i >= nodeCount)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(nodeCount == 1)
		{
			head = null;
			tail = null;
		}
		else if(i == 0)
		{
			head = head.getNext();
		}
		else
		{
			ListNode node = head;
			for(int j = 1; j < i; j++)
			{
				node = node.getNext();
			}
			toRemove = node.getNext();
			node.setNext(node.getNext().getNext());
		}
		nodeCount--;
		return toRemove.getValue();
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(ListNode node = head; node != null; node = node.getNext())
		{
			if(node == tail)
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

}
