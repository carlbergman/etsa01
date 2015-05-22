import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The class handling the operator graphical user interface.
 * @author carlbergman
 *
 */
public class OperatorGUI {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu addMenu;
	private JMenu removeMenu;
	private JMenu findMenu;
	private JMenu showMenu;
	private JMenu quitMenu;
	private JMenuItem addUser;
	private JMenuItem removeUser;
	private JMenuItem showAllUsers;
	private JMenuItem searchForUser;
	private JMenuItem addBike;
	private JMenuItem removeBike;
	private JMenuItem showAllBikes;
	private JMenuItem searchForBike;
	private JMenuItem showLog;
	private JMenuItem quit;
	private JTextArea textArea;
	protected BicycleGarageManager manager;

	/**
	 * Register the class managing this bicycle garage
	 * @param manager The manager
	 */
	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}

	/**
	 * Constructor for the GUI.
	 * Adds a window and some listeners.
	 */
	public OperatorGUI() {

		// Create a frame
		frame = new JFrame("Operator interface");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(500,500);
		
		// Add a text area for displaying information
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setVisible(true);
		
		JScrollPane panel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(panel);
		
		// Create the menu bar		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		// Add menus to the menu bar
		addMenu = new JMenu("Add");
		removeMenu = new JMenu("Remove");
		findMenu = new JMenu("Find");
		showMenu = new JMenu("Show");
		quitMenu = new JMenu("Quit");
		menuBar.add(addMenu);
		menuBar.add(removeMenu);
		menuBar.add(findMenu);
		menuBar.add(showMenu);
		menuBar.add(quitMenu);
		
		// Add menu items to each menu
		addUser = new JMenuItem("Add user");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserButtonPressed();
			}
		});
		addMenu.add(addUser);
		
		removeUser = new JMenuItem("Remove user");
		removeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeUserButtonPressed();
			}
		});
		removeMenu.add(removeUser);

		showAllUsers = new JMenuItem("Show all users");
		showAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllUsersButtonPressed();
			}
		});
		showMenu.add(showAllUsers);
		
		searchForUser= new JMenuItem("Search for a user");
		searchForUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchForUserButtonPressed();
			}
		});
		findMenu.add(searchForUser);
		
		addBike= new JMenuItem("Add bike");
		addBike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBikeButtonPressed();
			}
		});
		addMenu.add(addBike);
		
		removeBike = new JMenuItem("Remove bike");
		removeBike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeBikeButtonPressed();
			}
		});
		removeMenu.add(removeBike);
		
		showAllBikes = new JMenuItem("Show all bikes");
		showAllBikes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBikesButtonPressed();
			}
		});
		showMenu.add(showAllBikes);
		
		searchForBike = new JMenuItem("Search for a bike");
		searchForBike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchForBikeButtonPressed();
			}
		});
		findMenu.add(searchForBike);
		
		showLog = new JMenuItem("Show log");
		showLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLogButtonPressed();
			}
		});
		showMenu.add(showLog);
		
		quit = new JMenuItem("Save and quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitButtonPressed();
			}
		});
		quitMenu.add(quit);

		frame.setVisible(true);
	}

	/**
	 * Search for a bike
	 */
	protected void searchForBikeButtonPressed() {
		textArea.append("\n> Search for a bike �\n");
		
		String bikeId;
		Bike bike;
		
		try {
			bikeId = getBikeId();
		} catch (NullPointerException e) {
			textArea.append("Cancelled.\n");
			return;
		}
		
		bike = manager.getBike(bikeId);
		
		if (bike != null) {
			textArea.append(bike.toString() + "\n");
		} else {
			textArea.append("No bike with barcode " + bikeId + " found. \n");
		}
	}

	/**
	 * Show all bikes
	 */
	protected void showAllBikesButtonPressed() {
		textArea.append("\n> Show all bikes �\n");
		
		ArrayList<Bike> bikes = manager.getAllBikes();
		
		textArea.append("There are " + bikes.size() + " bikes in the system.\n");
		
		for (Bike b : bikes) {
			textArea.append(b.toString() + "\n");
		}
	}

	/**
	 * Remove a bike
	 */
	protected void removeBikeButtonPressed() {
		textArea.append("\n> Remove bike �\n");
		
		String bikeId;
		
		try {
			bikeId = getBikeId();
		} catch (NullPointerException e) {
			textArea.append("Cancelled.\n");
			return;
		}

		if (manager.removeBike(bikeId)) {
			textArea.append("The bike with barcode " + bikeId + " was removed.\n");
		} else {
			textArea.append("No bike found with barcode " + bikeId + ".\n");
		}
	}

	/**
	 * Add a bike
	 */
	protected void addBikeButtonPressed() {
		textArea.append("\n> Add bike �\n");
		
		String ssn;
		String name;
		ArrayList<User> users = new ArrayList<User>();;
		
		// Get the social security number of the owner.
		try {
			ssn = getSsn();
		} catch (NullPointerException noSsn) {
			textArea.append("Cancelled.\n");
			return;
		}
		
		// Find all users with that ssn.
		try {
			users = manager.searchUser(ssn);
		} catch (Exception e) {
			textArea.append(e.getMessage() + "\n");
			
			// Add new user?
			int i = JOptionPane.showConfirmDialog(frame, "No user with that social security number exists. Do you want to add a new user?");
			// 0 = Yes
			// 1 = No
			// 2 = Cancel
			// -1 = Kryss
			
			if (i == 0) { // Yes
				// Get name
				try {
					name = getName();
				} catch (NullPointerException noName) {
					textArea.append("Cancelled.\n");
					return;
				}
				
				// Create user
				try {
					textArea.append(name + " (" + ssn + ")" +  " was added with pin code " + manager.newUser(name, ssn) + "\n");
				} catch (Exception userExists) {
					textArea.append(userExists.getMessage() + "\n");
					return;
				}
				
				// Get that user object
				try {
					users = manager.searchUser(ssn);
				} catch (Exception notfound) {
					textArea.append(notfound.getMessage() + "\n");
				}
		
			} else { // No
				return;
			}
			
		}
		
		// If there weren't only one hit.
		if (users.size() != 1) {
			textArea.append("An unexpected error occurred.\n");
			return;
		}

		// Get that user.
		User u = users.get(0);
		
		// Add the bike and show the result to the operator
		String s = manager.newBike(u).toString();
		textArea.append("New bike added:\n");
		textArea.append(s + "\n");
	}

	/**
	 * Search for a user
	 */
	protected void searchForUserButtonPressed() {
		textArea.append("\n> Search for a user �\n");
		
		String str;
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			str = JOptionPane.showInputDialog(new JFrame("Search for user"),
					"Username, PIN, or social security number (YYMMDD-NNNN):");
		} catch (NullPointerException e) {
			return;
		}
		
		try {
			users = manager.searchUser(str);
		} catch (Exception notfound) {
			textArea.append(notfound.getMessage() + "\n");
			return;
		}
		
		textArea.append(users.size() + " users found.\n");
		
		for (User u : users) {
			textArea.append(u.toString() + "\n");
		}
	}

	/**
	 * Show all users
	 */
	protected void showAllUsersButtonPressed() {
		textArea.append("\n> Show all users �\n");
		
		ArrayList<User> users = manager.getAllUsers();
		
		textArea.append("There are " + users.size() + " users in the system.\n");
		
		for (User u : users) {
			textArea.append(u.toString() + "\n");
		}
	}

	/**
	 * Remove a user
	 */
	protected void removeUserButtonPressed() {
		textArea.append("\n> Remove user �\n");
		
		String ssn;
		
		try {
			ssn = getSsn();
		} catch (NullPointerException e) {
			textArea.append("Cancelled.\n");
			return;
		}
		
		try {
			manager.removeUser(ssn);
			textArea.append("The user with social security number " + ssn + " was removed.\n");
		} catch (Exception e) {
			textArea.append(e.getMessage() + "\n");
		}
	}

	/**
	 * Add a user
	 */
	protected void addUserButtonPressed() {
		textArea.append("\n> Add user �\n");
		
		String name;
		String ssn;
		
		try {
			name = getName();
		} catch (NullPointerException e) {
			textArea.append("Cancelled.\n");
			return;
		}
		
		try {
			ssn = getSsn();
		} catch (NullPointerException e) {
			textArea.append("Cancelled.\n");
			return;
		}	
		
		try {
			textArea.append("Added " + name + " (" + ssn + ")" +  " with pin code " + manager.newUser(name, ssn) + "\n");
		} catch (Exception e) {
			textArea.append(e.getMessage() + "\n");
		}
	}
	
	/**
	 * Show the log
	 */
	protected void showLogButtonPressed() {
		textArea.append("\n> Show log �\n");
		
		ArrayList<InOutLog> log = manager.getLog();
		
		textArea.append("There are " + log.size() + " logged events.\n");
		
		for(InOutLog e : log) {
			textArea.append(e.toString() + "\n");
		}
	}
	
	protected void quitButtonPressed() {
		textArea.append("\n> Quit �\n");
		
		manager.quit();		
	}

	/**
	 * Prompt the user for a name
	 * @return A string
	 */
	protected String getName() {
		String name;

		do {
			name = JOptionPane.showInputDialog(new JFrame("Get name"), "Name:");
		} while (!name.matches("^[\\pL\\s]+$"));

		return name;
	}

	/**
	 * Prompt the user for a social security number
	 * @return A string
	 */
	protected String getSsn() {
		String ssn;
		
		do {
			ssn = JOptionPane.showInputDialog(new JFrame("Get social security number"),
					"Social security number (YYMMDD-NNNN):");
		} while (!ssn.matches("^([0-9]{6}[-+]{1}[0-9]{4})$"));

		return ssn;
	}
	
	/**
	 * Prompt the user for a bike id (=bar code)
	 * @return A string
	 */
	protected String getBikeId() {
		String bikeId;
		
		do {
			bikeId = JOptionPane.showInputDialog(new JFrame("Get bike barcode"),
					"Barcode (a code with five digits):");
		} while (!bikeId.matches("^[0-9]{5}$"));

		return bikeId;
	}
}
