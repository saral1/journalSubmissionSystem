package journalSystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AdminViewUnassignedPapers {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewUnassignedPapers window = new AdminViewUnassignedPapers();
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
	public AdminViewUnassignedPapers() {
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
		
		JLabel lblApproveFeedback = new JLabel("UNASSIGNED PAPERS");
		lblApproveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblApproveFeedback.setBounds(264, 31, 171, 16);
		frame.getContentPane().add(lblApproveFeedback);
		
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
		btnLogout.setBounds(577, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 70, 647, 361);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnAssignReviewers = new JButton("Assign Reviewers");
		btnAssignReviewers.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAssignReviewers.setBounds(421, 443, 150, 29);
		frame.getContentPane().add(btnAssignReviewers);
		
		JButton btnDownloadPaper = new JButton("Download Paper");
		btnDownloadPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.getSelectedValue();
				//ALLOW ADMIN TO DOWNLOAD PAPER
			}
		});
		btnDownloadPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDownloadPaper.setBounds(139, 443, 150, 29);
		frame.getContentPane().add(btnDownloadPaper);
		btnAssignReviewers.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        	//KEEP TRACK OF WHICH PAPER WAS SELECTED TO DISPLAY IN THE FRAME CREATED BELOW
	        	frame.dispose();
	        	AdminReviewAllFeedbackPaper shell = new AdminReviewAllFeedbackPaper();
        		shell.frame.setVisible(true);
	        }
	    });
	}

}
