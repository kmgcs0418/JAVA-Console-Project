package hosptial.usersession.admin.forummanagement.forumnboard;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import hosptial.DataPath;

/**
 * 게시판관리 클래스입니다.
 * @author joung
 *
 */
public class BoardDAO {
	Scanner sc = new Scanner(System.in);
	Stack<BoardVO> prev = new Stack<>();
	Stack<BoardVO> now = new Stack<>();
	Stack<BoardVO> next = new Stack<>();
	List<BoardVO> boardList = new ArrayList<>();
	int page = 5;

	/**
	 * 게시판 목록을 불러오는 생성자입니다.
	 */
	public BoardDAO() {
		setBoardList();
	}
	
	/**
	 * 게시판 목록의 데이터를 불러오는 메서드입니다.
	 */
	private void setBoardList() {
		
		File file = new File(DataPath.postscript);
		
		if(!file.exists()) {
			System.out.println("파일을 불러오는데 에러가 발생했습니다.");
			System.out.println("다시 시도해주세요.");
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String loop = null;
			while((loop = br.readLine())!=null) {
				if(loop.equals("")) continue;
				String[] temp = loop.split(",");
				
				BoardVO vo = new BoardVO();
				vo.setSequence(temp[0]);
				vo.setRating(temp[1]);
				vo.setWriter(temp[2]);
				vo.setContent(temp[3]);
				vo.setRegistDate(temp[4]);
				vo.setHospitalName(temp[5]);
				boardList.add(vo);
			}//while
			
		} catch (Exception e) {
			System.out.println("시스템 오류 발생했습니다. in ForbiddenDAO");
			e.printStackTrace();
		}
		pageSet();
	}
	/**
	 * 게시판 페이지 설정 메서드입니다.
	 */
	private void pageSet() {
		for(int i=boardList.size()-1; i>=0; i--) {
			next.push(boardList.get(i));
		}
		for(int i=0; i<page; i++) {
			if(next.isEmpty()) continue;
			now.push(next.pop());
		}
	}
	/**
	 * 다음페이지 선택시 페이지 세팅해주는 메서드입니다.
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
				System.out.println("BoardDAO Thread Error");
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
	 * 이전페이지 세팅시 페이지 세팅해주는 메서드입니다.
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
	 * 게시판 글목록 출력 메서드입니다.
	 */
	public void getBoardList() {
		System.out.println("=======================================================");
		System.out.println("\t\t   [후기 목록]");
		System.out.println("-------------------------------------------------------");
		System.out.println("번호  |  평점  |  작성자  |    작성일           |    병원명");
		System.out.println("=======================================================");
		if(boardList.isEmpty()) {
			System.out.println("\t작성된 게시글이 없습니다.");
		} else {
			for(int i=0; i<now.size(); i++) {
				System.out.printf("%-4s | %-5s | %-5s | %-17s  | %-10s\n"
						,boardList.indexOf(now.get(i))+1
						,now.get(i).getRating(), now.get(i).getWriter()
						,now.get(i).getRegistDate(),now.get(i).getHospitalName());
			}//for
		}//if
		System.out.println("=======================================================");
		System.out.println("0.이전메뉴, 1.이전페이지, 2.다음페이지");
		System.out.println("3.상세보기, 4.수정, 5.삭제");
	}
	/**
	 * 게시판 글 상세정보 출력 메서드입니다.
	 * @param select 선택한 게시판 번호의 글번호입니다.
	 */
	public void boardDetail(int select) {
		if(boardList.isEmpty()) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		BoardVO vo = new BoardVO();
		vo = boardList.get(select-1);
		System.out.println("No." + select);
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("평점 : " + vo.getRating());
		System.out.println("================================================");
		System.out.println(vo.getContent());
		System.out.println("================================================");
		System.out.println("0.목록, 1.수정, 2.삭제");
		System.out.print("입력 :");
		select = Integer.parseInt(sc.nextLine());
		if(select == 1) {
			boardUpdate(boardList.indexOf(vo) + 1);
		} else if(select == 2) {
			boardDelete(boardList.indexOf(vo) + 1);
		} else if(select == 0) {
			return;
		} else {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	/**
	 * 게시판 글 수정을 위한 메서드입니다.
	 * @param select 선택한 글번호의 메서드를 수정합니다.
	 */
	public void boardUpdate(int select) {
		if(boardList.isEmpty()) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		
		BoardVO vo = new BoardVO();
		vo = boardList.get(select-1);
		
		System.out.print("평점(취소:0) :");
		String rating = sc.nextLine();
		if("0".equals(rating)) {
			System.out.println("수정이 취소되었습니다.");
			return;
		}
		
		System.out.print("글내용(취소:0) :");
		String content = sc.nextLine();
		if("0".equals(content)) {
			System.out.println("수정이 취소되었습니다.");
			return;
		}
		
		System.out.print("병원명(취소:0) :");
		String hostpitalName = sc.nextLine();
		if("0".equals(hostpitalName)) {
			System.out.println("수정이 취소되었습니다.");
			return;
		}
		
		//수정 취소를 하지 않았을 경우 입력한 값을 저장
		vo.setRating(rating);
		vo.setContent(content);
		vo.setHospitalName(hostpitalName);
		
		//등록한 현재 날짜 저장
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(수정됨)");
		String registData = sdf.format(new Date());
		vo.setRegistDate(registData);	//등록 날짜를 수정 날짜로 변경
		
		boardList.set(boardList.indexOf(vo), vo);
		System.out.println("글이 수정되었습니다.\n");
		save();
	}

	/**
	 * 게시판 글 삭제 메서드입니다.
	 * @param select 선택한 번호의 글을 삭제합니다.
	 */
	public void boardDelete(int select) {
		if(boardList.isEmpty()) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		BoardVO vo = new BoardVO();
		vo = boardList.get(select-1);
		boardList.remove(boardList.indexOf(vo));
		System.out.println(boardList.size());
		System.out.println(select + "번 글이  삭제되었습니다.");
		save();
	}
	/**
	 * 수정된 내용을 저장하는 메서드입니다.
	 */
	public void save() {
		File file = new File(DataPath.postscript);

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			for(BoardVO vo : boardList) {
				//1,4,조기공,친절합니다.,2022-02-02,가나의원
				String temp = String.format("%s,%s,%s,%s,%s,%s\n"
						,vo.getSequence(), vo.getRating(), vo.getWriter(), vo.getContent(), vo.getRegistDate(),vo.getHospitalName());
				bw.write(temp);
			}
		} catch (IOException e) {
			System.out.println("저장중 에러가 발생했습니다.");
			e.printStackTrace();
		}
		deleteLastLine();
	}
	//공백제거용
	/**
	 * 파일 저장 후 마지막 개행문자를 지워주는 메서드입니다.
	 */
	private void deleteLastLine() {
		File oldFile = new File(DataPath.postscript);
		File newFile = new File(DataPath.temp);
		try(BufferedReader br = new BufferedReader(new FileReader(new File(DataPath.postscript)));
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(DataPath.temp)));) {
			String lineToRemoveFromFile = "n";
			String lieFromuserfile;
			boolean addnewLine = false;
			while ((lieFromuserfile = br.readLine())!=null) {

				String trimmedLine = lieFromuserfile.trim();
				if(trimmedLine.equals(lineToRemoveFromFile)) {
					continue;
				}
				if(addnewLine) {
					bw.newLine();
				} else {
					addnewLine = true;
				}
				bw.append(lieFromuserfile);
			}
		} catch (Exception e) {
			System.out.println("deleteLastLine 에러");
		}
		oldFile.delete();
		newFile.renameTo(oldFile);
	}
	
	
}
