package journalSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class AdminApproveReviewer {

	public JFrame frame;
	private JTable table;
	private JButton btnAcceptAccount;
	private JButton btnRejectAccount;
	
	private dbConnection dbConn;
	private User user;
	
	private String[][] tableData;
	private HashSet<User> set;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApproveReviewer window = new AdminApproveReviewer(null, null);
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
	public AdminApproveReviewer(User u, dbConnection conn) {
		this.user = u;
		this.dbConn = conn;
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
		
		String[] columnNames = {"First Name", "Last Name", "Professional Email"};
		set = new HashSet<User>();
		if(dbConn != null) {
			set = dbConn.getUnapprovedReviewer();
		}
		tableData = new String[set.size()][3];
		int i=0;
		for(User u: set) {
			tableData[i][0] = u.getfirstName();
			tableData[i][1] = u.getLastName();
			tableData[i][2] = u.getEmail();
			i++;	
		}
		table = new JTable(tableData, columnNames);
		table.setBounds(30, 77, 642, 338);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 77, 642, 338);
		frame.getContentPane().add(scrollPane);
		
		btnAcceptAccount = new JButton("Accept Account");
		btnAcceptAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAcceptAccount.setBounds(168, 431, 134, 29);
		frame.getContentPane().add(btnAcceptAccount);
		
		btnRejectAccount = new JButton("Reject Account");
		btnRejectAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnRejectAccount.setBounds(398, 431, 127, 29);
		frame.getContentPane().add(btnRejectAccount);
		
		addButtonPurposes();
	}
	
	private void addButtonPurposes() {
		btnAcceptAccount.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
		        	int row = table.getSelectedRow();
		        	if(row < 0) {//no row selected
		        		JOptionPane.showMessageDialog(null, "Please select an account to approve");
		        		return;
		        	}
		        	Object[] msg = { "Are you sure you want to approve this reviewer?\n\nPlease ensure that they have a professional email.\n\n",
							"First Name: "+ tableData[row][0],
							"Last Name: "+ tableData[row][1],
							"Email: " + tableData[row][2],
					};
					int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(confirm == JOptionPane.YES_OPTION){
						//approve reviewer
						//find reviewer that has the same email and add him to database
						for(User u: set) {
							if(u.getEmail().equals(tableData[row][2])) {
								boolean approveR = dbConn.approveReviewer(u);
								if(approveR) {
									JOptionPane.showMessageDialog(null, "The reviewer has been approved");
									//refresh page
									frame.dispose();    
									AdminApproveReviewer submitStatus = new AdminApproveReviewer(user, dbConn);
					        		submitStatus.frame.setVisible(true);
								}else {
									JOptionPane.showMessageDialog(null, "There was an error approving the reviewer. Please try again later.");
								}
							}
						}
					}
	        }
	    });
		
		btnRejectAccount.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
//	        	createdAccounts.getSelectedValue();
	        		//REMOVE ACCOUNT FROM DATABASE 
	        }
	    });
	}
}
