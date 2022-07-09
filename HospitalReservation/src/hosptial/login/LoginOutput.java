package hosptial.login;
/**
 * 로그인 파트 전반적인 출력메서드를 담고있는 클래스입니다.
 * @author joung
 *
 */
public class LoginOutput {


	public static void main() {
		System.out.println("=====================================");
		System.out.println("\t회원 유형을 선택해주세요.");
		System.out.println("=====================================");
	}
	
	public static void commonUserMain() {
		System.out.println("=====================================");
		System.out.println("\t일반회원 로그인 메뉴입니다.");
		System.out.println("=====================================");
	}
	public static void adminUserMain() {
		System.out.println("=====================================");
		System.out.println("\t관리자 로그인 메뉴입니다.");
		System.out.println("=====================================");
	}
	public static void doctorUserMain() {
		System.out.println("=====================================");
		System.out.println("\t의사 로그인 메뉴입니다.");
		System.out.println("=====================================");
	}

	public static void menu() {
		System.out.println("1. 회원 로그인");
		System.out.println("2. 의사 로그인");
		System.out.println("3. 관리자 로그인");
		System.out.println("0. 뒤로가기");
		
	}
	public static void loginFail() {
		System.out.println("=====================================");
		System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다.");
		System.out.println("=====================================");
		System.out.println("0. 이전메뉴로...");
		System.out.println("계속하려면 아무키나 누르십시오");
		System.out.print("입력 :");
	}
	
	public static void loginSuccess() {
		System.out.println("=====================================");
		System.out.println("\t로그인에 성공했습니다.");
		System.out.println("잠시후 메인화면으로 돌아갑니다.");
		System.out.println("=====================================");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("LoginOutput.loginSuccess Thread Exception");
		}
	}
	
	public static void emptyElement() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("빈 문자열은 입력하실 수 없습니다.");
		System.out.println("=====================================");
		System.out.println("0. 이전메뉴로...");
		System.out.println("계속하려면 아무키나 누르십시오");
		System.out.print("입력 :");
	}
	
	public static void userTypeAdmin() {
		System.out.println("=====================================");
		System.out.println("관리자 계정은 관리자 로그인 창에서 로그인해주세요.");
		System.out.println("=====================================");
	}
	public static void userTypeDoctor() {
		System.out.println("=====================================");
		System.out.println("의사 계정은 의사 로그인 창에서 로그인해주세요.");
		System.out.println("=====================================");
	}
	public static void userTypeCommon() {
		System.out.println("=====================================");
		System.out.println("일반 계정은 일반 로그인 창에서 로그인해주세요.");
		System.out.println("=====================================");
	}

}
