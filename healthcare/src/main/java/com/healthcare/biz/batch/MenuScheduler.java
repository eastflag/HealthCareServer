package com.healthcare.biz.batch;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.healthcare.biz.mybatis.domain.MenuData;
import com.healthcare.biz.mybatis.domain.SchoolInfo;
import com.healthcare.biz.service.MenuService;
import com.healthcare.biz.service.SchoolService;
import com.healthcare.schoolapi.SchoolApi;

@Component
public class MenuScheduler {
	/** 
	------    --------  -----------------  ----------------------------------
	필  드    의  미    범  위             허용 문자
	------    --------  -----------------  ----------------------------------
	첫번째    초        0-59               , - * /
	두번째    분        0-59               , - * /
	세번째    시        0-23               , - * /
	네번째    일        1-31               , - * ? / L W
	다섯번째  월        1-12 or JAN-DEC    , - * /
	여섯번째  요일      1-7 or SUN-SAT     , - * ? / L #
	일곱번째  년(옵션)  1970-2099          , - * /
	------    --------  -----------------  ----------------------------------
	-------------------------------------------------------------------------
	* 모든 값
	? 특정 값 없음
	- 범위 지정에 사용
	, 여러 값 지정 구분에 사용
	/ 초기값과 증가치 설정에 사용
	L 지정할 수 있는 범위의 마지막 값
	W 월~금요일 또는 가장 가까운 월/금요일
	# 몇 번째 주의 무슨 요일 6#3 => 세 번째주 금요일
	-------------------------------------------------------------------------
	**/

	private static final Logger logger = LoggerFactory.getLogger(MenuScheduler.class);

	@Autowired
	private SchoolService schoolService; 
	@Autowired
	private MenuService menuService;
	

    @Scheduled(cron="0 0 1 * * *") // 매일 1시 
    public void updateUrbanServiceArea() {
    	// step1. 학교 목록 가져오기
    	ArrayList<SchoolInfo> schoolList = schoolService.getSchoolList();
    	
    	if(schoolList!=null && schoolList.size()>0) {
    		// 가져온 학교 for문
    		for(int i=0; i<schoolList.size(); i++) {
    			// 학교 코드가 있는 것만 가져온다
    			if(schoolList.get(i).getCode()!=null && !schoolList.get(i).getCode().equals("")) {
        	        logger.debug("***** "+schoolList.get(i).getName());
        	        // 점심 메뉴 가져오기
        	        ArrayList<MenuData> menuList = SchoolApi.getWeeklyMenu(
        	        		SchoolApi.getContry(schoolList.get(i).getSido()), // 지역
        	        		schoolList.get(i).getCode(), // 학교코드
        	                 SchoolApi.getSchoolType(schoolList.get(i).getSection()),// 학교타입 E,M,H
        	                 schoolList.get(i).getSchoolId()
        	                 ); 
        	        
        	        // 점심메뉴가 있을 경우 
        	        if(menuList!=null && menuList.size()>0) {
            	        logger.debug(menuList.toString());
            	        for(int j=0; j<menuList.size(); j++) {
            	        	if(menuList.get(j).getMenuContent()!=null && !menuList.get(j).getMenuContent().equals("")) {
            	        		menuList.get(j).setDay(Integer.toString(j)); // 요일
            	        		int menuCnt = menuService.getMenuData(menuList.get(j));
            	        		if(menuCnt==0) {
            	        			menuService.insertMenu(menuList.get(j));
            	        		}
            	        	}	            	        	
            	        }
        	        }
    			}
    		}
    	}
        
    }
		
		
}
