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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import project.InsertUpdateDelete;
import project.Select;

public class ManageRoom extends JFrame {

	private JPanel contentPane;
	private JTextField txtRoomNumber;
	private JTextField txtPrice;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageRoom frame = new ManageRoom();
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
	public ManageRoom() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from rooms ");
				DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
				dtm.setRowCount(0);

				try {

					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5) });

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

		JLabel lblNewLabel = new JLabel("Manage Room");
		lblNewLabel.setIcon(new ImageIcon(ManageRoom.class.getResource("/imagesAndIcons/manage room.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(31, 36, 265, 30);
		contentPane.add(lblNewLabel);

		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		contentPane.add(table);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageRoom.class.getResource("/imagesAndIcons/close.png")));
		btnNewButton.setBounds(1310, 11, 46, 30);
		contentPane.add(btnNewButton);

		JLabel lblRoomNumber = new JLabel("Room Number");
		lblRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoomNumber.setBounds(112, 182, 113, 14);
		contentPane.add(lblRoomNumber);

		JLabel lblNewLabel_1_1 = new JLabel("Room Type");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(112, 280, 83, 22);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Bed");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(112, 382, 83, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(112, 482, 83, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtRoomNumber = new JTextField();
		txtRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtRoomNumber.setBounds(252, 182, 353, 20);
		contentPane.add(txtRoomNumber);
		txtRoomNumber.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(252, 482, 353, 20);
		contentPane.add(txtPrice);

		JComboBox txtRoomType = new JComboBox();
		txtRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtRoomType.setModel(new DefaultComboBoxModel(new String[] { "AC", "Non-AC" }));
		txtRoomType.setBounds(252, 282, 353, 22);
		contentPane.add(txtRoomType);

		JComboBox txtBed = new JComboBox();
		txtBed.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBed.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		txtBed.setBounds(252, 382, 353, 22);
		contentPane.add(txtBed);

		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNumber = txtRoomNumber.getText();
				String roomType = (String) txtRoomType.getSelectedItem();
				String bed = (String) txtBed.getSelectedItem();
				String price = txtPrice.getText();

				if (roomNumber.equals("") || price.equals("")) {
					JOptionPane.showMessageDialog(null, "Every Field is Required");

				} else {
					String Query;
					Query = "INSERT INTO rooms (roomNumber, roomType, bed,price,status) VALUES ('" + roomNumber + "', '"
							+ roomType + "', '" + bed + "','" + price + "', 'false')";

					InsertUpdateDelete.setData(Query, "Added Successfully");
					setVisible(false);
					new ManageRoom().setVisible(true);

				}

			}
		});
		btnAddRoom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddRoom.setBackground(new Color(128, 0, 0));
		btnAddRoom.setForeground(new Color(255, 255, 255));
		btnAddRoom.setBounds(252, 527, 104, 23);
		contentPane.add(btnAddRoom);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(739, 155, 541, 441);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int index = table_1.getSelectedRow(); // Doğru satır indeksini al

		        if (index != -1) {
		            TableModel model = table_1.getModel();
		            String roomNumber = model.getValueAt(index, 0).toString();
		            String roomType = model.getValueAt(index, 1).toString();
		            String bed = model.getValueAt(index, 2).toString();
		            String price = model.getValueAt(index, 3).toString();
		            String status = model.getValueAt(index, 4).toString();

		            int a = JOptionPane.showConfirmDialog(null, "Do you want to delete " + roomNumber + " room",
		                    "Select", JOptionPane.YES_NO_OPTION);
		            if (a == JOptionPane.YES_OPTION) {
		                InsertUpdateDelete.setData("delete from rooms where roomNumber='" + roomNumber + "'",
		                        "Room Deleted Successfully");
		                DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
		                tableModel.removeRow(index);
		            }
		        }
		    }
		});

		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Room Number", "Room Type", "Bed", "Price", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_2 = new JLabel("*CLICK ON ROW TO DELETE ROOM");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(821, 619, 392, 30);
		contentPane.add(lblNewLabel_2);
		
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setIcon(new ImageIcon(ManageRoom.class.getResource("/imagesAndIcons/admin Home.png")));
				lblNewLabel_1.setBounds(0, 0, 1366, 768);
				contentPane.add(lblNewLabel_1);

		setUndecorated(true);
	}
}
