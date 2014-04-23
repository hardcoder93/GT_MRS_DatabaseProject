import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;


public class RatePanel{
	JPanel panel;
	Connection con;
	JComboBox comboBox;
	JComboBox comboBox_1;
	String P_username;
	ArrayList<String> arrayList2 = new ArrayList<String>();
	/**
	 * Create the panel.
	 */
	public RatePanel(JPanel panel, Connection con, String P_username) {
		this.panel = panel;
		this.con = con;
		this.P_username = P_username; 
		Statement stmt = null;

		System.out.println("Creating statement...");
		try {
			stmt = con.createStatement();
		} catch (SQLException k) {
			// TODO Auto-generated catch block
			k.printStackTrace();
		}

		
		String sql = "SELECT LicenseNumber, LastName, FirstName FROM Doctor";
		String sql2 = "SELECT D_LicenseNumber FROM Doctor";
		//System.out.println(sql);
		try {
			//rs = stmt.executeQuery(begin);
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<String> arrayList = new ArrayList<String>();
			while (rs.next()) {
				arrayList.add(rs.getString("FirstName") + rs.getString("LastName"));
				arrayList2.add(rs.getString("LicenseNumber"));
			}
			panel.setBackground(new Color(224, 255, 255));
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gridBagLayout);
			
			JLabel lblNewLabel = new JLabel("Rate a Doctor");
			lblNewLabel.setFont(new Font("Angsana New", Font.BOLD, 30));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = 0;
			panel.add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Selected Doctor");
			lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 2;
			panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			comboBox = new JComboBox();
			String[] a = new String[arrayList.size()];
			for (int i = 0; i < arrayList.size(); i++) {
				a[i] = arrayList.get(i);
			}
			comboBox.setModel(new DefaultComboBoxModel(a));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 2;
			panel.add(comboBox, gbc_comboBox);
			
			JLabel lblNewLabel_2 = new JLabel("Rating");
			lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 3;
			panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
			gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_1.gridx = 3;
			gbc_comboBox_1.gridy = 3;
			panel.add(comboBox_1, gbc_comboBox_1);
			
			JButton btnNewButton = new JButton("Submit");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton.gridx = 7;
			gbc_btnNewButton.gridy = 8;
			panel.add(btnNewButton, gbc_btnNewButton);
			btnNewButton.addActionListener(new SubmitRatePerformer());
		} catch (SQLException m) {

			m.printStackTrace();
		}
		//rs.close();
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private class SubmitRatePerformer implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String rating = (String)comboBox_1.getSelectedItem();
			String D_name = (String)comboBox.getSelectedItem();
			int i = comboBox.getSelectedIndex();
			String licenseNumber = arrayList2.get(i);
			Statement stmt = null;


			System.out.println("Creating statement...");
			try {
				stmt = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}
			String sql = "INSERT INTO Rates(D_LicenseNumber, P_Username, Rating) VALUES ('"+licenseNumber+"','"+ P_username+"','"+rating+"')";


			try {
				//rs = stmt.executeQuery(begin);
				stmt.execute(sql);
			} catch (SQLException m) {

				m.printStackTrace();
			} 


			//rs.close();
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			panel.removeAll();
			new PatientMenuPanel(panel,con,P_username);
			panel.validate();
			panel.repaint();
		}
	}
}
