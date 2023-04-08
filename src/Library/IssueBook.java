package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class IssueBook extends JFrame implements ActionListener {
	
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JButton bt1, bt2;
	JPanel p1, p2;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	Choice ch;
	Font f, f1;
	
	IssueBook(){
		super("Issue Book");
		setLocation(400, 200);
		setSize(650, 400);
		
		f = new Font("Arial", Font.BOLD, 25);
		f1 = new Font("Arial", Font.BOLD, 20);
		
		l1 = new JLabel("Issue Book");
		l2 = new JLabel("Book ID");
		l3 = new JLabel("Book No");
		l4 = new JLabel("Book Name");
		l5 = new JLabel("Student ID");
		l6 = new JLabel("Student Name");
		l7 = new JLabel("Student Contact");
		l8 = new JLabel("Book Quantity");
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		
		bt1 = new JButton("Issue Book");
		bt2 = new JButton("Cancel");
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		l1.setFont(f);
		l2.setFont(f1);
		l3.setFont(f1);
		l4.setFont(f1);
		l5.setFont(f1);
		l6.setFont(f1);
		l7.setFont(f1);
		l8.setFont(f1);
		
		tf1.setFont(f1);
		tf1.setEditable(false);
		tf2.setFont(f1);
		tf2.setEditable(false);
		tf3.setFont(f1);
		tf4.setFont(f1);
		tf5.setFont(f1);
		tf6.setEditable(false);
		tf6.setForeground(Color.RED);
		
		bt1.setFont(f1);
		bt2.setFont(f1);
		
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.WHITE);
		
		
		ch = new Choice();
		ch.setFont(f1);
		try {
			ConnectionClass obj = new ConnectionClass();
			String q = "select id from addBook";
			ResultSet rest = obj.stn.executeQuery(q);
			while (rest.next()) {
				ch.add(rest.getString("id"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1, 10, 10));
		p1.add(l1);
		p1.setBackground(Color.BLUE);
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(8, 2, 10, 10));
		p2.add(l2);
		p2.add(ch);
		p2.add(l3);
		p2.add(tf1);
		p2.add(l4);
		p2.add(tf2);
		p2.add(l5);
		p2.add(tf3);
		p2.add(l6);
		p2.add(tf4);
		p2.add(l7);
		p2.add(tf5);
		p2.add(l8);
		p2.add(tf6);
		p2.add(bt1);
		p2.add(bt2);
		
		setLayout(new BorderLayout(10, 10));
		add(p1, "North");
		add(p2, "Center");
		
		ch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ConnectionClass obj2 = new ConnectionClass();
					int id = Integer.parseInt(ch.getSelectedItem());
					String q1 = "select * from addBook where id='"+id+"'";
					ResultSet rest1 = obj2.stn.executeQuery(q1);
					while (rest1.next()) {
						tf1.setText(rest1.getString("bookNo"));
						tf2.setText(rest1.getString("bookName"));
						tf6.setText(rest1.getString("quantity"));
					}
				}
				catch (Exception exx) {
					exx.printStackTrace();
				}
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt1) {
			int qnt = 0;
			int id = Integer.parseInt(ch.getSelectedItem());
			String bookNo = tf1.getText();
			String bookName = tf2.getText();
			int stuId = Integer.parseInt(tf3.getText());
			String stuName = tf4.getText();
			String stuCont = tf5.getText();
			String date = new java.util.Date().toString();
			try {
				ConnectionClass obj3 = new ConnectionClass();
				String q0 = "select quantity from addBook where id='"+id+"'";
				ResultSet rest0 = obj3.stn.executeQuery(q0);
				while (rest0.next()) {
					qnt = Integer.parseInt(rest0.getString("quantity"));
				}
				if (qnt <= 0) {
					JOptionPane.showMessageDialog(null, "Sorry! Book not available.");
					this.setVisible(false);
				}
				else {
					String q2 = "insert into issueBook (bookId, bookNo, bookName, stuId, stuName, stuCont, date) values ('"+id+"', '"+bookNo+"', '"+bookName+"', '"+stuId+"', '"+stuName+"', '"+stuCont+"', '"+date+"')";
					String q3 = "update addBook set issueBook = issueBook + 1 where id='"+id+"'";
					String q4 = "update addBook set quantity = quantity - 1 where id='"+id+"'";
					int aa = obj3.stn.executeUpdate(q2);
					int aaa = obj3.stn.executeUpdate(q3);
					int aaaa = obj3.stn.executeUpdate(q4);
					
					if (aa == 1) {
						if (aaa == 1) {
							if (aaaa == 1) {
								JOptionPane.showMessageDialog(null, "Data successfully updated");
								this.setVisible(false);
							}
							else {
								JOptionPane.showMessageDialog(null, "Please fill data carefully!");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Please fill data carefully!");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill data carefully!");
					}
				}
				
			}
			catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		if (e.getSource() == bt2) {
			JOptionPane.showMessageDialog(null, "Are you sure?");
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		new IssueBook().setVisible(true);

	}

}
