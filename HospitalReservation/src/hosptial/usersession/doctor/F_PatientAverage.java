package hosptial.usersession.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.DoctorUser;

/**
 * 
 * @author 변창현 진료한 환자의 통계를 보여주는 클래스
 */
public class F_PatientAverage {
	/**
	 * 오늘 총 진료한 환자의 데이터값을 처방전으로 받아와서 처방전 작성이 끝난 목록을 도출해준다.
	 */
	public void patientAverage() {

		DoctorUser user = (DoctorUser) LoginSession.getSession();
		ArrayList<Prescription> list = new ArrayList<Prescription>();

		patientLoad(list);

		// 오늘 진료한 환자 통계 보기
		System.out.println("====================");
		System.out.println("     진료환자 통계");
		System.out.println("====================");

		int count = 0;

		for (Prescription prescription : list) {
			if (user.getName().equals(prescription.getDoctorName())) {
				System.out.printf("이름 : %s\n", prescription.getPatientName());
				count++;
			}

		}
		System.out.println("---------------------------------------");
		System.out.printf(" 오늘 의사 %s님은 총 %d명의 환자를 진료하셨습니다.\n", user.getName(), count);
		System.out.println("---------------------------------------");

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
