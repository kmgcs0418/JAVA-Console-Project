package hosptial.usersession.admin.membermanagement;

import java.util.Scanner;
import hosptial.usersession.admin.membermanagement.memberboard.MemberDAO;

/**
 * 관리자의 멤버관리 메인클래스입니다.
 * @author joung
 *
 */
public class MemberManagementMain {

	public void main() {
		
		MemberDAO md = new MemberDAO();	
		Scanner sc = new Scanner(System.in);
		
		boolean loop = true;
		while(loop) {
			md.getMemberList();
			int select = Integer.parseInt(sc.nextLine());
			
			if(select == 0) {		//종료 하기
				loop = false;
			} else if(select == 1) {
				md.prevPage();
			} else if(select == 2) {
				md.nextPage();
			} else if(select == 3) {	//회원 상세정보보기
				System.out.println("상세보기할 유저의 번호를 선택하세요.");
				System.out.print("입력:");
				int input = Integer.parseInt(sc.nextLine());
				md.memberDetail(input);
			} else if(select == 4) {	//수정하기
				System.out.println("계정정보를 수정할 유저의 번호를 선택하세요.(취소 : 0)");
				System.out.print("입력:");
				int input = Integer.parseInt(sc.nextLine());
				if(input == 0) {
					return;
				} else {
					md.memberUpdate(input);
					md = new MemberDAO();				
				}
			} else if(select == 5) {	//삭제하기
				System.out.println("삭제할 유저의 번호를 선택하세요.");
				System.out.print("입력:");
				int input = Integer.parseInt(sc.nextLine());
				md.memberDelete(input);
				md = new MemberDAO();
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}//while
	}
	
}