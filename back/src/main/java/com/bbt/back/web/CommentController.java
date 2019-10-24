package com.bbt.back.web;

import com.bbt.back.entities.Comment;
import com.bbt.back.model.PageInfoResult;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.CommentService;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/12 10:35
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/list")
    private Object comments(HttpServletRequest request, Integer pageNo, Integer pageSize) throws Exception{
        ResultEntity resultEntity=new ResultEntity();
        //1.获取前端传递的userId参数
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        PageInfo<Comment> pageInfo = commentService.selectAllByUserId(userId, pageNo, pageSize);
        PageInfoResult pageInfoResult=new PageInfoResult(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getPageNum(),pageInfo.getPageSize());
        resultEntity.setMsg("获取成功");
        resultEntity.setData(pageInfoResult);
        return resultEntity;
    }

    @RequestMapping("/delete")
    private Object delComment(HttpServletRequest request) {
        ResultEntity resultEntity = new ResultEntity();
        int commentId = HttpServletRequestUtil.getInt(request,"commentId");
        int code = commentService.delComment(commentId);
        if (code == 0){
            resultEntity.setMsg("删除评论成功");
        } else {
            resultEntity.setMsg("删除评论成功");
        }
        return resultEntity;
    }

    @RequestMapping("/update")
    private Object updateComment(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String commentStr = HttpServletRequestUtil.getString(request,"comment");
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(commentStr,Comment.class);
        if (commentService.updateComment(comment) == 0){
            resultEntity.setMsg("更新评论成功");
        } else{
            resultEntity.setMsg("更新评论失败");
        }
        return resultEntity;
    }

    @RequestMapping("/add")
    private  Object addComment(HttpServletRequest request) throws IOException{
        ResultEntity resultEntity = new ResultEntity();
        String commentStr = HttpServletRequestUtil.getString(request,"comment");
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(commentStr,Comment.class);
        if (commentService.addComment(comment) == 0){
            resultEntity.setMsg("添加评论成功");
        } else {
            resultEntity.setMsg("添加评论失败");
        }
        return resultEntity;
    }

    @RequestMapping("/likeComment")
    private Object likeComment(HttpServletRequest request) throws IOException{
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        int commentId = HttpServletRequestUtil.getInt(request, "commentId");
        if(commentService.likeComment(userId,commentId)==1){
            resultEntity.setMsg("点赞评论成功");
        }else {
            resultEntity.setMsg("取消点赞评论成功");
        }
        return resultEntity;
    }
}
