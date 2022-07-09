package hosptial.searchReservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.User;
import hosptial.usersession.common.CommonUserSesstion;

/**
 * 병원을 검색하는 클래스입니다.
 * @author 박선미
 *
 */
public class Search {


	User user = LoginSession.getSession();
	
	/**
	 * 증상별 검색하는 메서드입니다.
	 * 검색원하는 증상을 입력받아 증상과 해당되는 진료과 목록을 조회합니다.
	 * 해당되는 진료과목을 가진 병원목록이 출력되고 예약원하는 병원을 입력받습니다.
	 * 입력받은 병원정보를 String배열에 저장합니다.
	 * 병원정보를 저장한 배열을 예약객체로 넘겨줍니다.
	 */

	public void symptomSearch() {


		try {

			BufferedReader reader = new BufferedReader(new FileReader(DataPath.keyword2));

			System.out.println("=====================================");
			System.out.println("\t증상별검색");
			System.out.println("※ 0번 입력시 이전메뉴로 이동합니다.");
			System.out.println("=====================================");
			System.out.println("[겪고 있는 불편한 증상을 입력하세요.]");
			System.out.print("입력: ");

			Scanner scan = new Scanner(System.in);

			String symptom = scan.nextLine();

			String line = null;

			String department = "";

			if ("0".equals(symptom)) {
				reader.close();

				CommonUserSesstion comSession = new CommonUserSesstion();
				comSession.main();

			} else {
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					// System.out.println(line + "-" + line.contains(symptom));
					if (line.contains(symptom)) {
						// System.out.println(line); //8,기침,내과
						System.out.println("[겪고 있는 증상과 연관된 진료과입니다.]");
						System.out.println(line.split(",")[2]); // 과 확정
						department = line.split(",")[2];
						System.out.println();
						break; // while
					}
			}
			reader.close();

			// 진료과 확정> 병원+ 담당의사 목록 출력
			System.out.println("[예약가능한 병원목록]");

			reader = new BufferedReader(new FileReader(DataPath.userDataDoctor));

			line = "";

			ArrayList<String[]> list = new ArrayList<String[]>(); // 의사의 정보를 넣은 배열을 담은 ArrayList

			while ((line = reader.readLine()) != null) {
				// 202,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과

				String[] temp = line.split(",");

				if (temp[6].equals(department)) {
					System.out.println(temp[1] + "[" + temp[6] + "]" + getDoctorName(temp[0]));
					list.add(temp); // ArrayList에 해당되는 의사의 정보를 저장
				}
			}


			String[] hosptialData = new String[8]; // 의사 정보를 저장할
													// 배열(202,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과)
			String DoctorName = "";
			boolean flag = true;
			while (flag) {
				System.out.println("[예약원하는 병원을 입력해주세요.]");
				System.out.print("입력:");
				String input = scan.nextLine();

				// 병원 + 담당의사 목록 출력 > 병원이름
				// 예약 r = new 예약
				// r.호출("병원이름");
				for (String[] s : list) {
					if (s[1].equals(input)) { // hosptialData의 병원명 == input, 김진의원 == 김진의원
						hosptialData = s;
						flag = false;
						// System.out.println("s[0]"+s[0]);
						DoctorName = getDoctorName(s[0]);
					}
				}
				if (hosptialData[1] == null) {
					System.out.println("병원이름을 잘못 입력하셨습니다. 다시 입력해주세요");
				}

			}
			Reservation reservation = new Reservation(hosptialData, DoctorName); // 의사 정보를 저장한
																					// 배열 넘겨줌
			reservation.reservationHosptial();
			// 예약 객체로 넘어가서 작동중

			reader.close();



			}

		} catch (Exception e) {
			System.out.println("Search.symptomSearch");
			e.printStackTrace();
		}
	}

	private String getDoctorName(String doctorNum) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(DataPath.userData));

			String line = "";

			while ((line = reader.readLine()) != null) {
				// 201,1,nxgz6x,iMqHYBy77q,이남찬,010-3153-4540,대전광역시 동구 우동,600103-117512

				String[] temp = line.split(",");

				if (temp[0].equals(doctorNum)) {
					return temp[4];
				}
			}

		} catch (Exception e) {
			System.out.println("Search.getDoctorName");
			e.printStackTrace();
		}
		return null;
	}// symptomSearch

	/**
	 * 신체부위별 검색하는 메서드입니다.
	 * 검색원하는 신체부위를 입력받아 해당 신체부위와 연관되는 증상 목록을 조회합니다.
	 * 증상을 입력받아 해당되는 진료과목을 가진 병원목록이 출력되고 예약원하는 병원을 입력받습니다.
	 * 입력받은 병원정보를 String배열에 저장합니다.
	 * 병원정보를 저장한 배열을 예약객체로 넘겨줍니다.
	 */
	public void partSearch() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DataPath.keyword1));

			System.out.println("=====================================");
			System.out.println("\t신체부위별검색");
			System.out.println("※ 0번 입력시 이전메뉴로 이동합니다.");
			System.out.println("=====================================");
			System.out.println("[검색할 신체부위를 선택해주세요.]");
			System.out.println("1. 머리");
			System.out.println("2. 목허리");
			System.out.println("3. 가슴");
			System.out.println("4. 위장관계");
			System.out.println("5. 비뇨생식기계");
			System.out.println("6. 혈액 및 호르몬");
			System.out.println("7. 눈,귀,코");
			System.out.println("8. 피부");
			System.out.print("입력: ");

			Scanner scan = new Scanner(System.in);

			String part = scan.nextLine();

			String line = null;

			String department = "";

			if ("0".equals(part)) {
				reader.close();

				CommonUserSesstion comSession = new CommonUserSesstion();
				comSession.main();

			} else {
				System.out.println("[검색한 신체부위와 관련된 증상입니다.]");
				int count = 1;
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					// 1,머리,두통,신경과
					// 1,머리,어지럽다,신경과
					if (line.contains(part)) {
						// System.out.println(line);
						System.out.println(count + ". " + line.split(",")[2]);
						// TODO 증상리스트가 떠야하는데..1개만뜸...,증상선택하게하기>스캐너삽입위치(-)
						department = line.split(",")[3];
						count++;
					}
				}
				reader.close();

			// 증상선택하도록 스캐너삽입
			System.out.println("[진료원하는 증상을 입력하세요]");
			System.out.print("입력: ");
			scan = new Scanner(System.in);
			String sx = scan.nextLine();



			// 진료과 확정> 병원+ 담당의사 목록 출력
			System.out.println("[예약가능한 병원목록]");

			reader = new BufferedReader(new FileReader(DataPath.userDataDoctor));

			line = "";

			ArrayList<String[]> list = new ArrayList<String[]>(); // 의사의 정보를 넣은 배열을 담은 ArrayList

			while ((line = reader.readLine()) != null) {
				// 202,1,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과

				String[] temp = line.split(",");

				if (temp[6].equals(department)) {
					System.out.println(temp[1] + "[" + temp[6] + "]" + getDoctorName(temp[0]));
					list.add(temp); // ArrayList에 해당되는 의사의 정보를 저장
				}
			}



			// 스캐너> 병원선택
			// 해당병원정보와 예약페이지 출력됨

			String[] hosptialData = new String[8]; // 의사 정보를 저장할
													// 배열(202,1,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과)
			String DoctorName = "";
			boolean flag = true;
			while (flag) {
				System.out.println("[예약원하는 병원을 입력해주세요.]");
				System.out.print("입력:");
				String input = scan.nextLine();

				// 병원 + 담당의사 목록 출력 > 병원이름
				for (String[] s : list) {
					if (s[1].equals(input)) { // hosptialData의 병원명 == input, 김진의원 == 김진의원
						hosptialData = s;
						flag = false;
						// System.out.println("s[0]"+s[0]);
						DoctorName = getDoctorName(s[0]);
					}
				}
				if (hosptialData[1] == null) {
					System.out.println("병원이름을 잘못 입력하셨습니다. 다시 입력해주세요");
				}

			}
			Reservation reservation = new Reservation(hosptialData, DoctorName); // 의사 정보를 저장한
																					// 배열 넘겨줌
			reservation.reservationHosptial();
			// 예약 객체로 넘어가서 작동중

			reader.close();



			}

		} catch (Exception e) {
			System.out.println("Search.partSearch");
			e.printStackTrace();
		}


	}// partSearch

	/**
	 * 진료과를 검색하는 메서드입니다.
	 * 예약원하는 진료과를 입력받아 해당되는 진료과목을 가진 병원목록이 출력되고 예약원하는 병원을 입력받습니다.
	 * 입력받은 병원정보를 String 배열에 저장합니다.
	 * 병원정보를 저장한 배열을 예약객체로 넘겨줍니다.
	 */
	public void medicalSearch() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DataPath.keyword1));

			System.out.println("=====================================");
			System.out.println("\t진료과별검색");
			System.out.println("※ 0번 입력시 이전메뉴로 이동합니다.");
			System.out.println("=====================================");
			System.out.println("1.내과");
			System.out.println("2.피부과");
			System.out.println("3.이비인후과");
			System.out.println("4.정형외과");
			System.out.println("5.안과");
			System.out.println("6.정신건강의학과");
			System.out.println("7.외과");
			System.out.println("8.신경외과");
			System.out.println("9.신경과");
			System.out.println("10.성형외과");
			System.out.println();
			System.out.println("[예약을 원하는 진료과를 입력하세요]");
			System.out.print("입력: ");

			Scanner scan = new Scanner(System.in);

			String medicalpart = scan.nextLine();

			String line = null;

			if ("0".equals(medicalpart)) {
				reader.close();

				CommonUserSesstion comSession = new CommonUserSesstion();
				comSession.main();

			} else {
				while ((line = reader.readLine()) != null) {
					if (line.contains(medicalpart)) {
						System.out.println(line.split(",")[3]);
						medicalpart = line.split(",")[3];
						System.out.println();
						break; // while
					}
				}

			}
			reader.close();


			// 진료과 확정> 병원+ 담당의사 목록 출력
			System.out.println("[예약가능한 병원목록]");

			reader = new BufferedReader(new FileReader(DataPath.userDataDoctor));

			line = "";

			ArrayList<String[]> list = new ArrayList<String[]>(); // 의사의 정보를 넣은 배열을 담은 ArrayList

			while ((line = reader.readLine()) != null) {
				// 202,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과

				String[] temp = line.split(",");


				if (temp[6].equals(medicalpart)) {
					System.out.println(temp[1] + "[" + temp[6] + "]" + getDoctorName(temp[0]));
					list.add(temp); // ArrayList에 해당되는 의사의 정보를 저장
				}
			} // while


			// 스캐너> 병원선택
			// 해당병원정보와 예약페이지 출력됨

			String[] hosptialData = new String[8]; // 의사 정보를 저장할
													// 배열(202,1,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과)
			String DoctorName = "";
			boolean flag = true;
			while (flag) {
				System.out.println("[예약원하는 병원을 입력해주세요.]");
				System.out.print("입력:");
				String input = scan.nextLine();

				// 병원 + 담당의사 목록 출력 > 병원이름
				for (String[] s : list) {
					if (s[1].equals(input)) { // hosptialData의 병원명 == input, 김진의원 == 김진의원
						hosptialData = s;
						flag = false;
						// System.out.println("s[0]"+s[0]);
						DoctorName = getDoctorName(s[0]);
					}
				}
				if (hosptialData[1] == null) {
					System.out.println("병원이름을 잘못 입력하셨습니다. 다시 입력해주세요");
				}

			}
			Reservation reservation = new Reservation(hosptialData, DoctorName); // 의사 정보를 저장한
																					// 배열 넘겨줌
			reservation.reservationHosptial();
			// 예약 객체로 넘어가서 작동중

			reader.close();



		} catch (Exception e) {
			System.out.println("Search.partSearch");
			e.printStackTrace();
		}


	}// medicalSearch


}// Search
