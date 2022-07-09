package hosptial.usersession.admin;

/**
 * 관리자 세션 출력전용 클래스입니다.
 * @author joung
 *
 */
public class AdminSessionOutput {

	public static void adminMain() {
		System.out.println("=====================================");
		System.out.println("\t[관리자]님 환영합니다.");
		System.out.println("=====================================");
		System.out.println("1. 회원 관리");
		System.out.println("2. 게시판 관리");
		System.out.println("0. 뒤로가기");
		System.out.print("번호 : ");
	}

}
