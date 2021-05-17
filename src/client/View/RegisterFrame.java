package client.View;

import javax.swing.JFrame;

import client.DAO.CustomerDAO;
import client.VO.CustomerVO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class RegisterFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField id;
	private JTextField password;
	private JTextField passwordCheck;
	private JTextField email;
	private JTextField phoneNumber;
	private JButton registerButton;

	private CustomerDAO customerDao = CustomerDAO.getInstance();
	private CustomerVO customer;

	private JDialog dialog1 = new JDialog(this, "회원가입 완료", true);
	private JDialog dialog2 = new JDialog(this, "회원가입 실패", true);
	private JDialog dialog3 = new JDialog(this, "비밀번호 확인", true);
	
	public RegisterFrame() {
		buildGUI();
		setEvent();
		setDialog();
	}

	public void clear() {
		name.setText("");
		id.setText("");
		password.setText("");
		passwordCheck.setText("");
		email.setText("");
		phoneNumber.setText("");
	}

	public void setEvent() {
		registerButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {
			customerDao.connect();
			System.out.println("회원가입 시도");

			// 회원가입 할때 등록 과정을 어디서 처리해야하나?
			// 회원가입 유효성 판단 - 비밀번호 check
			// System.out.println(name.getText() + " , " + id.getText() + ',' +
			// password.getText().trim() + ","+passwordCheck.getText().trim() +
			// ","+email.getText()+","+phoneNumber.getText());

			if (password.getText().trim().equals(passwordCheck.getText().trim())) {
				customer = new CustomerVO(name.getText().trim(), id.getText().trim(), password.getText().trim(),
						email.getText().trim(), phoneNumber.getText().trim());
				if (customerDao.register(customer)) {
					System.out.println("회원가입 완료");
					dispose();
					dialog1.setVisible(true);

				} else {
					System.out.println("회원가입 실패");
					dialog2.setVisible(true);
				}
			} else {
				System.out.println("비밀번호 오류 ");
				dialog3.setVisible(true);
			}
		}
	}

	// 다이알로그 구성
	private void setDialog() {
		JLabel text1 = new JLabel("회원가입 성공");
		JLabel text2 = new JLabel("회원가입 실패");
		JLabel text3 = new JLabel("비밀번호 오류 ");
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		text2.setHorizontalAlignment(SwingConstants.CENTER);
		text3.setHorizontalAlignment(SwingConstants.CENTER);
		
		Point pt = getLocation();
		Dimension my = getSize();
		Dimension dsize = dialog1.getSize();
		int x = (int) pt.getX() + my.width / 2 - dsize.width / 2;
		int y = (int) pt.getY() + my.height / 2 - dsize.height / 2;
		
		dialog1.setLocation(x, y);
		dialog2.setLocation(x, y);
		dialog3.setLocation(x, y);
		
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

	public void buildGUI() {
		getContentPane().setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		setTitle("회원가입");
		setSize(303, 384);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		setResizable(false); // 함부로 크기를 변경하지 않게.
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblNewLabel = new JLabel("\uC774\uB984");
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 43, 57, 15);
		getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(27, 84, 57, 15);
		getContentPane().add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(27, 126, 57, 15);
		getContentPane().add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("\uC774\uBA54\uC77C");
		lblNewLabel_3.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(27, 211, 57, 15);
		getContentPane().add(lblNewLabel_3);
		JLabel lblNewLabel_4 = new JLabel("\uD734\uB300\uD3F0 \uBC88\uD638");
		lblNewLabel_4.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(27, 257, 86, 15);
		getContentPane().add(lblNewLabel_4);
		JLabel lblNewLabel_5 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblNewLabel_5.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(27, 170, 86, 15);
		getContentPane().add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel_6.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(109, 8, 153, 25);
		getContentPane().add(lblNewLabel_6);

		name = new JTextField();
		name.setBounds(135, 43, 137, 21);
		getContentPane().add(name);
		name.setColumns(10);

		id = new JTextField();
		id.setColumns(10);
		id.setBounds(135, 84, 137, 21);
		getContentPane().add(id);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(135, 126, 137, 21);
		getContentPane().add(password);

		passwordCheck = new JTextField();
		passwordCheck.setColumns(10);
		passwordCheck.setBounds(135, 170, 137, 21);
		getContentPane().add(passwordCheck);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(135, 211, 137, 21);
		getContentPane().add(email);

		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(135, 257, 137, 21);
		getContentPane().add(phoneNumber);

		registerButton = new JButton("\uB4F1\uB85D");
		registerButton.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		registerButton.setBounds(135, 288, 137, 43);
		getContentPane().add(registerButton);
	}

}
