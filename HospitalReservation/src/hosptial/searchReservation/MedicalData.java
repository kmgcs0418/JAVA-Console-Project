package hosptial.searchReservation;

import java.util.Calendar;

/**
 * 진료내역을 저장하는 클래스입니다.
 * @author 박선미
 *
 */

public class MedicalData {
	/*
	 
	 고길지 patientName
	 370728-136349 patientRegNo
	 2022-04-18 reservationDate
	 안기의원 hospitalName
	 10:00 reservationTime
	 황자웅 doctorName
	 259 doctorNum
	
	   
	 */
	
	private String patientName;
	private String patientRegNo;
	private String reservationDate;
	private String hospitalName;
	private String reservationTime;
	private String doctorName;
	private String doctorNum;
	
	/**
	 * 
	 * @param patientName 환자이름
	 * @param patientRegNo 환자주민등록번호
	 * @param reservationDate 예약시간
	 * @param hospitalName 병원이름
	 * @param reservationTime 예약시간
	 * @param doctorName 의사이름
	 * @param doctorNum 의사회원번호
	 */
	
	public MedicalData(String patientName, String patientRegNo, String reservationDate,
			String hospitalName, String reservationTime, String doctorName, String doctorNum) {
		super();
		this.patientName = patientName;
		this.patientRegNo = patientRegNo;
		this.reservationDate = reservationDate;
		this.hospitalName = hospitalName;
		this.reservationTime = reservationTime;
		this.doctorName = doctorName;
		this.doctorNum = doctorNum;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientRegNo() {
		return patientRegNo;
	}

	public void setPatientRegNo(String patientRegNo) {
		this.patientRegNo = patientRegNo;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorNum() {
		return doctorNum;
	}

	public void setDoctorNum(String doctorNum) {
		this.doctorNum = doctorNum;
	}

	@Override
	public String toString() {
		return String.format(
				"MedicalData [patientName=%s, patientRegNo=%s, reservationDate=%s, hospitalName=%s, reservationTime=%s, doctorName=%s, doctorNum=%s]",
				patientName, patientRegNo, reservationDate, hospitalName, reservationTime,
				doctorName, doctorNum);
	}
	
	

}
