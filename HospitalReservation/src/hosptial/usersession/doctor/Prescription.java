package hosptial.usersession.doctor;


/**
 * 
 * @author 처방전 클래스
 *
 */
public class Prescription {

	
	
	private String patientType = "0";
	private String patientName;
	private String regNum;
	private String doctorType = "1";
	private String doctorName;
	private String hospitalName;
	private String medicine;
	private String department;
	private String diseaseCode;
	
	/**
	 * 
	 * @param patientType 환자유형
	 * @param patientName 환자이름
	 * @param regNum 환자주민등록번호
	 * @param doctorType 의사유형
	 * @param doctorName 의사이름
	 * @param hospitalName 병원이름
	 * @param medicine 처방약
	 * @param department 진료과목
	 * @param diseaseCode 질병분류기호
	 */
	
	// constructor
	public Prescription(String patientType, String patientName, String regNum, String doctorType,
			String doctorName, String hospitalName, String medicine, String department,
			String diseaseCode) {
		super();
		this.patientType = patientType;
		this.patientName = patientName;
		this.regNum = regNum;
		this.doctorType = doctorType;
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
		this.medicine = medicine;
		this.department = department;
		this.diseaseCode = diseaseCode;
	}
	
	//getter, setter
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getDoctorType() {
		return doctorType;
	}
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Prescription [patientType=%s, patientName=%s, regNum=%s, doctorType=%s, doctorName=%s, hospitalName=%s, medicine=%s, department=%s, diseaseCode=%s]",
				patientType, patientName, regNum, doctorType, doctorName, hospitalName, medicine,
				department, diseaseCode);
	}
	
	
	
	
	
	
}
