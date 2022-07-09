package hosptial.myPage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import hosptial.DataPath;
/**
 * 파일 데이터, 메모리(배열)
 * @author KIMMINGYU
 *
 */
public class myPageData {

	public static ArrayList<info> infolist = new ArrayList<info>();
	public static ArrayList<medicalInfo> medicalInfoList = new ArrayList<medicalInfo>();
	/**
	 * 회원정보 infolist
	 * 진료내역 medicalInfoList
	 */
	public static void load() {

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DataPath.userData));
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if(line.length() < 3) continue;
				
				String[] temp = line.split(",");

				info a = new info(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);

				infolist.add(a);
			}

			reader.close();
			
			reader = new BufferedReader(new FileReader(DataPath.medicalInfo));
			line = null;
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				medicalInfo a = new medicalInfo(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);

				medicalInfoList.add(a);
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 메모리  파일 데이터
	 * infolist  회원정보
	 */
	public static void infoSave() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.userData));

			for (info a : infolist) {
				String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s\n"
											, a.getSequence()
											, a.getUserTypeCheck()
											, a.getId()
											, a.getPassword()
											, a.getName()
											, a.getPhoneNum()
											, a.getAddress()
											, a.getRegNo());
				writer.write(line);
			}

			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 메모리  파일 데이터
	 * medicalInfoList  진료내역
	 */
	public static void medicalSave() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.medicalInfo));

			for (medicalInfo b : medicalInfoList) {
				String line = String.format("%s,%s,%s,%s,%s,%s,%s\n"
											, b.getPatientName()
											, b.getRegNo()
											, b.getDate()
											, b.getHospital()
											, b.getTime()
											, b.getDoctorName()
											, b.getDepartment());
				writer.write(line);
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
