package org.jl.utils;

import java.util.ArrayList;
import java.util.List;

import org.jl.domain.Image;
import org.jl.domain.MenuName;
import org.jl.domain.Restaurant;
import org.jl.exception.CompleteException;
import org.jl.sqlite.DatabaseContext;
import org.jl.sqlite.SdCardDBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * Class Name: SqliteUtils.java Function: Sqlite���ӹ��� Modifications:
 * 
 * @author ��־�� DateTime 2014-4-8 ����8:28:15
 * @version 1.0
 */
public class SqliteUtils {
	/**
	 * 
	 * Function: ����sqlite
	 * 
	 * @param context
	 * @return SQLiteDatabase
	 */
	public static SQLiteDatabase getConn(Context context) {
		String dataBase_name;
		SQLiteDatabase sql;
		dataBase_name = "eatanything.db3";
		DatabaseContext dbContext = new DatabaseContext(context);
		SdCardDBHelper dbHelper = new SdCardDBHelper(dbContext, dataBase_name,
				3);
		sql = dbHelper.getWritableDatabase();
		return sql;
	}

	/**
	 * 
	 * Function: �������ݿ�����
	 * 
	 * @author ��־�� DateTime 2014-4-8 ����8:37:46
	 * @param context
	 * @param sqlStr
	 * @return
	 */
	public static List<MenuName> getMenuDataBaseList(Context context,
			String sqlStr, int id, String flag) {
		SQLiteDatabase sql = SqliteUtils.getConn(context);
		Cursor cursor = sql.rawQuery(sqlStr,
				new String[] { Integer.toString(id), flag });
		List<MenuName> list = new ArrayList<MenuName>();
		while (cursor.moveToNext()) {
			MenuName menu = new MenuName();
			menu.setId(cursor.getString(cursor.getColumnIndex("id")));
			menu.setName(cursor.getString(cursor.getColumnIndex("name")));
			menu.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			menu.setR_id(cursor.getString(cursor.getColumnIndex("r_id")));
			menu.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
			menu.setDescript(cursor.getString(cursor.getColumnIndex("descript")));
			list.add(menu);
		}
		cursor.close();
		sql.close();
		return list;
	}

	/**
	 * 
	 * Function: �������ݿ�����
	 * 
	 * @author ��־�� DateTime 2014-4-8 ����8:37:46
	 * @param context
	 * @param sqlStr
	 * @return
	 */
	public static List<MenuName> getMenuDataBaseList(Context context,
			String sqlStr, int id) {
		SQLiteDatabase sql = SqliteUtils.getConn(context);
		Cursor cursor = sql.rawQuery(sqlStr,
				new String[] { Integer.toString(id) });
		List<MenuName> list = new ArrayList<MenuName>();
		while (cursor.moveToNext()) {
			MenuName menu = new MenuName();
			menu.setId(cursor.getString(cursor.getColumnIndex("id")));
			menu.setName(cursor.getString(cursor.getColumnIndex("name")));
			menu.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			menu.setR_id(cursor.getString(cursor.getColumnIndex("r_id")));
			menu.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
			menu.setDescript(cursor.getString(cursor.getColumnIndex("descript")));
			list.add(menu);
		}
		cursor.close();
		sql.close();
		return list;
	}

	/**
	 * 
	 * Function: �������ݿ�����
	 * 
	 * @author ��־�� DateTime 2014-4-8 ����8:37:46
	 * @param context
	 * @return
	 */
	public static Restaurant getResDataBaseList(Context context,
			int id) {
		SQLiteDatabase sql = SqliteUtils.getConn(context);
		Restaurant res = new Restaurant();
		Cursor cursor = sql.rawQuery("select * from restaurant where id = ?",
				new String[] { Integer.toString(id) });
		cursor.moveToNext();
		res.setId(cursor.getString(cursor.getColumnIndex("id")));
		res.setName(cursor.getString(cursor.getColumnIndex("name")));
		res.setTel(cursor.getString(cursor.getColumnIndex("tel")));
		res.setTel2(cursor.getString(cursor.getColumnIndex("tel2")));
		res.setTel3(cursor.getString(cursor.getColumnIndex("tel3")));
		res.setAddr(cursor.getString(cursor.getColumnIndex("addr")));
		res.setPic(cursor.getString(cursor.getColumnIndex("pic")));
		res.setDescript(cursor.getString(cursor.getColumnIndex("descript")));
		res.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
		cursor.close();
		sql.close();
		return res;
	}

