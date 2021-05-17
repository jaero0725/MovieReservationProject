package client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.VO.MovieVO;
import client.util.CloseUtil;
import client.util.ConnUtil;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();

	private MovieDAO() {
	}

	public static MovieDAO getInstance() {
		return instance;
	}

	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public void connect() {
		try {
			con = ConnUtil.getConnection();
			// System.out.println("데이터 베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 무비 리스트 출력하기.
	public ArrayList<MovieVO> selectList() {
		ArrayList<MovieVO> list = new ArrayList<>();
		try {
			connect();
			String sql = "select * from movie";

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setMovieName(rs.getString("moviename"));
				movie.setMovieEnglishName(rs.getString("movieenglishname"));
				movie.setAgeLimit(rs.getString("agelimit"));
				movie.setTime(rs.getInt("time"));
				movie.setImgSrc(rs.getString("imgSrc"));
				list.add(movie);
			}
		} catch (Exception e) {
		} finally {
			CloseUtil.close(pstmt, con);
		}
		return list;
	}

	// 무비 하나 출력
	public MovieVO getMovie(String name) { // 영화 이름 을 받고 영화를 출력함.
		String sql = "select * from movie where moviename = ?";
		MovieVO movie = new MovieVO();
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				movie.setMovieName(rs.getString("moviename"));
				movie.setMovieEnglishName(rs.getString("movieenglishname"));
				movie.setAgeLimit(rs.getString("agelimit"));
				movie.setTime(rs.getInt("time"));
				movie.setImgSrc(rs.getString("imgSrc"));
				return movie;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt, con);
		}
		return null;
	}
}
