package hosptial.Bbs;
/**
 * 
 * @author KIMMINGYU
 * 후기 데이터 생성자, getter, setter
 *
 */
public class BbsPost {
	
	private String sequence;
	private String grade;
	private String name;
	private String text;
	private String date;
	private String hospital;
	/**
	 * 
	 * @param sequence 고유번호
	 * @param grade 평점
	 * @param name 이름
	 * @param text 내용
	 * @param date 작성날짜
	 * @param hospital 병원명
	 */
	public BbsPost(String sequence, String grade, String name, String text, String date, String hospital) {
		super();
		this.sequence = sequence;
		this.grade = grade;
		this.name = name;
		this.text = text;
		this.date = date;
		this.hospital = hospital;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

}
