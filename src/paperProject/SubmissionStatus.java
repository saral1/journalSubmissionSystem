package paperProject;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Font;

public class SubmissionStatus {

	public JFrame frame;
	private JTextField textField;
	private JButton btnUpload;
	private dbConnection dbConn;
	private String firstName;
	private String lastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmissionStatus window = new SubmissionStatus();
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
	public SubmissionStatus() {
		initialize();
	}
	
	public SubmissionStatus(String fN, String lN, dbConnection dbConn) {
		this.firstName = fN;
		this.lastName = lN;
		this.dbConn = dbConn;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPaperTitle = new JLabel("PAPER TITLE");
		lblPaperTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPaperTitle.setBounds(176, 35, 142, 16);
		frame.getContentPane().add(lblPaperTitle);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblStatus.setBounds(433, 35, 78, 16);
		frame.getContentPane().add(lblStatus);
		
		JList feedbackList = new JList();
		feedbackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		feedbackList.setBounds(266, 219, 1, 1);
		frame.getContentPane().add(feedbackList);
		
		JScrollPane scrollPane = new JScrollPane(feedbackList);
		scrollPane.setBounds(657, 94, -617, 260);
		frame.getContentPane().add(scrollPane);
		
		JButton btnSelectReviewer = new JButton("Select Reviewer");
		btnSelectReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSelectReviewer.setBounds(262, 366, 186, 29);
		frame.getContentPane().add(btnSelectReviewer);
		btnSelectReviewer.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		feedbackList.getSelectedValue();
	        		//SEND OFF TO DATABASE
	        		//DISPLAY STRING AS JDIALOG BOX THAT POPS UP
	        }
	    });
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnUpload.setBounds(394, 420, 117, 29);
		frame.getContentPane().add(btnUpload);
		
		JButton btnResubmit = new JButton("Resubmit");
		btnResubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnResubmit.setBounds(523, 420, 117, 29);
		frame.getContentPane().add(btnResubmit);
		
		textField = new JTextField();
		textField.setBounds(86, 420, 277, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(0, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AuthorShell shell = new AuthorShell();
	        		shell.frame.setVisible(true);
	        }
	    });
		
		addButtonPurposes();
	}
	private void addButtonPurposes() {
		btnUpload.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser fc = new JFileChooser();
	            fc.setVisible(true);
	            int val = fc.showDialog(null,"Choose");
	            if(val == JFileChooser.APPROVE_OPTION) {
	            	File file = fc.getSelectedFile();
	            	textField.setText(file.getName());
	            }
	        }
	    });
	}
	
	

}
