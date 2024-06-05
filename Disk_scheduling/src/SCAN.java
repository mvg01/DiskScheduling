import java.util.ArrayList;

public class SCAN implements Diskscheduler {
	ArrayList<Integer> queue;
	ArrayList<Integer> result;
	private int dis=0;
	private int head=0;
	public SCAN(ArrayList<Integer> queue, int head) {
		this.queue = new ArrayList<>(queue);       // �Ǹ����� ť ����Ʈ
		this.head=head;
		this.result=new ArrayList<Integer>();
	}
	@Override
	public void Schedule() {  
		// ���� �� ��û �Ǹ����� ���´ٸ� ��� chk[��û�Ǹ�����ȣ] �迭�� �߰� + ��û �Ǹ������� �߰�
		boolean up=true;  // ���� �ö󰣴�. false -> �Ʒ��� ������
		boolean chk[]=new boolean[101];  // ��û �Ǹ��� Ȯ�� �迭
		for(int i=0; i<queue.size(); i++)
			chk[queue.get(i)]=true;
		
		int cnt=queue.size();   // ��û �Ǹ����� ����
		int next_head=head;
		while (cnt > 0) {
            if (up) {  // �ö󰡴� ���
                for (int i = next_head; i <= 100; i++) {
                    if (chk[i]) {
                        result.add(i);
                        chk[i] = false;
                        dis += Math.abs(next_head - i);
                        next_head = i;
                        cnt--;  
                    }
                }
                if (cnt > 0) {  // ��ũ ���� ���������� ���� ��û �Ǹ����� �ִ� ���
                    dis += 100 - next_head;
                    next_head = 100;
                    result.add(100);
                    up = false;   // ���� ����
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
                if (cnt > 0) {  // ������ �ڵ�δ� ����� �� ���� �ڵ�..
                    dis += next_head; 
                    next_head = 0;
                    result.add(0);
                    up = true;   // ���� ����
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
