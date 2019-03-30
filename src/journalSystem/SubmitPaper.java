package journalSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.List;

public class SubmitPaper {

	public JFrame frame;
	private dbConnection dbConn;
	private User user;
	
	private JTextField fileName;
	private JTextField paperTitle;
	
	private JButton btnUpload;

	private JLabel topLabel;
	private JLabel titleLabel;
	
	private JButton btnLogout;
	private JButton btnSubmit;
	private JButton btnBack;
	
	private File fileSelected;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmitPaper window = new SubmitPaper();
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
	public SubmitPaper() {
		initialize();
	}
	/**
	 * Create the application.
	 */
	public SubmitPaper(User u, dbConnection conn) {
		this.user = u;
		this.dbConn = conn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 870, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		topLabel = new JLabel("Please select your paper as a pdf to submit");
		topLabel.setBounds(41, 105, 500, 20);
		frame.getContentPane().add(topLabel);
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnUpload.setBounds(459, 124, 117, 29);
		frame.getContentPane().add(btnUpload);
		
		fileName = new JTextField();
		fileName.setEditable(false);
		fileName.setBounds(37, 124, 315, 26);
		frame.getContentPane().add(fileName);
		fileName.setColumns(10);
		
		titleLabel = new JLabel("Title of your paper:");
		titleLabel.setBounds(41, 52, 500, 50);
		frame.getContentPane().add(titleLabel);
		
		paperTitle = new JTextField();
		paperTitle.setEditable(true);
		paperTitle.setBounds(200, 64, 370, 26);
		frame.getContentPane().add(paperTitle);
		paperTitle.setColumns(10);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnLogout.setBounds(705, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSubmit.setBounds(37, 250, 180, 29);
		frame.getContentPane().add(btnSubmit);
		
		if(user != null && user.getfirstName() != null) {
			JLabel nameLabel = new JLabel(user.getfirstName() + " " + user.getLastName());
			nameLabel.setBounds(630,6,117,29);
			frame.getContentPane().add(nameLabel);
		}
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(34, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		
		addButtonPurposes();
	}
	private void addButtonPurposes() {
		btnUpload.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser fc = new JFileChooser();
	            fc.setVisible(true);
	            int val = fc.showDialog(null,"Choose");
	            if(val == JFileChooser.APPROVE_OPTION) {
	            	fileSelected = fc.getSelectedFile();
	            	if(!fileSelected.getName().endsWith("pdf")) {
	            		JOptionPane.showMessageDialog(null, "Please submit a pdf version of your paper.");
	            	}else {
	            		fileName.setText(fileSelected.getName());
	            	}
	            }
	        }
	    });
		
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AuthorShell shell = new AuthorShell(user, dbConn);
	        		shell.frame.setVisible(true);
	        }
	    });	
		
		btnSubmit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	if(paperTitle.getText().length() <= 0) {
	        		JOptionPane.showMessageDialog(null, "Your paper title cannot be empty");
	        	}else if(fileName.getText().length() <= 0) {
	        		JOptionPane.showMessageDialog(null, "Please select your paper to submit");
	        	}else {
	        		//submit paper
	        		if(dbConn.submitPaper(user, paperTitle.getText(), fileSelected)) {
	        			JOptionPane.showMessageDialog(null, "Thanks for your submission! Please select your reviewers if you have not done so, a member of our team will review your submission shortly.");
	        			frame.dispose();
		        		AuthorShell shell = new AuthorShell(user, dbConn);
		        		shell.frame.setVisible(true);
	        		}else {
	        			JOptionPane.showMessageDialog(null, "An error occured while submitting your paper.Please try again later.");
	        		}
	        	}
	        }
	    });
	}
}
