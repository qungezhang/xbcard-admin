/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class WxUserWarpper extends BaseControllerWrapper {

    public WxUserWarpper(Map<String, Object> single) {
        super(single);
    }

    public WxUserWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public WxUserWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public WxUserWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Integer sex = (Integer) map.get("sex");
        map.put("sexName", sex == 1 ? "男" : "女");
        Integer isvip = (Integer) map.get("isvip");
        map.put("isVipName", isvip == 1 ? "是" : "否");
        String addressAll = "";
        if (ToolUtil.isNotEmpty(map.get("country"))) {
            addressAll = addressAll + map.get("country") + "-";
        }
        if (ToolUtil.isNotEmpty(map.get("province"))) {
            addressAll = addressAll + map.get("province") + "-";
        }
        if (ToolUtil.isNotEmpty(map.get("city"))) {
            addressAll = addressAll + map.get("city");
        }
        map.put("addressAll", addressAll);

    }

}
