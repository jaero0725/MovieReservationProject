package client.View;

import javax.swing.JFrame;

import client.DAO.MovieDAO;
import client.DAO.SeatDAO;
import client.DAO.TicketDAO;
import client.VO.MovieVO;
import client.VO.SeatVO;
import client.VO.TheatherVO;
import client.VO.TicketVO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;

public class SeatView extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	// VO
	private TicketVO ticket;
	private MovieVO movie;
	private SeatVO seat;

	private ArrayList<String> reservedSeat = new ArrayList<String>();

	// DAO
	private MovieDAO movieDao = MovieDAO.getInstance();
	private SeatDAO seatDao = SeatDAO.getInstance();
	private TicketDAO ticketDao = TicketDAO.getInstance(); // 등록 용도

	// src
	private String movieImgSrc;
	private int person = 0;// 좌석에 등록할 인원수.
	private int personCheck = 0; // 좌석 등록할 인원수 check용

	// 좌석을 미리 배열로 만들어 놓아야 한다.
	// 행, 열
	private JCheckBox[][] seats = new JCheckBox[15][15];
	private Vector<String> seatsNumber = new Vector<String>(); // 여기에 check한 좌석이 저장된다.
	private Vector<JLabel> seatDetails = new Vector<>();

	private ImgPanel imgPanel;

	class ImgPanel extends JPanel {
		private ImageIcon icon;
		private Image img; // 이미지 객체

		public ImgPanel() {
			// 이미지 받아와야함.
			movie = movieDao.getMovie(ticket.getMovieName());
			movieImgSrc = movie.getImgSrc();
			//System.out.println(movieImgSrc);
			icon = new ImageIcon("./" + movieImgSrc);
			img = icon.getImage(); // 이미지 객체
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension d = getSize();
			g.drawImage(img, 0, 0, d.width, d.height, null);
		}
	}

	private JPanel titlePanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel seatPanel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JPanel leftBottomPanel = new JPanel();
	private JLabel lblNewLabel_1_1 = new JLabel("극장 :");
	private JLabel lblNewLabel_3_1 = new JLabel("날짜 :");
	private JLabel lblNewLabel_5 = new JLabel("인원 :");
	private JLabel lblNewLabel_7 = new JLabel("\uAE08\uC561 :");

	private JLabel titleLabel = new JLabel("-");
	private JLabel theatherLabel = new JLabel("-");
	private JLabel dayLabel = new JLabel("-");
	private JLabel personLabel = new JLabel("-");
	private JLabel costLabel = new JLabel("-");

	private final JLabel checkSeatLabel = new JLabel("\uC120\uD0DD\uD55C \uC88C\uC11D \uBC88\uD638");
	private JButton ticketingButton = new JButton("\uC88C\uC11D\uC120\uD0DD ");
	private JButton reCheckSeatButton = new JButton("다시 선택");
	private JLabel seatInfoLabel = new JLabel("\uC88C\uC11D \uD604\uD669");
	private JLabel seatInfoDetailLabel = new JLabel("255 / 255");

	public SeatView(TicketVO ticket) {
		this.ticket = ticket;
		setInfotoLabel();
		setPerson();
		buildGUI();
		setEvent();
		checkInfo();
	}

	public void setInfotoLabel() {
		titleLabel.setText(ticket.getCustomerName() + " 님");
		titleLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		theatherLabel.setText(ticket.getTheatherName());
		dayLabel.setText(ticket.getDay());
		personLabel.setText(Integer.toString(ticket.getPerson()) +"명");
		costLabel.setText(Integer.toString(ticket.getCost()));

		person = ticket.getPerson();
		// 좌석 하나 눌렀을때 Label 이 찍은 좌석으로 변경.
		// 인원수에 맞게 좌석을 다 눌렀을 경우, 모든 좌석 체크 못하도록 변경 한다.

	}

	// 인원수 만큼 좌석 check 해주는거 넣어주기 .
	public void setPerson() {
		for (int i = 0; i < person; i++) {
			JLabel jLabel = new JLabel("좌석 " + (i + 1));
			jLabel.setBounds(11, (35 + (i * 25)), 57, 15);
			jLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
			jLabel.setForeground(Color.GRAY);

			// Vector에 넣어서 관리가능하도록 한다.
			seatDetails.add(jLabel);
			panel_1.add(jLabel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 좌석 다시선택.
		if (e.getSource() == reCheckSeatButton) {
			seatAllchecked(); // 이게 아님 ***************************************** -> DB 에서 받아서 넣어야함.
			personCheck = 0;
			for (int i = 0; i < person; i++) {
				seatDetails.get(i).setText("좌석 " + (i + 1));
				// check 한 좌석을 unchecked로 변경해줘야함.
				String text = seatsNumber.get(i);

				int row = text.charAt(0); // 행값
				String colStr = text.substring(1);// 1뒤에 있는 값 가져와야됨.

				row = row - 65;
				int col = Integer.parseInt(colStr) - 1;
				seats[row][col].setSelected(false);
			}
			
			for (int i = 0; i < reservedSeat.size(); i++) {
				//System.out.println(reservedSeat.get(i) + " 좌석이 예약되어 있음. ");
				String text = reservedSeat.get(i);
				int row = text.charAt(0); // 행값
				String colStr = text.substring(1);// 1뒤에 있는 값 가져와야됨.
				row = row - 65;
				int col = Integer.parseInt(colStr) - 1;
				seats[row][col].setEnabled(false);
			}
			reCheckSeatButton.setEnabled(false);
			ticketingButton.setEnabled(false);
		}

		if (e.getSource() == ticketingButton) {
			// 좌석 예매 하기.
			reserve();
		}
	}

	public void checkInfo() {
		// '종로점','1관','O15',1,'2021년 2월 5일' 이정보를 가지고 좌석 checkbox를 바꿔준다. enabled 하게
		String theathername = ticket.getTheatherName();
		String roomNumber = ticket.getRoomNumber();
		int time = ticket.getTime();
		String day = ticket.getDay();

		reservedSeat = seatDao.getSeatList(theathername, roomNumber, time, day);
		for (int i = 0; i < reservedSeat.size(); i++) {
			//System.out.println(reservedSeat.get(i) + " 좌석이 예약되어 있음. ");
			String text = reservedSeat.get(i);
			int row = text.charAt(0); // 행값
			String colStr = text.substring(1);// 1뒤에 있는 값 가져와야됨.
			row = row - 65;
			int col = Integer.parseInt(colStr) - 1;
			seats[row][col].setEnabled(false);
		}
		
		int leftSeat = (255 - reservedSeat.size());
		seatInfoDetailLabel.setText(Integer.toString(leftSeat) + " / 255");
	}

	// 좌석을 완벽히 선택하면 실행되는 메서드
	public void reserve() {
		// setReserveDate
		String seatNumberCheck = "";
		// set SeatVO reserved
		for (int i = 0; i < person; i++) {
			// 인원수만큼 티켓 VO를 만들어준다.
			// SeatVO ~ reserved 되게 변경
			SeatVO seatVO = new SeatVO();
			seatVO.setTheathername(ticket.getTheatherName());
			seatVO.setRoomNumber(ticket.getRoomNumber());
			seatVO.setTime(ticket.getTime());
			seatVO.setReserved("y");
			seatVO.setDay(ticket.getDay());
			seatVO.setSeatNumber(seatsNumber.get(i)); // 좌석 번호 넣기
			//System.out.println(seatVO.toString());
			seatDao.setSeatRerserved(seatVO);
			
			//seatNumber만들기
			if((person-1) == i ) {
				seatNumberCheck += seatsNumber.get(i);
			} else {
				seatNumberCheck += (seatsNumber.get(i)+"/");
			}
		}
		// 티켓 도 만들어 줘야함.
		ticket.setSeatNumber(seatNumberCheck);
		// ticket insert
		ticketDao.insert(ticket); //티켓등록 
		dispose();
		TicketConfirmView ticketConfirmView = new TicketConfirmView(ticket);
	}


	// 모든 좌석 못누르게 하기.
	public void seatAlldisabledchecked() {
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 15; i++) {
				seats[j][i].setEnabled(false);
			}
		}
		ticketingButton.setEnabled(true);
	}

	// 모든 좌석 누를 수있게 변경.
	public void seatAllchecked() {
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 15; i++) {
				seats[j][i].setEnabled(true);
			}
		}
	}

	// 좌석에 리스너 달기.
	class SeatChangeListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				// System.out.println("checked" + e.getSource());

				// 좌석 번호 뽑아내기.
				JCheckBox tempCheckbox = (JCheckBox) e.getSource();
				String test = tempCheckbox.getText().trim();
				String[] testArr = test.split(",", 0);

				// 행
				String row = testArr[0];
				String col = testArr[1];

				seatsNumber.add(row + col); // 좌석 번호 vector에 넣기.
				seatsNumber.set(personCheck, row + col);
				seatDetails.get(personCheck).setText(row + col);
				personCheck++;
				if (person == personCheck) {
					System.out.println("모든 좌석 check 완료.");
					reCheckSeatButton.setEnabled(true);
					seatAlldisabledchecked();
				}
			} else {
				// check 지워질때 바꿔주는 것도 필요하다.
				if (personCheck != 0) {
					personCheck--;
				}
				// System.out.println("unchecked" + personCheck);
				seatDetails.get(personCheck).setText("좌석 " + (personCheck + 1));
			}
		}
	}

	public void setEvent() {
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 15; i++) {
				seats[j][i].addItemListener(new SeatChangeListener());
			}
		}
		reCheckSeatButton.addActionListener(this);
		ticketingButton.addActionListener(this);
	}

	public void buildGUI() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./favicon.PNG");
		setIconImage(img);

		setTitle("좌석 선택");
		setSize(818, 612);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);

		titlePanel.setBounds(0, 0, 812, 31);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);

		titleLabel.setBounds(609, 10, 100, 21);
		titlePanel.add(titleLabel);

		leftPanel.setBounds(608, 52, 192, 521);
		getContentPane().add(leftPanel);
		leftPanel.setLayout(null);

		// 이미지 판넬이 들어감.
		imgPanel = new ImgPanel();
		imgPanel.setBounds(0, 0, 180, 383);
		leftPanel.add(imgPanel);
		imgPanel.setLayout(null);

		leftBottomPanel.setBounds(0, 373, 180, 148);
		leftPanel.add(leftBottomPanel);
		leftBottomPanel.setLayout(null);

		lblNewLabel_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(12, 10, 63, 25);
		leftBottomPanel.add(lblNewLabel_1_1);

		lblNewLabel_3_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(12, 35, 63, 25);
		leftBottomPanel.add(lblNewLabel_3_1);

		lblNewLabel_5.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(12, 60, 63, 25);
		leftBottomPanel.add(lblNewLabel_5);

		lblNewLabel_7.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(12, 85, 63, 25);
		leftBottomPanel.add(lblNewLabel_7);

		ticketingButton.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		ticketingButton.setBounds(74, 113, 94, 25);
		leftBottomPanel.add(ticketingButton);

		theatherLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		theatherLabel.setBounds(87, 10, 85, 25);
		leftBottomPanel.add(theatherLabel);

		dayLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		dayLabel.setBounds(87, 39, 85, 25);
		leftBottomPanel.add(dayLabel);

		personLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		personLabel.setBounds(87, 64, 85, 25);
		leftBottomPanel.add(personLabel);

		costLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		costLabel.setBounds(87, 89, 85, 25);
		leftBottomPanel.add(costLabel);

		seatPanel.setBounds(10, 41, 598, 532);
		getContentPane().add(seatPanel);
		seatPanel.setLayout(null);
		panel_1.setBackground(Color.WHITE);

		panel_1.setBounds(465, 10, 127, 480);
		seatPanel.add(panel_1);
		panel_1.setLayout(null);
		checkSeatLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		checkSeatLabel.setBounds(11, 10, 103, 15);
		panel_1.add(checkSeatLabel);

		reCheckSeatButton.setBounds(17, 447, 97, 23);
		panel_1.add(reCheckSeatButton);

		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 10, 447, 480);
		seatPanel.add(panel_2);
		panel_2.setLayout(null);
		JLabel screenLabel = new JLabel("\t\t\t\t\t\t\t\tScreen");
		screenLabel.setBackground(Color.WHITE);
		screenLabel.setForeground(Color.BLACK);
		screenLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		screenLabel.setBounds(198, 10, 76, 15);
		panel_2.add(screenLabel);
		panel_2.setForeground(Color.WHITE);
		seatInfoLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		seatInfoLabel.setBounds(186, 427, 59, 21);
		
		panel_2.add(seatInfoLabel);
		seatInfoDetailLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		seatInfoDetailLabel.setBounds(186, 449, 68, 21);
		
		panel_2.add(seatInfoDetailLabel);
		reCheckSeatButton.setEnabled(false);
		ticketingButton.setEnabled(false);
		repaint();
		// 열
		for (int i = 0; i < 15; i++) {
			JLabel seatColLabel = new JLabel();
			char input = (char) ('A' + i);
			seatColLabel.setText(Character.toString(input));
			seatColLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
			seatColLabel.setBounds(25, (72 + (i * 20)), 22, 15);
			panel_2.add(seatColLabel);

		}

		// 행
		for (int i = 0; i < 15; i++) {
			JLabel seatRowLabel = new JLabel();
			String input = Integer.toString(i + 1);
			seatRowLabel.setText(input);
			seatRowLabel.setBounds(61 + (i * 22), 55, 22, 15);
			seatRowLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
			panel_2.add(seatRowLabel);
		}

		// 좌석 checkBox
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 15; i++) {
				JCheckBox chkBox = new JCheckBox("");
				chkBox.setBackground(Color.WHITE);
				chkBox.setForeground(Color.WHITE);
				chkBox.setBounds(61 + (i * 22), 72 + (j * 20), 22, 15);
				seats[j][i] = chkBox; // 좌석을 seats배열에 넣어줌. 2차원배열로 생성.
				char input = (char) (j + 65);
				chkBox.setText(input + "," + Integer.toString(i + 1)); // 좌석의 값들을 얻을 수 잇음.
				panel_2.add(chkBox);
			}
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
