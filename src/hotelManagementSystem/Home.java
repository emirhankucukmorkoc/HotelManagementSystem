package hotelManagementSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("MANAGE ROOM");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageRoom().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/manage room.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(21, 33, 192, 51);
		contentPane.add(btnNewButton);

		JButton btnC = new JButton("CUSTOMER CHECK IN");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerCheckIn().setVisible(true);
			}
		});
		btnC.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/Customer Registration & Check IN.png")));
		btnC.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnC.setBounds(223, 33, 226, 51);
		contentPane.add(btnC);

		JButton btnNewButton_1_1 = new JButton("CUSTOMER CHECK OUT");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerCheckOut().setVisible(true);
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/Customer Check Out.png")));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1.setBounds(454, 33, 229, 51);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("CUSTOMER DETAIL BİLLS");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerDetailsBill().setVisible(true);
			}
		});
		btnNewButton_1_1_1.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/Customer Details Bill.png")));
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1_1.setBounds(686, 33, 250, 51);
		contentPane.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1_1_1 = new JButton("LOG OUT");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want log out  ", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}
			}
		});
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/logout.png")));
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1_1_1.setBounds(946, 33, 192, 51);
		contentPane.add(btnNewButton_1_1_1_1);

		JButton btnNewButton_1_1_1_1_1 = new JButton("EXİT");
		btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want close this application ", "Select",
						JOptionPane.YES_NO_OPTION);
				
				if (a == 0) {
					setVisible(false);
					
				}

			}
		});
		btnNewButton_1_1_1_1_1.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/exit.png")));
		btnNewButton_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1_1_1_1.setBounds(1148, 33, 192, 51);
		contentPane.add(btnNewButton_1_1_1_1_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/imagesAndIcons/home.png")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
	}

}
