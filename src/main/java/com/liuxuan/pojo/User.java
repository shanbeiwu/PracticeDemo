package com.liuxuan.pojo;
/**
 * 
 * @author liuyuxuan
 * ����Ϊpojo�࣬�������������Լ���������
 * �����ﶨ�����������ԣ�int ID��String name,String password
 * �ֱ��Ӧ���ݿ���ID��Ӧ����������
 * ���ҹ������set��get�����Լ�ȫ�����Ĺ��������޲ι�����
 * ����д��tostring����
 *
 */
public class User {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	@Override
	public String toString() {
		return "User [idΪ��" + id + ", �û���Ϊ��" + name + ", ����Ϊ��" + password
				+ "]";
	}
    
}