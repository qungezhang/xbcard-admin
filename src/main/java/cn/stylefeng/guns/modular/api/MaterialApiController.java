package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.dto.MaterialAddListDTO;
import cn.stylefeng.guns.modular.dto.MaterialChildPListDTO;
import cn.stylefeng.guns.modular.dto.MaterialDTO;
import cn.stylefeng.guns.modular.dto.MaterialInfoDto;
import cn.stylefeng.guns.modular.dto.MaterialNewPListDTO;
import cn.stylefeng.guns.modular.dto.MaterialTopPlistDTO;
import cn.stylefeng.guns.modular.dto.MaterialUpdateDTO;
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qiniu.common.QiniuException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 控制器
 *
 * @author qunge
 * @Date 2019-11-04 21:58:16
 */
@Controller
@RequestMapping("/api/material")
@Api(tags = "素材")
@Slf4j
public class MaterialApiController extends BaseController {


    @Autowired
    private IMaterialService materialService;
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private IWxUserService wxUserService;
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/pList")
//    @ApiOperation("获取分页列表--------待废弃")
//    @ResponseBody
//    public ResponseData pList(@RequestBody PageListDTO<Material> pageListDTO) {
//        Page<Material> page = new PageFactory<Material>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
//        PageUtils pageUtils = new PageUtils(materialService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody()).orderBy("create_time",false)));
//        return new SuccessResponseData(pageUtils);
//    }
//
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/List")
//    @ApiOperation("获取列表--------待废弃")
//    @ResponseBody
//    public ResponseData List(@RequestBody Material material) {
//        material.setIsDeleted(0);
//        List<Material> materials = materialService.selectList(new EntityWrapper<>(material).orderBy("create_time",false));
//        return new SuccessResponseData(materials);
//    }

