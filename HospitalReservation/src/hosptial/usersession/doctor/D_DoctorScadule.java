package hosptial.usersession.doctor;

import java.util.Scanner;
import hosptial.LoginSession;
import hosptial.domain.DoctorUser;

/**
 * 
 * @author 변창현 의사 스케줄 관리를 보여주는 클래스
 */

public class D_DoctorScadule {
	/**
	 * 의사의 본인 스케줄 관리표를 보여주는 메소드이다.
	 */
	public void doctorScadule() {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("==================================================");
			System.out.printf("\t\t의사 스케쥴 관리\n");
			System.out.println("==================================================");
			System.out.println("1. 의사 휴식시간");
			System.out.println("2. 의사 휴진요일");
			System.out.println("3. 진료 마감시간");
			System.out.println("0. 뒤로가기");
			System.out.println("==================================================");
			System.out.print("번호 : ");
			int input = sc.nextInt();

			// 로그인할때 입력받은 의사이름 받아와야함
			// 의사정보 가져오기
			DoctorUser doctorUser = (DoctorUser) LoginSession.getSession();

			if (input == 0) {
				System.out.println("뒤로가기");
				return;

			} else if (input == 1) {
				// 의사 휴식시간
				// 점심시간 포함 2시간
				System.out.println("--------------------------------------------------");
				System.out.printf("  %s님의 휴식시간은 12:00 ~ 14:00까지 입니다.\n", doctorUser.getName());
				System.out.println("--------------------------------------------------");

//				FindDoctorUserList.doctorlist.stream().forEach(u -> {
//					System.out.printf("%s", u.getName());
//				});

			} else if (input == 2) {
				// 의사 휴진요일
				// 매주 수요일
				System.out.println("--------------------------------------------------");
				System.out.printf("    %s님의 휴진일은 매주 수요일 입니다.\n", doctorUser.getName());
				System.out.println("--------------------------------------------------");

			} else {
				// 진료 마감시간
				// 오후 6시 (18시)
				System.out.println("--------------------------------------------------");
				System.out.printf("    %s님의 진료 마감시간은 18:00 입니다.\n", doctorUser.getName());
				System.out.println("--------------------------------------------------");
			}

		} // while
	}// main
}// class
