package com.phone.cn.bean;

/**
 * @author zgdcool
 * @version 2014年10月29日 下午7:42:17
 * 
 *          _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\|
 *          |// `. / \\||| : |||// \ / _||||| -:- |||||- \ | | \\\ - /// | | |
 *          \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ .""
 *          '< `.___\_<|>_/___.' >'"". | | : `- \`.;`\ _ /`;.`/ - ` : | | \ \
 *          `-. \_ __\ /__ _/ .-` / /
 *          ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰:
 *          写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠； 酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 */
public class ResultBean {

	private Boolean isSuccess;
	private Integer code;
	private String message;

	private Object result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public static ResultBean ok(String msg) {
		return new ResultBean(Boolean.TRUE, msg);
	}

	public static ResultBean fail(String msg) {
		return new ResultBean(Boolean.FALSE, msg);
	}

	public static ResultBean fail(int code, String msg) {
		ResultBean resultBean = new ResultBean(Boolean.FALSE, msg);
		resultBean.setCode(code);
		return resultBean;
	}

	public static ResultBean suc(String msg) {
		return new ResultBean(Boolean.TRUE, msg);
	}

	public static ResultBean suc(String msg, Object result) {
		ResultBean resultBean = new ResultBean(Boolean.TRUE, msg);
		resultBean.setResult(result);
		return resultBean;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultBean(Boolean isSuccess, String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public ResultBean() {
		super();
	}

}
