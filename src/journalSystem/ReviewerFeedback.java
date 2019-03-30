package journalSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ReviewerFeedback extends JFrame {

	private JPanel contentPane;
	public static ReviewerFeedback frame = new ReviewerFeedback();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public ReviewerFeedback() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Will be name of pdf file trying to provide feedback on
		JLabel lblPaperTitle = new JLabel("PAPER TITLE");
		lblPaperTitle.setBounds(248, 34, 117, 16);
		lblPaperTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		contentPane.add(lblPaperTitle);

		JButton btnSubmitFeedback = new JButton("Submit Feedback");
		btnSubmitFeedback.setBounds(53, 354, 151, 29);
		contentPane.add(btnSubmitFeedback);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 6, 117, 29);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		ReviewJournals review = new ReviewJournals();
	        		review.frame.setVisible(true);
	        }
	    });

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(577, 6, 117, 29);
		contentPane.add(btnLogout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(54, 62, 570, 280);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea(10,10);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);



		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
	}
}
