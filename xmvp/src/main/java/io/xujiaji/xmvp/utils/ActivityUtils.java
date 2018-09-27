package io.xujiaji.xmvp.utils;

/**
 * Activity工具类 <br />
 * Activity Tool class
 */
public class ActivityUtils {
    private static long exitTime = 0;

    /**
     * 作用：2s内点击两次返回则退出程序 <br /> Function: click twice within 2s to exit the program
     *
     * @return 是否退出程序 <br /> whether to quit the program
     */
    public static boolean exitBy2Click() {
        return exitBy2Click(2000);
    }

    /**
     * @param space 两次点击时间间隔 <br /> custom interval time
     */
    public static boolean exitBy2Click(int space) {
        if ((System.currentTimeMillis() - exitTime) > space) {
            exitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }

}
