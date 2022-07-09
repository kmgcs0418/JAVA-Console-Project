package hosptial.searchCovidHospital;

import java.util.ArrayList;

/**
 * 코로나 안심병원 메뉴의 검색 기능에 관련된 클래스입니다.
 * @author 박채은
 *
 */
public class Search {

	/**
	 * 사용자가 입력한 검색 키워드에 해당하는 국민 안심병원 목록을 탐색하고 반환합니다.
	 * keyWordCovidSafeList는 국민안심병원 목록을 순서대로 진행시켜 search의 탐색과정이 끝나면 검색 목록을 반환시켜줍니다.
	 * 전화번호는 '-'을 구분자로 String 배열에 저장합니다.
	 * 같은 클래스의 search 메소드가 String 배열과 국민안심병원 데이터를 전달받아 탐색 과정을 진행합니다.
	 * @param keyWord 사용자가 입력한 검색 키워드
	 * @return 국민 안심병원 검색 목록을 반환
	 */
	public static ArrayList<CovidSafeHospital> keyWordCovidSafeList(String keyWord) {
		ArrayList<CovidSafeHospital> list = new ArrayList<CovidSafeHospital>(50);
		for(CovidSafeHospital c : HospitalData.covidSafeList) {
			
			String[] phoneNumber = c.getPhoneNumber().split("-");
		
			search(keyWord, list, c, phoneNumber);
			
		}
		
		return list;
		
	}

	/**
	 * 국민 안심병원 목록에 사용자가 입력한 키워드가 있는 지 탐색합니다.
	 * @param keyWord 사용자 입력 키워드
	 * @param list 국민 안심병원 검색 목록을 저장할 ArrayList
	 * @param c 탐색해야하는 국민안심병원 데이터
	 * @param phoneNumber 탐색해야하는 국민안심병원 전화번호 배열
	 */
	private static void search(String keyWord, ArrayList<CovidSafeHospital> list,
			CovidSafeHospital c, String[] phoneNumber) {
		for(String number : phoneNumber) {
			if(number.equals(keyWord)) {
				list.add(c);
			}
		}
		
		if(c.getCity_do().contains(keyWord)) {
			list.add(c);
		} else if(c.getSi_gu_gun().contains(keyWord)) {
			list.add(c);
		} else if(c.getHospitalName().contains(keyWord)) {
			list.add(c);
		}
	}
	
	/**
	 * 사용자가 입력한 검색 키워드에 해당하는 호흡기 클리닉 목록을 탐색하고 반환합니다.
	 * keyWordRespiratoryClinicList는 호흡기 클리닉 목록을 순서대로 진행시켜 search의 탐색과정이 끝나면 검색 목록을 반환시켜줍니다.
	 * 전화번호는 '-'을 구분자로 String 배열에 저장합니다.
	 * 같은 클래스의 search 메소드가 String 배열과 호흡기 클리닉 데이터를 전달받아 탐색 과정을 진행합니다.
	 * @param keyWord 사용자가 입력한 검색 키워드
	 * @return 호흡기 클리닉 검색 목록을 반환
	 */
	public static ArrayList<RespiratoryClinic> keyWordRespiratoryClinicList(String keyWord) {
		ArrayList<RespiratoryClinic> list = new ArrayList<RespiratoryClinic>(50);
		for(RespiratoryClinic c : HospitalData.clinicList) {
			
			String[] phoneNumber = c.getPhoneNumber().split("-");
		
			search(keyWord, list, c, phoneNumber);
			
		}
		
		return list;
		
	}

	/**
	 * 호흡기 클리닉 목록에 사용자가 입력한 키워드가 있는 지 탐색합니다.
	 * @param keyWord 사용자 입력 키워드
	 * @param list 호흡기 클리닉 검색 목록을 저장할 ArrayList
	 * @param c 탐색해야하는 호흡기 클리닉 데이터
	 * @param phoneNumber 탐색해야하는 호흡기 클리닉 전화번호 배열
	 */
	private static void search(String keyWord, ArrayList<RespiratoryClinic> list,
			RespiratoryClinic c, String[] phoneNumber) {
		for(String number : phoneNumber) {
			if(number.equals(keyWord)) {
				list.add(c);
			}
		}
		
		if(c.getCity_do().contains(keyWord)) {
			list.add(c);
		} else if(c.getSi_gu_gun().contains(keyWord)) {
			list.add(c);
		} else if(c.getHospitalName().contains(keyWord)) {
			list.add(c);
		}
	}
	
}
