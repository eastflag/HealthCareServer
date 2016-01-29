package com.healthcare.schoolapi;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthcare.biz.mybatis.domain.MenuData;

public class SchoolApi {
	
	private static final Logger logger = LoggerFactory.getLogger(SchoolApi.class);

	/**
	 * 학교 종류
	 */
	public static class SchoolType {
		public static final int KINDERGARTEN = 1;
		public static final int ELEMENTARY = 2;
		public static final int MIDDLE = 3;
		public static final int HIGH = 4;
	}

	/**
	 * 지역 관할 교육청 코드
	 */
	public static class Country {
		public static final String SEOUL = "hes.sen.go.kr";
		public static final String ULSAN = "hes.use.go.kr";
		public static final String JEONBUK = "hes.jbe.go.kr";
		public static final String BUSAN = "hes.pen.go.kr";
		public static final String SEJONG = "hes.sje.go.kr";
		public static final String JEONNAM = "hes.jne.go.kr";
		public static final String DAEGU = "hes.dge.go.kr";
		public static final String GYEONGGI = "hes.goe.go.kr";
		public static final String GYEONGBUK = "hes.gbe.go.kr";
		public static final String INCHEON = "hes.ice.go.kr";
		public static final String KANGWON = "hes.kwe.go.kr";
		public static final String GYEONGNAM = "hes.gne.go.kr";
		public static final String GWANGJU = "hes.gen.go.kr";
		public static final String CHUNGBUK = "hes.cbe.go.kr";
		public static final String JEJU = "hes.jje.go.kr";
		public static final String DAEJEON = "hes.dje.go.kr";
		public static final String CHUNGNAM = "hes.cne.go.kr";
	}

	/**
	 * 급식 식단표 정보(월간) URL
	 */
	public static final String MENU_MONTH_URL = "sts_sci_md00_001.do";
	public static final String MENU_WEEK_URL = "sts_sci_md01_001.do";

	/**
	 * 학사일정 정보(월간) URL
	 */
	public static final String SCHEDULE_URL = "sts_sci_sf01_001.do";

