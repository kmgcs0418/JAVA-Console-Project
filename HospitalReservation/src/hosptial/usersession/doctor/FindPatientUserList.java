package hosptial.usersession.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import hosptial.DataPath;

/**
 * 
 * @author 환자회원의 목록을 배열로 구성해서 앞에서 찾을수있게 만들어주는 클래스
 *
 */

public class FindPatientUserList {

	public static ArrayList<Prescription> 처방전 = new ArrayList<Prescription>();

	/**
	 * 
	 * @param list 환자 회원의 리스트를 가져올수있게 텍스트파일을 버퍼드리더로 가져오는 메소드
	 */
	public static void patientLoad(ArrayList<Prescription> list) {

		try {

			// 환자목록.txt 파일을 patientlist에 넣기
			BufferedReader patientReader = new BufferedReader(new FileReader(DataPath.처방전));

			String line = null;

			while ((line = patientReader.readLine()) != null) {

				String[] temp = line.split(",");

				Prescription prescription = new Prescription(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],
						temp[6], temp[7], temp[8]);

				처방전.add(prescription);

			} // while
			patientReader.close();

		} catch (Exception e) {
			System.out.println("FindPatientUserList.load");
			e.printStackTrace();
		}

	}// patientLoad

	/**
	 * 
	 * @param list 환자 회원의 리스트를 가져온텍스트파일을 버퍼드라이터로 저장하는 메소드
	 */

	public static void patientSave(ArrayList<Prescription> list) {

		try {

			// 환자목록을 메모리에 파일데이터로 저장
			BufferedWriter patientWriter = new BufferedWriter(new FileWriter(DataPath.처방전));

			for (Prescription prescription : 처방전) {
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
