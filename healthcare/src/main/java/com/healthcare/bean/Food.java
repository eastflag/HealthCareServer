package com.healthcare.bean;

public class Food {

	String alg_id = ""; // 알러지 아이디
	String userId = ""; // 학생아이디
	String alg_name = ""; // 알러지이름
	String alg_state = ""; // 알러지 사용 상태
	String stu_alg_set_yn = ""; // 알러지 선택 유무
	String alg_main_id = ""; // 알러지 메인 아이디
	String bkst_id = ""; // 아침 구분 아이디
	String bkst_name = ""; // 아침 구분 이름
	String bkst_info = ""; // 아침 구분 상세정보
	String bkst_yn = ""; // 아침 선택 유무

	String age = ""; //
	String bksk_stand_amt = ""; //
	String height = ""; //
	String weight = ""; //
	String bmi = ""; //
	String bmi_status = ""; //
	String cal = ""; //
	String carbohy = ""; //
	String protein = ""; //
	String fat = ""; //
	String vitamin_a = ""; //
	String vitamin_c = ""; //
	String calcium = ""; //

	String menu_id = "";
	String menu_photo = "";
	String menu_cal = "";
	String menu_meal = "";
	String menu_souop = "";
	String menu_side1 = "";
	String menu_side2 = "";
	String menu_side3 = "";
	String menu_dessert = "";
	String menu_sauce = "";
	String min_cal = "";
	String max_cal = "";
	String min_age = "";
	String max_age = "";
	


	public String getMin_age() {
		return min_age;
	}
	public void setMin_age(String min_age) {
		this.min_age = min_age;
	}
	public String getMax_age() {
		return max_age;
	}
	public void setMax_age(String max_age) {
		this.max_age = max_age;
	}
	public String getMin_cal() {
		return min_cal;
	}
	public void setMin_cal(String min_cal) {
		this.min_cal = min_cal;
	}
	public String getMax_cal() {
		return max_cal;
	}
	public void setMax_cal(String max_cal) {
		this.max_cal = max_cal;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_photo() {
		return menu_photo;
	}
	public void setMenu_photo(String menu_photo) {
		this.menu_photo = menu_photo;
	}
	public String getMenu_cal() {
		return menu_cal;
	}
	public void setMenu_cal(String menu_cal) {
		this.menu_cal = menu_cal;
	}
	public String getMenu_meal() {
		return menu_meal;
	}
	public void setMenu_meal(String menu_meal) {
		this.menu_meal = menu_meal;
	}
	public String getMenu_souop() {
		return menu_souop;
	}
	public void setMenu_souop(String menu_souop) {
		this.menu_souop = menu_souop;
	}
	public String getMenu_side1() {
		return menu_side1;
	}
	public void setMenu_side1(String menu_side1) {
		this.menu_side1 = menu_side1;
	}
	public String getMenu_side2() {
		return menu_side2;
	}
	public void setMenu_side2(String menu_side2) {
		this.menu_side2 = menu_side2;
	}
	public String getMenu_side3() {
		return menu_side3;
	}
	public void setMenu_side3(String menu_side3) {
		this.menu_side3 = menu_side3;
	}
	public String getMenu_dessert() {
		return menu_dessert;
	}
	public void setMenu_dessert(String menu_dessert) {
		this.menu_dessert = menu_dessert;
	}
	public String getMenu_sauce() {
		return menu_sauce;
	}
	public void setMenu_sauce(String menu_sauce) {
		this.menu_sauce = menu_sauce;
	}
	public String getBksk_stand_amt() {
		return bksk_stand_amt;
	}
	public void setBksk_stand_amt(String bksk_stand_amt) {
		this.bksk_stand_amt = bksk_stand_amt;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getBmi_status() {
		return bmi_status;
	}
	public void setBmi_status(String bmi_status) {
		this.bmi_status = bmi_status;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public String getCarbohy() {
		return carbohy;
	}
	public void setCarbohy(String carbohy) {
		this.carbohy = carbohy;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	}
	public String getVitamin_a() {
		return vitamin_a;
	}
	public void setVitamin_a(String vitamin_a) {
		this.vitamin_a = vitamin_a;
	}
	public String getVitamin_c() {
		return vitamin_c;
	}
	public void setVitamin_c(String vitamin_c) {
		this.vitamin_c = vitamin_c;
	}
	public String getCalcium() {
		return calcium;
	}
	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBkst_yn() {
		return bkst_yn;
	}
	public void setBkst_yn(String bkst_yn) {
		this.bkst_yn = bkst_yn;
	}
	public String getAlg_main_id() {
		return alg_main_id;
	}
	public void setAlg_main_id(String alg_main_id) {
		this.alg_main_id = alg_main_id;
	}
	public String getBkst_name() {
		return bkst_name;
	}
	public void setBkst_name(String bkst_name) {
		this.bkst_name = bkst_name;
	}
	public String getBkst_info() {
		return bkst_info;
	}
	public void setBkst_info(String bkst_info) {
		this.bkst_info = bkst_info;
	}
	public String getBkst_id() {
		return bkst_id;
	}
	public void setBkst_id(String bkst_id) {
		this.bkst_id = bkst_id;
	}
	public String getAlg_id() {
		return alg_id;
	}
	public void setAlg_id(String alg_id) {
		this.alg_id = alg_id;
	}
	public String getStu_alg_set_yn() {
		return stu_alg_set_yn;
	}
	public void setStu_alg_set_yn(String stu_alg_set_yn) {
		this.stu_alg_set_yn = stu_alg_set_yn;
	}
	public String getAlg_name() {
		return alg_name;
	}
	public void setAlg_name(String alg_name) {
		this.alg_name = alg_name;
	}
	public String getAlg_state() {
		return alg_state;
	}
	public void setAlg_state(String alg_state) {
		this.alg_state = alg_state;
	}
	
	
}
