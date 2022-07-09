package hosptial.myPage;
/**
 * 
 * @author KIMMINGYU
 * 마이페이지 콘솔 화면 출력
 *
 */
public class myPageOutPut {
	/**
	 * 마이페이지 메인 타이틀 출력
	 */
	public static void myPageTitle() {
		
		System.out.println("========================================");
		System.out.println("           마이페이지");
		System.out.println("========================================");
		
	}
	/**
	 * 마이페이지 메인 화면 출력
	 */
	public static void myPageMain() {
		
		System.out.println("(1. 회원정보수정 2. 진료내역확인 3. 회원탈퇴 0. 뒤로가기)");
		System.out.println("번호를 입력하세요. : ");
		
	}
	/**
	 * 마이페이지서브 화면 출력
	 * @param title  서브화면 타이틀
	 */
	public static void subTitle(String title) {
		
		System.out.println();
		System.out.println();
		System.out.println("========================================");
		System.out.println("           " + title);
		System.out.println("========================================");
		
	}

}
