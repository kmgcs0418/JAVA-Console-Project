package hosptial.usersession.admin.membermanagement.memberboard;

/**
 * 회원의 정보를 담을 메서드입니다.
 * @author joung
 *
 */
public class MemberVO {						//맴버객체
	private long number;
	private String sequence;					//식별번호
	private String userTypeCheck;				//유저계정구별 -1 관리자, 0 사용자, 1 의사
	private String id;						//아이디
	private String password;				//패스워드
	private String name;					//이름
	private String phoneNum;				//전화번호
	private String address;					//주소
	private String regNo;					//주민번호
	private String hospitalName;			//병원 이름
	private String operatingTimeWeekdays;	//평일 운영 시간
	private String operatingTimeWeekends;	//주말 운영 시간
	private String operatingTimeHolidats;;	//공휴일 운영 시간
	private String licenseNumber;			//면허 번호
	private String department;				//병원 부서
	private String warning;					//경고 횟수
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getUserTypeCheck() {
		return userTypeCheck;
	}
	public void setUserTypeCheck(String userTypeCheck) {
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
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getOperatingTimeWeekdays() {
		return operatingTimeWeekdays;
	}
	public void setOperatingTimeWeekdays(String operatingTimeWeekdays) {
		this.operatingTimeWeekdays = operatingTimeWeekdays;
	}
	public String getOperatingTimeWeekends() {
		return operatingTimeWeekends;
	}
	public void setOperatingTimeWeekends(String operatingTimeWeekends) {
		this.operatingTimeWeekends = operatingTimeWeekends;
	}
	public String getOperatingTimeHolidats() {
		return operatingTimeHolidats;
	}
	public void setOperatingTimeHolidats(String operatingTimeHolidats) {
		this.operatingTimeHolidats = operatingTimeHolidats;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	
}
