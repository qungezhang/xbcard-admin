package cn.stylefeng.guns.core.util;

import cn.hutool.core.util.IdUtil;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/7 20:38
 */
public class UUIDUtill {

    public static String generateStr() {
        return IdUtil.fastSimpleUUID();
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.objectId());

    }
}
