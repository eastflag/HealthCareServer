package com.healthcare.biz.mybatis.persistence;

import java.util.ArrayList;

import com.healthcare.bean.GcmRequest;
import com.healthcare.biz.mybatis.domain.MeasureNotice;

public interface MeasureNoticeMapper {
	ArrayList<MeasureNotice> getMeasureNoticeListPerSchool(GcmRequest gcmRequest);
	ArrayList<MeasureNotice> getMeasureNoticeListPerSchoolNoOverlap(GcmRequest gcmRequest);

}
