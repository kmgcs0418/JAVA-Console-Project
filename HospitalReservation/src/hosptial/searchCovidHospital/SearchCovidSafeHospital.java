package hosptial.searchCovidHospital;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * 국민안심병원 검색 클래스 입니다.
 * @author 박채은
 *
 */
public class SearchCovidSafeHospital {
	
	/**
	 * 사용자가 국민안심병원에서 찾고 싶은 목록의 키워드를 입력할 수 있습니다. 
	 * 사용자가 키워드를 입력하면 목록을 탐색하고 검색 목록을 볼 수 있는 클래스로 이동시켜줍니다.
	 * 뒤로가기 키워드를 입력받으면 loop를 빠져나가 메소드가 종료됩니다.
	 * @return true 메소드 종료
	 */
	public static boolean searchCovidSafeHospital() {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			CovidOutput.serachPage("국민안심병원");
			String keyWord = sc.nextLine();
			
			if(keyWord.equals("0")) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} else {
				
				// 키워드 검색
				// 시도, 시군구, 전화번호로 키워드
				ArrayList<CovidSafeHospital> list = Search.keyWordCovidSafeList(keyWord);
				FindCovidSafeHospitalList findList = new FindCovidSafeHospitalList(list);
				findList.searchCovidSafeHospital();
			}
			
		}
		return true;
	}
	
}
