package client.VO;

import java.sql.Timestamp;

public class TicketVO {
	private String customerName; // 고객 이름 -ticketingView 0
	private String customerId; // 고객 아이디 -ticketingView 0
	private String seatNumber; // 좌석 이름 -seatView //A1,A2 .. 이런식으로 들어감.
	private String theatherName; // 극장 이름 -ticketingView 0
	private String roomNumber; // 관 번호 -ticketingView 0
	private String movieName; // 영화 이름 -ticketingView 0
	private String day; // 영화 예매 날짜. 년 월 일 -- > 예약한 날짜만 나옴. -ticketingView 0
	private int time; // 영화 회차 -ticketingView 0
	private String reserveDate; // 예매한 날짜 및 시간 -seatView 0

	// 추가
	private int cost; // 티켓 가격 -ticketingView 0
	private int person; // 인원 수 -ticketingView 0

	public TicketVO() {
		super();
	}

	public TicketVO(String customerName, String customerId, String seatNumber, String theatherName, String roomNumber,
			String movieName, String day, int time, String reserveDate, int cost, int person) {
		super();
		this.customerName = customerName;
		this.customerId = customerId;
		this.seatNumber = seatNumber;
		this.theatherName = theatherName;
		this.roomNumber = roomNumber;
		this.movieName = movieName;
		this.day = day;
		this.time = time;
		this.reserveDate = reserveDate;
		this.cost = cost;
		this.person = person;
	}
	@Override
	public String toString() {
		String text =
				customerName+" " + customerId + " "+ seatNumber + " " + theatherName + " " +roomNumber+ " " +movieName
				+ " " + day
				+ " " +time
				+ " " + reserveDate
				+ " " +cost +" " + person;
		return text;
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getTheatherName() {
		return theatherName;
	}

	public void setTheatherName(String theatherName) {
		this.theatherName = theatherName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

}
