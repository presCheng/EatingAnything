package org.jl.domain;

public class MenuName {
	private String id;
	private String name;
	private String money;
	private String r_id;
	private String flag;
	private String descript;

	public MenuName() {
	}

	public MenuName(String id, String name, String money, String r_id,
			String flag, String descript) {
		this.id = id;
		this.name = name;
		this.money = money;
		this.r_id = r_id;
		this.flag = flag;
		this.descript = descript;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "MenuName [id=" + id + ", name=" + name + ", money=" + money
				+ ", r_id=" + r_id + ", flag=" + flag + ", descript="
				+ descript + "]";
	}

}
