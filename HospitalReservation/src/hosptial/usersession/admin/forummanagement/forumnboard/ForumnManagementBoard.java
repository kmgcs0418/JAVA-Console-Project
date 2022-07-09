package hosptial.usersession.admin.forummanagement.forumnboard;

import java.util.Scanner;

public class ForumnManagementBoard {
	
	public void main() throws Exception {
		Scanner sc = new Scanner(System.in);
		BoardDAO dao = new BoardDAO();
		
		boolean loop = true;
		while(loop) {
			dao.getBoardList();
			System.out.print("선택:");
			int input = Integer.parseInt(sc.nextLine());
			
			if(input == 0) {
				loop = false;
			} else if(input == 1) {	
				dao.prevPage();
			} else if(input == 2) { 
				dao.nextPage();
			} else if(input == 3) {
				System.out.println("상세보기할 글 번호를 선택해주세요.");
				System.out.print("입력:");
				int select = Integer.parseInt(sc.nextLine());
				dao.boardDetail(select);
			} else if(input == 4) { 
				System.out.println("수정할 글 번호를 선택해주세요.");
				System.out.print("입력:");
				int select = Integer.parseInt(sc.nextLine());
				dao.boardUpdate(select);
				dao = new BoardDAO();
			} else if(input == 5) {	
				System.out.println("삭제할 글 번호를 선택해주세요.");
				System.out.print("입력:");
				int select = Integer.parseInt(sc.nextLine());
				dao.boardDelete(select);
				dao = new BoardDAO();
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}//while
		
	}
}
