package journalSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReviewerShell extends JFrame {

	private JPanel contentPane;
	public static ReviewerShell frame = new ReviewerShell();
//	private String firstName;
//	private String lastName;
	private dbConnection dbConn;
	private User user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ReviewerShell frame = new ReviewerShell();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReviewerShell() {
		initialize();
	}
	/**
	 * Create the frame.
	 */
	public ReviewerShell(User user, dbConnection conn) {
		this.user = user;
		this.dbConn = conn;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Welcome");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUsername.setBounds(46, 26, 90, 16);
		contentPane.add(lblUsername);

		JButton btnViewPapersFor = new JButton("View Papers for Review");
		btnViewPapersFor.setBounds(122, 141, 179, 29);
		contentPane.add(btnViewPapersFor);


		btnViewPapersFor.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		ReviewerSelection selection = new ReviewerSelection();
	        		selection.frame.setVisible(true);
	        }
	    });

		JButton btnMyPapers = new JButton("My Papers");
		btnMyPapers.setBounds(150, 100, 117, 29);
		contentPane.add(btnMyPapers);

		btnMyPapers.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		ReviewJournals papers = new ReviewJournals();
	        		papers.frame.setVisible(true);
	        }
	    });

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(327, 6, 117, 29);
		contentPane.add(btnLogout);

		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
	}
}
