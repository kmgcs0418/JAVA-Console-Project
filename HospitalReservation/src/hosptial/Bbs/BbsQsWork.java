package hosptial.Bbs;

import java.time.LocalDate;
import java.util.Scanner;

import hosptial.LoginSession;
import hosptial.domain.User;
/**
 * 
 * @author KIMMINGYU
 * 질의응답 작업 클래스
 *
 */
public class BbsQsWork {

	private Scanner scan;

	public BbsQsWork() {
		this.scan = new Scanner(System.in);
	}
	/**
	 * 질문하기 메서드
	 */
	public void question() {

		User user = LoginSession.getSession();

		LocalDate now = LocalDate.now();
		String date = now.toString();

		System.out.println();

		BbsOutput.subtiltle("질문하기");

		System.out.println("게시물 번호 : ");
		String bbsNo = scan.nextLine();

		System.out.println("질문하세요. ");
		String text = scan.nextLine();

		BbsQuestion q = new BbsQuestion(bbsNo, Long.toString(user.getSequence()),
				Long.toString(user.getUserTypeCheck()), text, date);

		BbsData.qlist.add(q);
		BbsData.questionSave();

		System.out.println();
		System.out.println("질문 완료");
		System.out.println();

	}
	/**
	 * 질의응답 목록 메서드
	 */
	public void list() {

		boolean loop = true;

		while (loop) {

			BbsOutput.subtiltle("질문 목록");

			System.out.println("[게시물번호][회원번호][유저타입][날짜]");

			for (BbsQuestion q : BbsData.qlist) {
				System.out.printf("%s\t%4s\t%4s\t%6s\n", q.getBbsNo(), q.getSequence(),
						q.getUserTypeCheck().equals("0") ? "환자" : "의사", q.getDate());
			}

			System.out.println();

			BbsOutput.question();

			String input = scan.nextLine();

			if (input.equals("1")) {
				question();
			} else if (input.equals("2")) {
				view();
			} else if (input.equals("3")) {
				answer();
			} else {
				loop = false;
			}

		}

		System.out.println("========================================");

	}
	/**
	 * 질의응답 상세히 보기 메서드
	 */
	public void view() {

		System.out.print("게시물 번호를 입력하세요. : ");

		String input = scan.nextLine();

		BbsQuestion question = null;

		for (BbsQuestion q : BbsData.qlist) {

			if (q.getBbsNo().equals(input)) {
				question = q;
				break;
			}

		}

		BbsAnswer answer = null;

		for (BbsAnswer a : BbsData.alist) {

			if (a.getBbsNo().equals(input)) {
				answer = a;
				break;
			}

		}

		if (question != null) {
			System.out.println("[질문] : " + question.getText());
		}
		if (answer != null) {
			System.out.println("[답변] : " + answer.getText());
		}

		System.out.println("========================================");

		System.out.println("뒤로가기(0입력) : ");
		String input1 = scan.nextLine();

	}
	/**
	 * 대답하기 메서드
	 */
	public void answer() {

		User user = LoginSession.getSession();

		LocalDate now = LocalDate.now();
		String date = now.toString();

		System.out.println();

		BbsOutput.subtiltle("대답하기");

		System.out.println("게시물 번호 : ");
		String bbsNo = scan.nextLine();

		System.out.println("대답하세요. : ");
		String text = scan.nextLine();

		BbsAnswer a = new BbsAnswer(bbsNo, Long.toString(user.getSequence()), Long.toString(user.getUserTypeCheck()),
				text, date);

		BbsData.alist.add(a);
		BbsData.answerSave();

		System.out.println();
		System.out.println("대답 완료");

		System.out.println("========================================");

	}

}
