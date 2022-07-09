package hosptial.signup;
/**
 * 일반유저 회원가입 클래스입니다.
 * @author joung
 *
 */
public class CommonUserSignup extends UserSignup {

	/**
	 * 일반유저 회원가입이 성공하면 메인메뉴로 돌아가게 만드는 메서드입니다.
	 * @author joung
	 * @return 성공시 true실패시 false를 리턴합니다.
	 */
	public boolean main() {
		
		SingupOutput.commonUserSignupMain();
		
		//sequence, UsertType, id, pw, 이름, 전화번호, 주소, 주민번호
		boolean loop = true;
		while(loop) {
			
			idInputCheck("0");					//0 일반유저
			if(escapeCheck()) return false;

			pwInputCheck();
			if(escapeCheck()) return false;
			
			nameInputCheck();
			if(escapeCheck()) return false;
			
			phoneNumberInputCheck();
			if(escapeCheck()) return false;
			
			addressInputCheck();
			if(escapeCheck()) return false;

			regNoInputCheck();
			if(escapeCheck()) return false;
			
			save("0");	//0 일반유저
			loop = false;
		}//while
		return true;
			
	}
	
}
