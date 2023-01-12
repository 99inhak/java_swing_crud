package finalProject_ysu;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class AllSearchActionListener implements ActionListener { // ��κ��� ��ư ������ ��
    JTable table;
    JTextField text1, text2, text3;
    
    AllSearchActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3) {
        this.table = table;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Connection conn = null;
        Statement stmt = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root","1234");
        	stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("select name, address, phoneno from studentinfo;");
        	
        	while (rs.next()) {
	        	String arr[] = new String[3];
	        	arr[0] = rs.getString("name");
	        	arr[1] = rs.getString("address");
	        	arr[2] = rs.getString("phoneno");
	        	model.addRow(arr);
        	}
        	
        	text1.setText(""); // �̸� �Է� �ʵ忡 �Էµ� �� �����
    		text2.setText(""); // �ּ� �Է� �ʵ忡 �Էµ� �� �����
    		text3.setText(""); // ��ȭ��ȣ �Է� �ʵ忡 �Էµ� �� �����
        }
        
        catch (ClassNotFoundException cnfe) {
        	System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." +	cnfe.getMessage());
        }
        
        catch(SQLException se) {
        	System.out.println(se.getMessage());
        }
        
        finally {
        	try {
        		stmt.close();
        	}
        	catch (Exception ignored) {
        	}
        	try {
        		conn.close();
        	}
        	catch (Exception ignored) {
        	}
        }
    }    
}