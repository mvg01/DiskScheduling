import java.util.ArrayList;

public class C_SCAN implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public C_SCAN(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // 실린더의 큐 리스트
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() {
		boolean chk[]=new boolean[101];  // 요청 실린더 확인 배열
		for(int i=0; i<queue.size(); i++)
			chk[queue.get(i)]=true;
		
		int cnt=queue.size();   // 요청 실린더의 개수
		int next_head=head;
		while (cnt > 0) {
			for (int i = next_head; i <= 100; i++) {
				if (chk[i]) {
                    result.add(i);
                    chk[i] = false;
                    dis += Math.abs(next_head - i);
                    next_head = i;
                    cnt--;  
                }
            } 
			if (cnt > 0) {  // 디스크 끝에 도달했지만 아직 요청 실린더가 있는 경우
            	dis+=100-next_head;
                next_head = 0;
                result.add(100);
                dis += 100;
                result.add(0);
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
		new DrawResult(result,queue,"C-SCAN",dis,head);
	}
}
