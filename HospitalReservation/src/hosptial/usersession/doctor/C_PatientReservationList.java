package hosptial.usersession.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.DoctorUser;

/**
 * 
 * @author 변창현 예약한 환자의 목록을 의사의 시점에서 보여주는 클래스
 *
 */
public class C_PatientReservationList {

	DoctorUser user = (DoctorUser) LoginSession.getSession();

	/**
	 * 처방전 리스트를 받아와서 예약환자 목록을 이름과 총 대기인원수를 보여준다.
	 */
	public static ArrayList<Prescription> reservationlist = new ArrayList<Prescription>();

	public void patientReservationList() {

		// 예약한 환자 리스트 뽑아서 목록 보여주기

		// 0,유광재,951227-169638,1,이성원,바마마병원,우을증약,치의학과,A544
		// 로그인 : 이성원

		// 처방전 ArrayList<>
		// 0,유광재,951227-169638,1,이성원,바마마병원,-,치의학과,-
		// 처방전 ArrayList<> 안에 [6], [8]가 - 처리된것은 처방전 작성이 안된것.
		// 즉 예약한 환자 이걸 불러와야함.

		// 1. 환자 예약 목록 조회
		// - 현재 환자 목록은
		// - 홍길동
		// - 홍길순
		// - 현재 의사 OOO님의 대기 환자는 총 %d명 입니다.

		Scanner sc = new Scanner(System.in);
		ArrayList<Prescription> list = new ArrayList<Prescription>();

		patientLoad(list);

		System.out.println("==================================================");
		System.out.printf("\t\t%s님의 환자 목록\n", user.getName());
		System.out.println("==================================================");

		int count = 0;

		for (Prescription prescription : list) {
			if (user.getName().equals(prescription.getDoctorName())) {
				System.out.printf("이름 : %s\n", prescription.getPatientName());
				count++;
			}
		}
		System.out.println("--------------------------------------------------");
		System.out.printf(" 현재 의사 %s님의 대기 환자는 총 %d명 입니다.\n", user.getName(), count);
		System.out.println("--------------------------------------------------");
		System.out.println();

		// 메모리 배열 즉 저장
		patientSave(list);

	}

	public static ArrayList<Prescription> prescription = new ArrayList<Prescription>();

	public static void patientLoad(ArrayList<Prescription> list) {

		try {

			// 환자목록.txt 파일을 patientlist에 넣기
			BufferedReader patientReader = new BufferedReader(new FileReader(DataPath.처방전));

			String line = null;

			while ((line = patientReader.readLine()) != null) {

				String[] temp = line.split(",");

				Prescription prescription = new Prescription(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],
						temp[6], temp[7], temp[8]);

				list.add(prescription);

			} // while
			patientReader.close();

		} catch (Exception e) {
			System.out.println("FindPatientUserList.load");
			e.printStackTrace();
		}

	}// patientLoad

	public static void patientSave(ArrayList<Prescription> list) {

		try {

			// 환자목록을 메모리에 파일데이터로 저장
			// 뒤에 이어지게 하고싶으면 datapath뒤에 true 붙일것. 까먹지말것.
			BufferedWriter patientWriter = new BufferedWriter(new FileWriter(DataPath.처방전, true));

			for (Prescription prescription : prescription) {

				/*
				 * 0 유광재 951227-169638 1 이성원 바마마병원 우을증약 치의학과 A544
				 */

				String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", prescription.getPatientType(),
						prescription.getPatientName(), prescription.getRegNum(), prescription.getDoctorType(),
						prescription.getDoctorName(), prescription.getHospitalName(), prescription.getMedicine(),
						prescription.getDepartment(), prescription.getDiseaseCode());
			} // for

			patientWriter.close();

		} catch (Exception e) {
			System.out.println("FindPatientUserList.patientSave");
			e.printStackTrace();
		}

	}// patientSave
}
