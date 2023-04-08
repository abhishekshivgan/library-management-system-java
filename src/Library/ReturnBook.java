package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ReturnBook extends JFrame implements ActionListener {
	JLabel l1, l2, l3;
	JButton bt1, bt2;
	JPanel p1, p2;
	Font f, f1;
	JTextField tf1, tf2;
	
	ReturnBook() {
		super("Return Book Page");
		setLocation(500, 300);
		setSize(500, 200);
		
		f = new Font("Arial", Font.BOLD, 25);
		f1 = new Font("Arial", Font.BOLD, 20);
		
		l1 = new JLabel("Return Books");
		l2 = new JLabel("Book No");
		l3 = new JLabel("Student ID");
		
		bt1 = new JButton("Return");
		bt2 = new JButton("Cancel");
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		
		l1.setFont(f);
		l2.setFont(f1);
		l3.setFont(f1);
		bt1.setFont(f1);
		bt2.setFont(f1);
		tf1.setFont(f1);
		tf2.setFont(f1);
		
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.WHITE);
		
//		Setting panel layout and Adding panel to frame
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1, 10, 10));
		p1.add(l1);
		p1.setBackground(Color.BLUE);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(3, 2, 10, 10));
		p2.add(l2);
		p2.add(tf1);
		p2.add(l3);
		p2.add(tf2);
		p2.add(bt1);
		p2.add(bt2);
		
		setLayout(new BorderLayout(10, 10));
		add(p1, "North");
		add(p2, "Center");
	}
	
	public void actionPerformed(ActionEvent e) {
		String bookNo = tf1.getText();
		int stuId = Integer.parseInt(tf2.getText());
		
		if (e.getSource() == bt1) {
			try {
				ConnectionClass obj = new ConnectionClass();
				String q = "delete from issueBook where bookNo='"+bookNo+"' and stuId='"+stuId+"'";
				int res = obj.stn.executeUpdate(q);
				if (res == 0) {
					JOptionPane.showMessageDialog(null, "Book is not issued.");
					this.setVisible(false);
				} 
				else {
					String q1 = "update addBook set issueBook = issueBook-1 where bookNo='"+bookNo+"'";
					String q2 = "update addBook set quantity = quantity+1 where bookNo='"+bookNo+"'";
					int aa = obj.stn.executeUpdate(q1);
					int aaa = obj.stn.executeUpdate(q2);
					if (aa == 1) {
						if (aaa == 1) {
							JOptionPane.showMessageDialog(null, "Book return details updated successfully");
							this.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please fill details carefully!");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill details carefully!");
					}
				}
			}
			catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		if (e.getSource() == bt2) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new ReturnBook().setVisible(true);
	}

}
