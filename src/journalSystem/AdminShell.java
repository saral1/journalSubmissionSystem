package journalSystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminShell {

	public JFrame frame;
	private dbConnection dbConn;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminShell window = new AdminShell();
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
	public AdminShell() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public AdminShell(User u, dbConnection conn) {
		this.user = u;
		this.dbConn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Username = new JLabel("Welcome");
		Username.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		Username.setBounds(178, 37, 83, 16);
		frame.getContentPane().add(Username);
		//CHANGE BASED ON LOGGED IN USERNAME ^^
		
		JButton btnReviewAccounts = new JButton("Review Created Accounts");
		btnReviewAccounts.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnReviewAccounts.setBounds(123, 90, 190, 29);
		frame.getContentPane().add(btnReviewAccounts);
		btnReviewAccounts.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AdminApproveReviewer paper = new AdminApproveReviewer(user, dbConn);
	        		paper.frame.setVisible(true);
	        }
	    });
		
		JButton btnAssignReviewers = new JButton("Assign Reviewers");
		btnAssignReviewers.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAssignReviewers.setBounds(123, 245, 190, 29);
		frame.getContentPane().add(btnAssignReviewers);
		btnAssignReviewers.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AdminViewUnassignedPapers status = new AdminViewUnassignedPapers();
	        		status.frame.setVisible(true);
	        }
	    });
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(327, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		
		JButton btnApprovePapers = new JButton("Approve Papers");
		btnApprovePapers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
        		AdminFinalPaperApproval status = new AdminFinalPaperApproval();
        		status.frame.setVisible(true);
			}
		});
		btnApprovePapers.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnApprovePapers.setBounds(123, 194, 190, 29);
		frame.getContentPane().add(btnApprovePapers);
		
		JButton btnApproveFeedback = new JButton("Approve Feedback");
		btnApproveFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
        		AdminApproveFeedback status = new AdminApproveFeedback();
        		status.frame.setVisible(true);
			}
		});
		btnApproveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnApproveFeedback.setBounds(123, 142, 190, 29);
		frame.getContentPane().add(btnApproveFeedback);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
	}

}
