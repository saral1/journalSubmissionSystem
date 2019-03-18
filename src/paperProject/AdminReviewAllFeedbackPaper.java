package paperProject;

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

public class AdminReviewAllFeedbackPaper {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminReviewAllFeedbackPaper window = new AdminReviewAllFeedbackPaper();
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
	public AdminReviewAllFeedbackPaper() {
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
		
		JLabel lblApproveFeedback = new JLabel("REVIEW ALL FEEDBACK");
		lblApproveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblApproveFeedback.setBounds(261, 30, 188, 16);
		frame.getContentPane().add(lblApproveFeedback);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AdminFinalPaperApproval shell = new AdminFinalPaperApproval();
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
		
		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnViewFeedback.setBounds(283, 443, 150, 29);
		frame.getContentPane().add(btnViewFeedback);
		btnViewFeedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		list.getSelectedValue();
	        		//DISPLAY FEEDBACK STRING IN A JDIALOG BOX
	        }
	    });
		
	}

}
