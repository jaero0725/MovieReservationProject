package client.VO;

import java.util.Date;

public class SeatVO {
	private String theathername; // 영화관에 대한 이름.
	private String roomNumber;// 관에 대한 정보
	private String seatNumber;// 좌석번호
	private int time; // 회차수
	private String reserved; // 예약 되어있는지 //날짜가 없는디?
	private String day; //날짜 

	public SeatVO() {
		super();
	}

	@Override
	public String toString() {
		String text =
				theathername +" " + roomNumber + " "+ seatNumber + " " + time + " " + reserved + " " +day;
		return text;
	}
	
	public SeatVO(String theathername, String roomNumber, String seatNumber, int time, String reserved, String day) {
		super();
		this.theathername = theathername;
		this.roomNumber = roomNumber;
		this.seatNumber = seatNumber;
		this.time = time;
		this.reserved = reserved;
		this.day = day;
	}

	public String getTheathername() {
		return theathername;
	}

	public void setTheathername(String theathername) {
		this.theathername = theathername;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	

}