	// ��������
	public static List<Restaurant> getResDataBaseList(Context context) {
		String sqlStr = "select * from restaurant";
		SQLiteDatabase sql = SqliteUtils.getConn(context);
		Cursor cursor = sql.rawQuery(sqlStr, new String[] {});
		List<Restaurant> list = new ArrayList<Restaurant>();
		while (cursor.moveToNext()) {
			Restaurant res = new Restaurant();
			res.setId(cursor.getString(cursor.getColumnIndex("id")));
			res.setName(cursor.getString(cursor.getColumnIndex("name")));
			res.setTel(cursor.getString(cursor.getColumnIndex("tel")));
			res.setTel2(cursor.getString(cursor.getColumnIndex("tel2")));
			res.setTel3(cursor.getString(cursor.getColumnIndex("tel3")));
			res.setAddr(cursor.getString(cursor.getColumnIndex("addr")));
			res.setPic(cursor.getString(cursor.getColumnIndex("pic")));
			res.setDescript(cursor.getString(cursor.getColumnIndex("descript")));
			res.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
			list.add(res);
		}
		cursor.close();
		sql.close();
		return list;
	}

	/**
	 * 
	 * Function: ��Restaurant���ݵ����ݿ� ��Ҫ�޸�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static void saveResDataBaseList(Context context, String resData) {
		List<Restaurant> listRes = JsonUtils.getRestaurant("Restaurant",
				resData);
		SQLiteDatabase db = SqliteUtils.getConn(context);
		// ��ǰid
		int point = Integer.parseInt(listRes.get(0).getId());
		// ѭ����
		int index = 0;
		// ���id
		int maxId = Integer.parseInt(listRes.get(listRes.size() - 1).getId());
		// ���鳤��
		int length = listRes.size();
		Cursor cursor = db.rawQuery("select id from restaurant", null);
		// ���lenght����cursor.getCount() �Ǿ�˵�����������и���
		if (length > cursor.getCount()) {
			// �����ǰ��ָ��С���������ݿ�����id�ž�һֱѭ��
			while (point < maxId) {
				point = Integer.parseInt(listRes.get(index).getId());
				cursor = db.rawQuery("select * from restaurant where id = ?",
						new String[] { Integer.toString(point) });
				// �ж�sqlite�����Ƿ���ڵ�ǰid������
				if (cursor.moveToNext() == false) {
					// ��������ھ�д���������
					Restaurant res = listRes.get(index);
					db.execSQL(
							"insert into restaurant values(?,?,?,?,?,?,?,?,?)",
							new String[] { res.getId(), res.getName(),
									res.getTel(), res.getTel2(), res.getTel3(),
									res.getAddr(), res.getPic(),
									res.getDescript(), res.getFlag() });
				}
				// ���index���������鳤��
				if (index < listRes.size()) {
					index++;
				}
				cursor.close();
			}
		} else {
			// ���point >= maxId ˵���������ݿ����������ݿ�����һ�£�����Ҫ����
			throw new CompleteException();
		}
		db.close();
	}

	/**
	 * 
	 * Function: ��menuname���ݵ����ݿ�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static void saveMenuDataBaseList(Context context, String menuData) {
		List<MenuName> listMenu = JsonUtils.getMenuName("MenuName", menuData);
		SQLiteDatabase sql = SqliteUtils.getConn(context);
		// ��ǰid
		int point = Integer.parseInt(listMenu.get(0).getId());
		// ѭ����
		int index = 0;
		// ���id
		int maxId = Integer.parseInt(listMenu.get(listMenu.size() - 1).getId());
		// ���鳤��
		int lenght = listMenu.size();
		Cursor cursor = sql.rawQuery("select id from menuname", null);
		if (lenght > cursor.getCount()) {
			sql.beginTransaction();
			while (point < maxId) {
				point = Integer.parseInt(listMenu.get(index).getId());
				Log.i("ok", index + "");
				cursor = sql.rawQuery("select * from menuname where id = ?",
						new String[] { Integer.toString(point) });// i+1 ��id��1��ʼ
				if (cursor.moveToNext() == false) {
					MenuName menu = listMenu.get(index);
					sql.execSQL(
							"insert into menuname values(?,?,?,?,?,?)",
							new String[] { menu.getId(), menu.getName(),
									menu.getMoney(), menu.getR_id(),
									menu.getFlag(), menu.getDescript() });
				}
				if (index < listMenu.size()) {
					index++;
				}
				cursor.close();
			}
			sql.setTransactionSuccessful();
			sql.endTransaction();
			sql.close();
		} else {
			// ���point >= maxId ˵���������ݿ����������ݿ�����һ�£�����Ҫ����
			throw new CompleteException();
		}
		sql.close();
	}

	/**
	 * 
	 * Function: ������ImageUrl�����ݿ� ��Ҫ�޸�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static void saveImgDataBaseList(Context context, String imgData) {
		if (imgData == null) {
			return;
		}
		List<Image> listImg = JsonUtils.getImageList("Imgurl", imgData);
		SQLiteDatabase db = SqliteUtils.getConn(context);
		db.execSQL("delete from imgurl");
		for (int i = 0; i < listImg.size(); i++) {
			Image img = listImg.get(i);
			db.execSQL("insert into imgurl values(?,?,?,?)",
					new String[] { img.getId(), img.getR_id(), img.getUrl(),
							img.getDescript() });
		}
		db.close();
	}

	/**
	 * 
	 * Function: ��ȡ����ImageUrl���ݿ� ��Ҫ�޸�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static List<Image> getImgDataBaseList(Context context) {
		String sqlStr = "select * from imgurl";
		SQLiteDatabase db = SqliteUtils.getConn(context);
		Cursor cursor = db.rawQuery(sqlStr, new String[] {});
		List<Image> list = new ArrayList<Image>();
		while (cursor.moveToNext()) {
			Image image = new Image();
			image.setId(cursor.getString(cursor.getColumnIndex("id")));
			image.setR_id(cursor.getString(cursor.getColumnIndex("r_id")));
			image.setUrl(cursor.getString(cursor.getColumnIndex("url")));
			image.setDescript(cursor.getString(cursor
					.getColumnIndex("descript")));
			list.add(image);
		}
		cursor.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * Function: ��ȡ����TopImageUrl���ݿ� ��Ҫ�޸�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static List<Image> getTopImgDataBaseList(Context context) {
		String sqlStr = "select * from topimgurl";
		SQLiteDatabase db = SqliteUtils.getConn(context);
		Cursor cursor = db.rawQuery(sqlStr, new String[] {});
		List<Image> list = new ArrayList<Image>();
		while (cursor.moveToNext()) {
			Image image = new Image();
			image.setId(cursor.getString(cursor.getColumnIndex("id")));
			image.setR_id(cursor.getString(cursor.getColumnIndex("r_id")));
			image.setUrl(cursor.getString(cursor.getColumnIndex("url")));
			image.setDescript(cursor.getString(cursor
					.getColumnIndex("descript")));
			list.add(image);
		}
		cursor.close();
		db.close();
		return list;
	}

	/**
	 * 
	 * Function: ������TopImageUrl�����ݿ� ��Ҫ�޸�
	 * 
	 * @author ��־�� DateTime 2014-4-9 ����12:49:31
	 * @return
	 */
	public static void saveTopImgDataBaseList(Context context, String imgData) {
		if (imgData == null) {
			return;
		}
		List<Image> listImg = JsonUtils.getImageList("Topimgurl", imgData);
		SQLiteDatabase db = SqliteUtils.getConn(context);
		db.execSQL("delete from topimgurl");
		for (int i = 0; i < listImg.size(); i++) {
			Image img = listImg.get(i);
			db.execSQL("insert into topimgurl values(?,?,?,?)",
					new String[] { img.getId(), img.getR_id(), img.getUrl(),
							img.getDescript() });
		}
		db.close();
	}

}
