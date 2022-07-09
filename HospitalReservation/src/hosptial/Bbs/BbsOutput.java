package hosptial.Bbs;
/**
 * 
 * @author KIMMINGYU
 * 게시판 콘솔 화면 출력
 *
 */
public class BbsOutput {
	/**
	 * 개사펀 메인 타이틀 출력
	 */
	public static void bbstitle() {
		System.out.println("========================================");
		System.out.println("           게시판");
		System.out.println("========================================");
	}
	/**
	 * 게시판 메인 화면 출력
	 */
	public static void bbsmain() {
		System.out.println("(1. 후기 2. 질의응답 0. 뒤로가기)");
		System.out.print("번호를 입력하세요 : ");
	}
	/**
	 * 게시판서브 화면 출력
	 * @param title 서브화면 타이틀
	 */
	public static void subtiltle(String title) {
		System.out.println();
		System.out.println();
		System.out.println("========================================");
		System.out.println("           " + title);
		System.out.println("========================================");
	}
	/**
	 * 후기 화면 출력
	 */
	public static void post() {
		System.out.println("(1. 상세보기 2. 글 쓰기 3. 삭제하기 0. 뒤로가기)");
		System.out.print("번호를 입력하세요. : ");
	}
	/**
	 * 질의응답 화면 출력
	 */
	public static void question() {
		System.out.println("(1. 질문하기 2. 상세보기 3. 대답하기 0. 뒤로가기)");
		System.out.print("번호를 입력하세요. : ");
	}
	

}
