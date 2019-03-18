package paperProject;

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

public class AdminApproveFeedback {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApproveFeedback window = new AdminApproveFeedback();
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
	public AdminApproveFeedback() {
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
		
		JLabel lblApproveFeedback = new JLabel("APPROVE FEEDBACK");
		lblApproveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblApproveFeedback.setBounds(274, 31, 161, 16);
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
		
		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnViewFeedback.setBounds(49, 443, 150, 29);
		frame.getContentPane().add(btnViewFeedback);
		btnViewFeedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//DISPLAY FEEDBACK STRING IN A JDIALOG BOX
	        }
	    });
		
		JButton btnApproveFeedback = new JButton("Approve Feedback");
		btnApproveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnApproveFeedback.setBounds(274, 443, 150, 29);
		frame.getContentPane().add(btnApproveFeedback);
		
		JButton btnDownloadPaper = new JButton("Download Paper");
		btnDownloadPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDownloadPaper.setBounds(509, 443, 150, 29);
		frame.getContentPane().add(btnDownloadPaper);
		btnDownloadPaper.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//ALLOW THE ADMIN TO DOWNLOAD THE PAPER
	        }
	    });
		btnApproveFeedback.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	list.getSelectedValue();
	        		//APPROVE THE FEEDBACK TO MAKE IT OFFICIAL AND VIEWABLE BY THE AUTHOR
	        }
	    });
	}

}
