package client.View;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.DAO.CustomerDAO;
import client.VO.CustomerVO;

import javax.swing.JButton;
import javax.swing.JDialog;


public class MainView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TopPanel topPanel = new TopPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();

	private JTextField idTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	private JLabel registerLabel = new JLabel("회원가입");
	private JLabel idfindLabel = new JLabel("아이디찾기");
	private JLabel findPwdLabel = new JLabel("비밀번호찾기");
	private JButton loginButton = new JButton("로그인");
	private String id = "";
	private String password = "";

	private CustomerDAO customerDao = CustomerDAO.getInstance();
	private CustomerVO customer = new CustomerVO();

	private TicketingView ticketingView;

	private JDialog dialog1 = new JDialog(this, "로그인 성공", true);
	private JDialog dialog2 = new JDialog(this, "비밀번호 불일치", true);
	private JDialog dialog3 = new JDialog(this, "등록되지 않은 아이디", true);


	public void setEvent() {
		loginButton.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			System.out.println("로그인 시도");
			id = idTextField.getText();
			password = passwordTextField.getText();
			System.out.println(id + ", " + password);
			login(id, password);
			clear();
		}

		if (e.getSource() == passwordTextField) {
			System.out.println("비번");
		}
	}

	// 로그인
	// 성공 : 0 실패 : 비밀번호오류 : -1, 등록되지않은 아이디 : -2 | 데이터베이스 오류 : -3;
	public void login(String id, String password) {
		customerDao.connect();
		int checkIndex = customerDao.login(id, password);
		switch (checkIndex) {
		case 0: // 성공
			loginSuccess();
			break;
		case -1:
			passwordError();
			break;
		case -2:
			loginFail();
			break;
		default:
			System.out.println("시스템 오류입니다.");
			break;
		}
	}

	// 로그인 성공
	private void loginSuccess() {
		dialog1.setVisible(true);
		customer = customerDao.selectById(id);
		dispose();
		ticketingView = new TicketingView(customer);
		
	}

	// 비밀번호 오류
	private void passwordError() {
		dialog2.setVisible(true);
	}

	// 등록되지 않은 아이디
	private void loginFail() {
		dialog3.setVisible(true);
	}

	// 다이알로그 구성
	private void setDialog() {
		JLabel text1 = new JLabel("로그인  성공");
		JLabel text2 = new JLabel("비밀번호가 일치하지 않습니다.");
		JLabel text3 = new JLabel("등록되지 않은 아이디 입니다");
		
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		text2.setHorizontalAlignment(SwingConstants.CENTER);
		text3.setHorizontalAlignment(SwingConstants.CENTER);
		
		Point pt = getLocation();
		Dimension my = getSize();
		Dimension dsize = dialog1.getSize();
		int x = (int) pt.getX() + my.width / 2 - dsize.width / 2;
		int y = (int) pt.getY() + my.height / 2 - dsize.height / 2;
		dialog1.setLocationRelativeTo(this);
		dialog2.setLocationRelativeTo(this);
		dialog3.setLocationRelativeTo(this);
		
		//dialog1.setLocation(x, y);
		//dialog2.setLocation(x, y);
		//dialog3.setLocation(x, y);

		dialog1.getContentPane().setLayout(new BorderLayout(3, 3));
		text1.setFont(new Font("Serif", Font.BOLD, 14));
		dialog1.getContentPane().add(text1, BorderLayout.CENTER);
		
		dialog1.setResizable(false);
		dialog1.setSize(250, 180);

		text2.setFont(new Font("Serif", Font.BOLD, 14));
		dialog2.getContentPane().add(text2, BorderLayout.CENTER);
		dialog2.setResizable(false);
		dialog2.setSize(250, 180);

		text3.setFont(new Font("Serif", Font.BOLD, 14));
		dialog3.getContentPane().add(text3, BorderLayout.CENTER);
		dialog3.setResizable(false);
		dialog3.setSize(250, 180);
	}

	public MainView() {
		super("KG CINEMA");
		buildGUI();
		setEvent();
		setDialog();
	}

	private void clear() {
		idTextField.setText("");
		passwordTextField.setText("");
	}

	private void buildGUI() {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./favicon.PNG");
		setIconImage(img);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 320);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// top
		topPanel.setBounds(0, 0, 490, 140);
		getContentPane().add(topPanel);
		topPanel.setBackground(Color.WHITE);

		// center
		centerPanel.setBounds(0, 138, 494, 107);
		centerPanel.setLayout(null);
		centerPanel.setBackground(Color.WHITE);
		getContentPane().add(centerPanel);

		idTextField.setColumns(10);
		idTextField.setBounds(43, 10, 271, 28);
		centerPanel.add(idTextField);

		loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.setBounds(338, 10, 111, 77);
		centerPanel.add(loginButton);

		passwordTextField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					id = idTextField.getText();
					password = passwordTextField.getText();
					System.out.println("로그인 시도");
					login(id, password);
					clear();
				}
			}
		});

		passwordTextField.setColumns(10);
		passwordTextField.setBounds(43, 59, 271, 28);
		centerPanel.add(passwordTextField);

		// bottom
		bottomPanel.setBounds(0, 243, 494, 48);
		bottomPanel.setBackground(Color.WHITE);
		getContentPane().add(bottomPanel);

		registerLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		bottomPanel.add(registerLabel);

		JLabel lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		bottomPanel.add(lblNewLabel_1);

		idfindLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		bottomPanel.add(idfindLabel);

		JLabel lblNewLabel_1_1 = new JLabel("|");
		lblNewLabel_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		bottomPanel.add(lblNewLabel_1_1);

		findPwdLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));
		bottomPanel.add(findPwdLabel);

		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click 회원가입");
				RegisterFrame registerFrame = new RegisterFrame();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				registerLabel.setForeground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				registerLabel.setForeground(Color.BLACK);
			}
		});

		idfindLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click 아이디찾기");
				IdFindFrame idFindFrame = new IdFindFrame();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				idfindLabel.setForeground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				idfindLabel.setForeground(Color.BLACK);
			}
		});

		findPwdLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click 비밀번호찾기");
				FindPwdFrame findPwdFrame = new FindPwdFrame();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				findPwdLabel.setForeground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				findPwdLabel.setForeground(Color.BLACK);
			}
		});

		setResizable(false);
		setVisible(true);
	}
}
