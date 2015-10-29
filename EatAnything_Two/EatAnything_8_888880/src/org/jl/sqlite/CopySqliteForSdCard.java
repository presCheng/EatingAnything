package org.jl.sqlite;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;
/**
 * 
 *  Class Name: CopySqliteForSdCard.java
 *  Function:
 * 		��sqlite���ݿ��п�������
 *     Modifications:   
 *  
 *  @author ��־��  DateTime 2014-3-22 ����2:41:06    
 *  @version 1.0
 */
public class CopySqliteForSdCard {
	InputStream input;// ���Ƶ�sqlite���ݿ�Ŀ��
	String dbDir;// ���ݿ�����Ŀ¼
	String dbPath;// ���ݿ�·��
	/**
	 * ����raw�е�sql�ļ���Ӧ�õ�sd����
	 * 
	 * @param input
	 *            ���Ƶ�sqlite���ݿ�Ŀ��
	 * @param dbDir
	 *            ���ݿ�����Ŀ¼
	 * @param dbPath
	 *            ���ݿ�·��
	 * */
	public CopySqliteForSdCard(InputStream input, String dbDir, String dbPath) {
		this.input = input;
		this.dbDir = dbDir;
		this.dbPath = dbPath;
	}

	/**
	 * 
	 *  Function:
	 * 		����sql�ļ��ķ���
	 *  @author ��־��  DateTime 2013-12-11 ����7:00:13
	 */
	public void copyFile() {
		BufferedInputStream bufferedInput = null;
		BufferedOutputStream bufferedOnput = null;
		byte[] buffer = new byte[1024];
		File dirFile = new File(dbDir);
		File dbFile = new File(dbPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		try {
			if (!dbFile.exists()) {
				dbFile.createNewFile();
			}
			// ����BufferedInputStream ����
			bufferedInput = new BufferedInputStream(input);
			bufferedOnput = new BufferedOutputStream(new FileOutputStream(
					dbFile));
			int bytesRead = 0;
			// ���ļ��а��ֽڶ�ȡ���ݣ����ļ�β��ʱread����������-1
			while ((bytesRead = bufferedInput.read(buffer)) != -1) {
				// ����ȡ���ֽ�תΪ�ַ�������
				bufferedOnput.write(buffer,0,bytesRead);
				bufferedOnput.flush();
				Log.i("nonstand", bytesRead+"");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر� BufferedInputStream
			try {
				if (bufferedInput != null) {
					bufferedInput.close();
					bufferedOnput.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
