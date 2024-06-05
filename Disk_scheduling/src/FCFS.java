import java.util.ArrayList;

public class FCFS implements Diskscheduler {
	ArrayList<Integer> queue;
	private int dis=0;
	private int head=0;
	public FCFS(ArrayList<Integer> queue, int head) {
		this.queue=queue;
		this.head=head;
	}
	@Override
	public void Schedule() {
		// FCFS�� ��ũ ť�� ������ ������� ��û�� ó���Ѵ�.
		int next_head=head;
		for(int i=0; i<queue.size(); i++) {
			dis+=Math.abs(next_head-queue.get(i));
			next_head=queue.get(i);
		}
	}
	@Override
	public int Distance() {
		return dis; 
	}
	@Override
	public ArrayList<Integer> getResult() {
		return queue;
	}
	@Override
	public void DrawResult() {
		new DrawResult(queue, queue,"FCFS",dis,head);
	}
}
