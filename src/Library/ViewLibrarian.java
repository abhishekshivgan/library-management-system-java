package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ViewLibrarian extends JFrame {
	
	String[] x = {"Id", "Name", "Email","Contact", "Address", "City"};
	JButton bt1;
	String[][] y = new String[20][6];
	int i = 0, j = 0;
	JTable t;
	Font f, f1;
	
	ViewLibrarian() {
		super("Librarian Information");
		setLocation(1, 1);
		setSize(1540, 1000);
		
		f = new Font("Arial", Font.BOLD, 15);
		f1 = new Font("Arial", Font.BOLD, 20);
		
		try {
			ConnectionClass obj = new ConnectionClass();
			String q = "select * from librarian";
			ResultSet rest = obj.stn.executeQuery(q);
			while (rest.next()) {
				y[i][j++] = rest.getString("Lid"); 
				y[i][j++] = rest.getString("name");
				y[i][j++] = rest.getString("email");
				y[i][j++] = rest.getString("contact");
				y[i][j++] = rest.getString("address");
				y[i][j++] = rest.getString("city");
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
		new ViewLibrarian().setVisible(true);

	}

}
