package finalProject_ysu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StudentCrud extends JFrame { // DB ����
	
    public StudentCrud() {
    	super("�л� ���� ���� ���α׷�");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	setSize(600, 100);
		setLocation(500, 300);
		
		Container contentPane = getContentPane();
    	
        JButton button1 = new JButton("�����ϱ�");
        
        button1.addActionListener(new ActionListener() {
        	StudentCrudFrame frame;
        	Connection conn;
  
            @Override
            public void actionPerformed(ActionEvent e) {
            	JButton btn = (JButton)e.getSource(); // Ŭ���� ��ư �˾Ƴ���
                if (btn.getText().equals("�����ϱ�")) {
                	frame = new StudentCrudFrame();  
                    btn.setText("�������");
                    
                    try {   
            			// Class Ŭ������ forName() �޼ҵ�� MySQL ������ JDBC ����̹� �ε�     
            			Class.forName("com.mysql.cj.jdbc.Driver");
            			
            			// �ڹ� �������α׷��� JDBC ����̹��� ����
                		// DriverManager.getConnection() �޼ҵ� ȣ�� >> DB�� ���� >> Connection ��ü ��ȯ
                		// DB �̸� : final_project, �α��� ���� �̸� : root, �α��� ������ �н����� : 1234
            			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root","1234"); 	
            			System.out.println("DB ���� ���� (false:����� / true:���� ����) >> " + conn.isClosed());
                    }
                    		
                    catch (ClassNotFoundException cnfe) {
                    	System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." +	cnfe.getMessage());
                    }
                    
                    catch(SQLException se) {
                    	System.out.println(se.getMessage());
                    }
                }
                
                else if (btn.getText().equals("�������")) {
                		frame.dispose(); // �ش� ������ �ݱ�
                		
                		try {
                			conn.close();
                			System.out.println("DB ���� ���� (false:����� / true:���� ����) >> " + conn.isClosed());
						}
                		catch (SQLException e1) {
							e1.printStackTrace();
						}
                		
                		btn.setText("�����ϱ�");
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