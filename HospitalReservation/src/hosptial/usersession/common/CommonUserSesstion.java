package hosptial.usersession.common;

import java.util.Scanner;
import hosptial.Bbs.Bbs;
import hosptial.myPage.myPage;
import hosptial.searchReservation.Search;
import hosptial.LoginSession;
import hosptial.Output;
/**
 * 일반회원 메뉴 클래스입니다.
 * @author joung
 * 
 */
public class CommonUserSesstion {
	/**
	 * 일반회원 메뉴선택 메서드입니다. 
	 */
	public void main() {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 마이페이지");
			System.out.println("2. 증상별 검색");
			System.out.println("3. 키워드 검색");
			System.out.println("4. 진료과별 검색");
			System.out.println("5. 건강검진");
			System.out.println("6. 게시판 작성");
			System.out.println("7. 로그아웃");
			System.out.println("0. 뒤로가기");
			System.out.print("번호 : ");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("뒤로가기");
				return;

			} else if (input == 1) {
				myPage a = new myPage();
				a.main();

			} else if (input == 2) {
				Search search = new Search();
				search.symptomSearch();

			} else if (input == 3) {
				Search search = new Search();
				search.partSearch();

			} else if (input == 4) {
				Search search = new Search();
				search.medicalSearch();
					

			} else if (input == 5) {
				Checkup checkup = new Checkup();
				checkup.checkup();

			} else if (input == 6) {
				Bbs b = new Bbs();
				b.main();

			} else if (input == 7) {
				logoutCheck();
				return;
			} else {
				System.out.println("잘못입력");
			}

		} // while

	}
	
	private static void logoutCheck() {
		if (LoginSession.getSession() != null) {
			LoginSession.setSession(null);
			try {
				if (LoginSession.getSession() == null)
					Output.logoutSuccess();
			} catch (Exception e) {
				System.out.println("로그아웃 시도중 에러가 발생했습니다.");
				e.printStackTrace();
			}
		} else {
			Output.logoutFail();
		} // if
	}// logoutCheck

}
