package hotelManagementSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import javax.swing.table.TableModel;

import project.Select;

public class CustomerDetailsBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDetailsBill frame = new CustomerDetailsBill();
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
	public CustomerDetailsBill() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select *from customer where checkOut is not NULL");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();

				try {
					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
								rs.getString(14), rs.getString(15), rs.getString(16) });
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CUSTOMER DETAİLS BİLL");
		lblNewLabel.setIcon(
				new ImageIcon(CustomerDetailsBill.class.getResource("/imagesAndIcons/Customer Details Bill.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 490, 50);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(CustomerDetailsBill.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1299, 11, 57, 33);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Search By Check OUT Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(331, 232, 202, 17);
		contentPane.add(lblNewLabel_1);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSearch.setBounds(543, 230, 272, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checkOut = txtSearch.getText();

				ResultSet rs = Select.getData("select *from customer where checkOut = '" + checkOut + "'");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);

				try {
					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
								rs.getString(14), rs.getString(15), rs.getString(16) });
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(855, 229, 115, 23);
		contentPane.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();
				if (index != -1) {
					TableModel model = table.getModel();
					String id = model.getValueAt(index, 0).toString();
					comon.OpenPdf.openById(id);
				}

			}
		});
		scrollPane.setBounds(10, 284, 1346, 458);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Mobile Number", "Nationality", "Gender", "Email", "ID Proof", "Address",
						"Check In", "Room Number", "Bed", "Room Type", "Price Per Day", "Number Of Days Stay",
						"Check Out", "Total Amount" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(CustomerDetailsBill.class.getResource("/imagesAndIcons/admin Home.png")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);
		setUndecorated(true);

		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		txtSearch.setText(myFormat.format(calendar.getTime()));
	}
}
