package com.chen.crm.settings.domain;

public class User {
	private String id;
	private String loginAct;//��¼�˻�
	private String name;
	private String loginPwd;//��¼����
	private String email;
	private String expireTime;//ʧЧʱ��
	private String lockState;//�����˻� 0������ 1������
	private String deptno;//���ű��
	private String allowIps;//������ʵ�ip��ַ
	private String createTime;//����ʱ��
	private String createBy;//������
	private String editTime;//�޸�ʱ��
	private String editBy;//�޸���
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginAct() {
		return loginAct;
	}
	public void setLoginAct(String loginAct) {
		this.loginAct = loginAct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getLockState() {
		return lockState;
	}
	public void setLockState(String lockState) {
		this.lockState = lockState;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getAllowIps() {
		return allowIps;
	}
	public void setAllowIps(String allowIps) {
		this.allowIps = allowIps;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getEditBy() {
		return editBy;
	}
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
