package paperProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

public class AdminAssignReviewers {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAssignReviewers window = new AdminAssignReviewers();
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
	public AdminAssignReviewers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AdminViewUnassignedPapers shell = new AdminViewUnassignedPapers();
	        		shell.frame.setVisible(true);
	        }
	    });
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(577, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
		
		JLabel lblNewLabel = new JLabel("ASSIGN REVIEWERS");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(271, 28, 159, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 82, 313, 150);
		frame.getContentPane().add(scrollPane);
		
		JList preferred = new JList();
		preferred.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preferred.setBackground(SystemColor.window);
		preferred.setForeground(Color.BLACK);
		scrollPane.setViewportView(preferred);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(354, 82, 325, 150);
		frame.getContentPane().add(scrollPane_1);
		
		JList conflict = new JList();
		conflict.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		conflict.setBackground(SystemColor.window);
		scrollPane_1.setViewportView(conflict);
		
		JLabel lblAuthorPreferredReviewers = new JLabel("Author Preferred Reviewers:");
		lblAuthorPreferredReviewers.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblAuthorPreferredReviewers.setBounds(24, 66, 191, 16);
		frame.getContentPane().add(lblAuthorPreferredReviewers);
		
		JLabel lblConflictOfInterest = new JLabel("Conflict of Interest Reviewers:");
		lblConflictOfInterest.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblConflictOfInterest.setBounds(354, 66, 212, 16);
		frame.getContentPane().add(lblConflictOfInterest);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 244, 655, 182);
		frame.getContentPane().add(scrollPane_2);
		
		JList reviewers = new JList();
		reviewers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(reviewers);
		
		JButton btnAssignReviewer = new JButton("Assign Reviewer");
		btnAssignReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAssignReviewer.setBounds(279, 438, 137, 29);
		frame.getContentPane().add(btnAssignReviewer);
		btnAssignReviewer.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	reviewers.getSelectedValue();
	        		//ADD THE SELECTED REVIEWERS TO THE PAPER
	        }
	    });
		
	}

}
