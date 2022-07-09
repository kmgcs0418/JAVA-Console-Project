package hosptial.searchReservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.User;

/**
 * 진료예약하는 클래스입니다.
 * @author 박채은
 *
 */
public class Reservation {
	User user = LoginSession.getSession();
	private String[] hosptialData;
	private String hosptialDoctorName;
	
	public Reservation(String[] hosptialData,String hosptiaDoctorlName) {
		this.hosptialData = hosptialData;
		this.hosptialDoctorName = hosptiaDoctorlName;
	}
	
	/**
	 * 진료날짜,시간을 선택할수 있는 메서드입니다.
	 * 원하는 진료날짜,시간을 입력받아 예약을 저장합니다.
	 * 진료내역, 처방전 읽기쓰기를 할 수 있습니다.
	 */
	public void reservationHosptial() {

		System.out.println("=====================================");
		System.out.println("\t예약페이지");
		System.out.println("=====================================");
		
			    
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		c.set(year, month, day);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		System.out.printf("오늘 날짜는 %tF %s요일입니다.\n", c, getDAYOFWEEK(dayOfWeek));
		printCalendar(year, month+1);

		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			Calendar date = Calendar.getInstance();
			System.out.print("진료 날짜:(평일 날짜만 입력해주세요)");
			int 진료날짜 = Integer.parseInt(scan.nextLine());
			date.set(year, month,진료날짜);
			int temp = date.get(Calendar.DAY_OF_WEEK);
			if(temp > 1 && temp < 7 && 진료날짜 > day) {
				flag = false;
				day = 진료날짜;
				c.set(year, month, day);
				dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			} else {
				System.out.println("=====================================");
				System.out.println("잘못된 입력입니다.");
				System.out.println("주말과 당일예약, 이전날짜은 예약이 불가능합니다.");
				System.out.println("=====================================");
			}
		}
		
