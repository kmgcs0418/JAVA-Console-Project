package hosptial.Bbs;

import java.time.LocalDate;
import java.util.Scanner;

import hosptial.LoginSession;
import hosptial.domain.User;
/**
 * 
 * @author KIMMINGYU
 * 게시판 후기 작업 클래스
 *
 */
public class BbsPostWork {

	private Scanner scan;
	
	public BbsPostWork() {
		this.scan = new Scanner(System.in);
	}
	/**
	 * 후기 쓰기 메서드
	 */
	public void write() {

		User user = LoginSession.getSession();

		LocalDate now = LocalDate.now();
		String date = now.toString();

		System.out.println();

		BbsOutput.subtiltle("후기 쓰기");

		System.out.print("평점을 입력하세요. : ");
		String grade = scan.nextLine();

		System.out.print("내용을 입력하세요 : ");
		String text = scan.nextLine();

		System.out.print("병원을 입력하세요. : ");
		String hospital = scan.nextLine();

		BbsPost p = new BbsPost(Long.toString(user.getSequence()), grade, user.getName(), text, date, hospital);

		BbsData.plist.add(p);
		BbsData.postSave();

		/*
		 * BbsData.plist.stream().forEach(u -> { System.out.println(u.getHospital());
		 * System.out.println(u.getText()); });
		 */

		System.out.println();
		System.out.println("후기 작성 완료");
		System.out.println();

	}
	/**
	 * 후기 목록 불러오기 메서드
	 */
	public void list() {

		boolean loop = true;

		while (loop) {

			BbsOutput.subtiltle("후기 목록");

			System.out.println("[회원번호]\t[평점]\t[이름]\t[날짜]\t\t[병원명]");

			for (BbsPost p : BbsData.plist) {
				System.out.printf("%s\t%2s\t%2s\t%2s\t%s\n", p.getSequence(), p.getGrade(), p.getName(), p.getDate(),
						p.getHospital());
			}

			System.out.println();

			BbsOutput.post();

			String input = scan.nextLine();

			if (input.equals("1")) {
				view();
			} else if (input.equals("2")) {
				write();
			} else if (input.equals("3")) {
				delete();
			} else {
				loop = false;
			}

		}

		System.out.println();

	}
	/**
	 * 목록 상세히 보기 메서드
	 */
	private void view() {

		System.out.print("회원번호를 입력하세요. : ");

		String input = scan.nextLine();

		BbsPost result = null;

		for (BbsPost p : BbsData.plist) {
			if (p.getSequence().equals(input)) {
				result = p;
				break;
			}
		}

		if (result != null) {
			System.out.println("[내용]" + result.getText());
		}

		System.out.println("========================================");

	}
	/**
	 * 게시물 삭제 메서드
	 */
	private void delete() {

		System.out.print("삭제할 게시물 선택(자신의 고유번호) : ");

		String input = scan.nextLine();

		BbsPost result = null;

		User user = LoginSession.getSession();

		for (BbsPost p : BbsData.plist) {
			if (p.getSequence().equals(input)) {
				result = p;
				break;
			}
		}

		if (result != null && Long.toString(user.getSequence()).equals(input)) {
			BbsData.plist.remove(result);
			BbsData.postSave();
			System.out.println("삭제 완료.");
		} else {
			System.out.println("자신의 게시물이 아닙니다.");
		}

		System.out.println("========================================");

	}

}
