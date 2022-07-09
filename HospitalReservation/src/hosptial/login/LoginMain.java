package hosptial.login;

import java.util.Scanner;
import hosptial.signup.SingupOutput;

/**
 * 로그인 유형 선택 클래스입니다.
 * @author joung
 *
 */
public class LoginMain {
	/**
	 * 로그인 유형 선택 메서드입니다.
	 * @author joung
	 */
	public void login() {
		Scanner sc = new Scanner(System.in);
		
		boolean loop = true;
		while(loop) {
			LoginOutput.main();
			LoginOutput.menu();
			System.out.print("번호 : ");
			int input = sc.nextInt();
			
			if(input==1) {
				boolean result = LoginCommonUser.main();
				if(result==true) return;
			} else if(input==2) {
				boolean result = LoginDoctorUser.main();
				if(result==true) return;
			} else if(input==3) {
				boolean result = LoginAdmin.main();
				if(result==true) return;
				
			} else if(input==0) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}//while
	}
	
}
