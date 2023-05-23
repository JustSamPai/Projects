/*
		* A class that implements a queue.  It is your job to complete this class.  Your queue
		* will use a linked list constructed by QueueElements.  However, your queue must be general and allow
		* setting of any type of Object.  Also you cannot use ArrayLists or arrays (you will get zero).
		* @author you
		*
		*/


		import java.util.NoSuchElementException;

/**
 * The type Queue.
 *
 * @param <T> the type parameter
 */
public class Queue<T> {

	//TODO:  You need some data to store the queue.  Put the attributes here.


	/* Constructs an empty Queue.
	 */
	// initialises a QueueElement T as the head and tail
	private QueueElement<T> head;
	private QueueElement<T> tail;
	// initialises size of queue
	private int size = 0;


	/**
	 * Instantiates a new Queue.
	 */
	public Queue() {
		this.head = null;
		this.tail = null;

	}

	/**
	 * Is empty boolean.
	 *
	 * @return the boolean
	 */
	/* Returns true if the queue is empty
	 */
	public boolean isEmpty() {
		//TODO:  Needs to return true when empty and false otherwise
		//if the head and tail = null then isEmpty is true
		if ((this.head == null) && (this.tail == null)) {
			return true;
		}
		return false;
	}


	/**
	 * Peek t.
	 *
	 * @return the t
	 * @throws NoSuchElementException the no such element exception
	 */
	/* Returns the element at the head of the queue
	 */
	public T peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return this.head.getElement();
		}
	}

	/**
	 * Dequeue.
	 *
	 * @throws NoSuchElementException the no such element exception
	 */
	/* Removes the front element of the queue
	 */
	public void dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		if (this.head == this.tail) {
			this.tail = null;
			this.head = null;
		}else {
		this.head = head.getNext();
	}
		if(isEmpty()) {
			this.tail = null;
			this.head = null;
		}
		size--;
	}

	/**
	 * Enqueue.
	 *
	 * @param element the element
	 */
	/* Puts an element on the back of the queue.
	 */
	public void enqueue(T element) {
		QueueElement<T> enqueue = new QueueElement<T>(element, head);
		if (isEmpty()) {
			this.head = enqueue;
		} else {
			this.tail.setNext(enqueue);
		}
		this.tail = enqueue;
		size++;
	}

	/**
	 * Method to print the full contents of the queue in order from head to tail.
	 */
	public void print() {
		//Code to print the code is needed here
		QueueElement<T> latestNode = this.head;
		for (int i = 0; i < size; i++) {
			System.out.println(latestNode.getElement());
			latestNode = latestNode.getNext();
		}
	}

}