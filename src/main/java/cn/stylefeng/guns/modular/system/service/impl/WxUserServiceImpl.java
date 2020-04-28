package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.dto.WxUserDto;
import cn.stylefeng.guns.modular.dto.WxUserTreeDto;
import cn.stylefeng.guns.modular.system.dao.WxUserMapper;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.core.util.EmojiFilter.filterEmoji;

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
    @Autowired
    private IUserService userService;

    @Override
    public WxUser getLoginWxUser() {
        return this.selectById(JwtTokenUtil.getUserId());
    }

    @Override
    public List<Map<String, Object>> selectUsers(String pCode ,Integer id) {

//        return this.baseMapper.selectUsers(pCode,id);
        return this.baseMapper.selectUsersNew(ToolUtil.isEmpty(id) ? 0 : id);
    }

//    @Override
//    public List<WxUserDto> selectUsersObj() {
//        return this.baseMapper.selectUsersObj(null,null);
//    }

    @Override
    public List<WxUserTreeDto> spacetreeUsers() {
        Integer sysUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(sysUserId);
        Integer wxUserId = user.getVersion();
        String pCode = null;
        boolean isAll = false;
        WxUserTreeDto treeMainDto = new WxUserTreeDto();
        if (ToolUtil.isNotEmpty(wxUserId)) {
            pCode = "[" + wxUserId + "]";
        } else {
            treeMainDto.setId(0);
            treeMainDto.setName("销帮总部");
            WxUserDto userMainDto = new WxUserDto();
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
            userMainDto.setChildCount(total);
            userMainDto.setVipNum(vipNumP);
            userMainDto.setOpNum(total - vipNumP);
            userMainDto.setHeadimgurl("https://img.xbdzmp.com/201912120156008d2a5bbcdba1.jpg");
            userMainDto.setNickName("销帮总部");
            treeMainDto.setData(userMainDto);
            isAll = true;
        }


        List<WxUserTreeDto> treeDtos = new ArrayList<>();
//        List<WxUserDto> wxUserDtos = this.baseMapper.selectUsersObj(pCode,wxUserId);
        List<WxUserDto> wxUserDtos = this.baseMapper.selectUsersObjNew(ToolUtil.isEmpty(wxUserId) ? 0 : wxUserId);
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
                String nickName = wxUserDto.getNickName();
                if (ToolUtil.isNotEmpty(nickName)) {
                    String filterEmoji = filterEmoji(nickName);
                    if (filterEmoji.length() > 4) {
                        String name = StringUtils.substring(filterEmoji, 0, 4);
                        wxUserDto.setNickName(name);
                    }

                }
                wxUserDto.setVipNum(vipNum);
                wxUserDto.setOpNum(wxUserDto.getChildCount()-vipNum);
                WxUserTreeDto userTreeDto = new WxUserTreeDto();
                userTreeDto.setId(wxUserDto.getId());
                userTreeDto.setName(wxUserDto.getMobile());
                userTreeDto.setData(wxUserDto);
                wxUserTreeDtos.add(userTreeDto);
                if (!isAll && wxUserDto.getId().equals(wxUserId)) {
                    wxUserDto.setEmpId(0);
                }
            }
            List<WxUserTreeDto> userTreeDtos = treeList(wxUserTreeDtos, 0);
            if (isAll) {
                treeMainDto.setChildren(userTreeDtos);
                treeDtos.add(treeMainDto);
            } else {
                treeDtos=userTreeDtos;
            }
        }
        return treeDtos;
    }

    @Override
    public List<Integer> getWxChildUserIdBySysUser() {
        Integer sysUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(sysUserId);
        Integer wxUserId = user.getVersion();
        if (ToolUtil.isNotEmpty(wxUserId)) {
            String pCode = "[" + wxUserId + "]";
//            List<Integer> list = this.baseMapper.selectChildId(pCode, wxUserId);
            List<Integer> list = this.baseMapper.selectChildIdNew(ToolUtil.isEmpty(wxUserId) ? 0 : wxUserId);
//            if (list.isEmpty()) {
//                list.add(-100);
//            }
            return list;
        }
        return null;
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
            List<WxUserTreeDto> children = treeList(otherList, treeDTO.getId());
            if (!CollectionUtils.isEmpty(children)) {
                treeDTO.setChildren(children);//通过反复调用自身方法循环输出归属上级集合的下级集合
            } else {
                treeDTO.setChildren(null);
            }
            result.add(treeDTO);
        }
        return result;
    }
}
