package kr.co.sist.prj.vo;

public class LoadData {
	private String name;
	private String client;
	private String phone;
	private String email;
		
	public LoadData(String name, String client, String phone, String email) {
		this.name = name;
		this.client=client;
		this.phone=phone;
		this.email=email;
	}

	@Override
	public String toString() {
		return "성명 : " + name + ", 거래처명 : " + client + ", 전화번호 : " + phone + ", 이메일 : " + email;
	}
	public String toString2() {
		return name +","+ client + "," + phone + "," + email;
	}

	public String getName() {
		return name;
	}

	public String getClient() {
		return client;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	};
	
}
