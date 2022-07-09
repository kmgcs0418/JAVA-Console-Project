package hosptial.Bbs;

import java.util.Scanner;
/**
 * 
 * @author KIMMINGYU
 * 게시판 메인 클래스
 *
 */
public class Bbs {
	/**
	 * 게시판 관리 메인 메서드
	 */
	public void main() {
		
		BbsData.load();
		
		BbsPostWork postWork = new BbsPostWork();
		BbsQsWork qsWork = new BbsQsWork();
		
		boolean loop = true; 
		
		while(loop) {
			
			BbsOutput.bbstitle();
			BbsOutput.bbsmain();
			
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				postWork.list();
			} else if (input.equals("2")) {
				qsWork.list();
			} else {
				loop = false;
			}
			
		}
		
	}

}