	public static MenuData[] getMonthlyMenu(String countryCode, String schoolCode, int schoolType, int year,
			int month) {

		StringBuffer targetURL = new StringBuffer("http://");
		targetURL.append(countryCode);
		targetURL.append("/");
		targetURL.append(MENU_MONTH_URL);

		try {
			Document menuData = Jsoup.connect(targetURL.toString())
					.data("schulCode", schoolCode)
					.data("schulCrseScCode", schoolType + "")
					.data("schulKndScCode", "0" + schoolType)
					// .data("schYm", year + "." + asTwoWord(month)).post();
					.data("ay", String.valueOf(year))
					.data("mm", String.format("%02d", month))
					.post();

			//System.out.println(menuData.toString());
			return menuParser(menuData);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static ArrayList<MenuData> getWeeklyMenu(String countryCode, String schoolCode, int schoolType, String schoolId) {

		logger.debug("***** getWeeklyMenu ***** countryCode:"+countryCode+" schoolCode"+schoolCode+" schoolType:"+schoolType);
		StringBuffer targetURL = new StringBuffer("http://");
		targetURL.append(countryCode);
		targetURL.append("/");
		targetURL.append(MENU_WEEK_URL);//MENU_WEEK_URL

		try {
			Document menuData = Jsoup.connect(targetURL.toString())
					.data("schulCode", schoolCode) // 학교코드
					.data("schulCrseScCode", schoolType + "") // 학교종과정분코드: 초등, 중등, 고등 코드
					.data("schulKndScCode", "0" + schoolType) // 학교종류구분코드: 초등, 중등, 고등 코드
					//.data("schYm", year + "." + String.format("%02d", month) + "." + String.format("%02d", date)) // 날짜
					//.data("schMmealScCode",2+"") // 조식 중식 석식
					.post();
					//.data("ay", String.valueOf(year))
					//.data("mm", String.format("%02d", 11))
					//.data("d", String.format("%02d", 21))
					//.post();

			//System.out.println(menuData.toString()); //가져오는 html
			return menuWeekParser(menuData,schoolId);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}
	

	/**
	 * 월간 학사 일정을 가져온다.
	public static ScheduleData[] getMonthlySchedule(String countryCode, String schoolCode, int schoolType, int year,
			int month) {

		StringBuffer targetURL = new StringBuffer("http://");
		targetURL.append(countryCode);
		targetURL.append("/");
		targetURL.append(SCHEDULE_URL);

		try {
			Document scheduleData = Jsoup.connect(targetURL.toString()).data("schulCode", schoolCode)
					.data("schulCrseScCode", schoolType + "").data("schulKndScCode", "0" + schoolType)
					.data("ay", "" + year).data("mm", String.format("%02d", month)).post();

			return scheduleParser(scheduleData);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}
	 */

	public static MenuData[] menuParser(Document menuData) throws NullPointerException {

		MenuData[] monthlyMenu = new MenuData[31];

		Element month = menuData.getElementById("contents").getElementsByClass("sub_con").first()
				.getElementsByClass("tbl_calendar").first().getElementsByTag("tbody").first();

		// 월 단위 메뉴 목록
		Elements weeks = month.getElementsByTag("tr");
		for (Element week : weeks) {

			// 주 단위 메뉴 목록
			Elements days = week.getElementsByTag("td");

			for (Element day : days) {
				// 일 단위 메뉴 목록
				Elements menus = day.getElementsByTag("div").first().getAllElements();

				// Elements menus =
				// day.getElementsByTag("div").first().getAllElements();
				for (Element menu : menus) {

					// 빈 메뉴가 아닐 경우에만 프로세싱
					String data = menu.text().trim();
					if (data.length() < 1)
						continue;

					// 불필요한 문자를 제거한다.
					data = data.replace("(석식)", "");

					// 급식 데이터의 첫 단위는 날짜이다.
					String[] chunk = data.split(" ");
					int date = Integer.valueOf(chunk[0]);

					// 급식 정보가 존재할 경우에만 데이터를 쓴다.
					// 
					if (chunk.length > 2)
						monthlyMenu[date - 1] = new MenuData(data);
					else
						monthlyMenu[date - 1] = new MenuData();
				}
			}
		}

		//System.out.println(monthlyMenu.toString());
		return monthlyMenu;
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<MenuData> menuWeekParser(Document menuData, String schoolId) throws NullPointerException {

		ArrayList<MenuData> weekMenu = new ArrayList<MenuData>();

		Element week = menuData.getElementById("contents").getElementsByClass("tbl_type3").first();
		
		// 날짜 추출
		Elements tableHeaders = week.getElementsByTag("thead").first().getElementsByAttributeValueContaining("scope", "col");
		
		for(Element header : tableHeaders){
			//System.out.println(header.text());
			MenuData menu = new MenuData();
			String menuDate = header.text().substring(0, 10);
			//menu.SetMenuDate(header.text().substring(0, 10));
			menu.setYear(menuDate.substring(0, 4));
			menu.setMonth(menuDate.substring(5, 7));
			menu.setDate(menuDate.substring(8));
			weekMenu.add(menu);
		}
		//System.out.println("----- weekMenu -----");
		//System.out.println(weekMenu);
		
		// body 추출
		Element tableBody = week.getElementsByTag("tbody").first();
		
		Elements trElements = tableBody.getElementsByTag("tr");
		//System.out.println(trElements.size());
		
		// Data validation 처리
		if(trElements.size() != 26)
				return null;
		
		// rule에 맞추어 추출 처리
		
		// 중식 추출
		Elements tdMenus = trElements.get(1).getElementsByTag("td");
		
		Elements tdCalorie = trElements.get(16).getElementsByTag("td");
		Elements tdCarbohydrate = trElements.get(17).getElementsByTag("td");
		Elements tdProtein = trElements.get(18).getElementsByTag("td");
		Elements tdFat = trElements.get(19).getElementsByTag("td");
		Elements tdVitaminA= trElements.get(20).getElementsByTag("td");
		Elements tdThiamine = trElements.get(21).getElementsByTag("td");
		Elements tdRiboflavin = trElements.get(22).getElementsByTag("td");
		Elements tdVitaminC= trElements.get(23).getElementsByTag("td");
		Elements tdCalcium= trElements.get(24).getElementsByTag("td");
		Elements tdIron= trElements.get(25).getElementsByTag("td");
		
		int index = 0;
		for (Element tdMenu : tdMenus){
			MenuData menuItem = weekMenu.get(index);
			
			String temp = tdMenu.text();
			if(temp.length() > 0){
				// 정형화 처리 작업 
				temp = temp.replace(" ", ",");

				// ①난류 ②우유 ③메밀 ④땅콩 ⑤대두 ⑥밀 ⑦고등어 ⑧게 ⑨새우 ⑩돼지고기 ⑪복숭아 ⑫토마토 ⑬아황산염
				temp = temp.replace("*", "");
				temp = temp.replace("①", "");
				temp = temp.replace("②", "");
				temp = temp.replace("③", "");
				temp = temp.replace("④", "");
				temp = temp.replace("⑤", "");
				temp = temp.replace("⑥", "");
				temp = temp.replace("⑦", "");
				temp = temp.replace("⑧", "");
				temp = temp.replace("⑨", "");
				temp = temp.replace("⑩", "");
				temp = temp.replace("⑪", "");
				temp = temp.replace("⑫", "");
				temp = temp.replace("⑬", "");

				//System.out.println("----- temp -----");
				//System.out.println(temp);
			}
			
			menuItem.setMenuContent(temp);
			
			menuItem.setCalorie(tdCalorie.get(index).text());
			menuItem.setCarbohydrate(tdCarbohydrate.get(index).text());
			menuItem.setProtein(tdProtein.get(index).text());
			menuItem.setFat(tdFat.get(index).text());
			menuItem.setVitaminA(tdVitaminA.get(index).text());
			menuItem.setThiamine(tdThiamine.get(index).text());
			menuItem.setRiboflavin(tdRiboflavin.get(index).text());
			menuItem.setVitaminC(tdVitaminC.get(index).text());
			menuItem.setCalcium(tdCalcium.get(index).text());
			menuItem.setIron(tdIron.get(index).text());
			
			menuItem.setSchoolId(schoolId);
			index++;
		}

		//System.out.println("----- weekMenu -----");
		//System.out.println(weekMenu.toString());
		return weekMenu;
	}

	/**
	 * 
	public static ScheduleData[] scheduleParser(Document scheduleData) throws NullPointerException {

		ScheduleData[] monthlySchedule = new ScheduleData[31];

		Element month = scheduleData.getElementById("contents").getElementsByClass("sub_con").first()
				.getElementsByClass("tbl_calendar").first().getElementsByTag("tbody").first();

		// 월간 일정 목록
		Elements weeks = month.getElementsByTag("tr");
		for (Element week : weeks) {

			// 주간 일정 목록
			Elements days = week.getElementsByTag("td");
			for (Element day : days) {

				// 일간 일정
				Element schedule = day.getElementsByTag("div").first();

				String dateString = schedule.getElementsByTag("em").first().text();
				if (dateString.length() < 1)
					continue;

				// 일자 취득
				int date = Integer.valueOf(dateString);

				// 일정 취득
				String events = schedule.getElementsByTag("a").text().trim();

				if (events.length() > 1)
					monthlySchedule[date - 1] = new ScheduleData(events);
				else
					monthlySchedule[date - 1] = new ScheduleData();

			}
		}

		return monthlySchedule;
	}
	 */

	public static String getContry(String contry) {
		if ("서울".equals(contry)) {
			return Country.SEOUL;
		} else if ("울산".equals(contry)) {
			return Country.ULSAN;
		} else if ("전북".equals(contry)) {
			return Country.JEONBUK;
		} else if ("부산".equals(contry)) {
			return Country.BUSAN;
		} else if ("세종".equals(contry)) {
			return Country.SEJONG;
		} else if ("전남".equals(contry)) {
			return Country.JEONNAM;
		} else if ("대구".equals(contry)) {
			return Country.DAEGU;
		} else if ("경기".equals(contry)) {
			return Country.GYEONGGI;
		} else if ("경북".equals(contry)) {
			return Country.GYEONGBUK;
		} else if ("인천".equals(contry)) {
			return Country.INCHEON;
		} else if ("강원".equals(contry)) {
			return Country.KANGWON;
		} else if ("경남".equals(contry)) {
			return Country.GYEONGNAM;
		} else if ("광주".equals(contry)) {
			return Country.GWANGJU;
		} else if ("충북".equals(contry)) {
			return Country.CHUNGBUK;
		} else if ("제주".equals(contry)) {
			return Country.JEJU;
		} else if ("대전".equals(contry)) {
			return Country.DAEJEON;
		} else if ("충남".equals(contry)) {
			return Country.CHUNGNAM;
		} else {
			return null;
		}
	}

	public static int getSchoolType(String type) {
		/*if (type.contains("유치원")) {
			return SchoolType.KINDERGARTEN;
		} else if (type.contains("초등")) {
			return SchoolType.ELEMENTARY;
		} else if (type.contains("중학")) {
			return SchoolType.MIDDLE;
		} else if (type.contains("고등")) {
			return SchoolType.HIGH;
		} else {
			return 0;
		}*/
		if (type.contains("E")) {
			return SchoolType.ELEMENTARY;
		} else if (type.contains("M")) {
			return SchoolType.MIDDLE;
		} else if (type.contains("H")) {
			return SchoolType.HIGH;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Calendar mealMonth;
		mealMonth = Calendar.getInstance();
        mealMonth.set(Calendar.DAY_OF_MONTH, 1);
		
		
		
        ArrayList<MenuData> listMenu = SchoolApi.getWeeklyMenu(SchoolApi.getContry("경기"),
                 "J100001199",
                 SchoolApi.getSchoolType("초등"),
                 mealMonth.get(Calendar.YEAR),
                 mealMonth.get(Calendar.MONTH) + 1);
        
        
        System.out.println(listMenu);*/
        
	}

}
