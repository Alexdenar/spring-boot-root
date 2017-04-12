package com.hotdog.springboot.common.util;

import java.util.UUID;

/**
 * 工具类
 * 项目名称：basestation-common   
 * 类名称：Common   
 * 类描述：   
 * 创建人：郭辰 
 * 创建时间：2016-4-22 下午5:27:54   
 * 修改人：hisign   
 * 修改时间：2016-4-22 下午5:27:54   
 * 修改备注：   
 * @version   3.1
 */
public class Common {

	/**
	 * 
	 * 方法功能说明：  生成32位编码
	 * 创建：2016-1-5 by hotdog   
	 * @return String     
	 * @throws
	 */
    public static String getUUID(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    }
    
    /**
     * 
     * 方法功能说明：  去掉字符串前后空格  包括全角 半角
     * 创建：2016-3-15 by hotdog   
     * @参数： @param str
     * @参数： @return      
     * @return String     
     * @throws
     */
    public static String trim(String str){
    	//这个字符串就是你拿到的含有全角空格的字符串。
    	if(str!=null){
    		str = str.trim(); 
    		while(str.startsWith("　")){ 
    			str = str.substring(1,str.length()).trim();  
    		}  
    		while(str.endsWith("　")){  
    			str = str.substring(0,str.length()-1).trim();  
    		}
    		return str ;
    	}else{
    		return "";
    	}
    }
}