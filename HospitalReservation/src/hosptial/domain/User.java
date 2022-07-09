package hosptial.domain;

/**
 * 모든 유저 계정의 최상위 클래스입니다.
 * @author joung
 *
 */
public class User {

	private long sequence;		//식별번호
	private long userTypeCheck;	//유저계정구별 -1 관리자, 0 사용자, 1 의사
	private String id;			//아이디
	private String password;	//패스워드
	private String name;		//이름
	private String phoneNum;	//전화번호
	private String address;		//주소
	private String regNo;		//주민번호
	
	public User(long sequence, long userTypeCheck, String id, String password, String name,
			String phoneNum, String address, String regNo) {
		this.sequence = sequence;
		this.userTypeCheck = userTypeCheck;
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
		this.regNo = regNo;
	}
	
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public long getUserTypeCheck() {
		return userTypeCheck;
	}
	public void setUserTypeCheck(long userTypeCheck) {
		this.userTypeCheck = userTypeCheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	@Override
	public String toString() {
		return String.format(
				"User [sequence=%s, userTypeCheck=%s, id=%s, password=%s, name=%s, phoneNum=%s, address=%s, regNo=%s]",
				sequence, userTypeCheck, id, password, name, phoneNum, address, regNo);
	}
	
}
