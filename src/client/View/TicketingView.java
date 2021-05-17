package client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.DAO.MovieDAO;
import client.VO.CustomerVO;
import client.VO.MovieVO;
import client.VO.TheatherVO;
import client.VO.TicketVO;
import client.View.SeatView.ImgPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JList;
import com.toedter.calendar.JCalendar;
import javax.swing.JRadioButton;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JRadioButtonMenuItem;

public class TicketingView extends JFrame implements ActionListener, ListSelectionListener, ItemListener{
	private static final long serialVersionUID = 1L;
	
	private MovieDAO movieDao = MovieDAO.getInstance();
	
	private CustomerVO customer;
	private TicketVO ticket = new TicketVO();
	private MovieVO movie = new MovieVO();
	private TheatherVO theather = new TheatherVO();
	private Calendar today = Calendar.getInstance();
	
	//좌석 선택
	private SeatView seatView; 
	
	// GUI
	private JPanel mainPanel = new JPanel();
	private JPanel mNorthPanel = new JPanel();
	private JPanel mCenterPanel = new JPanel();
	private JPanel mEastPanel = new JPanel();
	
	//포스터 동적으로 넣기. 
	private ImgPanel imgPanel;
	private String movieImgSrc;
	class ImgPanel extends JPanel {
		private ImageIcon icon;
		private Image img; // 이미지 객체

		public ImgPanel() {
			//이미지 받아와야함. 
			movie = movieDao.getMovie(ticket.getMovieName());
			movieImgSrc = movie.getImgSrc();
			//System.out.println("TicetingView"+movieImgSrc);
			icon = new ImageIcon("./" + movieImgSrc);
			img = icon.getImage(); // 이미지 객체
		}
		
		public void paint(Graphics g) {
			//System.out.println("Paint");
			super.paintComponent(g);
			Dimension d = getSize();
			g.drawImage(img, 0, 0, d.width, d.height, null);
		}
	}
	
	private JPanel mCenterMoviePanel = new JPanel();
	private JPanel mEastSouthPanel = new JPanel();
	private JPanel mEastSouthCenterPanel = new JPanel();
	private JPanel mCenterTheatherPanel = new JPanel();
	private JPanel mCenterCalendarPanel = new JPanel();
	private JPanel mCenerButtomPanel = new JPanel();
	private JPanel roomPanel = new JPanel();
	
	private JLabel theatherLabel = new JLabel("-");
	private JLabel dayLabel = new JLabel("-");
	private JLabel personLabel = new JLabel("-");
	private JLabel costLabel = new JLabel("-");
	private JLabel choiceMovieLabel = new JLabel("영화 선택");
	
	
	
	private JLabel choiceTheaterLabel = new JLabel("극장 선택");
	private JLabel choiceCalendarLabel = new JLabel("날짜 선택");

	private JCalendar calendar = new JCalendar();
	private ButtonGroup adultCount = new ButtonGroup();
	private ButtonGroup teenagerCount = new ButtonGroup();

	private JRadioButton one = new JRadioButton("1");
	private JRadioButton two = new JRadioButton("2");
	private JRadioButton three = new JRadioButton("3");
	private JRadioButton four = new JRadioButton("4");
	private JRadioButton five = new JRadioButton("5");
	private JRadioButton zero = new JRadioButton("0");
	
	private JRadioButton oneT = new JRadioButton("1");
	private JRadioButton twoT = new JRadioButton("2");
	private JRadioButton threeT = new JRadioButton("3");
	private JRadioButton fourT = new JRadioButton("4");
	private JRadioButton fiveT = new JRadioButton("5");
	private JRadioButton zeroT = new JRadioButton("0");
	
	private JButton ticketingButton = new JButton("예매하기");
	
	//관 정보
	private JLabel roomNumber1 = new JLabel("");
	private ButtonGroup timeGroup= new ButtonGroup();
	private JRadioButton timeButton1 = new JRadioButton("9:00~");
	private JRadioButton timeButton2 = new JRadioButton("12:00~");
	private JRadioButton timeButton3 = new JRadioButton("15:00~");
	private JRadioButton timeButton4 = new JRadioButton("18:00~");
	private JRadioButton timeButton5 = new JRadioButton("21:00~");
	//여기 관련 내용을 Database에서 꺼내게 바꾼다. 
	
