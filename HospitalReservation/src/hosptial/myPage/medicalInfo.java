package hosptial.myPage;
/**
 * 
 * @author KIMMINGYU
 * 진료내역 데이터 생성자, getter, setter
 *
 */
public class medicalInfo {
	
	private String patientName;
	private String regNo;
	private String date;
	private String hospital;
	private String time;
	private String doctorName;
	private String department;
	/**
	 * 
	 * @param patientName - 환자이름
	 * @param regNo - 주민번호
	 * @param date - 진료날짜
	 * @param hospital - 병원명
	 * @param time - 진료시간
	 * @param doctorName - 의사이름
	 * @param department - 진료과
	 */
	public medicalInfo(String patientName, String regNo, String date, String hospital, String time, String doctorName, String department) {
		super();
		this.patientName = patientName;
		this.regNo = regNo;
		this.date = date;
		this.hospital = hospital;
		this.time = time;
		this.doctorName = doctorName;
		this.department = department;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
