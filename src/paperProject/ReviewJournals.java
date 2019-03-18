package paperProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ReviewJournals extends JFrame {

	private JPanel contentPane;
	public static ReviewJournals frame = new ReviewJournals();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ReviewJournals frame = new ReviewJournals();
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
	public ReviewJournals() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 16, 117, 29);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		ReviewerShell shell = new ReviewerShell();
	        		shell.frame.setVisible(true);
	        }
	    });

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(577, 16, 117, 29);
		contentPane.add(btnLogout);

		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });

		JLabel lblPapersToReview = new JLabel("PAPERS TO REVIEW");
		lblPapersToReview.setBounds(275, 59, 166, 16);
		lblPapersToReview.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		contentPane.add(lblPapersToReview);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 110, 605, 300);
		contentPane.add(scrollPane);

		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		JButton btnProvideFeedback = new JButton("Provide Feedback");
		btnProvideFeedback.setBounds(196, 422, 153, 29);
		contentPane.add(btnProvideFeedback);

		JButton btnViewPaper = new JButton("View Paper");
		btnViewPaper.setBounds(67, 422, 117, 29);
		contentPane.add(btnViewPaper);
	}
}
