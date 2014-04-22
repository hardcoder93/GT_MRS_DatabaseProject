import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;


public class PaymentInformationPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel panel; private Connection con;

	/**
	 * Create the panel.
	 */
	public PaymentInformationPanel(JPanel panel, Connection con) {
		this.panel = panel;
		this.con = con;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{42, 42, 42, 42, 42, 42, 42, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel label = new JLabel("Payment Information");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(128, 0, 128));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);

		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		panel.add(splitPane, gbc_splitPane);

		JLabel label_1 = new JLabel("Card holder's name");
		splitPane.setLeftComponent(label_1);

		textField = new JTextField();
		textField.setColumns(10);
		splitPane.setRightComponent(textField);

		JSplitPane splitPane_1 = new JSplitPane();
		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_1.gridx = 0;
		gbc_splitPane_1.gridy = 2;
		panel.add(splitPane_1, gbc_splitPane_1);

		JLabel label_2 = new JLabel("Card Number");
		splitPane_1.setLeftComponent(label_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		splitPane_1.setRightComponent(textField_1);

		JSplitPane splitPane_2 = new JSplitPane();
		GridBagConstraints gbc_splitPane_2 = new GridBagConstraints();
		gbc_splitPane_2.fill = GridBagConstraints.BOTH;
		gbc_splitPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_2.gridx = 0;
		gbc_splitPane_2.gridy = 3;
		panel.add(splitPane_2, gbc_splitPane_2);

		JLabel lblTypeOfCard = new JLabel("Type of Card");
		splitPane_2.setLeftComponent(lblTypeOfCard);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"VISA", "MASTER", "AMEX"}));
		splitPane_2.setRightComponent(comboBox);

		JSplitPane splitPane_3 = new JSplitPane();
		GridBagConstraints gbc_splitPane_3 = new GridBagConstraints();
		gbc_splitPane_3.fill = GridBagConstraints.BOTH;
		gbc_splitPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_3.gridx = 0;
		gbc_splitPane_3.gridy = 4;
		panel.add(splitPane_3, gbc_splitPane_3);

		JLabel label_4 = new JLabel("CVV");
		splitPane_3.setLeftComponent(label_4);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		splitPane_3.setRightComponent(textField_3);

		JSplitPane splitPane_4 = new JSplitPane();
		GridBagConstraints gbc_splitPane_4 = new GridBagConstraints();
		gbc_splitPane_4.fill = GridBagConstraints.BOTH;
		gbc_splitPane_4.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane_4.gridx = 0;
		gbc_splitPane_4.gridy = 5;
		panel.add(splitPane_4, gbc_splitPane_4);

		JLabel lblDateOfExpiry = new JLabel("Date of Expiry");
		splitPane_4.setLeftComponent(lblDateOfExpiry);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		splitPane_4.setRightComponent(textField_4);

		JButton button = new JButton("Order");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 0;
		gbc_button.gridy = 6;
		panel.add(button, gbc_button);

	}

}