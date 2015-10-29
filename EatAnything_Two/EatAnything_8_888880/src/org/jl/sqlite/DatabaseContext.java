package org.jl.sqlite;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * 
 * Class Name: DatabaseContext.java Function: ����֧�ֶԴ洢��SD���ϵ����ݿ�ķ��� Modifications:
 * 
 * @author ��־�� DateTime 2013-12-11 ����7:02:24
 * @version 1.0
 */
public class DatabaseContext extends ContextWrapper {
	private String dbDir;
	private String dbPath;

	/**
	 * 
	 * @param base
	 */
	public DatabaseContext(Context base) {
		super(base);
	}

	/**
	 * 
	 * Function: ������ݿ�·������������ڣ��򴴽��������
	 * 
	 * @author ��־�� DateTime 2014-3-22 ����3:28:48
	 * @return String
	 */
	public static String getDbDir() {
		String str = android.os.Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		// �������ݿ��ļ���
		str += "/xzgdatabase";
		return str;
	}

	// ���ݿ�·��
	public static String getDbPath() {
		String str = getDbDir() + "/";
		return str;
	}

	@Override
	public File getDatabasePath(String name) {
		// �ж��Ƿ����sd��
		boolean sdExist = android.os.Environment.MEDIA_MOUNTED
				.equals(android.os.Environment.getExternalStorageState());
		if (!sdExist) {// ���������,
			Log.e("SD������", "2.SD�������ڣ������SD��");
			return null;
		} else {
			// ������ڻ�ȡsd��·��
			dbDir = getDbDir();// ���ݿ�����Ŀ¼
			dbPath = getDbPath() + name;// ���ݿ�·��
			// �ж�Ŀ¼�Ƿ���ڣ��������򴴽���Ŀ¼
			File dirFile = new File(dbDir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// ���ݿ��ļ��Ƿ񴴽��ɹ�
			boolean isFileCreateSuccess = false;
			// �ж��ļ��Ƿ���ڣ��������򴴽����ļ�
			File dbFile = new File(dbPath);
			if (!dbFile.exists()) {
				try {

					isFileCreateSuccess = dbFile.createNewFile();// �����ļ�
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				isFileCreateSuccess = true;
			}
			// �������ݿ��ļ�����
			if (isFileCreateSuccess) {
				return dbFile;
			} else
				return null;
		}
	}

	/**
	 * ���������������������SD���ϵ����ݿ�ģ�android 2.3�����»�������������
	 * 
	 * @param name
	 * @param mode
	 * @param factory
	 */
	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode,
			SQLiteDatabase.CursorFactory factory) {
		SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(
				getDatabasePath(name), null);
		return result;
	}

	/**
	 * Android 4.0����ô˷�����ȡ���ݿ⡣
	 * 
	 * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
	 *      int, android.database.sqlite.SQLiteDatabase.CursorFactory,
	 *      android.database.DatabaseErrorHandler)
	 * @param name
	 * @param mode
	 * @param factory
	 * @param errorHandler
	 */
	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode,
			CursorFactory factory, DatabaseErrorHandler errorHandler) {
		SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(
				getDatabasePath(name), null);
		return result;
	}
}