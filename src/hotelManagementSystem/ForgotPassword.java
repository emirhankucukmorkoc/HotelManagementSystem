package hotelManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.InsertUpdateDelete;
import project.Select;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtSecurityQuestion;
	private JTextField txtAnswer;
	private JPasswordField txtPasswordField;
	private String email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Forgot Password ?");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(556, 195, 310, 46);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("Email");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBackground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(396, 253, 155, 14);
		contentPane.add(lblName);

		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setForeground(new Color(255, 255, 255));
		lblSecurityQuestion.setBackground(new Color(255, 255, 255));
		lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSecurityQuestion.setBounds(396, 302, 155, 14);
		contentPane.add(lblSecurityQuestion);

		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setForeground(new Color(255, 255, 255));
		lblAnswer.setBackground(new Color(255, 255, 255));
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnswer.setBounds(396, 352, 155, 14);
		contentPane.add(lblAnswer);

		JLabel label_2_1 = new JLabel("New Password");
		label_2_1.setForeground(new Color(255, 255, 255));
		label_2_1.setBackground(new Color(255, 255, 255));
		label_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2_1.setBounds(396, 402, 155, 14);
		contentPane.add(label_2_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(531, 252, 335, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setBounds(531, 302, 335, 20);
		contentPane.add(txtSecurityQuestion);

		txtAnswer = new JTextField();
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(531, 352, 335, 20);
		contentPane.add(txtAnswer);

		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(531, 402, 335, 20);
		contentPane.add(txtPasswordField);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0);

				}
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(ForgotPassword.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1314, 11, 42, 33);
		contentPane.add(btnNewButton);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				email = txtEmail.getText();

				if (email.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Email Is Required");
				} else {

					ResultSet rs = Select.getData("SELECT * FROM users WHERE email = '" + email + "'");

					try {
						if (rs.next()) {
							check = 1;
							txtSecurityQuestion.setEditable(false);
							txtEmail.setEditable(false);
							txtSecurityQuestion.setText(rs.getString(4));

						}

					} catch (Exception e2) {
						JOptionPane.showConfirmDialog(null, e);
					}
				}
				if (check == 0) {
					JOptionPane.showMessageDialog(null, "Incorret Email");

				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(885, 251, 89, 23);
		contentPane.add(btnSearch);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				String securityQuestion = txtSecurityQuestion.getText();
				String answer = txtAnswer.getText();
				String newPassword = txtPasswordField.getText();

				if (answer.equals("") || newPassword.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Every Field is Required");

				} else {
					ResultSet rs = Select.getData("SELECT * FROM users WHERE email = '" + email
							+ "' AND securityQuestion = '" + securityQuestion + "' and answer = '" + answer + "'");

					try {
						if (rs.next()) {

							InsertUpdateDelete.setData(
									"update users set password = '" + newPassword + "' where email = '" + email + "'",
									"Password Set Successfully");
							setVisible(false);
							new ForgotPassword().setVisible(true);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Incorrect Answer!");
					}
				}
			}
		});
		btnSave.setBackground(new Color(128, 0, 0));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(531, 435, 89, 23);
		contentPane.add(btnSave);

		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SignUp().setVisible(true);
			}
		});
		btnSignup.setBackground(new Color(128, 0, 0));
		btnSignup.setForeground(new Color(255, 255, 255));
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignup.setBounds(652, 433, 89, 23);
		contentPane.add(btnSignup);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(777, 435, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ForgotPassword.class.getResource("/imagesAndIcons/forgot password.PNG")));
		lblNewLabel_1.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_1);
		setUndecorated(true);
	}
}
