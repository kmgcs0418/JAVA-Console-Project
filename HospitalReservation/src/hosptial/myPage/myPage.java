package hosptial.myPage;

import java.util.Scanner;
/**
 * 
 * @author KIMMINGYU
 * 마이페이지 메인 클래스
 *
 */
public class myPage {
	/**
	 * 마이페이지 메인 메서드
	 */
	public void main() {
		
		myPageData.load();
		
		myPageWork work = new myPageWork();
		
		boolean loop = true;
		
		while(loop) {
			
			myPageOutPut.myPageTitle();
			myPageOutPut.myPageMain();
			
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				work.modify();
			} else if (input.equals("2")) {
				work.medicalInfo();
			} else if (input.equals("3")){
				work.delete();
			} else {
				loop = false;
			}
			
		}

		
	}

}
