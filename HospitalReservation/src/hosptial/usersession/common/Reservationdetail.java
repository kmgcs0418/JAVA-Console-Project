package hosptial.usersession.common;
/**
 * 건강검진예약정보를 담을 메서드입니다
 * @author 이지영
 * 
 */


public class Reservationdetail {
	
	private String personName;//건강검진자이름
	private String date;//건강검진예약날짜
	private String hospitalcheckupName;//건강검진예약병원
	private String hour;//건강검진예약시간
	



	public Reservationdetail(String personName,  String date,
			String hospitalcheckupName, String hour) {
		super();
		this.personName = personName;
	
		this.date = date;
		this.hospitalcheckupName = hospitalcheckupName;
		this.hour = hour;
	}


	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHospitalcheckupName() {
		return hospitalcheckupName;
	}

	public void setHospitalcheckupName(String hospitalcheckupName) {
		this.hospitalcheckupName = hospitalcheckupName;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}



	@Override
	public String toString() {
		return String.format(
				"Reservationdetail [personName=%s, date=%s, hospitalcheckupName=%s, hour=%s]",
				personName, date, hospitalcheckupName, hour);
	}

	



	
	
	
}
