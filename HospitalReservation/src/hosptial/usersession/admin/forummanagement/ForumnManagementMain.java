package hosptial.usersession.admin.forummanagement;

import java.util.Scanner;
import hosptial.usersession.admin.forummanagement.forbidden.ForbiddenManagementBoard;
import hosptial.usersession.admin.forummanagement.forumnboard.ForumnManagementBoard;

/**
 * 게시판관리 메인 화면입니다.
 * @author joung
 *
 */
public class ForumnManagementMain {

	public void main() throws Exception {
		
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		
		while(loop) {
			ForumnManageMentOutput.forumnManagementMain();
			int input = Integer.parseInt(sc.nextLine());
			
			if(input == 0) {
				loop = false;
			} else if(input == 1) {
				//TODO 게시판관리
				ForumnManagementBoard board = new ForumnManagementBoard();
				board.main();
			} else if(input == 2) {
				//TODO 금지어 관리
				ForbiddenManagementBoard forbid = new ForbiddenManagementBoard();
				forbid.main();
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}//while

	}

}
