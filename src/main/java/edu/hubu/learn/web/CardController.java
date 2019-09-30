package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Card;
import edu.hubu.learn.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    //@RequestMapping("/{id}")
    //public ModelAndView user(@PathVariable Long id) {
        //ModelAndView mav = new ModelAndView();
        //User user = CardService.getUser(1l);
       //mav.addObject("user", user);
       // mav.setViewName("user");
      //return mav;
   // }

    @RequestMapping("/list")
    public ModelAndView cards() {
        ModelAndView mav = new ModelAndView();
        List<Card> cards = cardService.getcards();
        mav.addObject("card", cards);
        mav.setViewName("cards");
        return mav;
    }
}