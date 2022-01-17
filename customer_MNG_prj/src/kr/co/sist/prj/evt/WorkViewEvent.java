package kr.co.sist.prj.evt;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
//import com.sun.tools.classfile.StackMap_attribute.stack_map_frame;

import kr.co.sist.prj.view.WorkView;
import kr.co.sist.prj.vo.LoadData;

public class WorkViewEvent implements ActionListener{
	private WorkView wv;
	private File file;
	private 
	boolean flag = false;
	
	public WorkViewEvent(WorkView wv) {
		this.wv=wv;
		
	}//WorkViewEvent

	public void loadData() throws IOException {
		 file = new File("customer_MNG_prj/dataFile/work_data.dat");
		 String str="";
		  int lineCnt=0; 
		  BufferedReader br = null;

			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

				while ((str = br.readLine()) != null) {	
					try {
						lineCnt++;
						String[] tempArr = null;
						// ','을 기준으로 문자열을 나누어 배열에 저장
						tempArr = str.substring(0, str.length()).split(",");
							if(wv.getDlmData().size()==(lineCnt-1)) {
							wv.getDlmData().addElement(new LoadData(tempArr[0],tempArr[1],tempArr[2],tempArr[3]));	
							wv.getJlistDataView().setModel(wv.getDlmData());
							  
							}
					} catch (ArrayIndexOutOfBoundsException aioobe) {
						aioobe.printStackTrace();
					}

				}
			} finally {
				if (br != null) {
					br.close();
				}
				flag=true;
			}				
	}//loadData
	
	public void addData() throws IOException {
		BufferedWriter bw = null;
		File file = new File("customer_MNG_prj/dataFile/work_data.dat");
		LoadData newData = new LoadData(wv.getJtfName().getText(),wv.getJtfClient().getText(),wv.getJtfPhone().getText(), wv.getJtfEmail().getText());
		if(!wv.getDlmData().equals(newData)) {
		
		//기존 아이템 외에 새로운 아이템 하나 추가한다.
		wv.getDlmData().addElement(newData);
		//리스트에 모델을 적용한다.
		wv.getJlistDataView().setModel(wv.getDlmData());
		
		try {
			// 스트림 목적지 파일에 연결
			bw = new BufferedWriter(new FileWriter(file.toString(),true));
			// 내용 입력
			bw.write(newData.toString2()+"\n");
			// 스트림에 기록된 내용을 목적지 파일로 분출
			bw.flush();
		} finally {
			// 스트림 연결 끊기 (스트림에 기록된 내용이 목적지로 분출되고 연결이 끊어진다.)
			if (bw != null) {
				bw.close();
			}
		}
		}
	}//addData
	
	
	public void searchData() throws HeadlessException, IOException {
		boolean searchFlag = false;
		StringTokenizer st = null;
		 String searchClient = JOptionPane.showInputDialog(wv, "거래처명을 입력하세요.", "검색",JOptionPane.QUESTION_MESSAGE);
		if(searchClient == null || searchClient.equals("")) {
			return;
		} else {
			 file = new File("customer_MNG_prj/dataFile/work_data.dat");
			 String str="";
			  int cnt=0; 
			  BufferedReader br = null;
			  StringBuilder sb = new StringBuilder();
			  try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
					while ((str = br.readLine()) != null) {	
						String[] tempArr = null;
						
						// ','을 기준으로 문자열을 나누어 배열에 저장
						tempArr = str.substring(0, str.length()).split(",");
						
						if(tempArr[1].equals(searchClient)) {
							JOptionPane.showMessageDialog( wv, "성명 : " + tempArr[0] + ", 거래처명 : " + tempArr[1] + ", 전화번호 : " + tempArr[2] + ", 이메일 : " + tempArr[3], "검색결과", JOptionPane.INFORMATION_MESSAGE);
							searchFlag=true;
						} else continue;
					}//while
					if(!searchFlag) {
						JOptionPane.showMessageDialog(wv, "'"+searchClient+"'에 대한 검색결과가 없습니다.","검색결과", 0);	
					}
		 }finally {
			if (br != null) {
				br.close();
			}
		 }
		}
	}//searchData
	
	
	public void closeWin() {
		System.exit(0);
	}//closeWin
	
	public void resetData() {
		wv.getJtfName().setText("");
		wv.getJtfClient().setText("");
		wv.getJtfPhone().setText("");
		wv.getJtfEmail().setText("");
	}
	
	 public static boolean isvalidPhoneNumber(String number) {
	        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
	        Matcher matcher = pattern.matcher(number);
	        if (matcher.matches()) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

   
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == wv.getJbtnLoad()) {
			try {
				loadData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		if(ae.getSource() == wv.getJbtnAdd()) {
			if(flag != false) {
			try {
				if(wv.getJtfName().getText().equals("") || wv.getJtfClient().getText().equals("") ||
						wv.getJtfPhone().getText().equals("") ||wv.getJtfEmail().getText().equals("") ) {
					JOptionPane.showMessageDialog(wv, "빈칸을 입력해 주세요.");
				} else {
					if(isvalidPhoneNumber(wv.getJtfPhone().getText())) {
					if(isValidEmailAddress(wv.getJtfEmail().getText())) {
						addData();
						resetData();
					} else {
						JOptionPane.showMessageDialog(wv, "유효한 이메일이어야 합니다.");
					}
					} else {
						JOptionPane.showMessageDialog(wv, "유효하지 않은 전화번호입니다. 형식 : XXX-XXXX-XXXX");
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			} else {
				JOptionPane.showMessageDialog(wv, "로딩을 먼저하세요.");
			}
		}
		
		if(ae.getSource() == wv.getJbtnSearch()) {
			if(flag != false) {
			try {
				searchData();
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			} else {
				JOptionPane.showMessageDialog(wv, "로딩을 먼저하세요.");
			}
		}
		
		if(ae.getSource() == wv.getJbtnEnd()) {
			closeWin();
		}
	}//actionPerformed
	
	public WorkView getwv() {
		return wv;
	}
	
}
