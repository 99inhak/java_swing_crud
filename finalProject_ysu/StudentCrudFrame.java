package finalProject_ysu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class StudentCrudFrame extends JFrame { // 테이블
	private String colNames[] = { "이름", "주소", "전화번호" }; // 테이블의 헤더
	
	public StudentCrudFrame(){
		super("학생 정보 관리 프로그램");
		
		setPreferredSize(new Dimension(1000, 200));
		setLocation(400, 400);
		
		Container contentPane = getContentPane();

		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER); // 테이블을 센터로
		
		JPanel panel = new JPanel();
		JTextField namefield = new JTextField(4); // 이름
		JTextField addressfield = new JTextField(17); // 주소
		JTextField phonefield = new JTextField(8); // 전화번호
		
		JButton searchbtn = new JButton("검색");
		JButton insertbtn = new JButton("추가");
		JButton updatebtn = new JButton("수정");
		JButton deletebtn = new JButton("삭제");
		JButton selectbtn = new JButton("모두보기");
		
		panel.add(new JLabel("이름"));
		panel.add(namefield);
		
		panel.add(new JLabel("주소"));
		panel.add(addressfield);
		
		panel.add(new JLabel("전화번호"));
		panel.add(phonefield);
		
		panel.add(searchbtn); // 검색
		panel.add(insertbtn); // 추가
		panel.add(updatebtn); // 수정
		panel.add(deletebtn); // 삭제
		panel.add(selectbtn); // 모두보기
		
		contentPane.add(panel, BorderLayout.NORTH); // 입력 & 버튼을 상단으로
		
		searchbtn.addActionListener(new SearchActionListener(table, namefield, addressfield, phonefield)); // 검색
		insertbtn.addActionListener(new InsertActionListener(table, namefield, addressfield, phonefield)); // 추가
		updatebtn.addActionListener(new UpdateActionListener(table, namefield, addressfield, phonefield)); // 수정
		deletebtn.addActionListener(new DeleteActionListener(table, namefield, addressfield, phonefield)); // 삭제
		selectbtn.addActionListener(new AllSearchActionListener(table, namefield, addressfield, phonefield)); // 모두보기
		table.addMouseListener(new MyMouseListener(table, namefield, addressfield, phonefield)); // 테이블 row 선택 시
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100); // '이름' 열 폭(너비)
		table.getColumnModel().getColumn(1).setPreferredWidth(500); // '주소' 열 폭(너비)
		table.getColumnModel().getColumn(2).setPreferredWidth(200); // '전화번호' 폭(너비)
		
		
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
		int selectedRow = table.getSelectedRow(); // 선택된 row 값
		
		// getValueAt(int row, int col) >> 해당 위치의 데이터 값
		namefield.setText(table.getValueAt(selectedRow, 0).toString()); // 이름 입력 필드에 선택된 값 넣기
		addressfield.setText(table.getValueAt(selectedRow, 1).toString()); // 주소 입력 필드에 선택된 값 넣기
		phonefield.setText(table.getValueAt(selectedRow, 2).toString()); // 전화번호 입력 필드에 선택된 값 넣기
	}
}


