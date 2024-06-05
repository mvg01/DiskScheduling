import java.util.*;

public class LOOK implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> first_queue; // ���� ���� ť
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public LOOK(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // �Ǹ����� ť ����Ʈ
		this.first_queue = new ArrayList<>(queue); // ���� ���� ť ����
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() {
		boolean up=true;  // ���� �ö󰣴�. false -> �Ʒ��� ������
		int next_head = head;
		Collections.sort(queue);  // �������� ����

        while (!queue.isEmpty()) {
            if (up) { 
                for (int i = 0; i < queue.size(); i++) {
                    if (queue.get(i) >= next_head) {
                        result.add(queue.get(i));
                        dis += Math.abs(next_head - queue.get(i));
                        next_head = queue.get(i);
                        queue.remove(i);
                        i--;
                    }
                }
                up = false; 
            } else {  
                for (int i = queue.size() - 1; i >= 0; i--) {
                    if (queue.get(i) <= next_head) {
                        result.add(queue.get(i));
                        dis += Math.abs(next_head - queue.get(i));
                        next_head = queue.get(i);
                        queue.remove(i);
                    }
                }
                up = true;
            }
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
		new DrawResult(result,first_queue,"LOOK",dis,head);
	}
}
