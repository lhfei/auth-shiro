/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.y.auth.serve.orm.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 22, 2017
 */
public abstract class AbstractDomain implements Serializable {
	private static final long serialVersionUID = 6763989861666470792L;

	public AbstractDomain() {
		Date date = new Date();
		this.createTime = date;
		this.latestTime = date;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public int getLimit() {
		limit = (null == limit) ? 10 : limit;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;

		if (limit > 0) {
			this.start = (this.getPageNum() - 1) * limit;
		} else {
			start = 0;
		}
	}

	public int getStart() {
		start = (null == start) ? 0 : start;
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public int getPageNum() {
		pageNum = (null == pageNum) ? 1 : pageNum;
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;

		if (pageNum > 0) {
			this.start = (pageNum - 1) * this.getLimit();
		} else {
			start = 0;
		}
	}

	private Long pid;
	private Date createTime;
	private Date latestTime;
	private int dataStatus;
	@JsonIgnore
	private Integer start;
	@JsonIgnore
	private Integer limit;
	@JsonIgnore
	private Integer pageNum;
}
