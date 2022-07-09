package hosptial.usersession.doctor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import hosptial.DataPath;
import hosptial.domain.DoctorUser;

// 파일 데이터 <-> 메모리(배열)
/**
 * 
 * @author 의사회원의 목록을 배열로 구성해서 앞에서 찾을수있게 만들어주는 클래스
 *
 */
public class FindDoctorUserList {

	// 프로그램 전체에서 사용될 데이터는 static으로 선언하기.

	public static ArrayList<DoctorUser> doctorlist = new ArrayList<DoctorUser>();

	/**
	 * 
	 * @param list 의사 회원의 리스트를 가져올수있게 텍스트파일을 버퍼드리더로 가져오는 메소드
	 */
	public static void doctorLoad(ArrayList<DoctorUser> list) {

		try {


			// 의사회원.txt 파일을 doctorlist에 넣기
			BufferedReader doctorReader = new BufferedReader(new FileReader(DataPath.의사회원));

			String line = null;

			while ((line = doctorReader.readLine()) != null) {
				// 번호,회원유형,아이디,비밀번호,이름,전화번호,병원주소,주민번호
				// 201,1,nxgz6x,iMqHYBy77q,이남찬,010-3153-4540,대전광역시 동구 우동,600103-117512

				String[] temp = line.split(",");

				DoctorUser doctor = new DoctorUser(Long.parseLong(temp[0]), Long.parseLong(temp[1]),
						temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
				
				doctorlist.add(doctor);


			}

			doctorReader.close();

		} catch (Exception e) {
			System.out.println("Data.load");
			e.printStackTrace();
		}

	}// load

	/**
	 * 
	 * @param list
	 * 의사 회원의 리스트를 가져온텍스트파일을 버퍼드라이터로 저장하는 메소드
	 */
	public static void doctorSave(ArrayList<DoctorUser> list) {
		try {


			// 의사회원을 메모리에 파일데이터로 넣기

			// doctorlist에 의사회원.txt넣기
			BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.의사회원));

			for (DoctorUser doctor : doctorlist) {
				// 번호,회원유형,아이디,비밀번호,이름,전화번호,병원주소,주민번호
				// 201,1,nxgz6x,iMqHYBy77q,이남찬,010-3153-4540,대전광역시 동구 우동,600103-117512

				String line = String.format("%d,%d,%s,%s,%s,%s,%s,%s"
						, doctor.getSequence()
						, doctor.getUserTypeCheck()
						, doctor.getId()
						, doctor.getPassword()
						, doctor.getName()
						, doctor.getPhoneNum()
						, doctor.getAddress()
						, doctor.getRegNo());
				
			}

			writer.close();

		} catch (Exception e) {
			System.out.println("Data.save");
			e.printStackTrace();
		}



	} // save

}// class
