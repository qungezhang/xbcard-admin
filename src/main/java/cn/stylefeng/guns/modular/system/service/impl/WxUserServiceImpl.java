package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.dto.CategoryTreeDTO;
import cn.stylefeng.guns.modular.dto.WxUserDto;
import cn.stylefeng.guns.modular.dto.WxUserTreeDto;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.dao.WxUserMapper;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信用户 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {


    @Override
    public WxUser getLoginWxUser() {
        return this.selectById(JwtTokenUtil.getUserId());
    }

    @Override
    public List<Map<String, Object>> selectUsers(String pCode ,Integer id) {

        return this.baseMapper.selectUsers(pCode,id);
    }

    @Override
    public List<WxUserDto> selectUsersObj() {
        return this.baseMapper.selectUsersObj();
    }

    @Override
    public WxUserTreeDto spacetreeUsers() {
        WxUserTreeDto treeDto = new WxUserTreeDto();
        treeDto.setId(0);
        treeDto.setName("销帮总部");
        WxUserDto userDto = new WxUserDto();
        Map<String, Object> statistics = this.baseMapper.selectStatistics();
        Object concatIsVipP = statistics.get("concatIsVip");
        Integer vipNumP =0;
        if (ToolUtil.isNotEmpty(concatIsVipP)) {
            String[] isVips = String.valueOf(concatIsVipP).split(",");
            for (String s : isVips) {
                if (s.equals("1")) {
                    vipNumP++;
                }
            }
        }
        Integer total = Integer.valueOf(statistics.get("total") + "");
        userDto.setChildCount(total);
        userDto.setVipNum(vipNumP);
        userDto.setOpNum(total - vipNumP);
        userDto.setHeadimgurl("https://img.xbdzmp.com/201912120156008d2a5bbcdba1.jpg");
        treeDto.setData(userDto);

        List<WxUserDto> wxUserDtos = this.baseMapper.selectUsersObj();
        if (ToolUtil.isNotEmpty(wxUserDtos)) {
            List<WxUserTreeDto> wxUserTreeDtos = new ArrayList<>();
            for (WxUserDto wxUserDto : wxUserDtos) {
                String concatIsVip = wxUserDto.getConcatIsVip();
                Integer vipNum =0;
                if (ToolUtil.isNotEmpty(concatIsVip)) {
                    String[] isVips = concatIsVip.split(",");
                    for (String s : isVips) {
                        if (s.equals("1")) {
                            vipNum++;
                        }
                    }
                }
                wxUserDto.setVipNum(vipNum);
                wxUserDto.setOpNum(wxUserDto.getChildCount()-vipNum);
                WxUserTreeDto userTreeDto = new WxUserTreeDto();
                userTreeDto.setId(wxUserDto.getId());
                userTreeDto.setName(wxUserDto.getMobile());
                userTreeDto.setData(wxUserDto);
                wxUserTreeDtos.add(userTreeDto);
            }
            List<WxUserTreeDto> userTreeDtos = treeList(wxUserTreeDtos, 0);
            treeDto.setChildren(userTreeDtos);
        }
        return treeDto;
    }


    /**
     * explain: 遍历递归
     * @param list 遍历的集合
     * @param pid 关联父集合的id(当然也按照你们公司的规定)
     * @return
     */
    public static List<WxUserTreeDto> treeList(List<WxUserTreeDto> list, int pid){
        List<WxUserTreeDto> pList = new ArrayList<>();
        List<WxUserTreeDto> otherList = new ArrayList<>();
        for(WxUserTreeDto treeDTO :list) {
            if (treeDTO.getData().getEmpId().equals(pid)) {//首先先找到一级集合
                pList.add(treeDTO);
            } else {
                otherList.add(treeDTO);
            }
        }
        List<WxUserTreeDto> result = new ArrayList<>();
        for(WxUserTreeDto treeDTO :pList) {//循环遍历归属一级集合的子集
            treeDTO.setChildren(treeList(otherList,treeDTO.getId()));//通过反复调用自身方法循环输出归属上级集合的下级集合
            result.add(treeDTO);
        }
        return result;
    }
}
