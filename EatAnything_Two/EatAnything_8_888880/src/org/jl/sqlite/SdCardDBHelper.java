package org.jl.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 *  Class Name: SdCardDBHelper.java
 *  Function:
 * 		���ݿ�����ά���� 
 *     Modifications:   
 *  
 *  @author ��־��  DateTime 2014-3-22 ����3:03:03    
 *  @version 1.0
 */
public class SdCardDBHelper extends SQLiteOpenHelper{
    
    public static final String TAG = "SdCardDBHelper";
    /**
     * ���ݿ�汾
    **/
    public static int VERSION = 1;
    /**
     * ���캯��
     * 
     * @param    context �����Ļ���
    **/
    public SdCardDBHelper(Context context,String name,int version){
        super(context, name, null, version);
    } 
    /**
     * ���캯��
     * 
     * @param    context �����Ļ���
    **/
    public SdCardDBHelper(Context context,String name){
        this(context,name,VERSION);
    }
     
    /**
     * �������ݿ�ʱ�������������ߴ洢����Ҫ�����ݿ��
     *
     * @param    db
    **/
    @Override
    public void onCreate(SQLiteDatabase db) {
         Log.e(TAG, "��ʼ�������ݿ��");             
    }
    
    /** �������ݿ�ʱ������
     *
     * @param    db
     * @param    oldVersion
     * @param    newVersion
    **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	 Log.e(TAG, "�������ݿ�");          
     }
}
