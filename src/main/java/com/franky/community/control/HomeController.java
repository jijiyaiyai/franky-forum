package com.franky.community.control;

import com.franky.community.entity.DiscussPost;
import com.franky.community.entity.Page;
import com.franky.community.entity.User;
import com.franky.community.service.DiscussPostService;
import com.franky.community.service.LikeService;
import com.franky.community.service.UserService;
import com.franky.community.tool.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/error",method = RequestMethod.GET)
    public String getErrorPage(){
        return "/error/500";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page,
                               @RequestParam(name = "ordermod",defaultValue = "0")int ordermod) {
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findDiscussPostCount(0));
        page.setPath("/index?ordermod="+ordermod);

        List<DiscussPost> list = discussPostService
                .findDiscussPosts_bypage(0, page.getOffset(), page.getLimit(), ordermod);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount", likeCount);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("ordermod",ordermod);
        return "/index";
    }



}
