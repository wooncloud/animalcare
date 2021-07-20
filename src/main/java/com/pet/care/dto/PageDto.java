package com.pet.care.dto;

import java.io.Serializable;

public class PageDto implements Serializable{

	private static final long serialVersionUID = 3060089567494193334L;
	
	// 현재 선택된 페이지 0
	private int page;
	// 페이지당 몇개의 게시글을 보여줄지 정하는 수
	private int countList;
	// 총 게시글의 갯수
	private int totalCount;
	// 화면에 몇개의 페이지를 보여줄지 정하는 수 - 1 2 3 -
	private int countPage;
	// -123- -456- -7-
	private int totalPage;
	// 현재 페이지의 시작 페이지 번호
	private int startPage;
	// 현재 페이지의 끝 페이지 번호
	private int endPage;

	public PageDto() {
		}

		@Override
		public String toString() {
			return "PageDto [page=" + page + ", countList=" + getCountList() + ", totalCount=" + getTotalCount() + ", countPage="
					+ getCountPage() + ", totalPage=" + getTotalPage() + ", startPage=" + getStartPage() + ", endPage="
					+ getEndPage() + "]";
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			// url에서 총 페이지 갯수보다 큰 페이지가 입력되면 끝 페이지로 이동
			if (getTotalPage() < page) {
				page = getTotalPage();
			}

			this.page = page;
		}

		public int getCountList() {
			return countList;
		}

		public void setCountList(int countList) {
			this.countList = countList;
		}

		public int getCountPage() {
			return countPage;
		}

		public void setCountPage(int countPage) {
			this.countPage = countPage;
		}

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			// 총 게시글 갯수 / 페이지당 보여줄 게시글
			int totalPageResult = getTotalCount() / countList;
			if (getTotalCount() % countList > 0) {
				totalPageResult++;
			}

			this.totalPage = totalPageResult;
		}

		public int getStartPage() {
			return startPage;
		}

		public void setStartPage(int startPage) {
			int startPageResult = ((page - 1) / countPage) * countPage + 1;

			this.startPage = startPageResult;
		}

		public int getEndPage() {
			return endPage;
		}

		public void setEndPage(int endPage) {
			int endPageResult = startPage + countPage - 1;
			
			if (endPageResult > totalPage) {
				endPageResult = totalPage;
			}

			this.endPage = endPageResult;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
	
}
