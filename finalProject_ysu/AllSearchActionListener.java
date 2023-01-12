package finalProject_ysu;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class AllSearchActionListener implements ActionListener { // 모두보기 버튼 눌렀을 때
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
        	
        	text1.setText(""); // 이름 입력 필드에 입력된 값 지우기
    		text2.setText(""); // 주소 입력 필드에 입력된 값 지우기
    		text3.setText(""); // 전화번호 입력 필드에 입력된 값 지우기
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