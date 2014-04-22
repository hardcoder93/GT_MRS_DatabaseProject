import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class DoctorMenuPanel extends JPanel {

	private Connection con;
	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public DoctorMenuPanel(JPanel panel, Connection con) {
		this.con = con; this.panel = panel;
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblHomepageForDoctors = new JLabel("Homepage for Doctors");
		lblHomepageForDoctors.setBackground(Color.WHITE);
		lblHomepageForDoctors.setForeground(Color.GRAY);
		lblHomepageForDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHomepageForDoctors);
		
		
		JButton btnViewAppointmentsCalendar = new JButton("View Appointments Calendar");
		panel.add(btnViewAppointmentsCalendar);
		btnViewAppointmentsCalendar.addActionListener(new AddViewAppointmentsCalendar());
		
		JButton btnPatientVisits = new JButton("Patient Visits");
		panel.add(btnPatientVisits);
		btnPatientVisits.addActionListener(new AddPatientVisits());
		
		JButton btnRecordASurgery = new JButton("Record A Surgery");
		panel.add(btnRecordASurgery);
		btnRecordASurgery.addActionListener(new AddRecordASurgery());
		
		JButton btnCommunicate = new JButton("Communicate");
		panel.add(btnCommunicate);
		btnCommunicate.addActionListener(new AddCommunicate());
		
		JButton btnEditProfile = new JButton("Edit Profile");
		panel.add(btnEditProfile);
		btnEditProfile.addActionListener(new AddEditProfile());

	}
	
	
	private class AddViewAppointmentsCalendar implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}
	private class AddPatientVisits implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}	
	private class AddRecordASurgery implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}

	private class AddCommunicate implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}	
	private class AddEditProfile implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			new CreateAccountPanel(panel,con);
			panel.validate();
			panel.repaint();
		}
	}

}
