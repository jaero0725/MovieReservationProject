package client.VO;

import java.awt.Image;

public class MovieVO {
	private String movieName;
	private String movieEnglishName;
	private String ageLimit;
	private int time;
	private String imgSrc;
	
	public MovieVO() {}

	public MovieVO(String movieName, String movieEnglishName, String ageLimit, int time, String imgSrc) {
		super();
		this.movieName = movieName;
		this.movieEnglishName = movieEnglishName;
		this.ageLimit = ageLimit;
		this.time = time;
		this.imgSrc = imgSrc;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieEnglishName() {
		return movieEnglishName;
	}

	public void setMovieEnglishName(String movieEnglishName) {
		this.movieEnglishName = movieEnglishName;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}
