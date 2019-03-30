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

public class SelectReviewer {

	public JFrame frame;
	private dbConnection dbConn;
	private User user;
	
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
	
	//for second label
	private JLabel midLabel;
	//for third label
	
	private JButton btnLogout;
	private JButton btnSelectReviewers;
	private JButton btnAddConflict;
	private JButton btnDeleteConflict;
	private JButton btnDeleteReviewer;
	private JButton btnSubmitReviewer;
	
	//all reviewers
	private HashSet<User> reviewers;
	
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
	public SelectReviewer() {
		initialize();
	}
	/**
	 * Create the application.
	 */
	public SelectReviewer(User u, dbConnection conn) {
		this.user = u;
		this.dbConn = conn;
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
		
		midLabel = new JLabel("Please select reviewers you wish to review your submissions as well as any conflicts of interest:");
		midLabel.setBounds(41, 110, 800, 20);
		frame.getContentPane().add(midLabel);
		
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
		
		if(user.getfirstName() != null) {
			JLabel nameLabel = new JLabel(user.getfirstName() + " " + user.getLastName());
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
		scrollPane.setBounds(40, 163, 200, 210);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		//update reviewer list from database;
		
		reviewers = dbConn.getReviewer();
		listModel = new DefaultListModel<String>();
		if(reviewers != null) {
			for(User u: reviewers) {
				listModel.addElement(u.getLastName() + "," + u.getfirstName() + ":" + u.getEmail());
			}
		}
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
		
		btnAddConflict = new JButton("Add Conflict");
		btnAddConflict.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAddConflict.setBounds(32, 410, 173, 29);
		frame.getContentPane().add(btnAddConflict);
		
		btnDeleteReviewer = new JButton("Remove Reviewer");
		btnDeleteReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDeleteReviewer.setBounds(273, 380, 173, 29);
		frame.getContentPane().add(btnDeleteReviewer);
		
		btnDeleteConflict = new JButton("Remove Conflict");
		btnDeleteConflict.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDeleteConflict.setBounds(513, 380, 173, 29);
		frame.getContentPane().add(btnDeleteConflict);
		
		btnSubmitReviewer = new JButton("Submit");
		btnSubmitReviewer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
//		btnSubmitPaper.setBounds(287, 425, 117, 29);
		btnSubmitReviewer.setBounds(273, 475, 300, 30);
		frame.getContentPane().add(btnSubmitReviewer);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnBack.setBounds(34, 6, 117, 29);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		AuthorShell shell = new AuthorShell(user, dbConn);
	        		shell.frame.setVisible(true);
	        }
	    });
		
		//initialize hash sets
		selectedSet = new HashSet<String>();
		conflictReason = new HashMap<String,String>();
		addButtonPurposes();		
	}
	private void addButtonPurposes() {
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
		
		btnAddConflict.addActionListener(new ActionListener() {
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
						listModel3.addElement(entry.getKey() + "---" +entry.getValue());
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
					String[] a2 = a.split("---");
					conflictReason.remove(a2[0]);
				}
				
				//update listModel3 with hashMap
				listModel3.clear();
				for(Map.Entry<String, String> entry: conflictReason.entrySet()) {
					listModel3.addElement(entry.getKey() + "---" +entry.getValue());
				}
				//update window
				reviewerList3.setModel(listModel3);
	        }
	    });
		btnSubmitReviewer.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//submit reviewers
	        	if(dbConn.addAuthorReviewer(user, selectedSet) && dbConn.addAuthorConflict(user, conflictReason)) {
	        		JOptionPane.showMessageDialog(null, "Your suggested reviewers and conflicts have been updated in the system, thanks!");
		        	frame.dispose();       		
	        		AuthorShell shell = new AuthorShell(user, dbConn);
	        		shell.frame.setVisible(true);
	        	}else {
	        		JOptionPane.showMessageDialog(null, "There was an error adding your suggested reviewers. Please try again later");
		        	frame.dispose();       		
	        		AuthorShell shell = new AuthorShell(user, dbConn);
	        		shell.frame.setVisible(true);
	        	}
	        }
	    });
		
		
	}
}