package hosptial.usersession.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.DoctorUser;
import hosptial.domain.User;

/**
 * 
 * @author 변창현 예약환자를 바탕으로 처방전을 작성하는 클래스
 *
 */
public class E_Write_Prescription {

	/**
	 * 처방전 데이터를 배열로 받아오는 메소드 생성
	 */
	public static ArrayList<Prescription> prescription = new ArrayList<Prescription>();
	DoctorUser user = (DoctorUser) LoginSession.getSession();

	/**
	 * 처방전 데이터를 로드하는 메소드
	 */
	public void prescription() {

		patientLoad(prescription);
		// 처방전 작성하기
		// 양식
		// 0,이경석,240411-226413,1,박형동,차사가병원,감기약,소아청소년과,B1026
		// 약 이름 데이터 만드신분 감기약이 김기약이라고 되어있음 확인

		Scanner scan = new Scanner(System.in);

		// 로그인한 의사정보 가져오기
		DoctorUser doctorUser = (DoctorUser) LoginSession.getSession();

		System.out.println("========================================");
		System.out.println("\t    처방전 작성");
		System.out.println("========================================");

		System.out.println("처방전 작성 가능한 환자 목록");
		ArrayList<Prescription> plist = new ArrayList<Prescription>();
		int count = 0;
		for (Prescription p : prescription) {
			if (p.getDoctorName().equals(user.getName()) && p.getMedicine().equals("-")) {
				System.out.printf("%d. %s\n", ++count, p.getPatientName());
				plist.add(p);
			}
		}
		System.out.println("========================================");
		System.out.print("처방전 작성 환자 선택(예시 1): ");
		String in = scan.nextLine();
		if (!in.equals("0")) {
			Prescription pick = plist.get(Integer.parseInt(in) - 1);
			System.out.printf("선택환자 : %s, %s\n", pick.getPatientName(), pick.getRegNum());
			// 처방약과 질병코드를 입력
			System.out.println("처방약과 질병코드를 입력하세요");

			// 의사가 작성할수있게 만들기
			System.out.print("처방약 : ");
			String medicine = scan.nextLine();
			pick.setMedicine(medicine);

			// 의사가 작성할수있게 만들기
			System.out.print("질병코드 : ");
			String code = scan.nextLine();
			pick.setDiseaseCode(code);
			System.out.println("========================================");
			System.out.println("\t    작성된 처방전");
			System.out.println("========================================");
			System.out.printf("처방전 환자명: %s\n주민번호: %s\n처방약:%s\n질병코드: %s\n", pick.getPatientName(), pick.getRegNum(),
					pick.getMedicine(), pick.getDiseaseCode());

			/*
			 * 
			 * 0 patientType 유광재 patientName 951227-169638 regNum 1 doctorType 이성원 String
			 * 바마마병원 hospitalName 우을증약 medicine 치의학과 department A544 diseaseCode
			 * 
			 */

			for (Prescription pre : prescription) {
				if (pre.getPatientName().equals(pick.getPatientName())
						&& pre.getDoctorName().equals(pick.getDoctorName())) {
					pre = pick;
				}
			}

			patientSave(prescription);
			System.out.println("========================================");
			System.out.println("처방전 작성 완료");
			System.out.println("========================================");

		} else {
			System.out.println("이전페이지로 돌아갑니다");
		}

	}// main

	public static void patientLoad(ArrayList<Prescription> list) {

		try {

			// 환자목록.txt 파일을 patientlist에 넣기
			BufferedReader patientReader = new BufferedReader(new FileReader(DataPath.처방전));

			String line = null;

			while ((line = patientReader.readLine()) != null) {

				// System.out.println(line);

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
			// 덮어쓰기로 변경 true 삭제
			BufferedWriter patientWriter = new BufferedWriter(new FileWriter(DataPath.처방전));

			for (Prescription prescription : prescription) {
				/*
				 * 0 유광재 951227-169638 1 이성원 바마마병원 우을증약 치의학과 A544
				 */

				String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", prescription.getPatientType(),
						prescription.getPatientName(), prescription.getRegNum(), prescription.getDoctorType(),
						prescription.getDoctorName(), prescription.getHospitalName(), prescription.getMedicine(),
						prescription.getDepartment(), prescription.getDiseaseCode());

				patientWriter.write(line);

			} // for

			patientWriter.close();

		} catch (Exception e) {
			System.out.println("FindPatientUserList.patientSave");
			e.printStackTrace();
		}

	}// patientSave

}// class
