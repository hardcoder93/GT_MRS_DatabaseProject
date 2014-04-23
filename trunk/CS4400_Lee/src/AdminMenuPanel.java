import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;
import java.sql.Connection;


public class AdminMenuPanel extends JPanel {
	private JPanel panel;
	private Connection con;
	/**
	 * Create the panel.
	 */
	public AdminMenuPanel(JPanel panel, Connection con) {
		this.panel = panel; this.con = con;
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblAdminHome = new JLabel("Administrative Home");
		lblAdminHome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAdminHome);
		
		JButton btnNewButton = new JButton("Billing");
		panel.add(btnNewButton);
		
		JButton btnDoctorperformancereport = new JButton("Doctor Performance Report");
		panel.add(btnDoctorperformancereport);
		
		JButton btnSurgeryReport = new JButton("Surgery Report");
		panel.add(btnSurgeryReport);
		
		JButton btnPatientVisitReport = new JButton("Patient Visit Report");
		btnPatientVisitReport.setBackground(new Color(240, 255, 255));
		panel.add(btnPatientVisitReport);

	}

}
