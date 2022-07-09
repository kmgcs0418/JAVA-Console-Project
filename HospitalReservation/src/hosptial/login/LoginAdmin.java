package hosptial.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.Admin;
import hosptial.domain.CommonUser;
import hosptial.domain.User;
/**
 * 관리자 로그인 클래스입니다.
 * @author joung
 *
 */
public class LoginAdmin {
	/**
	 * 관리자 로그인 메인 화면입니다.
	 * @return 로그인 성공시 true를 리턴해 해당 유저에맞는 메뉴로 보내줍니다.
	 */
	public static boolean main() {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			
			LoginOutput.adminUserMain();
			
			System.out.print("아이디를 입력하세요 : ");
			String id = sc.nextLine().trim();
			System.out.print("비밀번호를 입력하세요 : ");
			String pw = sc.nextLine().trim();
			System.out.println();
			
			if(id.length()==0 || pw.length()==0 ) {
				LoginOutput.emptyElement();
				String result = sc.nextLine();
				if("0".equals(result)) return false;
			} else if(!loginCheck(id, pw)) {
				LoginOutput.loginFail();
				String result = sc.nextLine();
				if("0".equals(result)) return false;
			} else {
				LoginOutput.loginSuccess();
				loop = false;
			}			
		}//while
		return true;

	}
	/**
	 * 로그인시 입력받은 값이 실제 있는 계정인지 확인하는 메서드입니다.
	 * @param id 입력받은 아이디
	 * @param pw 입력받은 비밀번호
	 * @return 로그인 성공시 true, 실패시 flase 반환
	 */
	private static boolean loginCheck(String id, String pw) {
		
		try {
			File file = new File(DataPath.userData);
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while((line=br.readLine())!=null) {
					String[] temp = line.split(","); //2. id 3. pw
					if(id.equals(temp[2]) && pw.equals(temp[3]) && userTypeCheck(temp[1])) {
						inputSesstion(temp);
						return true;
					}//if
					
				}//while
				br.close();
			} else {
				System.out.println("오류가 발생했습니다. 개발자에게 문의해주세요.");
			}//if
		} catch (Exception e) {
			e.printStackTrace();
		}//try

		return false;
	}
	/**
	 * 로그인 성공시 로그인 세션에 계정의 정보를 보내주는 메서드입니다.
	 * @param info 로그인한 유저의 정보를 받아옵니다.
	 */
	private static void inputSesstion(String[] info) {
		
		try {
			User user = new Admin(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], info[3], info[4], info[5], info[6], info[7]);
			LoginSession.setSession(user);
		} catch (Exception e) {
			System.out.println("로그인 시도중에 오류가 발생했습니다.");
			e.printStackTrace();
		}//try
	}
	/**
	 * 관리자 로그인이 맞는지 확인하는 메서드입니다.
	 * @param userType -1 : 관리자, 0 : 일반 사용자, 1:의사 사용자입니다.
	 * @return 관리자 계정이 맞다면 true, 틀리다면 false를 리턴해 로그인을 취소시킵니다.
	 */
	private static boolean userTypeCheck(String userType) {	
		if("0".equals(userType)) {
			LoginOutput.userTypeCommon();
			return false;
		} else if("1".equals(userType)) {
			LoginOutput.userTypeDoctor();
			return false;
		}

		return true;
	}

}
