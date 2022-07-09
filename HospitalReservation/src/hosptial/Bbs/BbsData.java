package hosptial.Bbs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import hosptial.DataPath;
/**
 * 
 * @author KIMMINGYU
 * 파일 데이터  메모리(배열)
 *
 */
public class BbsData {
	
	public static ArrayList<BbsPost> plist = new ArrayList<BbsPost>();
	public static ArrayList<BbsQuestion> qlist = new ArrayList<BbsQuestion>();
	public static ArrayList<BbsAnswer> alist = new ArrayList<BbsAnswer>();
	/**
	 * 후기 plist
	 * 질문 qlist
	 * 대답 alist
	 */
	public static void load() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DataPath.postscript));
			String line = null;
			while((line=reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				BbsPost p = new BbsPost(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				
				plist.add(p);
			}
			
			reader.close();
			
			reader = new BufferedReader(new FileReader(DataPath.질문));
			line = null;
			while((line=reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				BbsQuestion q = new BbsQuestion(temp[0], temp[1], temp[2], temp[3], temp[4]);
				
				qlist.add(q);
			}
			
			reader.close();
			
			reader = new BufferedReader(new FileReader(DataPath.대답));
			line = null;
			while((line=reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				BbsAnswer a = new BbsAnswer(temp[0], temp[1], temp[2], temp[3], temp[4]);
				
				alist.add(a);
			}
			
			reader.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 메모리 파일 데이터
	 * plist 후기
	 */
	public static void postSave() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.postscript));
			
			for (BbsPost p : plist) {
				String line = String.format("%s,%s,%s,%s,%s,%s\n" 
											, p.getSequence()
											, p.getGrade()
											, p.getName()
											, p.getText()
											, p.getDate()
											, p.getHospital());
				writer.write(line);
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 메모리 파일 데이터
	 * qlist 질문
	 */
	public static void questionSave() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.질문));
			
			for (BbsQuestion q : qlist) {
				String line = String.format("%s,%s,%s,%s,%s\n" 
											, q.getBbsNo()
											, q.getSequence()
											, q.getUserTypeCheck()
											, q.getText()
											, q.getDate());
				writer.write(line);
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 메모리 파일 데이터
	 * alist 대답
	 */
	public static void answerSave() {
	
	try {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(DataPath.대답));
		
		for (BbsAnswer a : alist) {
			String line = String.format("%s,%s,%s,%s,%s\n" 
										, a.getBbsNo()
										, a.getSequence()
										, a.getUserTypeCheck()
										, a.getText()
										, a.getDate());
			writer.write(line);
		}
		
		writer.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	
}
