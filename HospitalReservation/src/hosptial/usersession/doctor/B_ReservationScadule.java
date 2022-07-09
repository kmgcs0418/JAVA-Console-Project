package hosptial.usersession.doctor;

import java.util.Scanner;

/**
 * 
 * @author 변창현 예약 스케줄 관리 클래스
 *
 */

public class B_ReservationScadule {

	/**
	 * 예약 스케줄 관리로 넘어오면 메뉴창을 띄워준다. 그리고 환자예약목록 조회와 의사 스케줄 관리표를 띄워준다.
	 */

	public void reservationScadule() {

		Scanner sc = new Scanner(System.in);
		C_PatientReservationList patientReservationList = new C_PatientReservationList();
		D_DoctorScadule doctorScadule = new D_DoctorScadule();

		while (true) {
			System.out.println("========================================");
			System.out.print("\t예약 스케줄 관리 메뉴\n");
			System.out.println("========================================");
			System.out.println("1. 환자 예약 목록 조회");
			System.out.println("2. 의사 스케쥴 관리");
			System.out.println("0. 뒤로가기");
			System.out.println("========================================");
			System.out.print("번호 : ");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("뒤로가기");
				return;

			} else if (input == 1) {
				patientReservationList.patientReservationList();

			} else {
				doctorScadule.doctorScadule();
			}
		}

	}// patientList

}// class reservationScadule
