package hosptial;

import java.util.Scanner;
import hosptial.domain.CommonUser;
import hosptial.domain.DoctorUser;
import hosptial.domain.User;
import hosptial.login.LoginMain;
import hosptial.searchCovidHospital.SearchCovidHospitalMain;
import hosptial.signup.SignupMain;
import hosptial.usersession.admin.AdminSession;
import hosptial.usersession.common.CommonUserSesstion;
import hosptial.usersession.doctor.A_DoctorSession;
/**
 * 프로그램 첫 실행 클래스입니다.
 * @author joung
 *
 */
public class Main {	
	/**
	 * 프로그램 메인화면 출력 메서드입니다.
	 * @param args 프로그램 실행시 입력받는 매개변수입니다.
	 * @throws InterruptedException 실행 실패시 오류 메세지를 돌려줍니다.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			Output.main();
			Output.menu();
			System.out.print("번호 : ");
			int input = sc.nextInt();
			
			if(input==0) {
				Output.exit();
				sc.close();
				System.exit(0);
			} else if(input==1) {
				if(LoginSession.getSession()!=null) {
					Output.logout();
					continue;
				}
				LoginMain longinMain = new LoginMain();								////1.로그인
				longinMain.login();					
			} else if(input==2) {
				if(LoginSession.getSession()!=null) {
					Output.logout();
					continue;
				}
				SignupMain signUpMain = new SignupMain();							////2.회원가입
				signUpMain.signUp();				
			} else if(input==3) {
				SearchCovidHospitalMain covidMain = new SearchCovidHospitalMain();	////3.코로나 안심병원 찾기
				covidMain.searchCovidHospital();
			} else if(input==4) {
				logoutCheck();														////4.로그아웃
			} else {
				Output.stop();
				Thread.sleep(3000);
			}
			
			if(LoginSession.getSession()!=null) userSesstion();

		}//while
		
	}
	/**
	 * 로그아웃을 위한 메서드입니다.
	 * @author joung
	 */
	private static void logoutCheck() {
		if(LoginSession.getSession()!=null) {
			LoginSession.setSession(null);
			try {
				if(LoginSession.getSession()==null) 
					Output.logoutSuccess();
			} catch (Exception e) {
				System.out.println("로그아웃 시도중 에러가 발생했습니다.");
				e.printStackTrace();
			}
		} else {
			Output.logoutFail();
		}//if
	}//logoutCheck
	
	/**
	 * 로그인 성공시 유저세션에 값을 넘겨주기 위한 메서드입니다.
	 * @author joung
	 */
	private static void userSesstion() {
		User currentUser = LoginSession.getSession();
		Long userType = currentUser.getUserTypeCheck();
		
		if(userType == -1) {
			AdminSession ad = new AdminSession();
			ad.main();
		} else if(userType == 0) {
			CommonUserSesstion cs = new CommonUserSesstion();
			cs.main();
		} else if(userType == 1) {
			A_DoctorSession ds = new A_DoctorSession();
			ds.main();
		}
	}

}
