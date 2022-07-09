package hosptial.usersession.common;

/**
 * 건강검진검사항목의 정보를 담을 메서드입니다
 * @author 이지영
 * 
 */

public class HosptialCheckUpList {

	private String hosptialName; //건강검진병원이름
	private String hosptialTime;//건강검진병원운영시간
	private String common;//건강검진공통검사가능한병원
	private String genderAge;//건강검진 성,연령별검사가능한병원
	private String cancer;//건강검진암검진가능한병원
	
	public HosptialCheckUpList(String hosptialName, String hosptialTime, String common,
			String genderAge, String cancer) {
		super();
		this.hosptialName = hosptialName;
		this.hosptialTime = hosptialTime;
		this.common = common;
		this.genderAge = genderAge;
		this.cancer = cancer;
	}

	public String getHosptialName() {
		return hosptialName;
	}

	public void setHosptialName(String hosptialName) {
		this.hosptialName = hosptialName;
	}

	public String getHosptialTime() {
		return hosptialTime;
	}

	public void setHosptialTime(String hosptialTime) {
		this.hosptialTime = hosptialTime;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getGenderAge() {
		return genderAge;
	}

	public void setGenderAge(String genderAge) {
		this.genderAge = genderAge;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String cancer) {
		this.cancer = cancer;
	}

		
	@Override
	public String toString() {
		return String.format(
				"HosptialCheckUpList [hosptialName=%s, hosptialTime=%s, common=%s, genderAge=%s, cancer=%s]",
				hosptialName, hosptialTime, common, genderAge, cancer);
	}
	
}
