package model;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import enums.Command;
import org.apache.log4j.Logger;
import utils.LogUtils;


import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * JenkinsServer 类
 * @author zhouxq
 * @date 2017-2-13
 * @time 13:57
 * description :
 */
public class JenkinsServerSession {

    private JenkinsServer jenkinsServer=null;

    private static Logger logger= LogUtils.getLogger(JenkinsServerSession.class);

    private static URI uri=URI.create("http://localhost:8080");

    public JenkinsServerSession(String connectionStr){
        uri=URI.create(connectionStr);
        jenkinsServer=new JenkinsServer(uri);
    }

    public JenkinsServerSession(String connectionStr,String userName,String password){
        uri=URI.create(connectionStr);
        jenkinsServer=new JenkinsServer(uri,userName,password);
    }

    public JenkinsServerSession(Map<String,String> connectionMap){
        if (connectionMap.keySet().size()==1){
            uri=URI.create(connectionMap.get("Connection"));
            jenkinsServer=new JenkinsServer(uri);
        }
        else if (connectionMap.keySet().size()==3){
            uri=URI.create(connectionMap.get("Connection"));
            jenkinsServer=new JenkinsServer(uri,connectionMap.get("UserName"),connectionMap.get("Password"));
        }
    }

    /**
     * 根据名称获取job对象
     * @param jobName
     * @return
     */
    public Job getJob(String jobName){
        Job job=null;
        try {
            job=jenkinsServer.getJob(jobName);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return job;
    }

    /**
     * 执行命令
     * @param commandMap keys:Command 执行的命令，JobName 任务名称，可用StringUtils的getCommand获取
     */
    public void execCommand(Map<String,String> commandMap){
        String commandStr=commandMap.get("Command");
        String jobName=commandMap.get("JobName");
        JenkinsJob jenkinsJob=new JenkinsJob(getJob(jobName));
        if (commandStr.equals(Command.build.toString()))
            jenkinsJob.build();
        else if (commandStr.equals(Command.rebuild.toString()))
            jenkinsJob.rebuild();
        else if (commandStr.equals(Command.buildwait.toString()))
            jenkinsJob.buildAndWaitUntilFinished(jenkinsServer);
        else if (commandStr.equals(Command.rebuildwait.toString()))
            jenkinsJob.rebuildAndWaitUntilFinished(jenkinsServer);
        else if (commandStr.equals(Command.stop.toString()))
            jenkinsJob.stop();
        else
            logger.error("Command [" + commandStr + "] is illegal,please input again!" );
    }


}