	private String[] movies =  { "소울", "캐롤", "북스마트", "인셉션" };
	private String[] theathers = { "종로점", "건대입구점", "홍대점", "강남점" };
	private JList movielist = new JList(movies);
	private JList theatherlist = new JList(theathers);
	

	//check 관련
	private String movieName ="";
	private String theatherName ="";
	private String checkDay; 
	private int roomNumber;  //관정보  
	private int time; //회차 정보 
	
	private static final int ADULTCOST = 10000;
	private static final int TEENAGECOST = 8000;
	
	// 가격
	private int totalCost = 0;
	private int adultCost = 0;
	private int teenagerCost = 0;
	private int person = 0;
	
	public void setEvent() {
		movielist.addListSelectionListener(this);
		theatherlist.addListSelectionListener(this);
		ticketingButton.addActionListener(this);
		one.addItemListener(this);
		two.addItemListener(this);
		three.addItemListener(this);
		four.addItemListener(this);
		five.addItemListener(this);
		oneT.addItemListener(this);
		twoT.addItemListener(this);
		threeT.addItemListener(this);
		fourT.addItemListener(this);
		fiveT.addItemListener(this);
		
		timeButton1.addItemListener(this);
		timeButton2.addItemListener(this);
		timeButton3.addItemListener(this);
		timeButton4.addItemListener(this);
		timeButton5.addItemListener(this);
	}

	public void roomSet() {
      //4관까지 있음. 
	  switch(movie.getMovieName()) {
	  	case "소울":
	  		ticket.setRoomNumber("1관");
	  		roomNumber1.setText("1관");
	  		timeButton1.setText("9:00 ~ 10:47");
	  		timeButton2.setText("12:00 ~ 13:47");
	  		timeButton3.setText("15:00 ~ 16:47");
	  		timeButton4.setText("18:00 ~ 19:47");
	  		timeButton5.setText("21:00 ~ 22:47");
			break;
		case "캐롤":
			ticket.setRoomNumber("2관");
			roomNumber1.setText("2관");
			timeButton1.setText("9:00 ~ 10:58");
	  		timeButton2.setText("12:00 ~ 13:58");
	  		timeButton3.setText("15:00 ~ 16:58");
	  		timeButton4.setText("18:00 ~ 19:58");
	  		timeButton5.setText("21:00 ~ 22:58");
			break;
		case "북스마트":
			ticket.setRoomNumber("3관");
			roomNumber1.setText("3관");
			timeButton1.setText("9:00 ~ 10:52");
	  		timeButton2.setText("12:00 ~ 13:52");
	  		timeButton3.setText("15:00 ~ 16:52");
	  		timeButton4.setText("18:00 ~ 19:52");
	  		timeButton5.setText("21:00 ~ 22:52");
			break;
		case "인셉션":
			ticket.setRoomNumber("4관");
			roomNumber1.setText("4관");
	  		timeButton1.setText("9:00 ~ 11:02");
	  		timeButton2.setText("12:00 ~ 14:02");
	  		timeButton3.setText("15:00 ~ 17:02");
	  		timeButton4.setText("18:00 ~ 20:02");
	  		timeButton5.setText("21:00 ~ 23:02");
			break;
		}
	  }
	
