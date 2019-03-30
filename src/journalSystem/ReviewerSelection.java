package journalSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/*
 * Idea for Code:
 *
 * -list of papers. Reviewer can select the ones they want to 'apply' for, then submit them all at once.
 */
public class ReviewerSelection extends JFrame {

	private JPanel contentPane;
	public static ReviewerSelection frame = new ReviewerSelection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ReviewerSelection frame = new ReviewerSelection();
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
	public ReviewerSelection() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 6, 117, 29);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		ReviewerShell shell = new ReviewerShell();
	        		shell.frame.setVisible(true);
	        }
	    });


		JButton btnSubmitSelections = new JButton("Submit Selections");
		btnSubmitSelections.setBounds(96, 408, 156, 29);
		contentPane.add(btnSubmitSelections);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(564, 6, 117, 29);
		contentPane.add(btnLogout);

		JLabel lblUnreviewedPapers = new JLabel("UNASSIGNED PAPERS");
		lblUnreviewedPapers.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblUnreviewedPapers.setBounds(287, 46, 205, 16);
		contentPane.add(lblUnreviewedPapers);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(96, 94, 585, 303);
		contentPane.add(scrollPane_1);

		JList list = new JList();
		scrollPane_1.setViewportView(list);

		btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        		Login login = new Login();
	        		login.frame.setVisible(true);
	        }
	    });
	}
}
