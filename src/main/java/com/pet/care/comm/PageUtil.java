package com.pet.care.comm;

import com.pet.care.dto.PageDto;

public class PageUtil {

	public static PageDto defaultPagingSetting(PageDto page, int allCnt) {
	// 총 게시물의 갯수
	page.setTotalCount(allCnt);
	// 출력될 개시글의 수
	page.setCountList(10);
	// 화면에 몇개의 페이지를 보여줄건지 (페이지 그룹)
	page.setCountPage(10);
	// 총페이지의 갯수
	page.setTotalPage(page.getTotalCount());

	return page;
}
	
}
