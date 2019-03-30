package journalSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class AdminFinalPaperApproval {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFinalPaperApproval window = new AdminFinalPaperApproval();
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
	public AdminFinalPaperApproval() {
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
		
		JLabel lblApprovePaper = new JLabel("APPROVE PAPER");
		lblApprovePaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblApprovePaper.setBounds(302, 28, 130, 16);
		frame.getContentPane().add(lblApprovePaper);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(0, 6, 117, 29);
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
		btnLogout.setBounds(583, 0, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 74, 639, 346);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDownload.setBounds(32, 432, 145, 29);
		frame.getContentPane().add(btnDownload);
		btnDownload.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//LET ADMIN DOWNLOAD PAPER
	        }
	    });
		
		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnViewFeedback.setBounds(200, 432, 145, 29);
		frame.getContentPane().add(btnViewFeedback);
		btnViewFeedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//DISPLAY FEEDBACK STRING IN A JDIALOG BOX
	        }
	    });
		
		JButton btnAcceptPaper = new JButton("Accept Paper");
		btnAcceptPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAcceptPaper.setBounds(361, 432, 145, 29);
		frame.getContentPane().add(btnAcceptPaper);
		btnAcceptPaper.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//ACCEPT PAPER
	        }
	    });
		
		JButton btnRejectPaper = new JButton("Reject Paper");
		btnRejectPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnRejectPaper.setBounds(526, 432, 145, 29);
		frame.getContentPane().add(btnRejectPaper);
		btnRejectPaper.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//REJECT PAPER
	        }
	    });
	}
}
