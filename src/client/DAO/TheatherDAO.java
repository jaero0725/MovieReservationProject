package client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.VO.SeatVO;
import client.VO.TheatherVO;
import client.util.CloseUtil;
import client.util.ConnUtil;

public class TheatherDAO {
	private static TheatherDAO instance = new TheatherDAO();

	private TheatherDAO() {
	}

	public static TheatherDAO getInstance() {
		return instance;
	}

	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public void connect() {
		try {
			con = ConnUtil.getConnection();
			System.out.println("데이터 베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 영화관 리스트 출력하기.
	public ArrayList<TheatherVO> selectList() {
		ArrayList<TheatherVO> list = new ArrayList<>();
		try {
			connect();
			String sql = "select * from theather";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TheatherVO theather = new TheatherVO();
				theather.setTheathername(rs.getString("theathername"));
				list.add(theather);
			}
		} catch (Exception e) {
		} finally {
			CloseUtil.close(pstmt, con);
		}
		return list;
	}
	
	//좌석을 출력한다. reserved 되어 있는지 안되어잇는지 확인한다. 
	public SeatVO[][] getSeats(){
		String sql = "select * from Theather where thathername = ? and roomNumber = ? and time = ?";
		SeatVO[][] seats= new SeatVO[15][15];
		return seats;
	}
	
	//하나 출력 한다. 

	//예약된 좌석의 갯수를 출력한다. 영화관이름, 관이름, 영화 회차 를 받아서.
	public int getReservedSeat(String theatherName, String roomNumber, int time) {
		String sql = "select * from Theather where thathername = ? and roomNumber = ? and time = ?";
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theatherName);
			pstmt.setString(2, roomNumber);
			pstmt.setInt(3, time);
			if(rs.next()) {
				return rs.getInt("reservedSeat"); //예약된 좌석의 갯수 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt, con);
		}
		return 0;
	}


}