    /**
     * 获取顶级列表
     */
    @PostMapping(value = "/getTopPList")
    @ApiOperation("获取顶级列表")
    @ResponseBody
    public ResponseData getTopPList(@RequestBody @Valid MaterialTopPlistDTO plistDTO) {
        Page<MaterialInfoDto> infoDtoPage = new PageFactory<MaterialInfoDto>().defaultPage(plistDTO.getPageNum(), plistDTO.getPageSize(), null, null);
        List<MaterialInfoDto> materialByPid = materialService.getMaterialByPid(plistDTO.getCardId(), 0, infoDtoPage);
        if (!CollectionUtils.isEmpty(materialByPid)) {
            for (MaterialInfoDto materialInfoDto : materialByPid) {
                String imgUrl = materialInfoDto.getImgUrl();
                materialInfoDto.setSimplename(imgUrl == null ? null : imgUrl + "?imageView2/1/w/300");
            }
        }
        infoDtoPage.setRecords(materialByPid);
        PageUtils pageUtils = new PageUtils(infoDtoPage);
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 获取下一级列表
     */
    @PostMapping(value = "/getChildPList")
    @ApiOperation("获取下一级列表")
    @ResponseBody
    public ResponseData getChildPList(@RequestBody @Valid MaterialChildPListDTO childPListDTO) {
        Page<MaterialInfoDto> infoDtoPage = new PageFactory<MaterialInfoDto>().defaultPage(childPListDTO.getPageNum(), childPListDTO.getPageSize(), null, null);
        List<MaterialInfoDto> materialByPid = materialService.getMaterialByPid(childPListDTO.getCardId(), childPListDTO.getPid(), infoDtoPage);
        if (!CollectionUtils.isEmpty(materialByPid)) {
            for (MaterialInfoDto materialInfoDto : materialByPid) {
                String imgUrl = materialInfoDto.getImgUrl();
                materialInfoDto.setSimplename(imgUrl == null ? null : imgUrl + "?imageView2/1/w/300");
            }
        }
        infoDtoPage.setRecords(materialByPid);
        PageUtils pageUtils = new PageUtils(infoDtoPage);
        return new SuccessResponseData(pageUtils);
    }
    /**
     * 最新动态 三天内的
     */
    @PostMapping(value = "/getNewMaterialByCardId")
    @ApiOperation("最新动态 三天内的")
    @ResponseBody
    public ResponseData getNewMaterialByCardId(@RequestBody @Valid MaterialNewPListDTO newPListDTO) {
        Page<MaterialInfoDto> infoDtoPage = new PageFactory<MaterialInfoDto>().defaultPage(newPListDTO.getPageNum(), newPListDTO.getPageSize(), null, null);
        List<MaterialInfoDto> newMaterialByCardId = materialService.getNewMaterialByCardId(newPListDTO.getCardId(), infoDtoPage);
        infoDtoPage.setRecords(newMaterialByCardId);
        PageUtils pageUtils = new PageUtils(infoDtoPage);
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody MaterialDTO materialDTO) {
        Integer cardId = materialDTO.getCardId();
        List<MaterialAddListDTO> imgList = materialDTO.getImgList();
        if (ToolUtil.isEmpty(materialDTO.getPid()) || ToolUtil.isEmpty(cardId) || ToolUtil.isEmpty(imgList)) {
            return new ErrorResponseData("父级id、名片id、图片对象list 均不可为空");
        }
        if (ToolUtil.isEmpty(materialDTO.getUserId())) {
            return new ErrorResponseData("当前登录用户id不可为空");
        }
        WxUser wxUser = wxUserService.selectById(materialDTO.getUserId());

        if (wxUser == null) {
            return new ErrorResponseData("用户授权登录异常");
        }
        if (wxUser.getIsvip().equals(0) || (wxUser.getIsvip().equals(1) && System.currentTimeMillis() > wxUser.getVipEndTime().getTime())) {
            EntityWrapper<Material> wrapper = new EntityWrapper<>();
            wrapper.eq("card_id", cardId).eq("is_deleted", 0).eq("pid", 0);
            int count = materialService.selectCount(wrapper);
            int total = count + imgList.size();
            if (total > 10) {
                log.info("用户不是VIP或已过期，最多上传10张单独图片");
                return new ErrorResponseData("用户不是VIP或已过期，最多上传10张单独图片");
            }
            if (materialDTO.getPid() > 0) {//非vip
                log.info("用户不是VIP或已过期，不可分类");
                return new ErrorResponseData("用户不是VIP或已过期，不可分类");
            }
        }

        for (MaterialAddListDTO addListDTO : imgList) {
            if (ToolUtil.isEmpty(addListDTO.getImgUrl())) {
                return new ErrorResponseData("图片list对象中的imgUrl不可为空");
            }
            Material material = new Material();
            material.setPid(materialDTO.getPid());
            material.setCardId(cardId);
            materialSetPcode(material);
            material.setImgUrl(addListDTO.getImgUrl());
            material.setDefaultImg(addListDTO.getDefaultImg());
            material.setDescription(addListDTO.getDescription());
            material.setUserId(wxUser.getId());
            material.setCreateBy(wxUser.getMobile());
            material.setIsDeleted(0);//是否删除（0否，1是）
            material.setCreateTime(new Date());
            material.setUpdateTime(new Date());
            materialService.insert(material);
        }
        return SUCCESS_TIP;
    }
    private void materialSetPcode(Material material) {
        Integer pid = material.getPid();
        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            material.setPid(0);
            material.setPcode("[0],");
        } else {
            Material pMaterial = materialService.selectById(pid);
            String pcode = pMaterial.getPcode();
            material.setPid(pid);
            material.setPcode(pcode + "[" + pid + "],");
        }
    }
    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    @ApiOperation("删除")
    public Object delete(@RequestParam(value = "materialId",required = false) Integer materialId) throws QiniuException {
//        Material selectById = materialService.selectById(materialId);
        if (ToolUtil.isEmpty(materialId)) {
            return new ErrorResponseData("materialId不可为空");
        }
        List<Material> childIdAndAll = materialService.selectChildIdNew(materialId);
        if (ToolUtil.isEmpty(childIdAndAll)) {
            return new ErrorResponseData("产品不存在");
        }
        for (Material material : childIdAndAll) {
            qiniuService.delete(StringUtil.getQiniKeyByUrl(material.getImgUrl()));
            materialService.deleteById(material.getId());
        }

        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @ApiOperation("修改")
    public Object update(@RequestBody @Valid MaterialUpdateDTO material) throws QiniuException {
        if (material.getPid() > 0) {//非vip
            if (ToolUtil.isEmpty(material.getUserId())) {
                return new ErrorResponseData("当前登录用户id不可为空");
            }
            WxUser wxUser = wxUserService.selectById(material.getUserId());
            if (wxUser.getIsvip() == 0) {
                return new ErrorResponseData("用户不是VIP，不可分类");
            }
        }
        Integer id = material.getId();
        String materialImgUrl = material.getImgUrl();
        if (ToolUtil.isNotEmpty(materialImgUrl)) {
            Material selectById = materialService.selectById(id);
            if (ToolUtil.isNotEmpty(selectById)) {
                qiniuService.deleteOldQiniuByUrl(selectById.getImgUrl(),materialImgUrl);
//                String qiniKeyByUrled = StringUtil.getQiniKeyByUrl(selectById.getImgUrl());
//                if (ToolUtil.isNotEmpty(qiniKeyByUrled) && !qiniKeyByUrled.equals(StringUtil.getQiniKeyByUrl(materialImgUrl))) {
//                    qiniuService.delete(qiniKeyByUrled);
//                }
            } else {
            }
        }
        Material updateMaterial = BeanMapperUtil.objConvert(material, Material.class);
        materialService.updateById(updateMaterial);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{materialId}")
    @ResponseBody
    @ApiOperation("详情")
    public Object detail(@PathVariable("materialId") Integer materialId) {
        Material material = materialService.selectById(materialId);
        MaterialInfoDto materialInfoDto = BeanMapperUtil.objConvert(material, MaterialInfoDto.class);
        Integer days = DateUtils.days(material.getCreateTime(), new Date());
        materialInfoDto.setLabel(days <= 7 ? 1 : 0);
        return new SuccessResponseData(materialInfoDto);
    }

//    /**
//     * 修改所属分类
//     */
//    @PostMapping(value = "/changeCategory")
//    @ResponseBody
//    @ApiOperation("修改所属分类")
//    public Object changeCategory(Integer id,Integer categoryId) {
//        Material material = new Material();
//        material.setId(id);
//        material.setCategoryId(categoryId);
//        material.setUpdateTime(new Date());
//        materialService.updateById(material);
//        return SUCCESS_TIP;
//    }

//    /**
//     * 分类中加素材
//     *
//     * @param materialDTO
//     * @return
//     */
//    @PostMapping("addInCategory")
//    @ResponseBody
//    @ApiOperation("分类中加素材")
//    public Object addMaterial(@RequestBody MaterialDTO materialDTO) {
//        if (materialDTO == null || materialDTO.getCardId() == null || materialDTO.getCategoryId() == null || materialDTO.getImgUrl() == null) {
//            return new ErrorResponseData("参数异常 图片地址、名片id、类别id 不可为空");
//        }
//        Material material = BeanMapperUtil.objConvert(materialDTO, Material.class);
//        material.setUserId(JwtTokenUtil.getUserId());
//        material.setIsDeleted(0);//是否删除（0否，1是）
//        material.setCreateTime(new Date());
//        material.setUpdateTime(new Date());
//        materialService.insert(material);
//        SuccessResponseData responseData = new SuccessResponseData();
//        responseData.setData(material);
//        return responseData;
//    }


//    /**
//     * 获取分类下的素材
//     *
//     * @param cardId
//     * @param categoryId
//     * @return
//     */
//    @GetMapping("getByCategory")
//    @ResponseBody
//    @ApiOperation("获取分类下的素材")
//    public ResponseData getByCategory(@RequestParam("cardId") Integer cardId, @RequestParam("categoryId") Integer categoryId) {
//        Material material = new Material();
//        material.setCardId(cardId);
//        material.setCategoryId(categoryId);
//        material.setIsDeleted(0);
//        List<Material> materialList = materialService.selectList(new EntityWrapper<>(material).orderBy("create_time", false));
//        SuccessResponseData responseData = new SuccessResponseData();
//        responseData.setData(materialList);
//        return responseData;
//    }
}
