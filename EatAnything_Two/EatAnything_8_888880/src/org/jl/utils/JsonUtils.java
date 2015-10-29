package org.jl.utils;

import java.util.ArrayList;
import java.util.List;

import org.jl.domain.Image;
import org.jl.domain.MenuName;
import org.jl.domain.Restaurant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * Class Name: JsonTools.java Function: Json解析与封装工具 Modifications:
 * 
 * @author 徐志国 DateTime 2014-4-8 上午8:27:56
 * @version 1.0
 */
public class JsonUtils {

	/**
	 * 
	 * Function: 解析接收到Restaurant的json 返回一个Restaurant对象
	 * 
	 * @author 徐志国 DateTime 2014-3-12 下午7:54:39
	 * @param jsonString
	 * @return Restaurant
	 */

	public static Restaurant getRestaurant(String jsonString) {
		Restaurant r = new Restaurant();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject rJsonObject = jsonObject.getJSONObject("Restaurant");
			r.setId(rJsonObject.getString("id"));
			r.setName(rJsonObject.getString("name"));
			r.setTel(rJsonObject.getString("tel"));
			r.setTel2(rJsonObject.getString("tel2"));
			r.setTel3(rJsonObject.getString("tel3"));
			r.setAddr(rJsonObject.getString("addr"));
			r.setDescript(rJsonObject.getString("descript"));
			r.setFlag(rJsonObject.getString("flag"));
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		return r;
	}

	/**
	 * 
	 * Function: 返回list数组 传入指定id餐馆的饭菜
	 * 
	 * @author 徐志国 DateTime 2014-3-12 下午8:58:41
	 * @param key
	 * @param jsonString
	 * @return
	 */

	public static List<MenuName> getMenuName(String key, String jsonString) {
		List<MenuName> list = new ArrayList<MenuName>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONArray mJsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < mJsonArray.length(); i++) {
				JSONObject jsonObject2 = mJsonArray.getJSONObject(i);
				MenuName m = new MenuName();
				m.setId(jsonObject2.getString("id"));
				m.setName(jsonObject2.getString("name"));
				m.setMoney(jsonObject2.getString("money"));
				m.setR_id(jsonObject2.getString("r_id"));
				m.setFlag(jsonObject2.getString("flag"));
				m.setDescript(jsonObject2.getString("descript"));
				list.add(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * Function: 返回list数组 或者查饭馆返回的json
	 * 
	 * @author 徐志国 DateTime 2014-3-12 下午8:58:41
	 * @param key
	 * @param jsonString
	 * @return
	 */

	public static List<Restaurant> getRestaurant(String key, String jsonString) {
		List<Restaurant> list = new ArrayList<Restaurant>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONArray mJsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < mJsonArray.length(); i++) {
				JSONObject jsonObject2 = mJsonArray.getJSONObject(i);
				Restaurant r = new Restaurant();
				r.setId(jsonObject2.getString("id"));
				r.setName(jsonObject2.getString("name"));
				r.setTel(jsonObject2.getString("tel"));
				r.setTel2(jsonObject2.getString("tel2"));
				r.setTel3(jsonObject2.getString("tel3"));
				r.setAddr(jsonObject2.getString("addr"));
				r.setDescript(jsonObject2.getString("descript"));
				r.setFlag(jsonObject2.getString("flag"));
				list.add(r);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * Function: 封装List<Restaurant> json
	 * 
	 * @param list
	 * @return
	 */
	public static String createJson(List<Restaurant> list) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			for (int i = 0; i < list.size(); i++) {
				Restaurant res = list.get(i);
				JSONObject resObject = new JSONObject();
				resObject.put("id", res.getId());
				resObject.put("name", res.getName());
				resObject.put("tel", res.getTel());
				resObject.put("tel", res.getTel());
				resObject.put("tel2", res.getTel2());
				resObject.put("tel3", res.getTel3());
				resObject.put("addr", res.getAddr());
				resObject.put("pic", res.getPic());
				resObject.put("descript", res.getDescript());
				resObject.put("flag", res.getFlag());
				array.put(resObject);
			}
			object.put("Restaurant", array);
		} catch (Exception e) {
		}
		return object.toString();
	}

	/**
	 * 
	 * Function: 封装List<MenuName> json
	 * 
	 * @param list
	 * @return
	 */
	public static String createMenuJson(List<MenuName> list) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			for (int i = 0; i < list.size(); i++) {
				MenuName res = list.get(i);
				JSONObject resObject = new JSONObject();
				resObject.put("id", res.getId());
				resObject.put("name", res.getName());
				resObject.put("money", res.getMoney());
				resObject.put("r_id", res.getR_id());
				resObject.put("flag", res.getFlag());
				resObject.put("descript", res.getDescript());
				array.put(resObject);
			}
			object.put("MenuName", array);
		} catch (Exception e) {
		}
		return object.toString();
	}

	/**
	 * 
	 * Function: 封装List<MenuName> json
	 * 
	 * @param list
	 * @return
	 */
	public static String createResJson(Restaurant res) {
		JSONObject object = new JSONObject();
		try {
			JSONObject resObject = new JSONObject();
			resObject.put("id", res.getId());
			resObject.put("name", res.getName());
			resObject.put("tel", res.getTel());
			resObject.put("tel", res.getTel());
			resObject.put("tel2", res.getTel2());
			resObject.put("tel3", res.getTel3());
			resObject.put("addr", res.getAddr());
			resObject.put("pic", res.getPic());
			resObject.put("descript", res.getDescript());
			resObject.put("flag", res.getFlag());
			// array.put(resObject);
			object.put("Restaurant", resObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object.toString();
	}

	/**
	 * 根据key 解析image 的 json
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Image> getImageList(String key, String jsonString) {
		List<Image> list = new ArrayList<Image>();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONArray mJsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < mJsonArray.length(); i++) {
				JSONObject singleJsonObject = mJsonArray.getJSONObject(i);
				Image image = new Image();
				image.setUrl(singleJsonObject.getString("url"));
				image.setR_id(singleJsonObject.getString("r_id"));
				image.setDescript(singleJsonObject.getString("descript"));

				list.add(image);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
