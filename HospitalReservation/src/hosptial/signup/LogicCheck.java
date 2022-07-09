package hosptial.signup;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import hosptial.DataPath;
/**
 * 회원가입시 입력받는 값들의 유효성 체크 클래스입니다.
 * @author joung
 *
 */
public class LogicCheck {
	private ArrayList<String> list;
	
	/**
	 * 중복회원 가입을 체크하기위해, 기존 유저들의 리스트를 가져옵니다.
	 */
	public LogicCheck() {
		list = new ArrayList<String>();
		fileReader();
	}
	
	/**
	 * 회원 유형을 얻어오는 메서드입니다.
	 * @return 회원 유형구분 번호를 반환합니다.
	 */
	public long getSequence() {
		long max = 0;
		for(String s : list) {
			long temp = Long.parseLong(s.split(",")[0]);
			if(temp > max) max = temp;
		}
		long sequence = ++max;
		
		return sequence;
	}
	/**
	 * 현재 회원가입한 유저들의 정보를 가져는 메서드입니다.
	 */
	public void fileReader() {
		String path = DataPath.userData;
		File file = new File(path);
		
		if(file.exists()) {
			try(BufferedReader br = new BufferedReader(new FileReader(file))) {
				String loop = null;
				
				while((loop=br.readLine())!=null) {
					if(loop.equals("")) continue;
					list.add(loop);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SingupOutput.signupFileError();
		}//if
	}
	/**
	 * 아이디 유효성 검사 메서드입니다.
	 * @param input 입력된 아이디입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean idLogicCheck(String input) {
		if(input==null || (input.length()<5 || input.length()>16)) return false;
		String format = "^[a-z0-9]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 비밀번호 유효성 검사 메서드입니다.
	 * @param input 입력된 비밀번호입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean pwLogicCheck(String input) {
		if(input==null || (input.length()<10 || input.length()>16)) return false;	
		String format = "^[a-zA-Z0-9]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 이름 유효성 검사 메서드입니다.
	 * @param input 입력된 이름입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean nameLogicCheck(String input) {
		if(input==null || (input.length()<2 || input.length()>5)) return false;
		String format = "^[가-힣]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 전화번호 유효성 검사 메서드입니다.
	 * @param input 입력된 전화번호입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean phoneNumberLogicCheck(String input) {
		if(input==null) return false;
		String format = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";	//휴대폰 번호만 체크가능
		return Pattern.matches(format, input);
	}
	/**
	 * 주민번호 입력길이 유효성 검사 메서드입니다.
	 * @param input 입력된 주민번호입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean regNoCheck(String input) {
		if(input==null) return false;
		String format = "\\d{6}\\-[1-4]\\d{6}";
		if(regNoLogicCheck(input)) return false;
		return Pattern.matches(format, input);
	}
	/**
	 * 주민번호 실제 유효성 검사 메서드입니다
	 * @param input 입력된 주민번호입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean regNoLogicCheck(String input) {
		//로직체크 성공하면 true 리턴
		return false;
	}
	/**
	 * 주소 유효성 검사 메서드입니다.
	 * @param input 입력된 주소입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean addressLogicCheck(String input) {
		if(input==null || input.length()<8) return false;
		String format = "^[가-힣]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 진료과 유효성 검사 메서드입니다.
	 * @param input 입력된 진료과입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean departmentLogicCheck(String input) {
		if(input==null || input.length()<2) return false;
		String format = "^[가-힣]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 병원이름 유효성 검사 메서드입니다.
	 * @param input 입력된 병원이름입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean hospitalNameLogicCheck(String input) {
		if(input==null || input.length()<3) return false;
		String format = "^[가-힣]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 의사면허 유효성 검사 메서드입니다.
	 * @param input 입력된 의사면허 번호입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean licenseNumberLogicCheck(String input) {
		if(input==null || input.length()!=5) return false;
		String format = "^[0-9]*$";
		return Pattern.matches(format, input);
	}
	/**
	 * 운영시간 유효성 검사 메서드입니다.
	 * @param input 입력된 시간입니다.
	 * @return 성공시 true를 리턴해 회원가입을 계속 진행합니다.
	 */
	public boolean operatingTimeLogicCheck(String input) {
		if(input==null) return false;
		String format = "([01]?[0-9]|2[0-3]):[0-5][0-9]~([01]?[0-9]|2[0-3]):[0-5][0-9]";
		return Pattern.matches(format, input);
	}
	/**
	 * 계정 중복체크 메서드입니다.
	 * @param index 계정 유형 구분 매개변수입니다
	 * @param input 입력된 계정정보입니다.
	 * @return 종복이 되어있을경우 true를 반환합니다.
	 */
	public boolean DuplicateCehck(int index, String input) {
		//2 = id,
		for(String s : list) {
			String temp = s.split(",")[index];
			if(input.equals(temp)) return true;
		}
		return false;
	}
	
	
}












