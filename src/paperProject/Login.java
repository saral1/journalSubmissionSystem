package paperProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private JTextField tEmail;
	private JPasswordField tPass;
	private JLabel labelEmail;
	private JLabel labelPass;
	private JLabel labelTitle;
	private JButton bLogin;
	private JButton bCreateAuthor;
	private JButton bCreateReviewer;
	
	private dbConnection dbConn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//get connection to database
		dbConn = new dbConnection();
		
		//create the frame and contents
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tEmail = new JTextField();
		tEmail.setBounds(200, 107, 193, 26);
		frame.getContentPane().add(tEmail);
		tEmail.setColumns(10);
		
		tPass = new JPasswordField();
		tPass.setBounds(200, 145, 193, 26);
		frame.getContentPane().add(tPass);
		tPass.setColumns(10);
		
		labelEmail = new JLabel("Email Address:");
		labelEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		labelEmail.setBounds(89, 112, 99, 16);
		frame.getContentPane().add(labelEmail);
		
		labelPass = new JLabel("Password:");
		labelPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		labelPass.setBounds(89, 150, 77, 16);
		frame.getContentPane().add(labelPass);
		
		bLogin = new JButton("Login");
		bLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bLogin.setBounds(183, 195, 130, 29);
		frame.getContentPane().add(bLogin);
		
		labelTitle = new JLabel("SYSTEM LOGIN");
		labelTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		labelTitle.setBounds(171, 26, 162, 42);
		frame.getContentPane().add(labelTitle);

		bCreateReviewer = new JButton("Create Reviewer Account");
		bCreateReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bCreateReviewer.setBounds(40, 278, 193, 29);
		frame.getContentPane().add(bCreateReviewer);
		
		bCreateAuthor = new JButton("Create Author Account");
		bCreateAuthor.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bCreateAuthor.setBounds(277, 278, 187, 29);
		frame.getContentPane().add(bCreateAuthor);
		
		//add purposes to buttons
		addButtonPurpose();
	}
	private void addButtonPurpose() {
		bLogin.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	//check against user table
	        	String[] user = dbConn.validateUser(tEmail.getText(), new String(tPass.getPassword()));
	        	if(user[0].equals("")) {
	        		JOptionPane.showMessageDialog(null, "Your email or password is incorrect.");
	        	}else if(user[0].startsWith("AU")) { //Author
	        		frame.dispose();       		
	        		AuthorShell shell = new AuthorShell(user[1], user[2], dbConn);
	        		shell.frame.setVisible(true);
	        	}else if(user[0].startsWith("R")) { //Reviewer
//	        		JOptionPane.showMessageDialog(null, "You are a reviewer. Function to be implemented");
	        		ReviewerShell shell = new ReviewerShell(user[1], user[2], dbConn);
	        		shell.frame.setVisible(true);
	        	}else if(user[0].startsWith("AD")) { //Admin
//	        		JOptionPane.showMessageDialog(null, "You are an admin. Function to be implemented");
	        		AdminShell shell = new AdminShell(user[1], user[2], dbConn);
	        		shell.frame.setVisible(true);
	        	}
	        }
	    });

		
		//create a new reviewer
		bCreateReviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pop up a window to add info
				Object[] options = {"Submit", "Cancel"};
				JTextField emailField = new JTextField(20);
				JPasswordField passField = new JPasswordField(20);
				JPasswordField confirmPassField = new JPasswordField(20);
				JTextField firstName = new JTextField(20);
				JTextField lastName = new JTextField(20);
				
				Object[] message = { "Please enter your information:\n\nEnsure that your email is a university of research association email\n\n",
						"Email: ", emailField,
						"Password: ", passField,
						"Confirm Password: ", confirmPassField,
						"First Name: ", firstName,
						"Last Name: ", lastName,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				if(result == JOptionPane.OK_OPTION) {
					//error checking fields entered
					while(emailField.getText().length() <= 0 || !emailField.getText().contains("@") || emailField.getText().indexOf("@") == emailField.getText().length()-1) {
						JOptionPane.showMessageDialog(null, "Your email does not seem right");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && firstName.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "First name cannot be empty");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && lastName.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "Last name cannot be empty");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && !Arrays.equals(passField.getPassword(), confirmPassField.getPassword())) {
						JOptionPane.showMessageDialog(null, "Your passwords do not match");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					if(result == JOptionPane.OK_OPTION) {
						Object[] msg = { "Would you like to submit to be an reviewer:\n\n",
							"Email: "+ emailField.getText(),
							"First Name: "+ firstName.getText(),
							"Last Name: " + lastName.getText(),
						};
						int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if(confirm == JOptionPane.YES_OPTION){
							//add reviewer to database
							boolean addReviewer = dbConn.addReviewer(emailField.getText(),new String(passField.getPassword()),firstName.getText(),lastName.getText());
							if(addReviewer) {
								JOptionPane.showMessageDialog(null, "You have been submitted as a reviewer. Please wait for admin to review.");
							}else {
								JOptionPane.showMessageDialog(null, "There was an error adding you as a reviewer.");
							}
						}
					}
	
				}
			}
		});
		
		//create a new author
		bCreateAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pop up a window to add info
				Object[] options = {"Submit", "Cancel"};
				JTextField emailField = new JTextField(20);
				JPasswordField passField = new JPasswordField(20);
				JPasswordField confirmPassField = new JPasswordField(20);
				JTextField firstName = new JTextField(20);
				JTextField lastName = new JTextField(20);
				
				Object[] message = { "Please enter your information:\n\n",
						"Email: ", emailField,
						"Password: ", passField,
						"Confirm Password: ", confirmPassField,
						"First Name: ", firstName,
						"Last Name: ", lastName,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				if(result == JOptionPane.OK_OPTION) {
					//error checking fields entered
					while(emailField.getText().length() <= 0 || !emailField.getText().contains("@") || emailField.getText().indexOf("@") == emailField.getText().length()-1) {
						JOptionPane.showMessageDialog(null, "Your email does not seem right");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && firstName.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "First name cannot be empty");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && lastName.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "Last name cannot be empty");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while(result == JOptionPane.OK_OPTION && !Arrays.equals(passField.getPassword(), confirmPassField.getPassword())) {
						JOptionPane.showMessageDialog(null, "Your passwords do not match");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					if(result == JOptionPane.OK_OPTION) {
						Object[] msg = { "Would you like to submit to be an author:\n\n",
							"Email: "+ emailField.getText(),
							"First Name: "+ firstName.getText(),
							"Last Name: " + lastName.getText(),
						};
						int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if(confirm == JOptionPane.YES_OPTION){
							//add author to database
							boolean addAuthor = dbConn.addAuthor(emailField.getText(),new String(passField.getPassword()),firstName.getText(),lastName.getText());
							if(addAuthor) {
								JOptionPane.showMessageDialog(null, "You account as an author has been created.");
							}else {
								JOptionPane.showMessageDialog(null, "There was an error adding you as an author.");
							}
						}
					}
				}
			}
		});
		
	}
}
