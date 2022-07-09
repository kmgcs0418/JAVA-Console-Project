package hosptial.searchCovidHospital;

import java.util.ArrayList;
import java.util.Scanner;
import hosptial.domain.User;
import hosptial.login.LoginOutput;

/**
 * 코로나 안심병원 서비스의 메뉴 클래스입니다.
 * @author 박채은
 *
 */
public class SearchCovidHospitalMain  {	
	
	/**
	 * 코로나 안심병원 서비스의 메뉴를 출력하고, 검색창으로 이동할 수 있습니다.
	 */
	public void searchCovidHospital() {
		
		// 데이터 로드
		HospitalData.covidSafeListLoad();
		HospitalData.clinicListLoad();
	
		Scanner sc = new Scanner(System.in);
		
		boolean loop = true;
		while(loop) {
			CovidOutput.main();
			CovidOutput.menu();
			System.out.print("번호 : ");
			int input = sc.nextInt();
			
			if(input==1) {
				SearchCovidSafeHospital searchCovidSafeHospital = new SearchCovidSafeHospital();
				searchCovidSafeHospital.searchCovidSafeHospital();
			} else if(input==2) {
				SearchRespiratoryClinic searchRespiratoryClinic = new SearchRespiratoryClinic();
				searchRespiratoryClinic.searchRespiratoryClinic();
			} else if(input==0) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}//while
	}

}
