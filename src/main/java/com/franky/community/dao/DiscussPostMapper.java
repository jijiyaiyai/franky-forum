package com.franky.community.dao;

import com.franky.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //分页获取帖子，userID将被用于开发个人主页功能
    List<DiscussPost> selectDiscussPosts_bypage(int userId, int offset, int limit);

    //要使用动态sql而且只有一个参数的话就必须取别名
    int selectDiscussPost_count(@Param("userId") int userId);

    //查找某个作者发起的全部讨论
    List<DiscussPost> selectDiscussPosts_byUserId(@Param("userId") int userId);

    //新增帖子
    int insertDiscussPost(DiscussPost discussPost);

    //查询帖子的详情
    DiscussPost selectDiscussPostById(int id);

    //当有人回复你之后，需要用这个方法更新表中数据
    int updateCommentCount(int id, int commentCount);

    int updateType(int id, int type);

    int updateStatus(int id, int status);
}
