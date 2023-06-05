package hotelManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.InsertUpdateDelete;
import project.Select;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPasswordField;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setBounds(549, 360, 354, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(549, 403, 354, 20);
		contentPane.add(txtPasswordField);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(466, 356, 73, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(466, 399, 73, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(635, 301, 102, 37);
		contentPane.add(lblNewLabel_2);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				String email = txtEmail.getText();
				String password = txtPasswordField.getText();

				if (email.equals("") || password.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
				} else if (email.equals("hms") && password.equals("admin")) {
					check = 1;
					setVisible(false);
					new AdminHome().setVisible(true);
				} else {
					ResultSet rs = Select.getData(
							"SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");

					try {
						if (rs.next()) {
							check = 1;
							if (rs.getString("status").equals("true")) {
								setVisible(false);
								new Home().setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Wait Admin Approval");
							}
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
					if (check == 0) {
						JOptionPane.showMessageDialog(null, "Incorrect Email or Password");
					}
				}
			}
		});

		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(549, 435, 78, 23);
		contentPane.add(btnLogin);

		JButton btnForgotPassword = new JButton("Forgot Password ?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setBackground(new Color(128, 0, 0));
		btnForgotPassword.setForeground(new Color(255, 255, 255));
		btnForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnForgotPassword.setBounds(747, 435, 156, 23);
		contentPane.add(btnForgotPassword);

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
		btnSignup.setBounds(648, 435, 89, 23);
		contentPane.add(btnSignup);

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
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1320, 11, 36, 25);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/imagesAndIcons/login.PNG")));
		lblNewLabel_3.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_3);
		setUndecorated(true);
	}

}
