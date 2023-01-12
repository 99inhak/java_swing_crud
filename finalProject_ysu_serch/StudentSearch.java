package finalProject_ysu_serch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class StudentSearch extends JFrame {
	private String colNames[] = { "이름", "주소", "전화번호" }; // 테이블의 헤더
	
	public StudentSearch(){
		super("연락처 검색 프로그램");
		
		// 상위 레이아웃 관리자가 있을 때 사용
		// Dimension객체를 인자로 받으면서 해당 컴포넌트의 기본(최적) 크기 설정
		setPreferredSize(new Dimension(500, 200));
		
		// 내 화면에서 윈도우 창을 나타낼 위치 지정 (왼쪽 상단이 0,0)
		setLocation(500, 350);
		
		Container contentPane = getContentPane();

		// 데이터의 삽입, 삭제를 위해 DefaultTableModel 사용
		// 셀 값을 저장하는 TableModel의 구현
		// DefaultTableModel(Object[] columnNames, int rowCount)
		// 열의 이름이 포함된 배열과 테이블의 행 수로 DefaultTableModel을 구성
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER); // 테이블을 센터로
		
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(4); // 이름
		JTextField text2 = new JTextField(6); // 주소
		JTextField text3 = new JTextField(4); // 전화번호
		
		JButton button1 = new JButton("검색");
		button1.setBackground(Color.PINK);
		
		panel.add(new JLabel("이름"));
		panel.add(text1);
		
		panel.add(new JLabel("주소"));
		panel.add(text2);
		
		panel.add(new JLabel("전화번호"));
		panel.add(text3);
		
		panel.add(button1);
		
		contentPane.add(panel, BorderLayout.NORTH); // 검색 기능을 상단으로
		
		button1.addActionListener(new SearchActionListener(table, text1, text2, text3));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new StudentSearch();
	}
}


class SearchActionListener implements ActionListener {
    JTable table;
    JTextField text1, text2, text3;
    
    SearchActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3) {
        this.table = table;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }
    
    public void actionPerformed(ActionEvent e) {
       // String arr[] = new String[3];
        String name = text1.getText();
        String address = text2.getText();
        String phoneno = text3.getText();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Connection conn = null;
        Statement stmt = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root","8282");
        	stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("select name, address, phoneno from studentinfo where "
        	+ "name like '%" + name + "%' and "
        	+ "address like '%" + address + "%' and "
        	+ "phoneno like '%" + phoneno + "%';");
        	
        	while (rs.next()) {
	        	String arr[] = new String[3];
	        	arr[0] = rs.getString("name");
	        	arr[1] = rs.getString("address");
	        	arr[2] = rs.getString("phoneno");
	        	model.addRow(arr);
        	}
        	
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