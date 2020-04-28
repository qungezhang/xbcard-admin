package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.dto.WxUserDto;
import cn.stylefeng.guns.modular.system.model.WxUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信用户 Mapper 接口
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
public interface WxUserMapper extends BaseMapper<WxUser> {

    // TODO: 2019/11/24
    List<Map<String, Object>> getMaps();

    List<Map<String, Object>> selectUsers(@Param("pCode") String pCode,@Param("id") Integer id);
    List<Integer> selectChildId(@Param("pCode") String pCode,@Param("id") Integer id);
    List<WxUserDto> selectUsersObj(@Param("pCode") String pCode,@Param("id") Integer id);
    Map<String, Object> selectStatistics();

    List<Map<String, Object>> selectUsersNew(@Param("pid") Integer pid);
    List<Integer> selectChildIdNew(@Param("pid") Integer pid);
    List<WxUserDto> selectUsersObjNew(@Param("pid") Integer pid);

}
