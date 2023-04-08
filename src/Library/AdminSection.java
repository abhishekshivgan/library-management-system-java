package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
// import java.sql.*;

public class AdminSection extends JFrame implements ActionListener {
//	Declaring elements of frame
	JLabel l1;
	JButton bt1;
	JPanel p1, p2;
	Font f, f1;
	
	AdminSection() {
		super("Librarian Section");
		setLocation(1, 1);
		setSize(1540, 820);
		
		f = new Font("Arial", Font.BOLD, 25);
		f1 = new Font("Arial", Font.BOLD, 20);
		
//		Adding Image to frame
		ImageIcon img = new ImageIcon(this.getClass().getResource("/laptop.jpg"));
		Image i = img.getImage().getScaledInstance(1500, 800, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(i);
		l1 = new JLabel(img2);
		
//		Adding MenuBar, Menu, and MenuItem
		JMenuBar m1 = new JMenuBar();
		JMenu men1 = new JMenu("Add Info");
		JMenuItem ment1 = new JMenuItem("Add Librarian");
		
		JMenu men2 = new JMenu("View Info");
		JMenuItem ment2 = new JMenuItem("View Librarian");

		
		JMenu men3 = new JMenu("Delete Info");
		JMenuItem ment3 = new JMenuItem("Delete Librarian");
		
		JMenu men4 = new JMenu("Return");
		JMenuItem ment4 = new JMenuItem("Exit");
		
//		Adding Menu Items to menu
		men1.add(ment1);
		men2.add(ment2);
		men3.add(ment3);
		men4.add(ment4);
		
//		Adding menu to menubar
		m1.add(men1);
		m1.add(men2);
		m1.add(men3);
		m1.add(men4);
		
//		Setting Font
		men1.setFont(f);
		men2.setFont(f);
		men3.setFont(f);
		men4.setFont(f);
		
		ment1.setFont(f1);
		ment2.setFont(f1);
		ment3.setFont(f1);
		ment4.setFont(f1);
		
		ment1.addActionListener(this);
		ment2.addActionListener(this);
		ment3.addActionListener(this);
		ment4.addActionListener(this);
		
		setJMenuBar(m1);
		add(l1);
	}
	
	public void actionPerformed(ActionEvent e) {
		String comnd = e.getActionCommand();
		if (comnd.equals("Add Librarian")) {
			new AddLibrarian().setVisible(true);
		}
		else if (comnd.equals("View Librarian")) {
			new ViewLibrarian().setVisible(true);
		}
		else if (comnd.equals("Delete Librarian")) {
			new DeleteLibrarian().setVisible(true);
		}
		else if (comnd.equals("Exit")) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new AdminSection().setVisible(true);
	}

}
