package paperProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class AdminReviewAccountCreations {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminReviewAccountCreations window = new AdminReviewAccountCreations();
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
	public AdminReviewAccountCreations() {
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
		
		JLabel lblReviewAccountCreations = new JLabel("REVIEW ACCOUNT CREATIONS");
		lblReviewAccountCreations.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblReviewAccountCreations.setBounds(235, 33, 243, 16);
		frame.getContentPane().add(lblReviewAccountCreations);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AdminShell shell = new AdminShell();
	        		shell.frame.setVisible(true);
	        }
	    });
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(583, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 77, 642, 338);
		frame.getContentPane().add(scrollPane);
		
		JList createdAccounts = new JList();
		createdAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(createdAccounts);
		
		JButton btnAcceptAccount = new JButton("Accept Account");
		btnAcceptAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAcceptAccount.setBounds(168, 431, 134, 29);
		frame.getContentPane().add(btnAcceptAccount);
		btnAcceptAccount.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	createdAccounts.getSelectedValue();
	        		//ADD ACCOUNT AS OFFICIAL REVIEWER ACCOUNT IN DATABASE
	        }
	    });
		
		JButton btnRejectAccount = new JButton("Reject Account");
		btnRejectAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnRejectAccount.setBounds(398, 431, 127, 29);
		frame.getContentPane().add(btnRejectAccount);
		btnRejectAccount.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	createdAccounts.getSelectedValue();
	        		//REMOVE ACCOUNT FROM DATABASE 
	        }
	    });
	}
}
