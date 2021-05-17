package client.View;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.DAO.CustomerDAO;
import client.DAO.MovieDAO;
import client.VO.CustomerVO;
import client.VO.MovieVO;
import client.VO.TicketVO;
import client.View.SeatView.ImgPanel;

import java.awt.Color;
import java.awt.Dimension;

//예매 완료 페이지 
public class TicketConfirmView extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	//DAO
	private CustomerDAO customerDao = CustomerDAO.getInstance();
	//VO
	private TicketVO ticket;
	private CustomerVO customer;
	
	//Panel
	private JPanel panel = new JPanel();
	private ImgPanel imgPanel;
	class ImgPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private ImageIcon icon = new ImageIcon("./logo.GIF");
		private Image img = icon.getImage(); // 이미지 객체

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension d = getSize();
			g.drawImage(img, 0, 0, d.width, d.height, null);
		}
	}
	
	private JLabel lblNewLabel = new JLabel("예매가 완료되었습니다.");
	private JButton btn1 = new JButton("예매내역 확인");
	private JButton btn2 = new JButton("예매내역 취소");
	private JButton btn3 = new JButton("예매 더하기");
	
	//생성자
	public TicketConfirmView(TicketVO ticket) {
		this.ticket = ticket;
		buildGUI();
		setEvent();
	}

	//버튼 관련 액션 
	@Override
	public void actionPerformed(ActionEvent e) {
		//예매내역 확인
		if (e.getSource() == btn1) {
			System.out.println("예매내역 확인");
			TicketConfirmView01 view1 = new TicketConfirmView01(ticket);
		}
		
		//예매내역 취소
		if (e.getSource() == btn2) {
			System.out.println("예매내역 취소");
			TicketConfirmView02 view2 = new TicketConfirmView02(ticket);
		}
		
		//끝내기 
		if (e.getSource() == btn3) {
			System.out.println("예매 더하기");
			customer = customerDao.selectById(ticket.getCustomerId());
			dispose();
			TicketingView ticketingView = new TicketingView(customer);
		}
	}

	public void setEvent() {
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
	}
	
	//GUI생성
	public void buildGUI() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./favicon.PNG");
		setIconImage(img);
		setTitle("예매 완료");
		setSize(818, 612);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 812, 583);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		imgPanel = new ImgPanel();
		imgPanel.setBounds(219, 53, 325, 151);
		panel.add(imgPanel);
		
		lblNewLabel.setText("'" + ticket.getCustomerName() +"' 님 예매가 완료되었습니다.");
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		lblNewLabel.setBounds(223, 211, 368, 82);
		panel.add(lblNewLabel);
		
		btn1.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
		btn1.setBounds(149, 304, 149, 82);
		panel.add(btn1);
		
		btn2.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
		btn2.setBounds(322, 304, 149, 82);
		panel.add(btn2);
		
		btn3.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
		btn3.setBounds(498, 304, 149, 82);
		panel.add(btn3);
	}
}
