package finalProject_ysu;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class UpdateActionListener implements ActionListener { // 수정 버튼 눌렀을 때
    JTable table;
    JTextField text1, text2, text3;
    
    UpdateActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3) {
        this.table = table;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }
    
    public void actionPerformed(ActionEvent e) {
        String name = text1.getText();
        String address = text2.getText();
        String phoneno = text3.getText();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Connection conn = null;
        Statement stmt = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root","1234");
        	stmt = conn.createStatement();
        	
        	// 레코드 수정
        	stmt.executeUpdate("update studentinfo set address ='" 
        						+ address + "', phoneno = '"
        						+ phoneno + "' where name='" + name + "';");
        	
        	// 수정된 레코드 표시
        	ResultSet rs = stmt.executeQuery("select * from studentinfo where name = '" + name + "';");
        	
        	while (rs.next()) {
	        	String arr[] = new String[3];
	        	arr[0] = rs.getString("name");
	        	arr[1] = rs.getString("address");
	        	arr[2] = rs.getString("phoneno");
	        	model.addRow(arr);
        	}
        	System.out.println("데이터가 수정되었습니다.");
        }
        
        catch (ClassNotFoundException cnfe) {
        	System.out.println("해당 클래스를 찾을 수 없습니다." +	cnfe.getMessage());
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