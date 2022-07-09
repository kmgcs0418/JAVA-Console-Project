package hosptial.myPage;

import java.util.Scanner;

import hosptial.LoginSession;
import hosptial.Bbs.BbsData;
import hosptial.Bbs.BbsOutput;
import hosptial.Bbs.BbsPost;
import hosptial.domain.User;
/**
 * 
 * @author KIMMINGYU
 * 마이페이지 작업 클래스
 *
 */
public class myPageWork {

	private Scanner scan;

	public myPageWork() {
		this.scan = new Scanner(System.in);
	}
	/**
	 * 회원정보 수정하기 메서드
	 */
	public void modify() {

		User user = LoginSession.getSession();

		myPageOutPut.subTitle("수정하기");

		info result = null;

		for (info a : myPageData.infolist) {
			if (a.getSequence().equals(Long.toString(user.getSequence()))) {
				result = a;
				break;
			}

		}

		if (result != null) {
			System.out.print("비밀번호(미수정시 엔터입력) : ");
			String password = scan.nextLine();

			if (!password.equals("")) {
				result.setPassword(password);
			}

			System.out.print("이름(미수정시 엔터입력) : ");
			String name = scan.nextLine();

			if (!name.equals("")) {
				result.setName(name);
			}

			System.out.print("핸드폰 번호(미수정시 엔터입력) : ");
			String phoneNum = scan.nextLine();

			if (!phoneNum.equals("")) {
				result.setPhoneNum(phoneNum);
			}

			System.out.print("주소(미수정시 엔터입력) : ");
			String address = scan.nextLine();

			if (!address.equals("")) {
				result.setAddress(address);
			}

		}

		myPageData.infoSave();

		System.out.println();
		System.out.println("수정 완료");
		System.out.println();

	}
	/**
	 * 회원탈퇴 메서드
	 */
	public void delete() {

		info result = null;

		User user = LoginSession.getSession();

		for (info a : myPageData.infolist) {
			if (a.getSequence().equals(Long.toString(user.getSequence()))) {
				result = a;
				break;
			}
		}

		if (result != null) {
			myPageData.infolist.remove(result);
			myPageData.infoSave();
			System.out.println("회원탈퇴 완료");
		}

	}
	/**
	 * 진료내역 메서드
	 */
	public void medicalInfo() {

		medicalInfo result = null;
		
		User user = LoginSession.getSession();

		BbsOutput.subtiltle("진료 내역");

		for (medicalInfo b : myPageData.medicalInfoList) {
			if (b.getRegNo().equals(user.getRegNo())) {
				result = b;
				break;
			}
		
		}
		
		if (result != null) {
			
			System.out.printf("[회원이름] %s\n[주민번호] %s\n[날짜] %s\n[병원명] %s\n[예약시간] %s\n[의사이름] %s\n[진료과] %s\n"
					, result.getPatientName()
					, result.getRegNo()
					, result.getDate()
					, result.getHospital()
					, result.getTime()
					, result.getDoctorName()
					, result.getDepartment());
		}

		System.out.println();

		System.out.println();

	}

}
