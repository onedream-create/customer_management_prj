package kr.co.sist.prj.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import kr.co.sist.prj.evt.WorkViewEvent;
import kr.co.sist.prj.vo.LoadData;

@SuppressWarnings("serial")
public class WorkView extends JFrame{
	
	private JLabel jlbName;
	private JLabel jlbClient;
	private JLabel jlbPhone;
	private JLabel jlbEmail;

	
	private JTextField jtfName; 
	private JTextField jtfClient; 
	private JTextField jtfPhone; 
	private JTextField jtfEmail; 


	private JButton jbtnLoad; 
	private JButton jbtnAdd; 
	private JButton jbtnSearch; 
	private JButton jbtnEnd; 
	
	private JList<LoadData> jlistDataView;
	
	private JScrollPane jspJlistDataView;
	
	private DefaultListModel<LoadData> dlmData;//JList에 보이는 실제 데이터
	
	
	
	public WorkView()  {
		super("거래처관리");
		
		//버튼, 텍스트 정의 및 생성
		jlbName = new JLabel("성명");
		jlbName.setHorizontalAlignment(JLabel.CENTER);
		jlbClient = new JLabel("거래처명");
		jlbClient.setHorizontalAlignment(JLabel.CENTER);
		jlbPhone = new JLabel("전화번호");
		jlbPhone.setHorizontalAlignment(JLabel.CENTER);
		jlbEmail = new JLabel("이메일");
		jlbEmail.setHorizontalAlignment(JLabel.CENTER);
		
		jtfName = new JTextField();
		jtfClient = new JTextField();
		jtfPhone = new JTextField();
		jtfEmail = new JTextField();
		
		jbtnLoad = new JButton("로딩");
		jbtnAdd = new JButton("추가");
		jbtnSearch = new JButton("검색");
		jbtnEnd = new JButton("종료");
		
		jbtnLoad.setBackground(Color.yellow);
		jbtnAdd.setBackground(Color.pink);
		jbtnSearch.setBackground(Color.pink);
		jbtnEnd.setBackground(Color.white);
		
		Font font = new Font("Times", Font.BOLD, 15);
		
		jlbName.setFont(font);
		jlbClient.setFont(font);
		jlbPhone.setFont(font);
		jlbEmail.setFont(font);
		
		jbtnLoad.setFont(font);
		jbtnAdd.setFont(font);
		jbtnSearch.setFont(font);
		jbtnEnd.setFont(font);
		
		ImageIcon ii = new ImageIcon("customer_MNG_prj/img/crystal-ball.png");
		
		dlmData =new DefaultListModel<LoadData>();
		jlistDataView=new JList<LoadData>(dlmData);
		dlmData=(DefaultListModel<LoadData>)jlistDataView.getModel();
		
		jlistDataView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jspJlistDataView=new JScrollPane(jlistDataView);

		jspJlistDataView.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(ii.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		//배치
		jlbName.setBounds(30,10,140,35);
		jlbClient.setBounds(30,50,140,35);
		jlbPhone.setBounds(30,90,140,35);
		jlbEmail.setBounds(30,130,140,35);
		
		jtfName.setBounds(180,10,200,35);
		jtfClient.setBounds(180,50,200,35);
		jtfPhone.setBounds(180,90,200,35);
		jtfEmail.setBounds(180,130,200,35);
		
		jbtnLoad.setBounds(100,200,130,35);
		jbtnAdd.setBounds(250,200,130,35);
		jbtnSearch.setBounds(400,200,130,35);
		jbtnEnd.setBounds(550,200,130,35);
		
		jspJlistDataView.setBounds(400,10,600,165);
		
		background.setLayout(null);

		background.add(jlbName);
		background.add(jlbClient);
		background.add(jlbPhone);
		background.add(jlbEmail);
		background.add(jtfName);
		background.add(jtfClient);
		background.add(jtfPhone);
		background.add(jtfEmail);
		background.add(jbtnLoad);
		background.add(jbtnAdd);
		background.add(jbtnSearch);
		background.add(jbtnEnd);
		background.add(jspJlistDataView);
		
		add(background);
		
		//이벤트 연결
		WorkViewEvent wve = new WorkViewEvent(this);
		jbtnLoad.addActionListener(wve);
		jbtnAdd.addActionListener(wve);
		jbtnSearch.addActionListener(wve);
		jbtnEnd.addActionListener(wve);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1030,300);
		setResizable(false);
		setVisible(true);
	}


	public JTextField getJtfName() {
		return jtfName;
	}



	public JTextField getJtfClient() {
		return jtfClient;
	}



	public JTextField getJtfPhone() {
		return jtfPhone;
	}



	public JTextField getJtfEmail() {
		return jtfEmail;
	}



	public JButton getJbtnLoad() {
		return jbtnLoad;
	}



	public JButton getJbtnAdd() {
		return jbtnAdd;
	}



	public JButton getJbtnSearch() {
		return jbtnSearch;
	}



	public JButton getJbtnEnd() {
		return jbtnEnd;
	}



	public JList<LoadData> getJlistDataView() {
		return jlistDataView;
	}



	public JScrollPane getJspJlistDataView() {
		return jspJlistDataView;
	}



	public DefaultListModel<LoadData> getDlmData() {
		return dlmData;
	}
	
}
