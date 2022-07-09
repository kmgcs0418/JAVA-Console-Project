package hosptial.searchCovidHospital;

/**
 * 국민 안심병원 데이터를 넣을 수 있는 클래스입니다.
 * @author 박채은
 *
 */
public class CovidSafeHospital {

	private String city_do;
	private String si_gu_gun;
	private String hospitalName;
	private String hospitaladress;
	private String typeOFthreatment;
	private String phoneNumber;
	

	/**
	 * 국민안심병원 데이터를 넣는 클래스의 생성자는 모든 매개변수를 넣어야합니다.
	 * @param city_do 시도
	 * @param si_gu_gun 시군구
	 * @param hospitalName 병원 기관명
	 * @param hospitaladress 병원 주소
	 * @param typeOFthreatment 진료 유형 A : 외래진료, B : 외래진료 및 입원
	 * @param phoneNumber 병원 전화번호
	 */
	public CovidSafeHospital(String city_do, String si_gu_gun, String hospitalName,
			String hospitaladress, String typeOFthreatment, String phoneNumber) {
		this.city_do = city_do;
		this.si_gu_gun = si_gu_gun;
		this.hospitalName = hospitalName;
		this.hospitaladress = hospitaladress;
		this.typeOFthreatment = typeOFthreatment;
		this.phoneNumber = phoneNumber;
	}
	
	public String getCity_do() {
		return city_do;
	}

	public void setCity_do(String city_do) {
		this.city_do = city_do;
	}

	public String getSi_gu_gun() {
		return si_gu_gun;
	}

	public void setSi_gu_gun(String si_gu_gun) {
		this.si_gu_gun = si_gu_gun;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitaladress() {
		return hospitaladress;
	}

	public void setHospitaladress(String hospitaladress) {
		this.hospitaladress = hospitaladress;
	}

	public String getTypeOFthreatment() {
		return typeOFthreatment;
	}

	public void setTypeOFthreatment(String typeOFthreatment) {
		this.typeOFthreatment = typeOFthreatment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return String.format(
				"CoronaSafeHospital [city_do=%s, si_gu_gun=%s, hospitalName=%s, hospitaladress=%s, typeOFthreatment=%s, phoneNumber=%s]",
				city_do, si_gu_gun, hospitalName, hospitaladress, typeOFthreatment, phoneNumber);
	}
	
	
}
