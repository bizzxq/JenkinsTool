
import model.JenkinsServerSession;
import static utils.StringUtils.*;

/**
 * @author zhouxq
 * @date 2017-2-9
 * @time 18:10
 * description :
 */



public class JenkinsToolMain {

    public static void main(String args[]){
        if (args.length!=2){
            System.out.println("Your args is illegel,please input again ! For example:");
            System.out.println("http://localhost:8080 command=jobname");
            System.out.println("http://localhost:8080?username:password command=jobname");
        }
        else{
            JenkinsServerSession jenkinsServerSession=new JenkinsServerSession(getConnectionInfo(args[0]));
            jenkinsServerSession.execCommand(getCommand(args[1]));
        }
    }


}
