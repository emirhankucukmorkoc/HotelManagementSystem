package hotelManagementSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import project.InsertUpdateDelete;
import project.Select;

public class CustomerCheckOut extends JFrame {

	private JPanel contentPane;
	private JTextField txtRoomNumber;
	private JTextField txtCustomerName;
	private JTextField txtPricePerDay;
	private JTextField txtCheckInDate;
	private JTextField txtNumberOfDaysStay;
	private JTextField txtCheckOutDate;
	private JTextField txtTotalAmountToCollectFC;
	private JTextField txtCustomerMobileNumber;
	private JTextField txtEmail;
	private JTable table;
	int id;
	String Query;
	String roomType;
	String bed;
	String roomNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCheckOut frame = new CustomerCheckOut();
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
	public CustomerCheckOut() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

				ResultSet rs = Select.getData("select * from customer where checkOut is NULL");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);

				try {

					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13) });

					}

					rs.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CUSTOMER CHECK OUT");
		lblNewLabel
				.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/imagesAndIcons/Customer Check Out.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 428, 33);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1295, 11, 61, 33);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Room Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(425, 191, 115, 14);
		contentPane.add(lblNewLabel_1);

		txtRoomNumber = new JTextField();
		txtRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtRoomNumber.setBounds(550, 188, 131, 20);
		contentPane.add(txtRoomNumber);
		txtRoomNumber.setColumns(10);

		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String roomNumber = txtRoomNumber.getText();

				String Query = "select *from customer where roomNumber = '" + roomNumber + "' and checkOut is NULL";
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);

				try {

					ResultSet rs = Select.getData(Query);

					if (rs.next()) {

						txtRoomNumber.setEditable(false);
						id = rs.getInt(1);
						txtCustomerName.setText(rs.getString(2));
						txtCustomerMobileNumber.setText(rs.getString(3));
						txtPricePerDay.setText(rs.getString(13));
						txtEmail.setText(rs.getString(6));
						txtCheckInDate.setText(rs.getString(9));

						SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
						Calendar calendar = Calendar.getInstance();
						txtCheckOutDate.setText(myFormat.format(calendar.getTime()));
						String dateBeforeString = rs.getString(9);
						Date dateBefore = myFormat.parse(dateBeforeString);
						String dateAfterString = myFormat.format(calendar.getTime());
						Date dateAfter = myFormat.parse(dateAfterString);
						long difference = dateAfter.getTime() - dateBefore.getTime();
						int numboerOfDayStay = (int) (difference / (1000 * 60 * 60 * 24));
						if (numboerOfDayStay == 0) {
							numboerOfDayStay = 1;
							txtNumberOfDaysStay.setText(String.valueOf(numboerOfDayStay));
							float price = Float.parseFloat(txtPricePerDay.getText());
							txtTotalAmountToCollectFC.setText(String.valueOf(price * numboerOfDayStay));
							roomType = rs.getString(12);
							bed = rs.getString(11);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Room Number is not Booked or Room Number Does not Exist");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(691, 187, 119, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Customer Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(52, 250, 237, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Price Per Day");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(52, 352, 202, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Check Out Date (Today)");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1.setBounds(691, 250, 202, 14);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Number Of Days Stay");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1_1.setBounds(347, 352, 202, 14);
		contentPane.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Check In Date");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1_1_1.setBounds(347, 250, 157, 14);
		contentPane.add(lblNewLabel_2_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Total Amount  to Collet From Customer");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1_1_1_1.setBounds(691, 352, 267, 14);
		contentPane.add(lblNewLabel_2_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_2 = new JLabel("Customer Mobile Number");
		lblNewLabel_2_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1_1_1_2.setBounds(1006, 250, 202, 14);
		contentPane.add(lblNewLabel_2_1_1_1_1_2);

		JLabel lblNewLabel_2_1_1_1_1_3 = new JLabel("Email");
		lblNewLabel_2_1_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1_1_1_3.setBounds(1006, 352, 73, 14);
		contentPane.add(lblNewLabel_2_1_1_1_1_3);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerName.setBounds(52, 291, 202, 20);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		txtPricePerDay = new JTextField();
		txtPricePerDay.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPricePerDay.setColumns(10);
		txtPricePerDay.setBounds(52, 388, 202, 20);
		contentPane.add(txtPricePerDay);

		txtCheckInDate = new JTextField();
		txtCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCheckInDate.setColumns(10);
		txtCheckInDate.setBounds(347, 291, 202, 20);
		contentPane.add(txtCheckInDate);

		txtNumberOfDaysStay = new JTextField();
		txtNumberOfDaysStay.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNumberOfDaysStay.setColumns(10);
		txtNumberOfDaysStay.setBounds(347, 388, 202, 20);
		contentPane.add(txtNumberOfDaysStay);

		txtCheckOutDate = new JTextField();
		txtCheckOutDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCheckOutDate.setColumns(10);
		txtCheckOutDate.setBounds(691, 291, 202, 20);
		contentPane.add(txtCheckOutDate);

		txtTotalAmountToCollectFC = new JTextField();
		txtTotalAmountToCollectFC.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTotalAmountToCollectFC.setColumns(10);
		txtTotalAmountToCollectFC.setBounds(691, 388, 202, 20);
		contentPane.add(txtTotalAmountToCollectFC);

		txtCustomerMobileNumber = new JTextField();
		txtCustomerMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerMobileNumber.setColumns(10);
		txtCustomerMobileNumber.setBounds(1006, 291, 202, 20);
		contentPane.add(txtCustomerMobileNumber);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(1006, 388, 202, 20);
		contentPane.add(txtEmail);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 452, 1296, 285);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Mobile Number", "Nationality", "Gender", "Email", "Id Proof", "Address",
						"Check In Date", "Room Number", "Bed", "Room Type", "Price Per Day" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtCustomerName.getText();
				String mobieNumber = txtCustomerMobileNumber.getText();
				String email = txtEmail.getText();
				String checkOut = txtCheckOutDate.getText();
				String numberOfDaysStay = txtNumberOfDaysStay.getText();
				String totalAmount = txtTotalAmountToCollectFC.getText();

				roomNumber = txtRoomNumber.getText();
				String query = "UPDATE rooms SET status = 'false' WHERE roomNumber = '" + roomNumber + "'";
				InsertUpdateDelete.setData(query, "");

				String query2 = "UPDATE customer SET numberOfDaysStay = '" + numberOfDaysStay + "',totalAmount = '"
						+ totalAmount + "', checkOut = '" + checkOut + "'  where id = '" + id + "'";
				InsertUpdateDelete.setData(query2, "");
				String path = "C:\\Users\\emirh\\OneDrive\\Masaüstü\\pdf\\";

				Document doc = new Document();
				try {

					PdfWriter.getInstance(doc, new FileOutputStream(path + "" + id + ".pdf"));
					doc.open();

					Paragraph hotelName = new Paragraph(
							"							                                                                  EKM  Hotel 							");
					doc.add(hotelName);

					Paragraph starLine = new Paragraph(
							"****************************************************************************************************************");
					doc.add(starLine);

					Paragraph paragraph3 = new Paragraph("\tBill ID:" + id + "\nCUSTOMER DETAİLS:\nCustomer Name : "
							+ name + "\nMobile Number : " + mobieNumber + "\nEmail : " + email);
					doc.add(paragraph3);
					doc.add(starLine);

					Paragraph paragraph4 = new Paragraph(
							"\nROOM DETAİLS:\nRoom Number : " + txtRoomNumber.getText() + "\nRoom Type : " + roomType
									+ "\nBed : " + bed + "\nPrice Per Dat : " + txtPricePerDay.getText());
					doc.add(paragraph4);
					doc.add(starLine);

					PdfPTable tb1 = new PdfPTable(4);
					tb1.addCell("Check In Date: " + txtCheckInDate.getText());
					tb1.addCell("Check Out Date: " + checkOut);
					tb1.addCell("Number Of Days Stay: " + numberOfDaysStay);
					tb1.addCell("Total Amount: " + totalAmount);

					doc.add(tb1);
					doc.add(starLine);

					Paragraph thankMessag = new Paragraph("Thank you, Please visit again");
					doc.add(thankMessag);
					// open pdf
					

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				doc.close();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to print bill","Select",JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					try {
						comon.OpenPdf.openById(String.valueOf(id));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e);
					}
					
					
				}
				setVisible(false);
				new CustomerCheckOut().setVisible(true);

			}
		});
		btnCheckOut.setForeground(new Color(255, 255, 255));
		btnCheckOut.setBackground(new Color(128, 0, 0));
		btnCheckOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCheckOut.setBounds(52, 419, 123, 23);
		contentPane.add(btnCheckOut);

		JButton btnNewButton_2_1 = new JButton("Clear");
		btnNewButton_2_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomerCheckOut().setVisible(true);
			}
		});
		btnNewButton_2_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1.setBackground(new Color(128, 0, 0));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2_1.setBounds(200, 419, 89, 23);
		contentPane.add(btnNewButton_2_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/imagesAndIcons/admin Home.png")));
		lblNewLabel_3.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_3);

		setUndecorated(true);

		txtCheckOutDate.setEditable(false);
		txtCustomerMobileNumber.setEditable(false);
		txtCustomerName.setEditable(false);
		txtEmail.setEditable(false);
		txtNumberOfDaysStay.setEditable(false);
		txtPricePerDay.setEditable(false);
		txtTotalAmountToCollectFC.setEditable(false);
		txtCheckInDate.setEditable(false);
	}
}
