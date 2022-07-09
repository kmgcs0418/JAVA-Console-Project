package hosptial.searchReservation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import hosptial.DataPath;

public class Data {

	public static void save() {
			
			// 메모리 > 파일 데이터
			try {
				// 처방전
				BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.처방전));
		
					
				writer.close();	
				
			} catch (Exception e) {
				System.out.println("Data.save");
				e.printStackTrace();
			}
	
	}
}
