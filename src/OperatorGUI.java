import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OperatorGUI implements ActionListener {

	private JFrame frame;
	private JButton addUserButton;
	private JButton removeUserButton;
	private JButton showAllUsersButton;
	private JButton searchForUserButton;
	private JButton addBikeButton;
	private JButton removeBikeButton;
	private JButton showAllBikesButton;
	private JButton searchForBikeButton;
	protected BicycleGarageManager manager;

	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}
	
	public OperatorGUI() {
		
		addUserButton = new JButton("Add user");
		addUserButton.addActionListener(this);
		
		removeUserButton = new JButton("Remove user");
		removeUserButton.addActionListener(this);
		
		showAllUsersButton = new JButton("Show all users");
		showAllUsersButton.addActionListener(this);
		
		searchForUserButton = new JButton("Search for a user");
		searchForUserButton.addActionListener(this);		
		
		addBikeButton = new JButton("Add bike");
		addBikeButton.addActionListener(this);
		
		removeBikeButton = new JButton("Remove bike");
		removeBikeButton.addActionListener(this);
		
		showAllBikesButton = new JButton("Show all bikes");
		showAllBikesButton.addActionListener(this);
		
		searchForBikeButton = new JButton("Search for a bike");
		searchForBikeButton.addActionListener(this);		
		
		
		frame = new JFrame("Operator interface");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel();
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(addUserButton)
					.addComponent(removeUserButton)
					.addComponent(showAllUsersButton)
					.addComponent(searchForUserButton)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(addBikeButton)
					.addComponent(removeBikeButton)
					.addComponent(showAllBikesButton)
					.addComponent(searchForBikeButton)
				)
		);
		
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(addUserButton)
					.addComponent(removeUserButton)
					.addComponent(showAllUsersButton)
					.addComponent(searchForUserButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(addBikeButton)
					.addComponent(removeBikeButton)
					.addComponent(showAllBikesButton)
					.addComponent(searchForBikeButton)
				)
		);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//String n = name.getText();
		//name.setText(null);
		//informManager(n);
	}

	void informManager(String n) {
		//manager.newUser(n, user);
	}
}
