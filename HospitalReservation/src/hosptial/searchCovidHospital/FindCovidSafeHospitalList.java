package hosptial.searchCovidHospital;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 국민안심병원 검색 목록 출력하는 클래스입니다.
 * @author 박채은
 *
 */
public class FindCovidSafeHospitalList {

	private static ArrayList<CovidSafeHospital> list = new ArrayList<CovidSafeHospital>(50);

	/**
	 * 국민안심병원 목록을 입력받을 수 있습니다.
	 * 국민안심병원 검색 클래스에서 탐색과정에서 저장된 목록을 받을 수 있습니다.
	 * @param list 국민안심병원 목록
	 */
	public FindCovidSafeHospitalList(ArrayList<CovidSafeHospital> list) {
		FindCovidSafeHospitalList.list = list;
	}

	/**
	 * 검색 클래스에서 받은 국민안심병원 목록을 출력합니다.
	 * 검색 클래스에서 받은 목록이 없으면 '해당되는 목록이 없습니다.'가 출력됩니다.
	 * 뒤로가기 키워드를 입력받으면 loop를 빠져나가 메소드가 종료됩니다.
	 * @return true 메소드 종료
	 */
	public static boolean searchCovidSafeHospital() {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			
			CovidOutput.findPage("국민안심병원", true);
			if(list.size() > 0) {

				System.out.println("[번호]\t[시도]\t[시군구]\t\t[기관명]\t\t\t[유형]\t\t\t[전화번호]");
				int cnt = 0;
				for(CovidSafeHospital c : FindCovidSafeHospitalList.list) {
					System.out.printf("%4d\t%3s\t%-16s \t%-15s\t%-10s\t%s\n"
											, ++cnt
											, c.getCity_do()
											, c.getSi_gu_gun()
											, c.getHospitalName().length() < 8 ? c.getHospitalName() + "\t" : c.getHospitalName()
											, (c.getTypeOFthreatment().equals("A") ? "외래진료" : "외래진료 및 입원")
											, c.getPhoneNumber());
				}		
				CovidOutput.bar(true);
			} else {
				System.out.println("해당되는 목록이 없습니다.");
				CovidOutput.bar(true);
			}
			
			System.out.print("0.다시검색하기: ");
			String keyWord = sc.nextLine();
			
			if(keyWord.equals("0")) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} 
			
		}
		return true;
	}
	
}
