package com.example.persona.producer.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableConstructor {

	int limit;
	int offSet;
	int page;

	public Pageable construirPageable() {
		Pageable pageable = new PageRequest(page, limit);
		return pageable;
	}

	public int getPage() {
		page = page > 0 ? page : 0;
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		limit = limit > 0 ? limit : 1;
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

}
