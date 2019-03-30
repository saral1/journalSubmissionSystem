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

public class SubmissionStatus {

	public JFrame frame;
	private JTable table;
	private JButton btnDelete;
	private dbConnection dbConn;
	private User user;

	private String[][] tabledata;
	private int[] tableDataSubmitNum;

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
	/**
	 * Create the application.
	 */
	public SubmissionStatus(User u, dbConnection conn) {
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
		
		JLabel lblPaperTitle = new JLabel("Your Submissions");
		lblPaperTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPaperTitle.setBounds(176, 35, 142, 16);
		frame.getContentPane().add(lblPaperTitle);
		
		//add contents to JTable
		String[] columnNames = {"Title", "Date Submitted", "Status"};
		HashSet<Paper> set = new HashSet<Paper>();
		if(dbConn != null) {
			set = dbConn.getAuthorSubmission(user);
		}
		tabledata = new String[set.size()][3];
		tableDataSubmitNum = new int[set.size()];
		int i=0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		for(Paper p: set) {
			tabledata[i][0] = p.getTitle();
			tabledata[i][1] = dateFormat.format(p.getDate());
			tabledata[i][2] = p.getStatus();
			tableDataSubmitNum[i] = p.getNumber();
			i++;	
		}
		table = new JTable(tabledata, columnNames);
		table.setBounds(60, 80, 500, 300);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(60, 80, 500, 300);
		frame.getContentPane().add(scrollPane);
		
		btnDelete = new JButton("Delete Submission");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnDelete.setBounds(60, 400, 186, 29);
		frame.getContentPane().add(btnDelete);
		
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
		btnDelete.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	int row = table.getSelectedRow();
	        	if(row < 0) {//no row selected
	        		JOptionPane.showMessageDialog(null, "Please select a paper to delete");
	        		return;
	        	}
	        	Object[] msg = { "Are you sure you want to delete the following submission permanently from the system?:\n\n",
						"Title: "+ tabledata[row][0],
						"Date Submitted: "+ tabledata[row][1],
						"Status: " + tabledata[row][2],
					};
					int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(confirm == JOptionPane.YES_OPTION){
						//delete paper from database
						boolean deleteSubmit = dbConn.deleteSubmission(tableDataSubmitNum[row]);
						if(deleteSubmit) {
							JOptionPane.showMessageDialog(null, "Selected submission has been deleted");
							//refresh page
							frame.dispose();    
			        		SubmissionStatus submitStatus = new SubmissionStatus(user, dbConn);
			        		submitStatus.frame.setVisible(true);
							
						}else {
							JOptionPane.showMessageDialog(null, "There was an error deleting selected submission. Please try again later.");
						}
					}	        	
	        }
	    });
	}
	
	

}
