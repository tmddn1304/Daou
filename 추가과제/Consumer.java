package test;

import java.util.Random;

public class Consumer extends Thread{
	private final Random random;
	private final MultiThredQueue multiqueue;

	
	public Consumer(String name, MultiThredQueue multiqueue, long seed) {
		super(name);
		this.multiqueue = multiqueue;
		this.random = new Random(seed);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(random.nextInt(10));
				multiqueue.dequeue();
			}
		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
