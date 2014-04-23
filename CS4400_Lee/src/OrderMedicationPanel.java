import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingConstants;


public class OrderMedicationPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel; private Connection con;
	private JComboBox comboBox,comboBox_2,comboBox_3;
	private String p_username;
	private ArrayList<String> basket;
	/**
	 * Create the panel.
	 */
	public OrderMedicationPanel(JPanel panel, Connection con, String p_username) {
		this.panel = panel;
		this.con = con;
		this.p_username = p_username;
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		basket = new ArrayList<String>();

		JLabel lblOrderMedicationFrom = new JLabel("Order Medication from Pharmacy");
		lblOrderMedicationFrom.setForeground(new Color(255, 99, 71));
		lblOrderMedicationFrom.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOrderMedicationFrom);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JLabel lblMedicineName = new JLabel("Medicine Name");
		splitPane.setLeftComponent(lblMedicineName);

		textField = new JTextField();
		splitPane.setRightComponent(textField);
		textField.setColumns(10);

		JSplitPane splitPane_2 = new JSplitPane();
		panel.add(splitPane_2);

		JLabel label = new JLabel("Dosage");
		splitPane_2.setLeftComponent(label);

		JPanel panel_2 = new JPanel();
		splitPane_2.setRightComponent(panel_2);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		panel_2.add(comboBox);

		JLabel label_1 = new JLabel("per day");
		panel_2.add(label_1);

		JSplitPane splitPane_4 = new JSplitPane();
		panel.add(splitPane_4);

		JLabel lblDuration = new JLabel("Duration");
		splitPane_4.setLeftComponent(lblDuration);

		JPanel panel_1 = new JPanel();
		splitPane_4.setRightComponent(panel_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		panel_1.add(comboBox_2);

		JLabel lblMonths = new JLabel("months");
		panel_1.add(lblMonths);

		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel_1.add(comboBox_3);

		JLabel lblDays = new JLabel("days");
		panel_1.add(lblDays);

		JSplitPane splitPane_1 = new JSplitPane();
		panel.add(splitPane_1);

		JLabel lblConsultingDoctor = new JLabel("Consulting Doctor");
		splitPane_1.setLeftComponent(lblConsultingDoctor);

		textField_1 = new JTextField();
		splitPane_1.setRightComponent(textField_1);
		textField_1.setColumns(10);

		JSplitPane splitPane_3 = new JSplitPane();
		panel.add(splitPane_3);

		JLabel lblDateOfPrescription = new JLabel("Date of Prescription");
		splitPane_3.setLeftComponent(lblDateOfPrescription);

		textField_2 = new JTextField();
		splitPane_3.setRightComponent(textField_2);
		textField_2.setColumns(10);

		JButton btnAddToBasket = new JButton("Add medication to basket");
		btnAddToBasket.setForeground(new Color(0, 0, 205));
		btnAddToBasket.setBackground(new Color(0, 255, 255));
		panel.add(btnAddToBasket);
		btnAddToBasket.addActionListener(new AddToBucket());

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setForeground(new Color(0, 0, 139));
		panel.add(btnCheckout);
		btnCheckout.addActionListener(new AddCheckout());

	}

	private class AddCheckout implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String medName = textField.getText();
			String dosage = (String)comboBox.getSelectedItem();
			String durationM = (String)comboBox_2.getSelectedItem();
			String durationD = (String)comboBox_3.getSelectedItem();
			String duration = durationM +"/" +durationD;        // FORMAT!!! errata
			String doctor = textField_1.getText();
			String date = textField_2.getText();

			Statement stmt = null;

			System.out.println("Creating statement...");
			try {
				stmt = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			String sql = null;
			String sql2 = null;


			sql = "SELECT D_LicenseNumber,P_Username,DateOfVisit,MedicineName,Dosage,Duration FROM Prescription";
			//sql2 = 
			try {
				ResultSet rs = stmt.executeQuery(sql);



				while(rs.next()) {
					if (p_username.equals(rs.getString("P_Username")) && dosage.equals(rs.getString("Dosage")) && duration.equals(rs.getString("duration")) &&
							date.equals(rs.getString("DateOfVisit"))){

						panel.removeAll();
						new PaymentInformationPanel(panel,con,p_username,basket);
						panel.validate();
						panel.repaint();
						break;
					}
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


			//rs.close();
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class AddToBucket implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String medName = textField.getText();
			String dosage = (String)comboBox.getSelectedItem();
			String durationM = (String)comboBox_2.getSelectedItem();
			String durationD = (String)comboBox_3.getSelectedItem();
			String duration = durationM +"/" +durationD;        // FORMAT!!! errata
			String doctor = textField_1.getText();
			String date = textField_2.getText();

			Statement stmt = null;

			System.out.println("Creating statement...");
			try {
				stmt = con.createStatement();
			} catch (SQLException k) {
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			String sql = null;


			sql = "SELECT D_LicenseNumber,P_Username,DateOfVisit,MedicineName,Dosage,Duration FROM Prescription";
			//sql2 = 
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					if (p_username.equals(rs.getString("P_Username")) && dosage.equals(rs.getString("Dosage")) && duration.equals(rs.getString("duration")) &&
							date.equals(rs.getString("DateOfVisit")) && medName.equals(rs.getString("MedicineName"))){
						basket.add(medName);
						System.out.println("Success!");
						break;
					}
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace(); }

			//rs.close();
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
