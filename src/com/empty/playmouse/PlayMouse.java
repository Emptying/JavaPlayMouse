package com.empty.playmouse;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayMouse {
	public static void main(String [] args){
		new GameSatart("�ҵĴ������Ϸ");
	}
	
}

class GameSatart extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6850710230045993779L;
	//����
	private JLabel back;
	//����ͼƬ
	private ImageIcon backicom;
	//�����Ī
	private ImageIcon timo;
	//
	private JLabel[] timos;
	//��ʾ����
	private JLabel scoredisplay;
	//����
	private int score=0;
	
	//��Ӵ���
	private ImageIcon hammer1;
	//private ImageIcon hammer2;
	//
	private Toolkit tk ;
	
	
	
	public GameSatart(String titalName){
		super(titalName);
		init();
		reglistener();
		new Thread(new GameThread()).start();
	}
	
	public void init(){
		this.setLayout(null);
		this.setBounds(300, 200, 1035, 742);
		backicom = new ImageIcon(this.getClass().getResource("back.png"));
		timo = new ImageIcon(this.getClass().getResource("timo1.png"));
		timos =  new JLabel[6];
		//����
		hammer1 = new ImageIcon(this.getClass().getResource("chuizi.png"));
		//hammer2 = new ImageIcon(this.getClass().getResource("chuizi2.png"));
		
		//���ô���
		tk = Toolkit.getDefaultToolkit();
		Cursor cursor = tk.createCustomCursor(hammer1.getImage(),new Point(), null);
		this.setCursor(cursor);
		
		//���ְ�
		scoredisplay = new JLabel();
		//����������ʽ��С
		scoredisplay.setFont(new Font("", 20, 25));
		//����������ɫ
		scoredisplay.setForeground(Color.RED);
		//�������
		scoredisplay.setText("���ķ�����"+score+"��");
		//������ʾ����λ��
		scoredisplay.setBounds(800, 40, 240, 40);
		this.add(scoredisplay);
		//����ÿ����Ī��ʾ��λ��
		Integer[][] pos = {
				{80,295},
				{630,270},
				{390,375},
				{845,400},
				{160,530},
				{600,545}};
		
		for(int i=0;i<6;i++){
			timos[i] = new JLabel();
			//timos[i].setIcon(timo);
			timos[i].setBounds(pos[i][0], pos[i][1], timo.getIconWidth(), timo.getIconHeight());
			this.add(timos[i]);
		}
		
		//���ñ���ͼƬ
		back = new JLabel();
		back.setBounds(0, 0, 1035, 742);
		back.setIcon(backicom);
	
		this.add(back);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	//����Ī�¼�
	public void reglistener(){
		for(int i=0;i<6;i++){
			timos[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel label=(JLabel)e.getSource();
					if(label.getIcon()!=null){
						score++;
						scoredisplay.setText("���ķ�����"+score+"��");
					}
				}
			});
		}
	}
	//�����ʾ��Ī�߳�
	private class GameThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(500);
					int index = new Random().nextInt(6);
					timos[index].setIcon(timo);
					Thread.sleep(800);
					timos[index].setIcon(null);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
	} 
	
}
