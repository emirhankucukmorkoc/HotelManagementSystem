package hotelManagementSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project.InsertUpdateDelete;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtAnswer;
	private JTextField txtAddress;
	private JPasswordField txtPasswordField;
	JComboBox txtSecurityQuesiton = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(645, 169, 161, 40);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(455, 227, 117, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Email");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(455, 277, 117, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Password");
		lblNewLabel_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(455, 327, 117, 14);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("Security Question");
		lblNewLabel_2_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(455, 377, 117, 14);
		contentPane.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("Answer");
		lblNewLabel_2_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2_1_1.setBounds(455, 427, 117, 14);
		contentPane.add(lblNewLabel_2_2_1_1);

		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Address");
		lblNewLabel_2_2_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2_1_1_1.setBounds(455, 477, 117, 14);
		contentPane.add(lblNewLabel_2_2_1_1_1);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(582, 227, 382, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(582, 277, 382, 20);
		contentPane.add(txtEmail);

		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(582, 427, 382, 20);
		contentPane.add(txtAnswer);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddress.setColumns(10);
		txtAddress.setBounds(582, 477, 382, 20);
		contentPane.add(txtAddress);

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
		btnForgotPassword.setBounds(809, 508, 155, 23);
		contentPane.add(btnForgotPassword);

		JButton btnSignup = new JButton("Signup\r\n");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtName.getText();
				String email = txtEmail.getText();
				String password = txtPasswordField.getText();
				String securityQuestion = (String) txtSecurityQuesiton.getSelectedItem();
				String answer = txtAnswer.getText();
				String address = txtAddress.getText();

				if (name.equals("") || email.equals("") || password.equals("") || answer.equals("")
						|| address.equals("")) {
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
				} else {
					String Query;
					Query = "INSERT INTO users (name, email, password,securityQuestion, answer, address,status) VALUES ('"
							+ name + "', '" + email + "', '" + password + "','" + securityQuestion + "', '" + answer
							+ "', '" + address + "', 'false')";

					InsertUpdateDelete.setData(Query, "Registered Successfully");
					setVisible(false);
					new SignUp().setVisible(true);

				}
			}
		});
		btnSignup.setBackground(new Color(128, 0, 0));
		btnSignup.setForeground(new Color(255, 255, 255));
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignup.setBounds(582, 508, 99, 23);
		contentPane.add(btnSignup);

		JButton btnLogin = new JButton("Login\r\n");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(128, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(700, 508, 99, 23);
		contentPane.add(btnLogin);

		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(582, 327, 382, 20);
		contentPane.add(txtPasswordField);

		txtSecurityQuesiton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSecurityQuesiton.setModel(new DefaultComboBoxModel(new String[] { "What city were you born in?",
				"What is your oldest sibling’s middle name?", "What was the first concert you attended?",
				"In what city or town did your parents meet?", "What was the make and model of your first car?" }));
		txtSecurityQuesiton.setBounds(582, 377, 382, 22);
		contentPane.add(txtSecurityQuesiton);

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
		btnNewButton.setIcon(new ImageIcon(SignUp.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1323, 11, 33, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/imagesAndIcons/signup.PNG")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
	}

}
