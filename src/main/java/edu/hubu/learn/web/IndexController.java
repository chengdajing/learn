package edu.hubu.learn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Card;
import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.CardService;
import edu.hubu.learn.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(1l);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @Autowired
    private CardService cardService;
    @RequestMapping("/card")
    public ModelAndView card() {
        ModelAndView mav = new ModelAndView();
        Card card = cardService.getCard(1l);
        mav.addObject("card", card);
        mav.setViewName("card");
        return mav;
    }

}