package org.jl.domain;

/**
 * ²ÍÌübean
 * 
 * @author acer
 * 
 */
public class Restaurant {
	private String id;
	private String name;
	private String tel;
	private String tel2;
	private String tel3;
	private String addr;
	private String pic;
	private String descript;
	private String flag;

	public Restaurant() {
	}

	public Restaurant(String id, String name, String tel, String tel2,
			String tel3, String addr, String pic, String descript, String flag) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.addr = addr;
		this.pic = pic;
		this.descript = descript;
		this.flag = flag;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", tel=" + tel
				+ ", tel2=" + tel2 + ", tel3=" + tel3 + ", addr=" + addr
				+ ", pic=" + pic + ", descript=" + descript + ", flag=" + flag
				+ "]";
	}

}
