package sms;
import java.sql.Timestamp;

public class SmsBean {
	private Integer id;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 发送状态 0-待发送 1-已发送 2-发送失败
	 */
	private Integer status;
	/**
	 * 发送失败原因
	 */
	private String msg;
	/**
	 * 创建时间
	 */
	private Timestamp create_time;
	/**
	 * 创建人
	 */
	private Integer employeeId;
	/**
	*设置
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置手机号码
	*/
	public void setPhone(String phone){
		this.phone = phone;
	}
	/**
	*获取手机号码
	*/
	public String getPhone(){
		return phone;
	}
	/**
	*设置短信内容
	*/
	public void setContent(String content){
		this.content = content;
	}
	/**
	*获取短信内容
	*/
	public String getContent(){
		return content;
	}
	/**
	*设置发送状态 0-待发送 1-已发送 2-发送失败
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	*获取发送状态 0-待发送 1-已发送 2-发送失败
	*/
	public Integer getStatus(){
		return status;
	}
	/**
	*设置发送失败原因
	*/
	public void setMsg(String msg){
		this.msg = msg;
	}
	/**
	*获取发送失败原因
	*/
	public String getMsg(){
		return msg;
	}
	/**
	*设置创建时间
	*/
	public void setCreate_time(Timestamp create_time){
		this.create_time = create_time;
	}
	/**
	*获取创建时间
	*/
	public Timestamp getCreate_time(){
		return create_time;
	}
	/**
	*设置创建人
	*/
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}
	/**
	*获取创建人
	*/
	public Integer getEmployeeId(){
		return employeeId;
	}
}