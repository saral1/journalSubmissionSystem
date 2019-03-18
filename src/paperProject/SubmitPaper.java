package paperProject;

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
	private String firstName;
	private String lastName;
	
	private JTextField textField;
	private JButton btnUpload;
	
	//for displaying all reviewers
	private JScrollPane scrollPane;
	private JList<String> reviewerList;
	private DefaultListModel<String> listModel;
	
	//for displaying selected reviewers
	private JScrollPane scrollPane2;
	private JList<String> reviewerList2;
	private DefaultListModel<String> listModel2;
	
	//for displaying conflicting reviewers
	private JScrollPane scrollPane3;
	private JList<String> reviewerList3;
	private DefaultListModel<String> listModel3;
	
	//for first label
	private JLabel topLabel;
	//for second label
	private JLabel midLabel;
	//for third label
	
	private JButton btnLogout;
	private JButton btnSelectReviewers;
	private JButton btnSubmitConflict;
	private JButton btnDeleteConflict;
	private JButton btnDeleteReviewer;
	private JButton btnSubmitPaper;
	
	private HashSet<String> selectedSet;
	private HashMap<String, String> conflictReason;
	

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
	
	public SubmitPaper(String firstName, String lastName, dbConnection dbConn) {
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
		frame.setBounds(100, 100, 870, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		topLabel = new JLabel("Please select your paper as a pdf to submit");
		topLabel.setBounds(41, 45, 500, 20);
		frame.getContentPane().add(topLabel);
		
		midLabel = new JLabel("Please select reviewers you wish to review your paper as well as any conflicts of interest:");
		midLabel.setBounds(41, 110, 600, 20);
		frame.getContentPane().add(midLabel);
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnUpload.setBounds(459, 64, 117, 29);
		frame.getContentPane().add(btnUpload);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(37, 64, 315, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
		if(firstName != null) {
			JLabel nameLabel = new JLabel(firstName + " " + lastName);
			nameLabel.setBounds(630,6,117,29);
			frame.getContentPane().add(nameLabel);
		}
		
		JLabel labelT1 = new JLabel("Available Reviewers");
		labelT1.setBounds(41, 143, 600, 20);
		frame.getContentPane().add(labelT1);
		
		JLabel labelT2 = new JLabel("Selected Reviewers");
		labelT2.setBounds(281, 143, 600, 20);
		frame.getContentPane().add(labelT2);
		
		JLabel labelT3 = new JLabel("Conflicting Reviewers");
		labelT3.setBounds(521, 143, 600, 20);
		frame.getContentPane().add(labelT3);
		
		//for displaying all reviewers
		listModel = new DefaultListModel<String>();
		reviewerList = new JList<String>(listModel);
		scrollPane = new JScrollPane(reviewerList);
//		scrollPane.setBounds(40, 118, 619, 210);
		scrollPane.setBounds(40, 163, 200, 210);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		//update reviewer list from database;
		listModel = dbConn.getReviewer();
		reviewerList.setModel(listModel);
		
		listModel2 = new DefaultListModel<String>();
		reviewerList2 = new JList<String>(listModel2);
		scrollPane2 = new JScrollPane(reviewerList2);
		scrollPane2.setBounds(280, 163, 200, 210);
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane2);
		
		listModel3 = new DefaultListModel<String>();
		reviewerList3 = new JList<String>(listModel3);
		scrollPane3 = new JScrollPane(reviewerList3);
		scrollPane3.setBounds(520, 163, 300, 210);
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane3);
		
		btnSelectReviewers = new JButton("Add Reviewer");
		btnSelectReviewers.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSelectReviewers.setBounds(32, 380, 173, 29);
		frame.getContentPane().add(btnSelectReviewers);
		
		btnSubmitConflict = new JButton("Add Conflict");
		btnSubmitConflict.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSubmitConflict.setBounds(32, 410, 173, 29);
		frame.getContentPane().add(btnSubmitConflict);
		
		btnDeleteReviewer = new JButton("Remove Reviewer");
		btnDeleteReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDeleteReviewer.setBounds(273, 380, 173, 29);
		frame.getContentPane().add(btnDeleteReviewer);
		
		btnDeleteConflict = new JButton("Remove Conflict");
		btnDeleteConflict.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDeleteConflict.setBounds(513, 380, 173, 29);
		frame.getContentPane().add(btnDeleteConflict);
		
		btnSubmitPaper = new JButton("Submit Paper");
		btnSubmitPaper.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
//		btnSubmitPaper.setBounds(287, 425, 117, 29);
		btnSubmitPaper.setBounds(273, 475, 300, 30);
		frame.getContentPane().add(btnSubmitPaper);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(34, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AuthorShell shell = new AuthorShell(firstName, lastName, dbConn);
	        		shell.frame.setVisible(true);
	        }
	    });
		
		//initialize hash sets
		selectedSet = new HashSet<String>();
		conflictReason = new HashMap<String,String>();
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
		
		btnSelectReviewers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add selected to hashset
				for(String a : reviewerList.getSelectedValuesList()) {
					selectedSet.add(a);
				}
				
				//update listModel2 to hashSet
				listModel2.clear();
				for(String user : selectedSet) {
					listModel2.addElement(user);
				}
				reviewerList2.setModel(listModel2);
			}
		});
		
		btnSubmitConflict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//for each conflict selected, pop up a window asking for reason
				String conflict = reviewerList.getSelectedValue();
				//if conflict selected is in reviewers wanted, show error
				if(selectedSet.contains(conflict)) {
					JOptionPane.showMessageDialog(null, "You cannot add a conflict with a reviewer that you have selected to review");
					return;
				}
				//pop up a window to add comment
				Object[] options = {"Add", "Cancel"};
				JTextField reason = new JTextField(20);
				
				Object[] message = { "Please state your reason of conflict with:\n\n",
						"Reviewer:", conflict,
						"\nReason: ", reason,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				if(result == JOptionPane.OK_OPTION) {
					//error checking comment entered
					while(reason.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "Your reason cannot be empty");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					//add person and reason to conflictReason
					conflictReason.put(conflict, reason.getText());
					//update listModel3 with hashMap
					listModel3.clear();
					for(Map.Entry<String, String> entry: conflictReason.entrySet()) {
						listModel3.addElement(entry.getKey() + ":" +entry.getValue());
					}
					//update window
					reviewerList3.setModel(listModel3);
				}
			}
		});
		
		btnDeleteReviewer.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//delete selected from hashset
				for(String a : reviewerList2.getSelectedValuesList()) {
					selectedSet.remove(a);
				}
				
				//update listModel2 to hashSet
				listModel2.clear();
				for(String user : selectedSet) {
					listModel2.addElement(user);
				}
				reviewerList2.setModel(listModel2);
	        }
	    });
		
		btnDeleteConflict.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//delete selected from hashMap
				for(String a : reviewerList3.getSelectedValuesList()) {
					String[] a2 = a.split(":");
					System.out.println("removing " + a2[0]);
					conflictReason.remove(a2[0]);
				}
				
				//update listModel3 with hashMap
				listModel3.clear();
				for(Map.Entry<String, String> entry: conflictReason.entrySet()) {
					System.out.println(entry.getKey() + " " + entry.getValue());
					listModel3.addElement(entry.getKey() + ":" +entry.getValue());
				}
				//update window
				reviewerList3.setModel(listModel3);
	        }
	    });
	
		
		
	}
}
