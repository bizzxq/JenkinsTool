package model;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.JenkinsTriggerHelper;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import enums.JobStatus;
import org.apache.log4j.Logger;
import utils.LogUtils;


import java.io.IOException;


/**
 * Jenkins 任务类
 * @author zhouxq
 * @date 2017-2-13
 * @time 13:59
 * description :
 */
public class JenkinsJob {

    private String jobName = null;

    private Job job = null;

    private static Logger logger = LogUtils.getLogger(JenkinsJob.class);

    JenkinsJob(Job job) {
        this.job = job;
        this.jobName = getJobDetails().getName();
    }

    /**
     * 触发构建，不会等待构建完成
     */
    void build() {
        try {
            logger.debug("start to build job [" + jobName + "]!");
            job.build();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 触发构建并等待构建完成
     * @param jenkinsServer
     * @return
     */
    String buildAndWaitUntilFinished(JenkinsServer jenkinsServer) {
        BuildWithDetails buildWithDetails = null;
        try {
            logger.debug("Start to build job [" + jobName + "]!");
            buildWithDetails = new JenkinsTriggerHelper(jenkinsServer).triggerJobAndWaitUntilFinished(jobName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        String buildResult = buildWithDetails.getResult().toString();
        logger.debug("Job [" + jobName + "] building finished,result is [" + buildResult + "]");
        return buildResult;
    }

    /**
     * 停止在构建中的任务，重新构建。如果过已在队列中，则不会重新触发
     */
    void rebuild() {
        if (isBuilding()){
            stop();
        }
        build();
    }

    /**
     * 停止在构建中的任务，重新构建。如果过已在队列中，则不会重新触发
     * @param jenkinsServer
     */
    void rebuildAndWaitUntilFinished(JenkinsServer jenkinsServer) {
        if (isBuilding()){
            stop();
        }
        buildAndWaitUntilFinished(jenkinsServer);
    }

    /**
     * 停止正在构建的任务
     */
    void stop() {
        try {
            if (isBuilding()) {
                logger.debug("start to stop " + getJobDetails().getLastBuild().getNumber());
                getJobDetails().getLastBuild().Stop();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassCastException cce) {
        }
    }

    void cancel() {
        if (isInqueue()) {
            logger.debug("start to cancel " + getJobDetails().getQueueItem().getId());
            //todo:还没有找到取消队列中的方法
        }
    }

    /**
     * 获取任务详情
     * @return
     */
    JobWithDetails getJobDetails() {
        JobWithDetails jobWithDetails = null;
        try {
            jobWithDetails = job.details();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return jobWithDetails;
    }

    /**
     * 获取任务状态
     * @return
     */
    JobStatus getJobStatus() {
        JobWithDetails jobWithDetails = getJobDetails();
        JobStatus status = null;
        try {
            if (jobWithDetails.isInQueue()) {
                status = JobStatus.INQUEUE;
                if (jobWithDetails.getLastBuild().details().getResult() == null)
                    status = JobStatus.INQUEUE_BUILDING;
            } else if (jobWithDetails.getLastBuild().details().getResult() == null) {
                status = JobStatus.BUILDING;
            } else {
                status = JobStatus.STOPPED;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return status;
    }

    /**
     * 是否在队列中
     * @return
     */
    boolean isInqueue() {
        boolean isInqueue = false;
        if (getJobStatus()==JobStatus.INQUEUE || getJobStatus()==JobStatus.INQUEUE_BUILDING)
            isInqueue = true;
        return isInqueue;
    }

    /**
     * 是否正在构建
     * @return
     */
    boolean isBuilding() {
        boolean isBuilding = false;
        if (getJobStatus()==JobStatus.BUILDING || getJobStatus()==JobStatus.INQUEUE_BUILDING)
            isBuilding = true;
        return isBuilding;
    }
}
