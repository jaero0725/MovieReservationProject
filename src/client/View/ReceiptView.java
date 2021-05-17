package client.View;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import client.DAO.MovieDAO;
import client.VO.MovieVO;
import client.VO.TicketVO;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ReceiptView extends JFrame {
	private static final long serialVersionUID = 1L;
	private TicketVO ticket;
	private MovieDAO movieDao = MovieDAO.getInstance();
	private MovieVO movie;
	private ImgPanel imgPanel;
	
	class ImgPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private ImageIcon icon = new ImageIcon("./logo.GIF");
		private Image img = icon.getImage(); // ÀÌ¹ÌÁö °´Ã¼

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension d = getSize();
			g.drawImage(img, 0, 0, d.width, d.height, null);
		}
	}

	public ReceiptView(TicketVO ticket) {
		this.ticket = ticket;
		buildGUI();
	}
	public void buildGUI() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./favicon.PNG");
		setIconImage(img);
		setTitle("¿µ¼öÁõ");
		setSize(344, 612);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // È­¸é °¡¿îµ¥ Ãâ·Â½ÃÅ°±â
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 338, 583);
		getContentPane().add(panel);
		panel.setLayout(null);

		imgPanel = new ImgPanel();
		imgPanel.setBounds(0, 0, 338, 84);
		panel.add(imgPanel);

		JLabel lblNewLabel = new JLabel("\uC601\uD654\uC785\uC7A5\uAD8C");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 28));
		lblNewLabel.setBounds(94, 100, 145, 45);
		panel.add(lblNewLabel);

		JLabel label1 = new JLabel("\uC601\uC218\uC99D \uACB8\uC6A9");
		label1.setFont(new Font("±¼¸²", Font.BOLD, 14));
		label1.setBounds(124, 137, 86, 24);
		panel.add(label1);

		// ¿¹¾àÇÑ ½Ã°£ ÀúÀåÇÏ±â
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		String t= f.format(c.getTime()); // ÇöÀç³¯Â¥¸¦ Àü´Þ.
		
		JLabel timeLabel = new JLabel(t);
		timeLabel.setBounds(12, 193, 118, 15);
		panel.add(timeLabel);
		
		JLabel label2 = new JLabel("\uC601\uD654\uAD00/POS: 2021-212");
		label2.setBounds(193, 193, 133, 15);
		panel.add(label2);
		
		JLabel lineLabel = new JLabel("===========================================");
		lineLabel.setBounds(12, 211, 314, 24);
		panel.add(lineLabel);
		
		JLabel lineLabel_1 = new JLabel("===========================================");
		lineLabel_1.setBounds(12, 502, 314, 24);
		panel.add(lineLabel_1);
		
		String movieName = ticket.getMovieName();
		String theatherName = ticket.getTheatherName();
		String roomNumber = ticket.getRoomNumber();
		String seatNumber = ticket.getSeatNumber();
		String day = ticket.getDay();
		String time = Integer.toString(ticket.getTime());
		String person =  Integer.toString(ticket.getPerson());
		String  cost =  Integer.toString(ticket.getCost());
		
		movie = movieDao.getMovie(movieName);
		JLabel titleLabel = new JLabel(movieName);
		titleLabel.setFont(new Font("±¼¸²", Font.BOLD, 26));
		titleLabel.setBounds(12, 238, 314, 45);
		panel.add(titleLabel);
		
		String movieEnglishName = movie.getMovieEnglishName();
		String movieLimit = movie.getAgeLimit();
		JLabel englishNameLabel = new JLabel(movieEnglishName + "   " + movieLimit);
		englishNameLabel.setFont(new Font("±¼¸²", Font.BOLD, 11));
		englishNameLabel.setBounds(12, 278, 314, 14);
		panel.add(englishNameLabel);
		
		JLabel dayLabel = new JLabel(day +" " + time+"È¸" );
		dayLabel.setFont(new Font("±¼¸²", Font.BOLD, 14));
		dayLabel.setBounds(12, 291, 314, 24);
		panel.add(dayLabel);
		
		String [] seats = seatNumber.split("/");
		int seatSize = seats.length;
		String seatStr = "";
		for(int i = 0 ; i< seatSize; i++) {
			seatStr += seats[i]+" "; 
		}
		JLabel roomLabel = new JLabel(roomNumber+"(1Ãþ)   "+ seatStr+"    ÃÑ(" +person+"¸í)");
		roomLabel.setFont(new Font("±¼¸²", Font.BOLD, 14));
		roomLabel.setBounds(12, 313, 303, 24);
		panel.add(roomLabel);
		
		JLabel lineLabel_2 = new JLabel("===========================================");
		lineLabel_2.setBounds(12, 336, 314, 24);
		panel.add(lineLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uC81C\uD488\uBA85");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(12, 357, 57, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uB2E8\uAC00");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(136, 357, 57, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC218\uB7C9");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(193, 357, 57, 15);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uAE08\uC561");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(258, 357, 57, 15);
		panel.add(lblNewLabel_2_1_1);
		
		JLabel lineLabel_3 = new JLabel("--------------------------------------------------");
		lineLabel_3.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		lineLabel_3.setBounds(12, 370, 314, 24);
		panel.add(lineLabel_3);
		
		JLabel lblNewLabel_3 = new JLabel("\uC601\uD654\uD2F0\uCF13");
		lblNewLabel_3.setBounds(12, 391, 57, 15);
		panel.add(lblNewLabel_3);
		
		JLabel countLabel = new JLabel(person);
		countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		countLabel.setBounds(203, 391, 47, 15);
		panel.add(countLabel);
		
		JLabel costLabel = new JLabel(cost);
		costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		costLabel.setBounds(258, 391, 57, 15);
		panel.add(costLabel);
		
		JLabel lineLabel_3_1 = new JLabel("--------------------------------------------------");
		lineLabel_3_1.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		lineLabel_3_1.setBounds(12, 404, 314, 24);
		panel.add(lineLabel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("\uD560     \uC778");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(12, 427, 57, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("\uD569     \uACC4");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(12, 447, 57, 15);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(258, 427, 57, 15);
		panel.add(lblNewLabel_5);
		
		JLabel costLabel_1 = new JLabel(cost);
		costLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		costLabel_1.setBounds(258, 447, 57, 15);
		panel.add(costLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("[\uC601\uD654\uBC1C\uC804\uAE30\uAE08 3%, \uBD80\uAC00\uC138, \uC774\uC6A9\uB8CC\uD3EC\uD568]");
		lblNewLabel_6.setBounds(55, 488, 232, 15);
		panel.add(lblNewLabel_6);
		
		int total =ticket.getCost();
		int vat = (int) (total*0.03);
		
		JLabel vatLabel = new JLabel("(VAT:"+Integer.toString(vat)+")");
		vatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		vatLabel.setBounds(182, 463, 133, 15);
		panel.add(vatLabel);
		
		JLabel lblNewLabel_7 = new JLabel("\uACB0\uC81C \uC218\uB2E8 \uBCC0\uACBD \uBC0F \uAD50\uD658,  \uD658\uBD88\uC740 \uAD6C\uB9E4\uD55C \uB9E4\uC7A5\uC5D0\uC11C  \r\n");
		lblNewLabel_7.setFont(new Font("±¼¸²", Font.BOLD, 13));
		lblNewLabel_7.setBounds(12, 524, 314, 24);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("\uAD6C\uB9E4 \uB2F9\uC77C \uB0B4 \uAC00\uB2A5\uD558\uBA70, \uC601\uC218\uC99D \uBBF8\uC9C0\uCC38\uC2DC \uBD88\uAC00\uD569\uB2C8\uB2E4.\r\n");
		lblNewLabel_7_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setFont(new Font("±¼¸²", Font.BOLD, 13));
		lblNewLabel_7_1.setBounds(12, 545, 314, 28);
		panel.add(lblNewLabel_7_1);
		
		JLabel theatherLabel = new JLabel("ÄÉÀÌÁö½Ã³×¸¶ "+ theatherName);
		theatherLabel.setBounds(12, 175, 160, 15);
		panel.add(theatherLabel);
		
		JLabel theatherLabel_1 = new JLabel("02-3673-5300");
		theatherLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		theatherLabel_1.setBounds(182, 175, 133, 15);
		panel.add(theatherLabel_1);
	}
}
