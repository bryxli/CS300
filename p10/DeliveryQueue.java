import java.util.NoSuchElementException;

/**
 *This class implements the deliveryqueue object.
 */
public class DeliveryQueue {
	private static final int INITIAL_CAPACITY = 20;
	private Delivery[] heap;
	private int size;

	/**
	 * Creates a deliveryqueue with initial capacity at 20 and size 0.
	 */
	public DeliveryQueue() {
		heap = new Delivery[INITIAL_CAPACITY];
		size = 0;
	}

	/**
	 * Getter method to return parent index
	 * 
	 * @return parent index
	 */
	public int getParentIndex(int node) {
		return (node - 1) / 2;
	}

	/**
	 * Getter method to return left child index
	 * 
	 * @return left child index
	 */
	public int getLeftChildIndex(int node) {
		return (node * 2) + 1;
	}

	/**
	 * Getter method to return right child index
	 * 
	 * @return right child index
	 */
	public int getRightChildIndex(int node) {
		return (node * 2) + 2;
	}

	/**
	 * Add a delivery object to  the heap
	 * 
	 * @param delivery - object to be added
	 */
	public void offerDelivery(Delivery delivery) {
		if (size == heap.length) {
			Delivery[] temp = new Delivery[heap.length * 2];
			for (int i = 0; i < heap.length; i++) {
				temp[i] = heap[i];
			}
			heap = temp;
		}
		heap[size] = delivery;
		if (!isEmpty())
			percolateUp(size);
		size++;
	}

	/**
	 * Remove root delivery and all deliveries that are equal to it
	 * 
	 * @return object removed
	 */
	public Delivery pollBestDelivery() {
		if (isEmpty())
			throw new NoSuchElementException("Warning: Empty Heap!");
		Delivery best = heap[0];
		for(int i=0;i<size;i++) {
			if(heap[i]!=null&&heap[i].equals(best)) {
				heap[i]=heap[size];
				heap[size]=null;
				size--;
			}
		}
		percolateDown(0);
		return best;
	}

	/**
	 * Get root of heap without removing it
	 * 
	 * @return root
	 */
	public Delivery peek() {
		if (isEmpty())
			throw new NoSuchElementException("Warning: Empty Heap!");
		else
			return heap[0];
	}

	/**
	 * Getter method to return size field
	 * 
	 * @return size field
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Checks to see if heap is empty.
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Swaps two nodes in the heap
	 * 
	 * @param x - node to be swapped
	 * 
	 * @param y - node to be swapped
	 */
	public void swap(int x, int y) {
		Delivery temp = heap[x];
		heap[x] = heap[y];
		heap[y] = temp;
	}

	/**
	 * Moves up the heap and swaps
	 * 
	 * @param index - index to check for swap
	 */
	private void percolateUp(int index) {// 3--->4???
		if (heap[index].compareTo(heap[getParentIndex(index)]) < 0)
			swap(index, getParentIndex(index));
	}

	/**
	 * Moves down the heap and swaps
	 * 
	 * @param index - index to check for swap
	 */
	private void percolateDown(int index) {
		Delivery lc = null;
		Delivery rc = null;
		Delivery node = heap[index];
		if (heap[getLeftChildIndex(index)] != null) {
			lc = heap[getLeftChildIndex(index)];
		}
		if (heap[getRightChildIndex(index)] != null) {
			rc = heap[getRightChildIndex(index)];
		}
		if (lc != null && rc != null && node != null) {
			if (node.compareTo(lc) > 0 || node.compareTo(rc) > 0) {
				if (lc.compareTo(rc) < 0) {
					swap(index, getLeftChildIndex(index));
					percolateDown(getLeftChildIndex(index));
				} else {
					swap(index, getRightChildIndex(index));
					percolateDown(getRightChildIndex(index));
				}
			}
		} else if (rc == null && lc != null) {
			swap(index, getLeftChildIndex(index));
			percolateDown(getLeftChildIndex(index));
		} else if (lc == null && rc != null) {
			swap(index, getRightChildIndex(index));
			percolateDown(getRightChildIndex(index));
		}
	}

	@Override
	/**
	 * String representation of object
	 * 
	 * @return string representation
	 */
	public String toString() {
		String string = "This DeliveryQueue contains " + size + " elements";
		if (size == 0) {
			return string;
		}
		string += ": [ ";
		for (int i = 0; i < size; i++)
			string += "\n" + heap[i].toString();
		string += " ]\n";
		return string;
	}
}
