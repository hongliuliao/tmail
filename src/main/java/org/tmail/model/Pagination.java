package org.tmail.model;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Pagination<T> implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int start;

    private int count;

    private int totalCount;

    private List<T> resultList;

    public Pagination() {
    }
    public Pagination(int start, int count) {
        this.start = start;
        this.count = count;
      }
    public Pagination(int start, int count, int totalCount, List<T> resultList) {
      this.start = start;
      this.count = count;
      this.totalCount = totalCount;
      this.resultList = resultList;
    }

    public String toString() {
      return ToStringBuilder.reflectionToString(this,
          ToStringStyle.MULTI_LINE_STYLE);
    }

    public int getTotalCount() {
      return totalCount;
    }

    public void setTotalCount(int count) {
      this.totalCount = count;
    }
    

    public int getStart() {
      return start;
    }

    public void setStart(int start) {
      this.start = start;
    }
    /**
     * @return the pageSize
     */
    public int getCount() {
        return count;
    }
    /**
     * @param pageSize the pageSize to set
     */
    public void setCount(int pageSize) {
        this.count = pageSize;
    }
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
    
}

