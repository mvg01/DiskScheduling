import java.util.ArrayList;

public class SCAN implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public SCAN(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // 실린더의 큐 리스트
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() {  
		// 만약 새 요청 실린더가 들어온다면 즉시 chk[요청실린더번호] 배열에 추가 + 요청 실린더개수 추가
		boolean up=true;  // 위로 올라간다. false -> 아래로 내려감
		boolean chk[]=new boolean[101];  // 요청 실린더 확인 배열
		for(int i=0; i<queue.size(); i++)
			chk[queue.get(i)]=true;
		
		int cnt=queue.size();   // 요청 실린더의 개수
		int next_head=head;
		while (cnt > 0) {
            if (up) {  // 올라가는 경우
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
                    dis += 100 - next_head;
                    next_head = 100;
                    result.add(100);
                    up = false;   // 방향 변경
                }
            } else {
                for (int i = next_head; i >= 0; i--) {
                    if (chk[i]) {
                        result.add(i);
                        chk[i] = false;
                        dis += Math.abs(next_head - i);
                        next_head = i;
                        cnt--;
                    }
                }
                if (cnt > 0) {  // 현재의 코드로는 실행될 일 없는 코드..
                    dis += next_head; 
                    next_head = 0;
                    result.add(0);
                    up = true;   // 방향 변경
                }
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
		new DrawResult(result,queue,"SCAN",dis,head);
	}
}
