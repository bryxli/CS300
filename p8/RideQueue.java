import java.util.NoSuchElementException;

/**
 * This class implements the queue system.
 */
public class RideQueue implements QueueADT<BoardingGroup> {

	private BGNode front, back;
	private int capacity, numOfPeople, numOfGroups;

	/**
	 * Constructs an empty queue with the designated capacity.
	 * 
	 * @param capacity - The number of people this queue can fit.
	 */
	public RideQueue(int capacity) {
		front = null;
		back = null;
		this.capacity = capacity;
		numOfPeople = 0;
		numOfGroups = 0;
	}

	@Override
	/**
	 * Determines whether or not this queue is empty.
	 * 
	 * @return True when queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return numOfPeople == 0;
	}

	@Override
	/**
	 * Gives the number of BoardingGroup nodes in this queue.
	 * 
	 * @return The current number of nodes in the queue.
	 */
	public int size() {
		return numOfGroups;
	}

	@Override
	/**
	 * Adds the newGroup to the queue.
	 * 
	 * @return newGroup - The BoardingGroup to be added to the queue.
	 * 
	 * @throws java.lang.IllegalStateException - If the newGroup cannot fit into the
	 *                                         queue.
	 */
	public void enqueue(BoardingGroup newObject) {
		if (newObject.getNumber() + numOfPeople > capacity)
			throw new IllegalStateException("Number of groups has exceeded ride capacity.");
		BGNode node = new BGNode(newObject);
		if (front == null) {
			front = node;
			back = node;
		} else {
			if (!node.getGroup().getVIP()) {
				back.setNext(node);
				back = node;
			} else {
				node.setNext(front);
				front = node;
			}
		}
		numOfGroups++;
		numOfPeople += newObject.getNumber();
	}

	@Override
	/**
	 * Removes all groups from this queue. This queue will become empty.
	 */
	public void clear() {
		front = null;
		back = null;
		numOfPeople = 0;
		numOfGroups = 0;
	}

	@Override
	/**
	 * Returns the BoardingGroup at the front of this queue without removing it.
	 * 
	 * @return The Group at the front of the line.
	 * 
	 * @throws java.util.NoSuchElementException - If this queue is empty.
	 */
	public BoardingGroup peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty.");
		return front.getGroup();
	}

	@Override
	/**
	 * Returns the BoardingGroup at the front of this queue and removes it.
	 * 
	 * @return The BoardingGroup that was removed from this queue.
	 * 
	 * @throws java.util.NoSuchElementException - If this queue is empty.
	 */
	public BoardingGroup dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty.");
		}
		BoardingGroup temp = front.getGroup();
		if (front == back) {
			clear();
		} else {
			front = front.getNext();
			numOfGroups--;
			numOfPeople -= temp.getNumber();
		}
		return temp;
	}

	@Override
	/**
	 * Returns a string representation of this queue.
	 * 
	 * @return This queue represented as a string.
	 */
	public String toString() {
		String s = "Number of People in Queue: " + numOfPeople + "\n";
		s += "Number of Groups in Queue: " + numOfGroups + "\n";
		s += "Group Names in Queue: ";
		BGNode current = front;
		while (current != null) {
			String groupName = current.getGroup().getName();
			s += groupName + " ";
			current = current.getNext();
		}
		return s;
	}

}
