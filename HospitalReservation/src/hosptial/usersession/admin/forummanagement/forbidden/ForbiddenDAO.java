package hosptial.usersession.admin.forummanagement.forbidden;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import hosptial.DataPath;

/**
 * 현재 설정된 금지어를 게시판 형태로 출력합니다.
 * @author joung
 *
 */
public class ForbiddenDAO {
	Scanner sc = new Scanner(System.in);
	Stack<ForbiddenVO> prev = new Stack<>();
	Stack<ForbiddenVO> now = new Stack<>();
	Stack<ForbiddenVO> next = new Stack<>();
	List<ForbiddenVO> forbiddenList = new ArrayList<>();
	int page = 5;
	
	/**
	 * 금지어 목록을 불러오는 생성자입니다.
	 */
	public ForbiddenDAO() {
		setForbiddenList();
	}
	/**
	 * 금지어 목록이 담긴 파일을 읽어오는 메서드입니다.
	 */
	private void setForbiddenList() {
		
		File file = new File(DataPath.forbiddenWord);
		
		if(!file.exists()) {
			System.out.println("파일을 불러오는데 에러가 발생했습니다.");
			System.out.println("다시 시도해주세요.");
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String loop = null;
			while((loop = br.readLine())!=null) {
				if(loop.equals("")) continue;
				String[] temp = loop.split(",");
				
				for(int i=0; i<temp.length; i++) {
					ForbiddenVO vo = new ForbiddenVO();
					vo.setWord(temp[i]);
					forbiddenList.add(vo);
				}//for
			}//while
			
		} catch (Exception e) {
			System.out.println("시스템 오류 발생했습니다. in ForbiddenDAO");
			e.printStackTrace();
		}
		
		pageSet();
	}
	/**
	 * 금지어 게시판 페이지 설정 메서드입니다.
	 */
	private void pageSet() {
		for(int i=forbiddenList.size()-1; i>=0; i--) {
			next.push(forbiddenList.get(i));
		}
		for(int i=0; i<page; i++) {
			if(next.isEmpty()) continue;
			now.push(next.pop());
		}
	}
	/**
	 * 금지어 게시판 다음목록 세팅 메서드입니다.
	 */
	public void nextPage() {
		if(next.isEmpty()) {
			System.out.println("==========================================");
			System.out.println("\t마지막 페이지입니다.");
			System.out.println("잠시후 이전 메뉴로 돌아갑니다...");
			System.out.println("==========================================");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("forbiddenInsert Thread Error");
				e.printStackTrace();
			}
			return;
		}
		for(int i=0; i<page; i++) {
			if(now.isEmpty()) continue;
			prev.push(now.pop());
		}
		for(int i=0; i<page; i++) {
			if(next.isEmpty()) continue;
			now.push(next.pop());
		}

	}
	/**
	 * 금지어 게시판 이전목록 세팅 메서드입니다.
	 */
	public void prevPage() {
		if(prev.isEmpty()) {
			System.out.println("==========================================");
			System.out.println("\t첫 페이지입니다.");
			System.out.println("잠시후 이전 메뉴로 돌아갑니다...");
			System.out.println("==========================================");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("forbiddenInsert Thread Error");
				e.printStackTrace();
			}
			return;
		}
		for(int i=0; i<page; i++) {
			if(now.isEmpty()) continue;
			next.push(now.pop());
		}
		for(int i=0; i<page; i++) {
			if(prev.isEmpty()) continue;
			now.push(prev.pop());
		}
	}
	/**
	 * 금지어 목록을 게시판 형태로 출력해주는 메서드입니다.
	 */
	public void getForbiddenList() {
		System.out.println("==========================================");
		System.out.println("\t      <<금지어 목록>>");
		System.out.println("------------------------------------------");
		System.out.println("  번호  |\t\t      금지어");
		System.out.println("==========================================");
		if(forbiddenList.isEmpty()) {
			System.out.println("설정된 금지어가 없습니다.");
		} else {
			for(int i=0; i<now.size(); i++) {
				if(now.indexOf(now.get(i)) == -1) break;
				System.out.printf("%4d   |%17s\n", forbiddenList.indexOf(now.get(i))+1, now.get(i).getWord());
			}
		}//if
		System.out.println("==========================================");
		System.out.println("0.돌아가기, 1.이전목록, 2.다음목록, 3.추가, 4.삭제");
	}
	/**
	 * 금지어 추가 메서드입니다.
	 */
	public void forbiddenInsert() {
		ForbiddenVO vo = new ForbiddenVO();
		System.out.println("추가할 금지어를 입력해주세요.(취소:0)");
		System.out.print("입력:");
		String forbidden = sc.nextLine();
		if("0".equals(forbidden)) {
			System.out.println("==========================================");
			System.out.println("\t금지어 추가를 취소합니다.");
			System.out.println("잠시후 이전 메뉴로 돌아갑니다...");
			System.out.println("==========================================");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("forbiddenInsert Thread Error");
				e.printStackTrace();
			}
		}
		vo.setWord(forbidden);
		forbiddenList.add(vo);
		save(forbidden);
		System.out.println("==========================================");
		System.out.printf("금지어 : %s이 추가되었습니다.\n", forbidden);
		System.out.println("==========================================");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("forbiddenInsert Thread Error");
			e.printStackTrace();
		}
	}
	/**
	 * 금지어 삭제 메서드입니다.
	 * @param select 선택된 금지어 번호입니다.
	 */
	public void forbiddenDelete(int select) {
		if(forbiddenList.isEmpty()) {
			System.out.println("==========================================");
			System.out.println("\n등록된 금지어가 없습니다.");
			System.out.println("잠시후 이전 메뉴로 돌아갑니다...");
			System.out.println("==========================================");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("forbiddenInsert Thread Error");
				e.printStackTrace();
			}
			return;
		}
		if(select == 0) {
			System.out.println("==========================================");
			System.out.println("\t금지어 삭제를 취소합니다.");
			System.out.println("잠시후 이전 메뉴로 돌아갑니다...");
			System.out.println("==========================================");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("forbiddenInsert Thread Error");
				e.printStackTrace();
			}
			return;
		}
		
		ForbiddenVO vo = new ForbiddenVO();
		vo = forbiddenList.get(select - 1);
		forbiddenList.remove(forbiddenList.indexOf(vo));
		System.out.println("==========================================");
		System.out.printf("금지어 : %s이 삭제되었습니다.\n", vo.getWord());
		System.out.println("==========================================");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("forbiddenInsert Thread Error");
			e.printStackTrace();
		}
		delete();
	}
	/**
	 * 금지어 수정 후 파일에 저장해주는 메서드입니다.
	 * @param word 설정 또는 삭제한 금지어를 담는 매개변수입니다.
	 */
	public void save(String word) {
		File file = new File(DataPath.forbiddenWord);

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
			bw.write(word+",");
		} catch (IOException e) {
			System.out.println("저장중 에러가 발생했습니다.");
			e.printStackTrace();
		}
	}
	/**
	 * 금지어 삭제시 생기는 공백을 제거하는 메서드입니다.
	 */
	public void delete() {
		File newFile = new File(DataPath.temp);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
			for(ForbiddenVO vo : forbiddenList) {
				bw.write(vo.getWord()+",");
			}
		} catch (Exception e) {
			System.out.println("삭제중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		File oldFile = new File(DataPath.forbiddenWord);
		oldFile.delete();
		newFile.renameTo(oldFile);
	}
}
