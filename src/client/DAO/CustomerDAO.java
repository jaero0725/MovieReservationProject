package client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.VO.CustomerVO;
import client.util.CloseUtil;
import client.util.ConnUtil;

//singleton 으로 구현
public class CustomerDAO {

	private static CustomerDAO instance = new CustomerDAO();

	private CustomerDAO() {
	}

	public static CustomerDAO getInstance() {
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

	// select - 회원 검색 - 로그인
	/*
	 * 1) id 확인 - 있는 id -> 비밀번호확인 1. 비밀번호가 틀림 -> return // 비밀번호를 확인하세요 2. 비밀번호가 맞음
	 * -> login 완료. - 연결 해줌. 9+
	 * 
	 * - 없는 id -> return // id를 확인하세요
	 */

	// 아이디로 찾아오기
	public CustomerVO selectById(String id) {
		String sql = "select * from customer where id = ? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			CustomerVO customer = new CustomerVO();
			// id 확인
			if (rs.next()) {
				customer.setId(id);
				customer.setPassword(rs.getString("name"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPhoneNumber(rs.getString("phonenumber"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	// 로그인
	// 성공 : 0 실패 : 비밀번호오류 : -1, 등록되지않은 아이디 : -2 | 데이터베이스 오류 : -3;
	public int login(String id, String password) {
		String sql = "select * from customer where id = ? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// id 확인
			if (rs.next()) {
				System.out.println("id가 확인됨. ");
				String pwd = rs.getString("password"); // 실제 비밀번호

				// 1) 비밀번호가 맞는 경우.
				if (password.equals(pwd)) {
					System.out.println("로그인 완료");
					return 0;
				}
				// 2) 비밀번호가 틀린 경우
				else {
					return -1;
				}
			}
			// 없는 id
			else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -3; // 데이터베이스 오류
	}
	
	//회원가입
	public boolean register(CustomerVO customer) {
		String name = customer.getName();
		String id = customer.getId();
		String password = customer.getPassword();
		String email = customer.getEmail();
		String phoneNumber = customer.getPhoneNumber();

		String sql = "insert into customer values (?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			pstmt.setString(5, phoneNumber);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt, con);
		}
		return false;
	}


	// update - 아이디 및 비밀번호 변경

	// delete - 회원 삭제

}
