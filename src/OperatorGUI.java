import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OperatorGUI {

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
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserButtonPressed();
			}
		});

		removeUserButton = new JButton("Remove user");
		removeUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeUserButtonPressed();
			}
		});

		showAllUsersButton = new JButton("Show all users");
		showAllUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllUsersButtonPressed();
			}
		});

		searchForUserButton = new JButton("Search for a user");
		searchForUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchForUserButtonPressed();
			}
		});

		addBikeButton = new JButton("Add bike");
		addBikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBikeButtonPressed();
			}
		});

		removeBikeButton = new JButton("Remove bike");
		removeBikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeBikeButtonPressed();
			}
		});

		showAllBikesButton = new JButton("Show all bikes");
		showAllBikesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBikesButtonPressed();
			}
		});

		searchForBikeButton = new JButton("Search for a bike");
		searchForBikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchForBikeButtonPressed();
			}
		});

		frame = new JFrame("Operator interface");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel();

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup()
								.addComponent(addUserButton)
								.addComponent(removeUserButton)
								.addComponent(showAllUsersButton)
								.addComponent(searchForUserButton))
				.addGroup(
						layout.createParallelGroup()
								.addComponent(addBikeButton)
								.addComponent(removeBikeButton)
								.addComponent(showAllBikesButton)
								.addComponent(searchForBikeButton)));

		layout.setVerticalGroup(layout
				.createParallelGroup()
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(addUserButton)
								.addComponent(removeUserButton)
								.addComponent(showAllUsersButton)
								.addComponent(searchForUserButton))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(addBikeButton)
								.addComponent(removeBikeButton)
								.addComponent(showAllBikesButton)
								.addComponent(searchForBikeButton)));

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	protected void searchForBikeButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void showAllBikesButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void removeBikeButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void addBikeButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void searchForUserButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void showAllUsersButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void removeUserButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	protected void addUserButtonPressed() {
		String name;
		String ssn;
		
		JFrame frame = new JFrame("Add user");
		
		do {
			name = JOptionPane.showInputDialog(frame, "Name:");
			if (name == null) {
				return;
			}
		} while (!name.matches("^[\\pL\\s]+$"));
		
		do {
			ssn = JOptionPane.showInputDialog(frame, "Social security number (preferred format YYMMDD-NNNN):");
			if (ssn == null) {
				return;
			}
		} while (!ssn.matches("^([0-9]{6}[-+]{1}[0-9]{4})$"));
		
		JOptionPane.showMessageDialog(frame, manager.newUser(name, ssn));

	}
}
