package org.jl.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 *  Class Name: SdCardDBHelper.java
 *  Function:
 * 		数据库管理和维护类 
 *     Modifications:   
 *  
 *  @author 徐志国  DateTime 2014-3-22 下午3:03:03    
 *  @version 1.0
 */
public class SdCardDBHelper extends SQLiteOpenHelper{
    
    public static final String TAG = "SdCardDBHelper";
    /**
     * 数据库版本
    **/
    public static int VERSION = 1;
    /**
     * 构造函数
     * 
     * @param    context 上下文环境
    **/
    public SdCardDBHelper(Context context,String name,int version){
        super(context, name, null, version);
    } 
    /**
     * 构造函数
     * 
     * @param    context 上下文环境
    **/
    public SdCardDBHelper(Context context,String name){
        this(context,name,VERSION);
    }
     
    /**
     * 创建数据库时触发，创建离线存储所需要的数据库表
     *
     * @param    db
    **/
    @Override
    public void onCreate(SQLiteDatabase db) {
         Log.e(TAG, "开始创建数据库表");             
    }
    
    /** 更新数据库时触发，
     *
     * @param    db
     * @param    oldVersion
     * @param    newVersion
    **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	 Log.e(TAG, "更新数据库");          
     }
}
