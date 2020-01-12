package cn.stylefeng.guns.core.util;

import cn.stylefeng.roses.core.util.ToolUtil;

import java.util.HashMap;
import java.util.Map;

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
    public static Map<String, String> getDomainAndKeyByUrl(String url) {
        if (ToolUtil.isNotEmpty(url)) {
            Map<String, String> map = new HashMap<>();
            String[] strings = url.split("/");
            if (strings.length < 2) {
                return null;
            }
            map.put("domain", strings[2]);
            map.put("qiNiuKey", strings[strings.length - 1]);
            return map;
        }
        return null;
    }

}
