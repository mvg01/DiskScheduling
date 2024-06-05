import java.util.*;

public class SSTF implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> first_queue; // ���� ���� ť
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public SSTF(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // �Ǹ����� ť ����Ʈ
        this.first_queue = new ArrayList<>(queue); // ���� ���� ť ����
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() { 
		int next_head=head;
		while(!queue.isEmpty()) {  // queue�� �����ŭ �ݺ�
			int min_dif=100,min_idx=0;
			for(int i=0; i<queue.size(); i++) {
				if(min_dif>Math.abs(next_head-queue.get(i))) {
					min_dif=Math.abs(next_head-queue.get(i));
					min_idx=i;
				}
			}
			dis+=min_dif;
			next_head=queue.get(min_idx);
			result.add(queue.get(min_idx));
			queue.remove(min_idx);			
		}
	}

	@Override
	public int Distance() {
		return dis;
	}

	@Override
	public ArrayList<Integer> getResult() {
		return result;
	}
	@Override
	public void DrawResult() {
		new DrawResult(result,first_queue,"SSTF",dis,head);
	}
}
