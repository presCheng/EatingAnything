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
 * 		从sqlite数据库中拷贝数据
 *     Modifications:   
 *  
 *  @author 徐志国  DateTime 2014-3-22 下午2:41:06    
 *  @version 1.0
 */
public class CopySqliteForSdCard {
	InputStream input;// 复制的sqlite数据库目标
	String dbDir;// 数据库所在目录
	String dbPath;// 数据库路径
	/**
	 * 复制raw中的sql文件到应用的sd卡中
	 * 
	 * @param input
	 *            复制的sqlite数据库目标
	 * @param dbDir
	 *            数据库所在目录
	 * @param dbPath
	 *            数据库路径
	 * */
	public CopySqliteForSdCard(InputStream input, String dbDir, String dbPath) {
		this.input = input;
		this.dbDir = dbDir;
		this.dbPath = dbPath;
	}

	/**
	 * 
	 *  Function:
	 * 		复制sql文件的方法
	 *  @author 徐志国  DateTime 2013-12-11 下午7:00:13
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
			// 创建BufferedInputStream 对象
			bufferedInput = new BufferedInputStream(input);
			bufferedOnput = new BufferedOutputStream(new FileOutputStream(
					dbFile));
			int bytesRead = 0;
			// 从文件中按字节读取内容，到文件尾部时read方法将返回-1
			while ((bytesRead = bufferedInput.read(buffer)) != -1) {
				// 将读取的字节转为字符串对象
				bufferedOnput.write(buffer,0,bytesRead);
				bufferedOnput.flush();
				Log.i("nonstand", bytesRead+"");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭 BufferedInputStream
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