	//정보를 다 입력했는지 판단하고, 버튼은 enabled 해준다. 
	public void check() {
		if(ticket.getPerson() != 0 && ticket.getRoomNumber() != null && 
		   ticket.getTheatherName()  != null && ticket.getMovieName() != null 
		   && ticket.getDay() != null) {
			ticketingButton.setEnabled(true);
		} 
		if(ticket.getPerson() == 0) {
			ticketingButton.setEnabled(false);
		}
	}
	public TicketingView(CustomerVO customer) {
		this.customer = customer;
		changeTicketInfo();
		buildGUI();
		setEvent();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ticketingButton) {
			seatView = new SeatView(ticket); 
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//adult
		if(one.isSelected()) {
			adultCost = ADULTCOST;
			person += 1;
		} else if(two.isSelected()) {
			adultCost = ADULTCOST *2;
			person += 2;
		} else if(three.isSelected()) {
			adultCost = ADULTCOST *3;
			person += 3;
		} else if(four.isSelected()) {
			adultCost = ADULTCOST *4;
			person += 4;
		} else if(five.isSelected()) {
			adultCost = ADULTCOST *5;
			person += 5;
		} else if(zero.isSelected()) {
			adultCost = 0;
			person += 0;
		}
		
		//teenage
		if(oneT.isSelected()) {
			teenagerCost = TEENAGECOST;
			person += 1;
		} else if(twoT.isSelected()) {
			teenagerCost = TEENAGECOST *2;
			person += 2;
		} else if(threeT.isSelected()) {
			teenagerCost = TEENAGECOST *3;
			person += 3;
		} else if(fourT.isSelected()) {
			teenagerCost = TEENAGECOST *4;
			person += 4;
		} else if(fiveT.isSelected()) {
			teenagerCost = TEENAGECOST*5;
			person += 5;
		}  else if(zeroT.isSelected()) {
			teenagerCost = 0;
			person += 0;
		}
		
		//회차
		if(timeButton1.isSelected()) {
			time = 1; 
		}else if(timeButton2.isSelected()) {
			time = 2;
		}else if(timeButton3.isSelected()) {
			time = 3;
		}else if(timeButton4.isSelected()) {
			time = 4;
		}else if(timeButton5.isSelected()) {
			time = 5;
		}   
		
		totalCost = adultCost + teenagerCost;
		String totalCostStr = Integer.toString(totalCost);
		String personStr = Integer.toString(person);
		
		costLabel.setText(totalCostStr +" 원");
		personLabel.setText(personStr + " 명");
		//티켓 정보에 추가.
		ticket.setCost(totalCost);//총가격 
		ticket.setPerson(person);//인원수
		ticket.setTime(time); //회차
		
		//여기서 저장 을 해줘야 됨
		adultCost = 0;
		teenagerCost = 0;
		totalCost = 0;
		person = 0;
		
		check();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (movielist.getSelectedValue()!=null) { //getSelectedValue() : 선택된 항목(Object 타입) 반환
			movieName = ((String) movielist.getSelectedValue()).trim();
			ticket.setMovieName(movieName);
			movie = movieDao.getMovie(movieName); //영화 가져오기 .
			ticket.setMovieName(movie.getMovieName()); //티켓에 등록.  
			//이미지 동적으로 변경해줌. - 메모리상 비효율적.
			imgPanel = new ImgPanel();
			imgPanel.setBounds(0, 0, 180, 383);
			mEastPanel.add(imgPanel);
		} 
		
		if (theatherlist.getSelectedValue()!=null) { //getSelectedValue() : 선택된 항목(Object 타입) 반환
			theatherName = (String)theatherlist.getSelectedValue();
			theatherLabel.setText(theatherName);
			ticket.setTheatherName(theatherName);
			roomSet();
		} 
		
		imgPanel.revalidate();
		imgPanel.repaint();
		check();
	}
	
	public void changeTicketInfo() {
		ticket.setCustomerId(customer.getId());
		ticket.setCustomerName(customer.getName());
	}
	
	
	public void buildGUI() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./favicon.PNG");
		setIconImage(img);
		
