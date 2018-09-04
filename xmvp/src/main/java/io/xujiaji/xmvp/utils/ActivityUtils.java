package io.xujiaji.xmvp.utils;

/**
 * Activity工具类
 */
public class ActivityUtils {
    private static long exitTime = 0;

    /**
     * 作用如：2s内双击两次返回则退出程序
     *
     * @return 是否退出程序
     */
    public static boolean exitBy2Click() {
        return exitBy2Click(2000);
    }

    /**
     * 在某个时间段内双击两次
     *
     * @param space 两次点击最大时间间隔
     * @return 是否退出
     */
    public static boolean exitBy2Click(int space) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }

}
