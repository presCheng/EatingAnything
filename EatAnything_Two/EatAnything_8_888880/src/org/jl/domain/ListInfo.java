package org.jl.domain;

public class ListInfo {
	// 该view的id
	private int id;
	private int img_home;
	private int img_car;
	private int img_phone;
	private int img_song;
	private int img_shou;
	private String telNum1;
	private String telNum2;
	private String telNum3;
	private String restaurant;
	private String sortLetters; // 显示数据拼音的首字母

	public ListInfo() {
	}

	public ListInfo(int id, int img_home, int img_car, int img_phone,
			String telNum1, String restaurant) {
		this.id = id;
		this.img_home = img_home;
		this.img_car = img_car;
		this.img_phone = img_phone;
		this.telNum1 = telNum1;
		this.restaurant = restaurant;
	}

	public ListInfo(int id, int img_home, int img_car, int img_phone,
			String telNum1, String telNum2, String restaurant) {
		this.id = id;
		this.img_home = img_home;
		this.img_car = img_car;
		this.img_phone = img_phone;
		this.telNum1 = telNum1;
		this.telNum2 = telNum2;
		this.restaurant = restaurant;
	}

	public ListInfo(int id, int img_home, int img_car, int img_phone,
			String telNum1, String telNum2, String telNum3, String restaurant) {
		this.id = id;
		this.img_home = img_home;
		this.img_car = img_car;
		this.img_phone = img_phone;
		this.telNum1 = telNum1;
		this.telNum2 = telNum2;
		this.telNum3 = telNum3;
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImg_home() {
		return img_home;
	}

	public void setImg_home(int img_home) {
		this.img_home = img_home;
	}

	public int getImg_car() {
		return img_car;
	}

	public void setImg_car(int img_car) {
		this.img_car = img_car;
	}

	public void setImg_song(int img_song) {
		this.img_song = img_song;
	}

	public void setImg_shou(int img_shou) {
		this.img_shou = img_shou;
	}

	public int getImg_song() {
		return img_song;
	}

	public int getImg_shou() {
		return img_shou;
	}

	public int getImg_phone() {
		return img_phone;
	}

	public void setImg_phone(int img_phone) {
		this.img_phone = img_phone;
	}

	public String getTelNum1() {
		return telNum1;
	}

	public void setTelNum1(String telNum1) {
		this.telNum1 = telNum1;
	}

	public String getTelNum2() {
		return telNum2;
	}

	public void setTelNum2(String telNum2) {
		this.telNum2 = telNum2;
	}

	public String getTelNum3() {
		return telNum3;
	}

	public void setTelNum3(String telNum3) {
		this.telNum3 = telNum3;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
