import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Insets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class MessageInbox{
	private JTable table;
	JPanel panel;
	Connection con;
	String username;

	public MessageInbox(JPanel panel, Connection con, String username) {
		this.panel =panel;
		this.con=con;
		this.username = username;
		
		panel.setLayout(new FlowLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Status");
		model.addColumn("DateTime");
		model.addColumn("From");
		model.addColumn("Message");
		JButton btnMessages = new JButton("back");
		panel.add(btnMessages);
		btnMessages.addActionListener(new backButtonListner());
		
		String query = "SELECT FirstName, LastName, Status, Datetime, Content FROM Doctor, Sends_message_to_Pat WHERE P_Username ='" + username +"' AND LicenseNumber = D_LicenseNumber ";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString(3));
				vector.add(rs.getString(4));
				vector.add(rs.getString(1)+rs.getString(2));
				vector.add(rs.getString(5));
				
				model.addRow(vector);
			}
			
			JTable table = new JTable(model);
			JScrollPane pane = new JScrollPane(table);
			panel.add(pane);
			
			panel.revalidate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
		private class backButtonListner implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			panel.removeAll();
			new PatientMenuPanel(panel,con,username);
			panel.validate();
			panel.repaint();

	}
	}


	
}
