package hosptial;

import hosptial.domain.User;
/**
 * 로그인 완료시 계정 정보를 담기위한 클래스입니다.
 * @author joung
 *
 */
public class LoginSession {

	private static User session;

	public static User getSession() {
		return session;
	}

	public static void setSession(User session) {
		LoginSession.session = session;
	}
	
}
