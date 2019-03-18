package paperProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AuthorShell {

	public JFrame frame;
	private String firstName;
	private String lastName;
	private dbConnection dbConn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorShell window = new AuthorShell();
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
	public AuthorShell() {
		initialize();
	}
	
	public AuthorShell(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		initialize();
	}
	/**
	 * Create the application.
	 */
	public AuthorShell(String firstName, String lastName, dbConnection dbConn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dbConn = dbConn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Username = new JLabel("Welcome");
		if(firstName != null) {
			Username.setText("Welcome " + firstName + " " + lastName);
		}
		Username.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		Username.setBounds(163, 37, 200, 16);
		frame.getContentPane().add(Username);
		//CHANGE BASED ON LOGGED IN USERNAME ^^
		
		JButton btnSubmitPaper = new JButton("Submit Paper");
		btnSubmitPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSubmitPaper.setBounds(133, 120, 180, 29);
		frame.getContentPane().add(btnSubmitPaper);
		btnSubmitPaper.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		SubmitPaper paper = new SubmitPaper(firstName, lastName, dbConn);
	        		paper.frame.setVisible(true);
	        }
	    });
		
		JButton btnViewStatus = new JButton("View Status");
		btnViewStatus.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnViewStatus.setBounds(133, 183, 180, 29);
		frame.getContentPane().add(btnViewStatus);
		btnViewStatus.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		SubmissionStatus status = new SubmissionStatus(firstName, lastName, dbConn);
	        		status.frame.setVisible(true);
	        }
	    });
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(327, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
	}

}
