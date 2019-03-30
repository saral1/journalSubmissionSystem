package journalSystem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Font;

public class ViewSelectedReviewer {
	public JFrame frame;
	private JTable table1;
	private JTable table2;
	private dbConnection dbConn;
	private User user;

	private String[][] revTabledata;
	private String[][] conTabledata;

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
	public ViewSelectedReviewer() {
		initialize();
	}
	/**
	 * Create the application.
	 */
	public ViewSelectedReviewer(User u, dbConnection conn) {
		this.user = u;
		this.dbConn = conn;
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
		
		JLabel lblPaperTitle = new JLabel("Your Current Suggested Reviewers and Conflicts");
		lblPaperTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPaperTitle.setBounds(100, 35, 500, 16);
		frame.getContentPane().add(lblPaperTitle);
		
		JLabel lblPaperTitle2 = new JLabel("You can makes changes in the selecte reviewer screen");
		lblPaperTitle2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblPaperTitle2.setBounds(80, 55, 500, 16);
		frame.getContentPane().add(lblPaperTitle2);
		
		//add contents to JTable
		String[] columnNames = {"Name", "Email"};
		HashSet<User> set = new HashSet<User>();
		if(dbConn != null) {
			set = dbConn.getUnapprovedReviewer();
		}
		revTabledata = new String[2][2];
		
		table1 = new JTable(revTabledata, columnNames);
		table1.setBounds(60, 80, 200, 300);
		table1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(60, 80, 200, 300);
		frame.getContentPane().add(scrollPane);
		
		conTabledata = new String[3][3];

		String[] columnNames2 = {"Name", "Email", "Reason"};
		table2 = new JTable(conTabledata, columnNames2);
		table2.setBounds(300, 80, 300, 300);
		table2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(300, 80, 300, 300);
		frame.getContentPane().add(scrollPane2);
		
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
	        		AuthorShell shell = new AuthorShell(user, dbConn);
	        		shell.frame.setVisible(true);
	        }
	    });
		
		addButtonPurposes();
	}
	
	private void addButtonPurposes() {
		
	}
}
