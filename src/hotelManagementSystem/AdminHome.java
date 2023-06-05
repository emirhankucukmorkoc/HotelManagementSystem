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

import project.InsertUpdateDelete;
import project.Select;

public class AdminHome extends JFrame {

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
					AdminHome frame = new AdminHome();
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
	public AdminHome() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from users ");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);

				try {

					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6),
								rs.getString(7) });

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

		JLabel lblNewLabel = new JLabel("ADMİN PANEL");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setBounds(21, 17, 413, 52);
		contentPane.add(lblNewLabel);

		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogout.setIcon(new ImageIcon(AdminHome.class.getResource("/imagesAndIcons/logout.png")));
		btnLogout.setBounds(996, 17, 162, 52);
		contentPane.add(btnLogout);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application", "Select",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0);

				}
			}
		});
		btnExit.setIcon(new ImageIcon(AdminHome.class.getResource("/imagesAndIcons/exit.png")));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(1182, 17, 162, 52);
		contentPane.add(btnExit);

		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");

			}
		});
		btnNewButton_1_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBounds(1245, 141, 89, 23);
		contentPane.add(btnNewButton_1_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOrEmail = txtSearch.getText();
				ResultSet rs = Select.getData("select * from users where name like  '%" + nameOrEmail
						+ "%' or email like  '%" + nameOrEmail + "%'");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);

				try {

					while (rs.next()) {
						dtm.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6),
								rs.getString(7) });

					}

					rs.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBounds(776, 141, 89, 23);
		contentPane.add(btnSearch);

		JLabel lblNewLabel_1 = new JLabel("Search By Name Or Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(353, 145, 162, 14);
		contentPane.add(lblNewLabel_1);

		txtSearch = new JTextField();
		txtSearch.setBounds(525, 142, 241, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(21, 186, 1323, 557);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String email = model.getValueAt(index, 1).toString();
				String status = model.getValueAt(index, 4).toString(); // Sütun endeksi 3

				if (status.equals("true")) {
					status = "false";
				} else {
					status = "true";
					try {
						int a = JOptionPane.showConfirmDialog(null, "Do you want to change status of " + email + "",
								"Select", JOptionPane.YES_NO_OPTION);
						if (a == 0) {
							InsertUpdateDelete.setData(
									"update users set status = '" + status + "' where email = '" + email + "'",
									"Status Set Successfully");
							setVisible(false);
							new AdminHome().setVisible(true);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);

					}
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Email", "Security Question", "Address", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AdminHome.class.getResource("/imagesAndIcons/admin Home.png")));
		lblNewLabel_2.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_2);

		setUndecorated(true);
	}

}