		System.out.println("=====================================");
		System.out.println("\t진료예약 가능시간 ");
		System.out.println("=====================================");
		System.out.printf("예약날짜:%tF %s요일\n", c, getDAYOFWEEK(dayOfWeek));
		printTimeList(c);

	}

	private void printTimeList(Calendar c) {
		// hosptialData
		//202,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과
		Scanner in = new Scanner(System.in);
		String fristHour = hosptialData[2].split(":")[0];
		String lastHour = hosptialData[2].split("~")[1];
		lastHour = lastHour.split(":")[0];
		int breakHour = 13;

		System.out.println("=====================================");
		for(int i=Integer.parseInt(fristHour); i<Integer.parseInt(lastHour); i++) {
			if(i == breakHour) {
				continue;
			}
			System.out.printf("%d:00\n", i);
		}
		System.out.println("=====================================");
		System.out.println("희망하는 예약시간을 입력해주세요(예시 : 10)");
		System.out.print("시간 선택: ");
		boolean flag = true;
		while(flag) {			
			int 진료시간 = in.nextInt();
			
			if(진료시간 >= Integer.parseInt(fristHour) && 진료시간 != breakHour && 진료시간 <= Integer.parseInt(lastHour)) {
				System.out.println();
				System.out.println("===================================================================");
				System.out.printf("%s님 %tF %s에 %s:00시에 진료예약이 완료되었습니다.\n", user.getName(), c, hosptialData[1], 진료시간);
				System.out.println("===================================================================");
				System.out.println();
				
				try {
					Thread.sleep(2000);
					
				} catch (Exception e) {
					System.out.println("Reservation.printTimeList Thread Exception"); 
					e.printStackTrace();
				}
				// 파일 저장에 넣을 리스트 작성해야함 
				// 처방전(이미 있는 파일) 
				// 0,고길지,370728-136349,1,민길원,마아사병원,-,소아청소년과,-
				
				
				// 202,김진의원,9:00~18:00,9:00~18:00,미운영,62661,신경과
				String time = 진료시간 + ":00";
				
				// System.out.println("메인화면으로 돌아갑니다.");
				// System.out.printf("%s %tF %s %s %s", user.getName(), c, hosptialData[1], time, hosptialDoctorName);
				// System.out.println(hosptialData[1]);
				flag = false;
				
				ArrayList<Prescription> list = new ArrayList<Prescription>(150);
				Prescription p = new Prescription("0", user.getName(), user.getRegNo(), "1", hosptialDoctorName, hosptialData[1], "-", hosptialData[6], "-");		
				
				
				// 파일 읽기 쓰기 구현 장소
				try {

					BufferedReader reader = new BufferedReader(new FileReader(DataPath.처방전));
					
					String line = null;
					
					while ((line = reader.readLine()) != null) {

						String[] temp = line.split(",");
						Prescription p2 = new Prescription(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
						list.add(p2);
						
					}//while
					list.add(p);
					reader.close();
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.처방전));
					//처방전 쓰기작업
					for(Prescription p3 : list) {
						// 처방전
						String line2 = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n"
											, p3.getPatientType()
											, p3.getPatientName()
											, p3.getRegNum()
											, p3.getDoctorType()
											, p3.getDoctorName()
											, p3.getHospitalName()
											, p3.getMedicine()
											, p3.getDepartment()
											, p3.getDiseaseCode());
						writer.write(line2);
					}
					writer.close();

				} catch (Exception e) {
					System.out.println("Data.load");
					e.printStackTrace();
				}
				
				
				// 진료내역 파일 읽기, 쓰기
				// 환자이름, 주민번호, 진료날짜, 진료병원, 진료시간, 의사명, 의사번호
				//고길지,370728-136349,2022-04-18,안기의원,10:00,황자웅,259
				// 환자 정보, user.getXXX
				// 의사 정보, hosptialData 배열

				
				ArrayList<MedicalData> mlist = new ArrayList<MedicalData>();
				String cal = String.format("%tF", c); // 캘린더 날짜 데이터 형변환 String
				MedicalData m = new MedicalData(user.getName(), user.getRegNo(), cal, hosptialData[1], time, hosptialDoctorName, hosptialData[6]);
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(DataPath.medicalInfo));
					
					String line = null;
					
					while ((line = reader.readLine()) != null) {
						String[] temp1 = line.split(",");
						MedicalData m2 = new MedicalData(temp1[0], temp1[1], temp1[2], temp1[3], temp1[4], temp1[5], temp1[6]);
						mlist.add(m2);
					}
					mlist.add(m);
					reader.close();
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.medicalInfo, true));
					
					String line3 = null;
					
					for(MedicalData m3: mlist) {
						line3 = String.format("%s,%s,%s,%s,%s,%s,%s\n"
														,user.getName()
														,user.getRegNo()
														,cal
														,hosptialData[1]
														,time
														,hosptialDoctorName
														,hosptialData[6]);
						
					}
					writer.write(line3);
					
					writer.close();
					
				} catch (Exception e) {
					System.out.println("Reservation.printTimeList");
					e.printStackTrace();
				}
				
				
				
				
				
				
			} else {
				System.out.println("잘못된 시간을 입력하셨습니다. 다시 입력해주세요");
			}
		}
		
		
	} //

	private String getDAYOFWEEK(int dayofweek) {
	
		if(dayofweek == 1) return "일";
		else if(dayofweek == 2) return "월";
		else if(dayofweek == 3) return "화";
		else if(dayofweek == 4) return "수";
		else if(dayofweek == 5) return "목";
		else if(dayofweek == 6) return "금";
		else return "토";
	}	
	
	private void printCalendar(int year, int month) {
      
		int lastDay = getLastDay(year, month);
		int dayOfWeek = getWeek(year, month);
		
		System.out.println("======================================================="); // 55
		System.out.printf("\t\t       %d년 %d월\n", year, month);
		System.out.println("=======================================================");
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\t");
		
		for(int i=1; i<=dayOfWeek; i++) {
			System.out.print("\t");
		}

		for(int i=1; i<=lastDay; i++) {
			System.out.printf("%3d\t", i);
			if(i % 7 == 7-dayOfWeek) { 
				System.out.println();
			}
		}

	}

	
	private int getLastDay(int year, int month) {
		
		switch(month){
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11:
				return 30;
			case 2:
				return isLeafYear(year) == true ? 29 : 28;
			
		}
		return 0;
	}
	
	
	private boolean isLeafYear(int year) {
		
		return (year%4==0 && year%100!=0) || year%400==0 ? true : false;
		
	}
	
	
	private int getWeek(int year, int month){
		
		int total = 0;
		int date = 1;
		
		for(int i=1; i<year; i++) {
			if(isLeafYear(i)) {
				total += 366;
			} else {
				total += 365;
			}
		}
		
		for(int i=1; i<month; i++) {
			total += getLastDay(year, i);
		}
		total += date;
		
		return total % 7;
	}
	
}