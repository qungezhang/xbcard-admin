package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/12/18 15:15
 */
@Data
public class CommentReplyPListByCommentIdDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页 默认1")
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量 默认10")
    private Integer pageSize;
    @ApiModelProperty(value = "评论id" , required = true)
    private Integer commentId;
}
