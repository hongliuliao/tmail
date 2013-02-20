/**
 * 
 */
package org.tmail.model;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-16 下午6:35:18
 */
public class VCodeMsg {
	
	private int code;

    private String msg;
    
    private Object data;
    
    public static final int SUCCESS_CODE = 0;
    
    private static final int FAIL_CODE = 1;
    
    public static final VCodeMsg SUCCESS = new VCodeMsg(SUCCESS_CODE, "success");

    public VCodeMsg() {
        super();
    }

    public VCodeMsg(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public VCodeMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public VCodeMsg setData(Object data) {
		this.data = data;
		return this;
	}
	
	public static VCodeMsg failOf(String msg) {
		return new VCodeMsg(FAIL_CODE, msg);
	}
	
}
