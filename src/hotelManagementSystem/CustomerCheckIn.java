package hotelManagementSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project.InsertUpdateDelete;
import project.Select;

public class CustomerCheckIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtMobileNumber;
	private JTextField txtNationality;
	private JTextField txtEmail;
	private JTextField txtCheckInDate;
	private JTextField txtAddress;
	private JTextField txtIdProof;
	private JTextField txtPrice;
	JComboBox txtRoomNumber = new JComboBox();
	String bed;
	String roomType;
	String roomNumber;
	String price;
	JComboBox txtBed = new JComboBox();
	JComboBox txtRoomType = new JComboBox();
	JComboBox txtGender = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCheckIn frame = new CustomerCheckIn();
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
	public CustomerCheckIn() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer Check In");
		lblNewLabel.setIcon(new ImageIcon(
				CustomerCheckIn.class.getResource("/imagesAndIcons/Customer Registration & Check IN.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 439, 40);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new Home().setVisible(true);

			}
		});
		btnNewButton.setIcon(new ImageIcon(CustomerCheckIn.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1314, 11, 42, 29);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(82, 170, 340, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mobile Number");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(82, 270, 327, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Nationality");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(82, 370, 361, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Gender");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(82, 470, 340, 14);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Email");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(82, 570, 311, 14);
		contentPane.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("ID Proof");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(499, 170, 395, 14);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_5_1 = new JLabel("Address");
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1.setBounds(499, 270, 340, 14);
		contentPane.add(lblNewLabel_1_5_1);

		JLabel lblNewLabel_1_5_1_1 = new JLabel("Check In Date (Today)");
		lblNewLabel_1_5_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1_1.setBounds(499, 370, 340, 14);
		contentPane.add(lblNewLabel_1_5_1_1);

		JLabel lblNewLabel_1_5_1_1_1 = new JLabel("Bed");
		lblNewLabel_1_5_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1_1_1.setBounds(948, 170, 89, 14);
		contentPane.add(lblNewLabel_1_5_1_1_1);

		JLabel lblNewLabel_1_5_1_1_1_1 = new JLabel("Room Type");
		lblNewLabel_1_5_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1_1_1_1.setBounds(948, 270, 89, 14);
		contentPane.add(lblNewLabel_1_5_1_1_1_1);

		JLabel lblNewLabel_1_5_1_1_1_2 = new JLabel("Room Number");
		lblNewLabel_1_5_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1_1_1_2.setBounds(948, 370, 113, 14);
		contentPane.add(lblNewLabel_1_5_1_1_1_2);

		JLabel lblNewLabel_1_5_1_1_1_2_1 = new JLabel("Price");
		lblNewLabel_1_5_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_5_1_1_1_2_1.setBounds(948, 470, 89, 14);
		contentPane.add(lblNewLabel_1_5_1_1_1_2_1);

		JButton btnNewButton_1 = new JButton("Albote Room");
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				int id = 1;
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String nationality = txtNationality.getText();
				String gender = (String) txtGender.getSelectedItem();
				String email = txtEmail.getText();
				String idProof = txtIdProof.getText();
				String address = txtAddress.getText();
				String checkIn = txtCheckInDate.getText();
				String bed = (String) txtBed.getSelectedItem();
				String roomType = (String) txtRoomType.getSelectedItem();
				String roomNumber = (String) txtRoomNumber.getSelectedItem();
				String price = txtPrice.getText();
				String Query = "select max(id) from customer";

				try {
					ResultSet rs = Select.getData(Query);
					while (rs.next()) {
						id = rs.getInt(1);
						id = id + 1;

						if (!price.equals("")) {

							Query = "update rooms set status = 'true' where roomNumber = '" + roomNumber + "'";

							InsertUpdateDelete.setData(Query, "");
							Query = "INSERT INTO customer (id, name, mobileNumber, nationality, gender, email, idProof, address, checkIn, roomNumber, bed, roomType, pricePerDay) VALUES ('"
									+ id + "','" + name + "', '" + mobileNumber + "', '" + nationality + "','" + gender
									+ "', '" + email + "', '" + idProof + "', '" + address + "','" + checkIn + "','"
									+ roomNumber + "','" + bed + "','" + roomType + "','" + price + "')";

							InsertUpdateDelete.setData(Query, "Customer Check In Successfully");

							setVisible(false);
							new CustomerCheckIn().setVisible(true);

							
							}
						if (name.equals("") || mobileNumber.equals("") || nationality.equals("") || email.equals("")
								|| idProof.equals("") || address.equals("")) {
							JOptionPane.showMessageDialog(null, "Every Field Is Required");
							

						}

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(948, 566, 127, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomerCheckIn().setVisible(true);

			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1.setBounds(948, 622, 127, 23);
		contentPane.add(btnNewButton_1_1);

		txtName = new JTextField();
		txtName.setFont(new Font("Dialog", Font.BOLD, 13));
		txtName.setBounds(82, 210, 340, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtMobileNumber = new JTextField();
		txtMobileNumber.setFont(new Font("Dialog", Font.BOLD, 13));
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(82, 318, 340, 20);
		contentPane.add(txtMobileNumber);

		txtNationality = new JTextField();
		txtNationality.setFont(new Font("Dialog", Font.BOLD, 13));
		txtNationality.setColumns(10);
		txtNationality.setBounds(82, 420, 340, 20);
		contentPane.add(txtNationality);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.BOLD, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(82, 641, 340, 20);
		contentPane.add(txtEmail);

		txtCheckInDate = new JTextField();
		txtCheckInDate.setFont(new Font("Dialog", Font.BOLD, 13));
		txtCheckInDate.setColumns(10);
		txtCheckInDate.setBounds(499, 420, 340, 20);
		contentPane.add(txtCheckInDate);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Dialog", Font.BOLD, 13));
		txtAddress.setColumns(10);
		txtAddress.setBounds(499, 318, 340, 20);
		contentPane.add(txtAddress);

		txtIdProof = new JTextField();
		txtIdProof.setFont(new Font("Dialog", Font.BOLD, 13));
		txtIdProof.setColumns(10);
		txtIdProof.setBounds(499, 210, 340, 20);
		contentPane.add(txtIdProof);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Dialog", Font.BOLD, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(948, 511, 340, 20);
		contentPane.add(txtPrice);

		txtGender.setFont(new Font("Dialog", Font.BOLD, 13));
		txtGender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		txtGender.setBounds(82, 510, 340, 22);
		contentPane.add(txtGender);

		txtRoomNumber.setFont(new Font("Dialog", Font.BOLD, 13));
		txtRoomNumber.setBounds(948, 419, 340, 22);
		contentPane.add(txtRoomNumber);
		txtRoomNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				roomNumber = (String) txtRoomNumber.getSelectedItem();

				try {

					ResultSet rs = Select.getData("select * from rooms where roomNumber = '" + roomNumber + "'");
					while (rs.next()) {
						txtPrice.setText(rs.getString(4));

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);

				}
			}

		});

		txtRoomType.setModel(new DefaultComboBoxModel(new String[] { "AC", "Non-AC" }));
		txtRoomType.setFont(new Font("Dialog", Font.BOLD, 13));
		txtRoomType.setBounds(948, 317, 340, 22);
		contentPane.add(txtRoomType);
		txtRoomType.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				roomDetails();

			}
		});

		txtBed.setModel(new DefaultComboBoxModel(new String[] { "Single", "Double", "Triple" }));
		txtBed.setFont(new Font("Dialog", Font.BOLD, 13));
		txtBed.setBounds(948, 209, 340, 22);
		contentPane.add(txtBed);
		txtBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDetails();

			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CustomerCheckIn.class.getResource("/imagesAndIcons/admin Home.png")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);

		setUndecorated(true);
		txtCheckInDate.setEditable(false);
		txtPrice.setEditable(false);

		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		txtCheckInDate.setText(myFormat.format(calendar.getTime()));

	}

	public void roomDetails() {
		txtRoomNumber.removeAllItems();
		txtPrice.setText(""); // Fiyat alanını sıfırla

		bed = (String) txtBed.getSelectedItem();
		roomType = (String) txtRoomType.getSelectedItem();

		try {
			ResultSet rs = Select.getData("SELECT * FROM rooms WHERE bed = '" + bed + "' AND roomType = '" + roomType
					+ "' AND status = 'false'");
			while (rs.next()) {
				txtRoomNumber.addItem(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
