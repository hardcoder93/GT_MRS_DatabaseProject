import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DoctorPerformancePanel extends JPanel {
	private JPanel panel;
	private Connection con;
	
	public DoctorPerformancePanel(JPanel panel, Connection con){
		this.panel = panel;
		this.con = con;
		panel.setLayout(new FlowLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Specialty");
		model.addColumn("Average Rating");
		model.addColumn("# of Surgeries");
		
		String query = "Select Specialty, rate, Count(p.D_LicenseNumber) FROM (SELECT Specialty, AVG(Rating) as rate,LicenseNumber FROM Doctor as d, Rates as r WHERE LicenseNumber = r.D_licenseNumber GROUP BY Specialty) as dr INNER JOIN Performs as p on dr.LicenseNumber = p.D_LicenseNumber GROUP BY Specialty"; 
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rates = stmt.executeQuery(query);
			
			while(rates.next()){
				Vector<String> vector = new Vector<String>();
				vector.add(rates.getString(1));
				vector.add(rates.getString(2));
				vector.add(rates.getString(3));
				
				model.addRow(vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		panel.add(pane);
		panel.repaint();
	}
}