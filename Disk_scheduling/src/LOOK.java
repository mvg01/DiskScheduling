import java.util.*;

public class LOOK implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> first_queue; // 원본 상태 큐
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public LOOK(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // 실린더의 큐 리스트
		this.first_queue = new ArrayList<>(queue); // 원본 상태 큐 복사
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() {
		boolean up=true;  // 위로 올라간다. false -> 아래로 내려감
		int next_head = head;
		Collections.sort(queue);  // 오름차순 정렬

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
