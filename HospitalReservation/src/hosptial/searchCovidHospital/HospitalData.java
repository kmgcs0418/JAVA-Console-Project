package hosptial.searchCovidHospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import hosptial.DataPath;

/**
 * 코로나 안심병원 서비스 메뉴 중 원하는 파일을 읽고 static 변수에 데이터를 저장하는 클래스입니다. 
 * @author 박채은
 *
 */
public class HospitalData {

	public static ArrayList<CovidSafeHospital> covidSafeList = new ArrayList<CovidSafeHospital>(200);
	public static ArrayList<RespiratoryClinic> clinicList = new ArrayList<RespiratoryClinic>(200);
	
	/**
	 * 국민 안심병원 파일을 읽고 static ArrayList에 정보를 저장합니다.
	 */
	public static void covidSafeListLoad() {

		// 국민안심병원 데이터 > covidSafeList
		// 호흡기 클리닉 데이터 > clinicList
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DataPath.covidSafeHospital));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				CovidSafeHospital safeHospital = new CovidSafeHospital(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				
				covidSafeList.add(safeHospital);
			
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("HospitalData.load");
			e.printStackTrace();
		}
	}
	
	/**
	 * 호흡기 클리닉 파일을 읽고 static ArrayList에 정보를 저장합니다.
	 */
	public static void clinicListLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DataPath.respiratoryClinic));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				
				RespiratoryClinic clinic = new RespiratoryClinic(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
				clinicList.add(clinic);
			
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("HospitalData.clinicListLoad");
			e.printStackTrace();
		}
	}
	
	
}
