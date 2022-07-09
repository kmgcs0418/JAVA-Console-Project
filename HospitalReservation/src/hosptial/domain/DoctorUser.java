package hosptial.domain;
/**
 * 의사 계정입니다.
 * @author joung
 *
 */
public class DoctorUser extends User {
	private String hospitalName;			//병원 이름
	private String operatingTimeWeekdays;	//평일 운영 시간
	private String operatingTimeWeekends;	//주말 운영 시간
	private String operatingTimeHolidats;;	//공휴일 운영 시간
	private String licenseNumber;			//면허 번호
	private String department;
	
	public DoctorUser (long sequence, long userTypeCheck, String id, String password, String name,
			String phoneNum, String address, String regNo) {
		super(sequence, userTypeCheck, id, password, name, phoneNum, address, regNo);
	}

	public DoctorUser(long sequence, long userTypeCheck, String id, String password, String name,
			String phoneNum, String address, String regNo, String hospitalName,
			String operatingTimeWeekdays, String operatingTimeWeekends,
			String operatingTimeHolidats, String licenseNumber, String department) {
		
		super(sequence, userTypeCheck, id, password, name, phoneNum, address, regNo);
		this.hospitalName = hospitalName;
		this.operatingTimeWeekdays = operatingTimeWeekdays;
		this.operatingTimeWeekends = operatingTimeWeekends;
		this.operatingTimeHolidats = operatingTimeHolidats;
		this.licenseNumber = licenseNumber;
		this.department = department;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
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

	@Override
	public String toString() {
		return String.format(
				"DoctorUser [hospitalName=%s, operatingTimeWeekdays=%s, operatingTimeWeekends=%s, operatingTimeHolidats=%s, licenseNumber=%s, department=%s]",
				hospitalName, operatingTimeWeekdays, operatingTimeWeekends, operatingTimeHolidats,
				licenseNumber, department);
	}

}
