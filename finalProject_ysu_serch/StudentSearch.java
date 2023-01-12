package finalProject_ysu_serch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class StudentSearch extends JFrame {
	private String colNames[] = { "�̸�", "�ּ�", "��ȭ��ȣ" }; // ���̺��� ���
	
	public StudentSearch(){
		super("����ó �˻� ���α׷�");
		
		// ���� ���̾ƿ� �����ڰ� ���� �� ���
		// Dimension��ü�� ���ڷ� �����鼭 �ش� ������Ʈ�� �⺻(����) ũ�� ����
		setPreferredSize(new Dimension(500, 200));
		
		// �� ȭ�鿡�� ������ â�� ��Ÿ�� ��ġ ���� (���� ����� 0,0)
		setLocation(500, 350);
		
		Container contentPane = getContentPane();

		// �������� ����, ������ ���� DefaultTableModel ���
		// �� ���� �����ϴ� TableModel�� ����
		// DefaultTableModel(Object[] columnNames, int rowCount)
		// ���� �̸��� ���Ե� �迭�� ���̺��� �� ���� DefaultTableModel�� ����
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER); // ���̺��� ���ͷ�
		
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(4); // �̸�
		JTextField text2 = new JTextField(6); // �ּ�
		JTextField text3 = new JTextField(4); // ��ȭ��ȣ
		
		JButton button1 = new JButton("�˻�");
		button1.setBackground(Color.PINK);
		
		panel.add(new JLabel("�̸�"));
		panel.add(text1);
		
		panel.add(new JLabel("�ּ�"));
		panel.add(text2);
		
		panel.add(new JLabel("��ȭ��ȣ"));
		panel.add(text3);
		
		panel.add(button1);
		
		contentPane.add(panel, BorderLayout.NORTH); // �˻� ����� �������
		
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