package egovframework.sonorous.user.service;

public class MemberVO {
	
	/** 회원ID */
	private String mId;
	
	/** 회원PW */
	private String mPw;
	
	/** 국가 */
	private String country;
	
	/** 영문이름 */
	private String eName;
	
	/** 한이름 */
	private String kName;
	
	/** 생년월일 */
	private String birth;

	/** 연락처 */
	private String phone;
	
	/** 이메일 */
	private String email;
	
	/** 등록일 */
	private String insDate;
	
	/** 수정일 */
	private String modDate;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getkName() {
		return kName;
	}

	public void setkName(String kName) {
		this.kName = kName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInsDate() {
		return insDate;
	}

	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
}
