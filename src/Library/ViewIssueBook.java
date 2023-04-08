package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ViewIssueBook extends JFrame {
	
	String[] x = {"Book ID", "Book No", "Book Name","Student Id", "Student Name", "Contact", "Date"};
	JButton bt1;
	String[][] y = new String[40][7];
	int i = 0, j = 0;
	JTable t;
	Font f, f1;
	
	ViewIssueBook() {
		super("Librarian Information");
		setLocation(1, 1);
		setSize(1540, 1000);
		
		f = new Font("Arial", Font.BOLD, 15);
		f1 = new Font("Arial", Font.BOLD, 20);
		
		try {
			ConnectionClass obj = new ConnectionClass();
			String q = "select * from issuebook";
			ResultSet rest = obj.stn.executeQuery(q);
			while (rest.next()) {
				y[i][j++] = rest.getString("bookId"); 
				y[i][j++] = rest.getString("bookNo");
				y[i][j++] = rest.getString("bookName");
				y[i][j++] = rest.getString("stuId");
				y[i][j++] = rest.getString("stuName");
				y[i][j++] = rest.getString("stuCont");
				y[i][j++] = rest.getString("date");
				i++;
				j = 0;
			}
			
			t = new JTable(y, x);
			t.setFont(f);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane sp = new JScrollPane(t);
		add(sp);
	}

	public static void main(String[] args) {
		new ViewIssueBook().setVisible(true);
	}

}
