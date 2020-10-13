package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class MultiThredQueue {
	private Queue<Integer> queue = new LinkedList();
	private int limit = 10;
	private static int count;
	private static int maxCount;
	private static final Vector<Integer> vector = new Vector<Integer>();
	
	public MultiThredQueue(int limit, int maxCount) {
		this.limit = limit;
		this.maxCount = maxCount;
	}

	StringBuffer aqSb = new StringBuffer();

	public synchronized void enqueue(int item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			System.out.println("queue가 가득차서 enqueue 대기중입니다.");
			wait();
		}
		if (this.queue.size() == 0) {
			System.out.println("queue가 비어서 다시금 add 시작합니다.");
			notifyAll();
		}
		
		if(count >= maxCount)
			Thread.currentThread().interrupt();
		else {
			this.queue.add(item);
			this.vector.add(item);
			AddCount();
			
			aqSb.append(Arrays.toString(vector.toArray())).append(" + ").append(item).append("을 넣었습니다. addque!!! 현재 큐에 담긴 갯수는").append(this.queue.size());
			
			System.out.println(aqSb.toString() +"  "+ count);
			aqSb.setLength(0);
		}
	}

	StringBuffer dqSb = new StringBuffer();

	public synchronized void dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			System.out.println("queue에 들어있는 값이 없기에 dequeue 대기중 입니다.");
			wait();
			
			System.out.println();
		}
		if (this.queue.size() == this.limit) {
			System.out.println("queue가 가득차서 다시금 poll 시작합니다.");
			notifyAll();
		}
		this.vector.remove(0);
		dqSb.append(Arrays.toString(vector.toArray())).append(" - ").append(this.queue.poll()).append("을 뺐습니다. dequeue!!! 현재 큐에 담긴 갯수는 ").append(this.queue.size());
		System.out.println(dqSb.toString());
		dqSb.setLength(0);	

	}
	
	private static synchronized void AddCount() {
		count++;
	}

}