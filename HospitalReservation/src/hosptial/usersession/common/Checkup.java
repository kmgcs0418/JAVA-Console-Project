package hosptial.usersession.common;
/**
 * 건강검진을 예약하기 위한 모든 절차가 담긴 클래스입니다.
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.User;


public class Checkup {

	User user = LoginSession.getSession();

	public ArrayList<HosptialCheckUpList> hlist = new ArrayList<HosptialCheckUpList>();
	// 환자이름,공통검사항목,성연령별검사항목,암검사항목
	public String commonUserCheckList;
	/**
	 * 건강검진을 예약절차선택 클래스입니다.
	 * @author 이지영
	 * 
	 */
	public void checkup() {

		// 데이터 로드
		load();
		Scanner sc = new Scanner(System.in);

		boolean loop = true;
		while (loop) {
			System.out.println("=====================================");
			System.out.println("\t건강검진예약");
			System.out.println("=====================================");
			System.out.println("1. 건강검진 해당 항목 조회");
			System.out.println("2. 건강검진 예약하기");
			System.out.println("0. 뒤로가기");
			System.out.print("번호: ");
			int input = sc.nextInt();

			if (input == 1) {
				test();
			} else if (input == 2) {
				test1();
				System.out.println("=====================================");
				System.out.println("\t검사가능한병원목록");
				System.out.println("=====================================");
				// 환자이름,s1,s2,s3
				String[] temp = commonUserCheckList.split(",");
				int cnt = 0;
				ArrayList<HosptialCheckUpList> tempList = new ArrayList<HosptialCheckUpList>();
				for (HosptialCheckUpList s : hlist) {
					if (s.getGenderAge().equals(temp[2]) && s.getCancer().equals(temp[2])) {
						System.out.printf("%d. %s\n", ++cnt, s.getHosptialName());
						tempList.add(s);
					}
				}
				System.out.println("예약할 병원을 입력해주세요.");
				System.out.print("번호: ");
				int in = sc.nextInt();
			//	System.out.println("================================================");

				HosptialCheckUpList pickHosptial = tempList.get(in - 1);

				// 달력 출력..
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DATE);
				c.set(year, month, day);
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				System.out.println("====================================================");
				System.out.printf("오늘 날짜는 %tF %s요일입니다.\n", c, getDAYOFWEEK(dayOfWeek));
				printCalendar(year, month + 1);

				Scanner scan = new Scanner(System.in);
				boolean flag = true;
				while (flag) {
					Calendar date = Calendar.getInstance();
					System.out.println("====================================================");
					System.out.print("희망하는 평일 날짜를 입력해주세요\n");
					System.out.print("날짜 선택: ");
					String 진료날짜 = scan.nextLine();
					date.set(year, month, Integer.parseInt(진료날짜));
					int temp1 = date.get(Calendar.DAY_OF_WEEK);
					if (temp1 > 1 && temp1 < 7 && Integer.parseInt(진료날짜) > day) {
						flag = false;
						day = Integer.parseInt(진료날짜);
						c.set(year, month, day);
						dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
					} else {
						//System.out.println("=====================================");
						System.out.println("잘못된 입력입니다.");
						System.out.println("주말과 당일예약, 이전날짜은 예약이 불가능합니다.");
						System.out.println("=====================================");
					}
				}
				// 진료표
				System.out.println("=========================================");
				System.out.println("\t건강검진 진료예약 가능시간 ");
				System.out.println("=========================================");
				System.out.printf("예약날짜:%tF %s요일\n", c, getDAYOFWEEK(dayOfWeek));
	
				boolean flag1 = printTimeList(c, pickHosptial);
				loop = flag1;
				
			} else if (input == 0) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		} // while
	}// checkup
	/**
	 * 건강검진의 진료예약시간을 선택하는 메서드입니다.
	 * 건강검진의 진료예약내역을 출력하기 위함이다
	 * @author 박채은
	 * 
	 */
	private boolean printTimeList(Calendar c, HosptialCheckUpList pick) {
		// HosptialCheckUpList
		// private String hosptialName;
		// private String hosptialTime;
		// private String common;
		// private String genderAge;
		// private String cancer;

		Scanner in = new Scanner(System.in);
		String fristHour = pick.getHosptialTime().split(":")[0];
		String lastHour = pick.getHosptialTime().split("~")[1];
		lastHour = lastHour.split(":")[0];
		int breakHour = 13;

		System.out.println("=========================================");
		for (int i = Integer.parseInt(fristHour); i < Integer.parseInt(lastHour); i++) {
			if (i == breakHour) {
				continue;
			}
			System.out.printf("%d:00\n", i);
		}
		System.out.println("=========================================");
		System.out.print("희망하는 예약시간을 입력해주세요(예시:10)\n");
		System.out.print("시간 선택: ");
	
		boolean flag = true;
		while (flag) {
			int 진료시간 = in.nextInt();
			System.out.println("=========================================");
			if (진료시간 >= Integer.parseInt(fristHour) && 진료시간 != breakHour
					&& 진료시간 <= Integer.parseInt(lastHour)) {
				System.out.printf("%s님\n -일자:%tF\n -%s\n -시간:%s:00\n건강검진예약이 완료되었습니다.\n", user.getName(), c,
						pick.getHosptialName(), 진료시간);
				System.out.println("=========================================");
				String time = 진료시간 + ":00";

				flag = false;



				ArrayList<Reservationdetail> list = new ArrayList<Reservationdetail>();
				String cal = String.format("%tF", c);

				Reservationdetail d = new Reservationdetail(user.getName(), cal, pick.getHosptialName(), time);
				// 조기공 ,2022-04-27 바가바병원 14:00
				// 파일 읽기 쓰기 구현 장소
				try {
					BufferedReader reader = new BufferedReader(new FileReader(DataPath.건강검진내역));

					String line = null;

					while ((line = reader.readLine()) != null) {

						String[] temp1 = line.split(",");

						Reservationdetail d2 =
								new Reservationdetail(temp1[0], temp1[1], temp1[2], temp1[3]);

						list.add(d2);

					} // while
					list.add(d);
					reader.close();
					
					// C:user//class//java//data.txt > 본인
					// D:user//class//java//data.txt

					
					// new FileWriter(DataPath.건강검진내역), (false) > 덮어쓰기
					// new FileWriter(DataPath.건강검진내역), true > 이어쓰기
					BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.건강검진내역)); // true
					String line2 = null;

					for (Reservationdetail d3 : list) {
						
						line2 = String.format("%s,%s,%s,%s\n"
								, d3.getPersonName()
								, d3.getDate()
								, d3.getHospitalcheckupName()
								, d3.getHour());	

						

						writer.write(line2);
					}
					writer.close();
					
					return false;
				} catch (Exception e) {
					System.out.println("Checkup.printTimeList");
					e.printStackTrace();
				}

			} else {
				System.out.println("잘못된 시간을 입력하셨습니다. 다시 입력해주세요");
			
			}
		}
		
		return true;
		
	} // printTimeList

	/**
	 * 병원목록의 파일을 읽는 메서드입니다.
	 * @author 이지영
	 * 
	 */
	private void load() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DataPath.병원목록));

			String line = "";

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");
				HosptialCheckUpList h =
						new HosptialCheckUpList(temp[0], temp[1], temp[2], temp[3], temp[4]);
				hlist.add(h);

			}
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 회원이 자신의 나이에 해당하는 건강검진해당항목을 조회하는 메서드입니다.
	 * @author 이지영
	 * 
	 */

	private void test() {

		System.out.println("======================================================================");
		System.out.println("\t건강검진 해당 항목 조회창");
		System.out.println("======================================================================");

		//String file1 = "C:\\class\\java\\HospitalReservation\\data\\전체회원목록.txt";
		// String file1 = "C:\\class\\java\\HospitalReservation\\data\\환자목록.txt";

		Scanner sc;


		try {

			String value = user.getId();

			sc = new Scanner(new FileInputStream(DataPath.userData));
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				
				if(str.length() < 3) continue;
				
				String[] arr = str.split(",");
				
				if (arr[2].equals(value)) {


					System.out.println(arr[4] + "님 2022년 해당하는 건강검진 검사항목 입니다.");

					BufferedReader reader = new BufferedReader(new FileReader(DataPath.공통검사));
					String line = "";
					while ((line = reader.readLine()) != null) {
						System.out.println(line);


					}

					// 환자이름, O, O, -
					String s1 = "O", s2 = "-", s3 = "-";

					String jumin = arr[7];
					int age = Integer.parseInt(jumin.substring(0, 2));
					age = 122 - age + 1;


					char c = jumin.charAt(7);
					System.out.print("[성,연령별 검사 항목]\n");
                
					if ((age >= 40 && age % 4 == 0 && c == '2')
							|| (age >= 24 && age % 4 == 0 && c == '1')) {
						System.out.println("이상지질혈증(총콜레스테롤, HDL콜레스테롤, LDL콜레스테롤, 트리글리세라이드)");
						s2 = "O";
					}

					if (age == 40) {
						System.out.println("B형간염검사");
						System.out.println("치면세균막검사");
						s2 = "O";
					}
					if (age == 54 && c == '2' || age == 66 && c == '2') {// 여성
						System.out.println("골다공증");
						s2 = "O";
					}
					if (age == 40 || age == 50 || age == 60 || age == 70) {
						System.out.println("생활습관평가");
						s2 = "O";
					}
					if (age == 66 || age == 70 || age == 80) {
						System.out.println("노인신체기능검사");
						s2 = "O";
					}
					if (age >= 66 && age % 2 == 0) {
						System.out.println("인지기능장애검사");
						s2 = "O";
					}
					if (age >= 20 && age % 10 == 0) {
						System.out.println("정신건강검사_우울증");
						s2 = "O";

					} else {
						System.out.println("더이상 해당하는 성,연령별 건강검진항목이없습니다");
						System.out.println();
					}
					System.out.print("[암검진]\n");
					if (age >= 40 && age % 2 == 0) {
						System.out.println("위암검진");
						s3 = "O";
					}

					if (age >= 50 && age % 1 == 0) {
						System.out.println("대장암검진");
						s3 = "O";
					}
					if (age == 54 && c == '2' || age == 66 && c == '2') {// 여성
						System.out.println("간암검진");// 다시
						s3 = "O";
					}
					if (age >= 40 && age % 2 == 0 && c == '2') {
						System.out.println("유방암검진");
						s3 = "O";
					}
					if (age >= 20 && age % 2 == 0 && c == '2') {
						System.out.println("자궁겸부암검진");
						s3 = "O";
					}
					if (age >= 54 && age <= 74 && age % 2 == 0) {
						System.out.println("폐암검진");
						s3 = "O";
					} else {
						System.out.println("더이상 해당하는 암건강검진항목이없습니다");
						System.out.println();
					}
                   
					// { [환자이름, s1, s2, s3], [환자이름, s1, s2, s3]}
					// 환자이름,s1,s2,s3
					commonUserCheckList = String.format("%s,%s,%s,%s", user.getName(), s1, s2, s3);

				}
			}
			System.out.println("이전 페이지로 돌아갑니다.");
			Thread.sleep(3000);
			System.out.println("======================================================================");
			sc.close();// 스캔추가시킴

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 회원이 자신의 나이에 해당하는 건강검진해당항목에 맞는 병원만 조회할수있는 메서드입니다.
	 * @author 박채은
	 * 
	 */

	private void test1() {

		//String file1 = "C:\\class\\java\\HospitalReservation\\data\\전체회원목록.txt";
		// String file1 = "C:\\class\\java\\HospitalReservation\\data\\환자목록.txt";

		Scanner sc;


		try {

			String value = user.getId();

			sc = new Scanner(new FileInputStream(DataPath.userData));
			while (sc.hasNextLine()) {
				String str = sc.nextLine();

				if(str.length() < 3) continue;
				
				String[] arr = str.split(",");

				if (arr[2].equals(value)) {

					// 환자이름, O, O, -
					String s1 = "O", s2 = "-", s3 = "-";

					String jumin = arr[7];
					int age = Integer.parseInt(jumin.substring(0, 2));
					age = 122 - age + 1;


					char c = jumin.charAt(7);

					if ((age >= 40 && age % 4 == 0 && c == '2')
							|| (age >= 24 && age % 4 == 0 && c == '1')) {
						s2 = "O";
					}

					if (age == 40) {
						s2 = "O";
					}
					if (age == 54 && c == '2' || age == 66 && c == '2') {// 여성
						s2 = "O";
					}
					if (age == 40 || age == 50 || age == 60 || age == 70) {
						s2 = "O";
					}
					if (age == 66 || age == 70 || age == 80) {
						s2 = "O";
					}
					if (age >= 66 && age % 2 == 0) {
						s2 = "O";
					}
					if (age >= 20 && age % 10 == 0) {
						s2 = "O";

					} else {
					}
					if (age >= 40 && age % 2 == 0) {
						s3 = "O";
					}

					if (age >= 50 && age % 1 == 0) {
						s3 = "O";
					}
					if (age == 54 && c == '2' || age == 66 && c == '2') {// 여성
						s3 = "O";
					}
					if (age >= 40 && age % 2 == 0 && c == '2') {
						s3 = "O";
					}
					if (age >= 20 && age % 2 == 0 && c == '2') {
						s3 = "O";
					}
					if (age >= 54 && age <= 74 && age % 2 == 0) {
						s3 = "O";
					} else {
					}
					// { [환자이름, s1, s2, s3], [환자이름, s1, s2, s3]}
					// 환자이름,s1,s2,s3
					commonUserCheckList =
							String.format("%s,%s,%s,%s\n", user.getName(), s1, s2, s3);

				}
			}
			sc.close();// 스캔추가시킴

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 건강검진예약일자를 선택하기 위한 메서드입니다.
	 * 달력날짜의 요일을 출력하기 위함이다
	 * @author 박채은
	 * 
	 */

	private String getDAYOFWEEK(int dayofweek) {

		if (dayofweek == 1)
			return "일";
		else if (dayofweek == 2)
			return "월";
		else if (dayofweek == 3)
			return "화";
		else if (dayofweek == 4)
			return "수";
		else if (dayofweek == 5)
			return "목";
		else if (dayofweek == 6)
			return "금";
		else
			return "토";
	}
	/**
	 * 건강검진예약일자를 선택하기 위한 메서드입니다.
	 * 
	 */
	private void printCalendar(int year, int month) {

		int lastDay = getLastDay(year, month);
		int dayOfWeek = getWeek(year, month);

		System.out.println("===================================================="); // 55
		System.out.printf("\t\t       %d년 %d월\n", year, month);
		System.out.println("====================================================");
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t");

		for (int i = 1; i <= dayOfWeek; i++) {
			System.out.print("\t");
		}

		for (int i = 1; i <= lastDay; i++) {
			System.out.printf("%3d\t", i);
			if (i % 7 == 7 - dayOfWeek) {
				System.out.println();
			}
		}

	}
	/**
	 * 건강검진예약일자를 선택하기 위한 메서드입니다.
	 * 한달이 몇일인지 출력하기 위함이다
	 * 
	 */
	private static int getLastDay(int year, int month) {

		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return isLeafYear(year) == true ? 29 : 28;

		}
		return 0;
	}
	/**
	 * 건강검진예약일자를 선택하기 위한 메서드입니다.
	 * 
	 */
	private static boolean isLeafYear(int year) {

		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? true : false;

	}
	/**
	 * 건강검진예약일자를 선택하기 위한 메서드입니다.
	 * 윤년계산
	 * 
	 */
	private static int getWeek(int year, int month) {

		int total = 0;
		int date = 1;

		for (int i = 1; i < year; i++) {
			if (isLeafYear(i)) {
				total += 366;
			} else {
				total += 365;
			}
		}

		for (int i = 1; i < month; i++) {
			total += getLastDay(year, i);
		}
		total += date;

		return total % 7;
	}


} // Checkup
