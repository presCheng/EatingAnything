package org.jl.domain;

public class Image {
	private String id;
	private String r_id;
	private String url;
	private String descript;

	public Image() {
	}

	public Image(String id, String r_id, String url, String descript) {
		this.id = id;
		this.r_id = r_id;
		this.url = url;
		this.descript = descript;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
