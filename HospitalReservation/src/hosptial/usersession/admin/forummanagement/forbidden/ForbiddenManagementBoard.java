package hosptial.usersession.admin.forummanagement.forbidden;

import java.util.*;

public class ForbiddenManagementBoard {

	public void main() {
		Scanner sc = new Scanner(System.in);
		ForbiddenDAO dao = new ForbiddenDAO();
		
		while(true) {
			dao.getForbiddenList();
			System.out.print("입력:");
			int input = Integer.parseInt(sc.nextLine());
			if(input==0) {	//종료
				return;
			} else if(input==1) {	//이전
				dao.prevPage();
			} else if(input==2) {	//다음
				dao.nextPage();
			} else if(input==3) {	//추가
				dao.forbiddenInsert();
				dao = new ForbiddenDAO();
			} else if(input==4) {	//삭제
				System.out.println("삭제할 금지어 번호를 입력해주세요.(취소:0)");
				System.out.print("입력:");
				int select = Integer.parseInt(sc.nextLine());
				dao.forbiddenDelete(select);
				dao = new ForbiddenDAO();
			} else {
				System.out.println("잘못 선택하셨습니다.");
			}//if
		}//while

	}//main

}
