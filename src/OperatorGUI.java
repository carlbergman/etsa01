import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OperatorGUI implements ActionListener {

	private JFrame frame;
	private JTextField name;
	private JButton addButton;
	protected BicycleGarageManager manager;

	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}
	
	public OperatorGUI() {
		frame = new JFrame("Add new user");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		name = new JTextField(5);
		panel.add(new JLabel("Name:"), BorderLayout.WEST);
		panel.add(name, BorderLayout.EAST);

		addButton = new JButton("Add user");
		addButton.addActionListener(this);
		panel.add(addButton, BorderLayout.SOUTH);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String n = name.getText();
		name.setText(null);
		informManager(n);
	}

	void informManager(String n) {
		manager.newUser(n);
	}
}
