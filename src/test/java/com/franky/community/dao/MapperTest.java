package com.franky.community.dao;


import com.franky.community.FrankyCommunityApplication;
import com.franky.community.entity.DiscussPost;
import com.franky.community.entity.LoginTicket;
import com.franky.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = FrankyCommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectByName(){
        System.out.println(userMapper.selectByName("aaa"));
        System.out.println(userMapper.selectByName("bbb"));
        System.out.println(userMapper.selectByName("ccc"));
        System.out.println(userMapper.selectByName("asdf"));
    }

   @Test
    public void testInsertUser(){
       User user = new User("asdf","asdf","asdf","asdf",0,1,
               "asdf","asdf",new Date());
       userMapper.insertUser(user);
       System.out.println(userMapper.selectByName("asdf"));
   }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(99, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(99, "http://images.nowcoder.com/head/99t.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(99, "user99");
        System.out.println(rows);
    }

   @Test
    public void testSelectDiscussPosts_page(){
       List<DiscussPost> list = discussPostMapper.selectDiscussPosts_bypage(149, 0, 10, 0);
       for (DiscussPost post : list) {
           System.out.println(post);
       }

       int rows = discussPostMapper.selectDiscussPost_count(149);
       System.out.println(rows);
   }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(99);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }
    @Test
    public void testUpdateLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }
}
