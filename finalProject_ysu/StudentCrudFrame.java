package finalProject_ysu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class StudentCrudFrame extends JFrame { // ���̺�
	private String colNames[] = { "�̸�", "�ּ�", "��ȭ��ȣ" }; // ���̺��� ���
	
	public StudentCrudFrame(){
		super("�л� ���� ���� ���α׷�");
		
		setPreferredSize(new Dimension(1000, 200));
		setLocation(400, 400);
		
		Container contentPane = getContentPane();

		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER); // ���̺��� ���ͷ�
		
		JPanel panel = new JPanel();
		JTextField namefield = new JTextField(4); // �̸�
		JTextField addressfield = new JTextField(17); // �ּ�
		JTextField phonefield = new JTextField(8); // ��ȭ��ȣ
		
		JButton searchbtn = new JButton("�˻�");
		JButton insertbtn = new JButton("�߰�");
		JButton updatebtn = new JButton("����");
		JButton deletebtn = new JButton("����");
		JButton selectbtn = new JButton("��κ���");
		
		panel.add(new JLabel("�̸�"));
		panel.add(namefield);
		
		panel.add(new JLabel("�ּ�"));
		panel.add(addressfield);
		
		panel.add(new JLabel("��ȭ��ȣ"));
		panel.add(phonefield);
		
		panel.add(searchbtn); // �˻�
		panel.add(insertbtn); // �߰�
		panel.add(updatebtn); // ����
		panel.add(deletebtn); // ����
		panel.add(selectbtn); // ��κ���
		
		contentPane.add(panel, BorderLayout.NORTH); // �Է� & ��ư�� �������
		
		searchbtn.addActionListener(new SearchActionListener(table, namefield, addressfield, phonefield)); // �˻�
		insertbtn.addActionListener(new InsertActionListener(table, namefield, addressfield, phonefield)); // �߰�
		updatebtn.addActionListener(new UpdateActionListener(table, namefield, addressfield, phonefield)); // ����
		deletebtn.addActionListener(new DeleteActionListener(table, namefield, addressfield, phonefield)); // ����
		selectbtn.addActionListener(new AllSearchActionListener(table, namefield, addressfield, phonefield)); // ��κ���
		table.addMouseListener(new MyMouseListener(table, namefield, addressfield, phonefield)); // ���̺� row ���� ��
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100); // '�̸�' �� ��(�ʺ�)
		table.getColumnModel().getColumn(1).setPreferredWidth(500); // '�ּ�' �� ��(�ʺ�)
		table.getColumnModel().getColumn(2).setPreferredWidth(200); // '��ȭ��ȣ' ��(�ʺ�)
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new StudentCrudFrame();
	}
}

class MyMouseListener extends MouseAdapter {
	JTable table;
    JTextField namefield, addressfield, phonefield;
    
	MyMouseListener(JTable table, JTextField namefield, JTextField addressfield, JTextField phonefield) {
        this.table = table;
        this.namefield = namefield;
        this.addressfield = addressfield;
        this.phonefield = phonefield;
    }
	
	public void mouseClicked(MouseEvent e) { 
		int selectedRow = table.getSelectedRow(); // ���õ� row ��
		
		// getValueAt(int row, int col) >> �ش� ��ġ�� ������ ��
		namefield.setText(table.getValueAt(selectedRow, 0).toString()); // �̸� �Է� �ʵ忡 ���õ� �� �ֱ�
		addressfield.setText(table.getValueAt(selectedRow, 1).toString()); // �ּ� �Է� �ʵ忡 ���õ� �� �ֱ�
		phonefield.setText(table.getValueAt(selectedRow, 2).toString()); // ��ȭ��ȣ �Է� �ʵ忡 ���õ� �� �ֱ�
	}
}


