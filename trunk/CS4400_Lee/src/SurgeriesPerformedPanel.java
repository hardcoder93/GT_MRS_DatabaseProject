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


public class SurgeriesPerformedPanel extends JPanel {
	private JPanel panel;
	private Connection con;
	
	public SurgeriesPerformedPanel(JPanel panel, Connection con){
		this.panel = panel;
		this.con = con;
		
		panel.setLayout(new FlowLayout());
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Surgery Type");
		model.addColumn("CPT code");
		model.addColumn("# of Procedures");
		model.addColumn("# of Doctors");
		model.addColumn("Total Billing");
		
		String query = "SELECT SurgeryType, p.CPTcode, COUNT(p.CPTcode), COUNT(NumberOfAssistants), SUM(CostOfSurgery) FROM Surgery AS s, Performs AS p WHERE s.CPTcode = p.CPTcode AND SurgeryStartTime BETWEEN DATE_ADD(CURDATE(), INTERVAL -3 MONTH)  AND CURDATE() GROUP BY p.CPTcode ";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString(1));
				vector.add(rs.getString(2));
				vector.add(rs.getString(3));
				vector.add(rs.getString(4));
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
}
