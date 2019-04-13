package enums;

/**
 * 命令枚举
 * 1、build 触发一次构建
 * 2、rebuild 触发一次构建，如果正在构建，则先停止再触发构建
 * 3、buildwait 触发一次构建并等待构建完成
 * 4、rebuildwait 触发一次构建并等待构建完成，如果正在构建，则先停止再触发构建
 * 5、stop 停止正在进行的构建
 * 如果已有构建在队列中，则不会触发新的构建
 * @author zhouxq
 * @date 2017-2-15
 * @time 17:49
 * description :
 */
public enum Command {
    build,rebuild,buildwait,rebuildwait,stop
}
