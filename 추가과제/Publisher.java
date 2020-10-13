package test;

import java.util.Random;

public class Publisher extends Thread {
	private final Random random;
	private final MultiThredQueue multiqueue;
	public static int id = 1;

	public Publisher(String name, MultiThredQueue multiqueue, long seed) {
		super(name);
		this.multiqueue = multiqueue;
		this.random = new Random(seed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(random.nextInt(10));
				multiqueue.enqueue(idPlus());
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("end");
		}

	}
	public static synchronized int idPlus() {
		return id++;
	}
}
