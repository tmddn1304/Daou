package test;

public class TestMain {
	public static void main(String[] args) {
		MultiThredQueue mq = new MultiThredQueue(10,300);
		
		
		//원하는 갯수 만큼 주석 처리 후 사용
		//생성용
		new Publisher("pulblisherThread-1", mq, 11223).start();
		new Publisher("pulblisherThread-2", mq, 12822).start();
		new Publisher("pulblisherThread-3", mq, 12341).start();
		new Publisher("pulblisherThread-4", mq, 71234).start();
		
		
		//소비용
		new Consumer("ConsumerThread-1", mq, 71234).start();
		new Consumer("ConsumerThread-2", mq, 33928).start();
		new Consumer("ConsumerThread-3", mq, 47342).start();
		new Consumer("ConsumerThread-4", mq, 17342).start();
		
	}

}
