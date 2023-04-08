package Library;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ViewBook extends JFrame {
	
	String[] x = {"Id", "Book No", "Book Name","Author", "Publication", "Quantity", "Issued","Date"};
	JButton bt1;
	String[][] y = new String[20][8];
	int i = 0, j = 0;
	JTable t;
	Font f, f1;
	
	ViewBook() {
		super("Book Information");
		setLocation(1, 1);
		setSize(1540, 1000);
		
		f = new Font("Arial", Font.BOLD, 15);
		f1 = new Font("Arial", Font.BOLD, 20);
		
		try {
			ConnectionClass obj = new ConnectionClass();
			String q = "select * from addBook";
			ResultSet rest = obj.stn.executeQuery(q);
			while (rest.next()) {
				y[i][j++] = rest.getString("id"); 
				y[i][j++] = rest.getString("bookNo");
				y[i][j++] = rest.getString("bookName");
				y[i][j++] = rest.getString("author");
				y[i][j++] = rest.getString("publisher");
				y[i][j++] = rest.getString("quantity");
				y[i][j++] = rest.getString("issueBook");
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
		new ViewBook().setVisible(true);
	}

}
