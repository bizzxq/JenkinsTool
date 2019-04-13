package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * String 工具类
 * @author zhouxq
 * @date 2017-2-13
 * @time 14:03
 * description :
 */
public class StringUtils {

    /**
     * 解析传入的命令，存在map里
     * @param str
     * @return map包含keys:Command、JobName
     */
    public static Map<String,String> getCommand(String str){
        String[] commands=str.split("=");
        Map<String,String> commandMap=new HashMap<String, String>();
        int comlength=commands.length;
        if (comlength==2){
            commandMap.put("Command",commands[0].toLowerCase());
            commandMap.put("JobName",commands[1]);
        }
        else{
            System.out.println("Command is illegal,please input again!");
        }
        return commandMap;
    }

    /**
     * 解析传入的连接字符串，存在map里
     * @param str
     * @return map包含keys:Connection、UserName、Password
     */
    public static Map<String,String> getConnectionInfo(String str){
        String[] connectionInfo=str.split("\\?");
        Map<String,String> connectionInfoMap=new HashMap<String, String>();
        int length=connectionInfo.length;
        if (length==1)
            connectionInfoMap.put("Connection",connectionInfo[0]);
        else if (length==2){
            connectionInfoMap.put("Connection",connectionInfo[0]);
            connectionInfoMap.put("UserName",connectionInfo[1].split(":")[0]);
            connectionInfoMap.put("Password",connectionInfo[1].split(":")[1]);
        }
        else{
            System.out.println("Connection string is illegal,please input again!");
        }
        return connectionInfoMap;
    }


}
