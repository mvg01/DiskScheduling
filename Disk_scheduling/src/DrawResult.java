import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class DrawResult extends JFrame {
	private MyPanel panel;
	private ArrayList<Integer> line;
	private ArrayList<Integer> first;
	private String algorithm;
	private int distance;
	private int head;
	
	// 결과 list, 원본 list, 알고리즘이름, 탐색 거리, 초기 헤드
	public DrawResult(ArrayList<Integer>result,ArrayList<Integer>first, String algorithm, int dist, int head) {
		setTitle("Disk Scheduling");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //닫기를 통해서 응용프로그램 자동 종료
		setSize(1000, 800);
		this.line=result;
		this.first=first;  // 초기 상태
		this.algorithm=algorithm;
		this.distance=dist;
		this.head=head;

		panel = new MyPanel();
		add(panel);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Dialog", Font.ITALIC, 20));
			g.drawString(algorithm, 460, 30);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Dialog", Font.ITALIC, 15));
			g.drawString("처음 헤드 위치: "+ head, 40, 50);
			g.drawString("큐에 있는 요청 실린더들 "+first.toString(), 40, 70);
			g.setColor(Color.BLACK);
			int x=50;
			for(int i=0; i<=100; i++) {
				if(i%10==0) {
					g.drawLine(x, 100, x, 110);
					g.drawString(String.valueOf(i), x-5, 95);
				}
				else if(i%5==0)
					g.drawLine(x, 100, x, 106);
				else
					g.drawLine(x, 100, x, 103);
				x+=8;
			}
			g.setColor(Color.RED);
			int y=160;
			int prev_x=50+head*8,prev_y=160;  // 초기 헤드의 위치
			g.drawString(String.valueOf(head)+" 처음 헤드의 위치", prev_x-10, prev_y-10);
			for(int i=0; i<line.size(); i++) {
				y+=45;
				x=50+line.get(i)*8;
				g.drawString(line.get(i).toString(), x, y+18);
				g.drawLine(prev_x, prev_y, x, y);
				prev_x=x;
				prev_y=y;
			}
			g.setColor(Color.BLUE);
			g.drawString("총 탐색 거리 : "+String.valueOf(distance),40,740);
		}
	}
}