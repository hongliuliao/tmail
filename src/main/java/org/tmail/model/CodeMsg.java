/**
 * 
 */
package org.tmail.model;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-16 下午6:35:18
 */
public class CodeMsg {
	
	private int code;

    private String msg;

    public CodeMsg() {
        super();
    }

    public CodeMsg(int code, String msg) {
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
    public CodeMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
