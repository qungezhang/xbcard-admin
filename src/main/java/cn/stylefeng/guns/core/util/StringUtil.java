package cn.stylefeng.guns.core.util;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/28 11:27
 */
public class StringUtil {

    public static String getQiniKeyByUrl(String url) {
        if (ToolUtil.isNotEmpty(url)) {
            String[] strings = url.split("/");
            return strings[strings.length - 1];
        }
        return null;
    }
}
