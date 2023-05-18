package xyz.itwill.student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class WindowBuilderApp extends JFrame {
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderApp frame = new WindowBuilderApp();
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
	public WindowBuilderApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 341);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewButton = new JButton("빨간색");
		btnNewButton.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnNewButton.setForeground(Color.RED);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("파란색");
		btnNewButton_1.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("초록색");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnNewButton_2.setForeground(Color.GREEN);
		panel.add(btnNewButton_2);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.DARK_GRAY);
		contentPane.add(textArea, BorderLayout.SOUTH);
	}

}