		setTitle("티켓 예메");
		setSize(818, 612);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);
		mainPanel.setBounds(13, 0, 799, 583);

		// main
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mNorthPanel.setBounds(0, 0, 799, 28);

		mainPanel.add(mNorthPanel);
		mNorthPanel.setLayout(null);
		JLabel nameLabel = new JLabel(
				"                                                                                                   "
						+ customer.getName() + "\uB2D8                 ");
		nameLabel.setBounds(5, 5, 789, 18);
		nameLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		nameLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		mNorthPanel.add(nameLabel);
		
		
		mEastPanel.setBounds(598, 38, 189, 545);
		mainPanel.add(mEastPanel);
		
		mEastPanel.setLayout(null);
		mEastSouthPanel.setBounds(0, 390, 178, 155);
		
		mEastPanel.add(mEastSouthPanel);
		mEastSouthPanel.setLayout(new BorderLayout(0, 0));
		JLabel blank_5 = new JLabel(" ");
		JLabel blank_6 = new JLabel(" ");
		JLabel blank_7 = new JLabel(" ");
		JLabel blank_8 = new JLabel(" ");
		mEastSouthPanel.add(blank_5, BorderLayout.NORTH);
		mEastSouthPanel.add(blank_6, BorderLayout.SOUTH);
		mEastSouthPanel.add(blank_7, BorderLayout.WEST);
		mEastSouthPanel.add(blank_8, BorderLayout.EAST);

		mEastSouthPanel.add(mEastSouthCenterPanel, BorderLayout.CENTER);
		mEastSouthCenterPanel.setLayout(new GridLayout(5, 2));

		JLabel lblNewLabel_1_1 = new JLabel("\uADF9\uC7A5 :");
		JLabel lblNewLabel_5 = new JLabel("\uC778\uC6D0 :");
		JLabel lblNewLabel_7 = new JLabel("금액 :");
		JLabel lblNewLabel_9 = new JLabel("");
		

		lblNewLabel_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		mEastSouthCenterPanel.add(lblNewLabel_1_1);
		theatherLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		mEastSouthCenterPanel.add(theatherLabel);
		JLabel lblNewLabel_3_1 = new JLabel("\uB0A0\uC9DC :");
		lblNewLabel_3_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		mEastSouthCenterPanel.add(lblNewLabel_3_1);
		dayLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		mEastSouthCenterPanel.add(dayLabel);
		lblNewLabel_5.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		mEastSouthCenterPanel.add(lblNewLabel_5);
		personLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		mEastSouthCenterPanel.add(personLabel);
		lblNewLabel_7.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		mEastSouthCenterPanel.add(lblNewLabel_7);
		costLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		mEastSouthCenterPanel.add(costLabel);
		mEastSouthCenterPanel.add(lblNewLabel_9);
		ticketingButton.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		mEastSouthCenterPanel.add(ticketingButton);
		mCenterPanel.setBounds(0, 38, 586, 545);

		mainPanel.add(mCenterPanel);
		mCenterPanel.setLayout(null);
		mCenterMoviePanel.setBounds(12, 10, 158, 325);
		// 영화 선택
		mCenterMoviePanel.setLayout(null);
		mCenterPanel.add(mCenterMoviePanel);
		
		
		movielist.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));

		theatherlist.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		movielist.setBounds(12, 35, 122, 281);
		theatherlist.setBounds(12, 35, 122, 280);
		mCenterMoviePanel.add(movielist);

		choiceMovieLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		choiceMovieLabel.setBounds(37, 10, 76, 15);
		mCenterMoviePanel.add(choiceMovieLabel);
		mCenterTheatherPanel.setBounds(182, 10, 158, 325);
		mCenterTheatherPanel.setLayout(null);
		mCenterTheatherPanel.add(theatherlist);

		mCenterPanel.add(mCenterTheatherPanel);
		choiceTheaterLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		choiceTheaterLabel.setBounds(40, 10, 70, 15);
		mCenterTheatherPanel.add(choiceTheaterLabel);
		mCenterCalendarPanel.setBounds(352, 10, 221, 325);
		mCenterCalendarPanel.setLayout(null);
		mCenterPanel.add(mCenterCalendarPanel);
		choiceCalendarLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		choiceCalendarLabel.setBounds(81, 10, 76, 15);
		mCenterCalendarPanel.add(choiceCalendarLabel);
		calendar.setBounds(12, 35, 185, 280);
		mCenterCalendarPanel.add(calendar);
		mCenerButtomPanel.setBounds(0, 345, 573, 199);
		mCenerButtomPanel.setLayout(null);
		mCenterPanel.add(mCenerButtomPanel);

		adultCount.add(one);
		adultCount.add(two);
		adultCount.add(three);
		adultCount.add(four);
		adultCount.add(five);
		adultCount.add(zero);
		teenagerCount.add(oneT);
		teenagerCount.add(twoT);
		teenagerCount.add(threeT);
		teenagerCount.add(fourT);
		teenagerCount.add(fiveT);
		teenagerCount.add(zeroT);
		one.setFont(new Font("Dialog", Font.BOLD, 9));
		one.setBounds(342, 72, 31, 29);
		mCenerButtomPanel.add(one);
		two.setFont(new Font("Dialog", Font.BOLD, 9));
		two.setBounds(377, 72, 31, 29);
		mCenerButtomPanel.add(two);
		three.setFont(new Font("Dialog", Font.BOLD, 9));
		three.setBounds(412, 72, 31, 29);
		mCenerButtomPanel.add(three);
		four.setFont(new Font("Dialog", Font.BOLD, 9));
		four.setBounds(447, 72, 31, 29);
		mCenerButtomPanel.add(four);
		five.setFont(new Font("Dialog", Font.BOLD, 9));
		five.setBounds(482, 72, 31, 29);
		mCenerButtomPanel.add(five);
		zero.setFont(new Font("Dialog", Font.BOLD, 9));
		zero.setBounds(517, 72, 31, 29);
		mCenerButtomPanel.add(zero);
		
		oneT.setFont(new Font("Dialog", Font.BOLD, 9));
		oneT.setBounds(342, 137, 31, 29);
		mCenerButtomPanel.add(oneT);
		twoT.setFont(new Font("굴림", Font.BOLD, 9));
		twoT.setBounds(377, 137, 31, 29);
		mCenerButtomPanel.add(twoT);
		threeT.setFont(new Font("굴림", Font.BOLD, 9));
		threeT.setBounds(412, 137, 31, 29);
		mCenerButtomPanel.add(threeT);
		fourT.setFont(new Font("굴림", Font.BOLD, 9));
		fourT.setBounds(447, 137, 31, 29);
		mCenerButtomPanel.add(fourT);
		fiveT.setFont(new Font("굴림", Font.BOLD, 9));
		fiveT.setBounds(482, 137, 31, 29);
		mCenerButtomPanel.add(fiveT);
		zeroT.setFont(new Font("Dialog", Font.BOLD, 9));
		zeroT.setBounds(517, 137, 31, 29);
		mCenerButtomPanel.add(zeroT);

		JLabel timeTableLabel = new JLabel("\uC0C1\uC601\uC2DC\uAC04\uD45C");
		timeTableLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		timeTableLabel.setBounds(12, 10, 108, 23);
		mCenerButtomPanel.add(timeTableLabel);

		JLabel headCountLabel = new JLabel("\uC778\uC6D0\uC120\uD0DD");
		headCountLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		headCountLabel.setBounds(342, 10, 121, 29);
		mCenerButtomPanel.add(headCountLabel);

		JLabel adultLabel = new JLabel("성인 (10,000 원)");
		adultLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		adultLabel.setBounds(342, 54, 121, 15);
		mCenerButtomPanel.add(adultLabel);

		JLabel teenagerLabel = new JLabel("청소년 (8,000 원)");
		teenagerLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		teenagerLabel.setBounds(342, 116, 121, 15);
		mCenerButtomPanel.add(teenagerLabel);
		
		
		//관 gui
		roomPanel.setBounds(12, 30, 322, 159);
		mCenerButtomPanel.add(roomPanel);
		roomPanel.setLayout(null);
		
		roomNumber1.setBounds(12, 10, 57, 15);
		roomPanel.add(roomNumber1);
		timeButton1.setFont(new Font("굴림", Font.PLAIN, 10));
		
		timeButton1.setBounds(8, 36, 99, 23);
		roomPanel.add(timeButton1);
		timeButton2.setFont(new Font("굴림", Font.PLAIN, 10));
		timeButton2.setBounds(113, 36, 99, 23);
		roomPanel.add(timeButton2);
		timeButton3.setFont(new Font("굴림", Font.PLAIN, 10));
		
		timeButton3.setBounds(215, 36, 99, 23);
		roomPanel.add(timeButton3);
		timeButton4.setFont(new Font("굴림", Font.PLAIN, 10));
		
		timeButton4.setBounds(8, 61, 99, 23);
		roomPanel.add(timeButton4);
		timeButton5.setFont(new Font("굴림", Font.PLAIN, 10));
		
		timeButton5.setBounds(113, 61, 99, 23);
		roomPanel.add(timeButton5);
		
		timeGroup.add(timeButton1);
		timeGroup.add(timeButton2);
		timeGroup.add(timeButton3);
		timeGroup.add(timeButton4);
		timeGroup.add(timeButton5);
		
		//end 관 
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		dayLabel.setText(year + "년 " + month +"월 " +day +"일");
		ticket.setDay(year + "년 " + month +"월 " +day +"일");
		ticketingButton.setEnabled(false);
		//ticketingButton.setEnabled(false); //모두 check 해야 enabled되게 설정.
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				final Calendar c = (Calendar) e.getNewValue();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				//System.out.println(year + "," + month +"," +day);
				checkDay = Integer.toString(year) + "년 " + Integer.toString(month) +"월 " + Integer.toString(day) +"일";
				dayLabel.setText(checkDay);
				ticket.setDay(checkDay); 
			}
		});
	}
}
