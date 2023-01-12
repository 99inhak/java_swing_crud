package finalProject_ysu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StudentCrud extends JFrame { // DB 연결
	
    public StudentCrud() {
    	super("학생 정보 관리 프로그램");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	setSize(600, 100);
		setLocation(500, 300);
		
		Container contentPane = getContentPane();
    	
        JButton button1 = new JButton("연결하기");
        
        button1.addActionListener(new ActionListener() {
        	StudentCrudFrame frame;
        	Connection conn;
  
            @Override
            public void actionPerformed(ActionEvent e) {
            	JButton btn = (JButton)e.getSource(); // 클릭된 버튼 알아내기
                if (btn.getText().equals("연결하기")) {
                	frame = new StudentCrudFrame();  
                    btn.setText("연결끊기");
                    
                    try {   
            			// Class 클래스의 forName() 메소드로 MySQL 서버의 JDBC 드라이버 로드     
            			Class.forName("com.mysql.cj.jdbc.Driver");
            			
            			// 자바 응용프로그램을 JDBC 드라이버에 연결
                		// DriverManager.getConnection() 메소드 호출 >> DB에 연결 >> Connection 객체 반환
                		// DB 이름 : final_project, 로그인 계정 이름 : root, 로그인 계정의 패스워드 : 1234
            			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root","1234"); 	
            			System.out.println("DB 연결 상태 (false:연결됨 / true:연결 끊김) >> " + conn.isClosed());
                    }
                    		
                    catch (ClassNotFoundException cnfe) {
                    	System.out.println("해당 클래스를 찾을 수 없습니다." +	cnfe.getMessage());
                    }
                    
                    catch(SQLException se) {
                    	System.out.println(se.getMessage());
                    }
                }
                
                else if (btn.getText().equals("연결끊기")) {
                		frame.dispose(); // 해당 프레임 닫기
                		
                		try {
                			conn.close();
                			System.out.println("DB 연결 상태 (false:연결됨 / true:연결 끊김) >> " + conn.isClosed());
						}
                		catch (SQLException e1) {
							e1.printStackTrace();
						}
                		
                		btn.setText("연결하기");
                }
            }
         });
        
        JPanel panel = new JPanel();
        panel.add(button1);
		contentPane.add(panel, BorderLayout.CENTER);
		
        setVisible(true);

    }
	
	public static void main(String[] args) {
		new StudentCrud();
	}
}