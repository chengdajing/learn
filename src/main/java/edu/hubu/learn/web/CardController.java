package edu.hubu.learn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("/{id}")
    public ModelAndView user(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
       Card card = cardService.getCard(id);
       mav.addObject("card", card);
        mav.setViewName("card");
     return mav;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        cardService.deleteCard(id);
        ModelAndView mav = new ModelAndView("redirect:/card/list");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView cards() {
        ModelAndView mav = new ModelAndView();
        List<Card> cards = cardService.getcards();
        mav.addObject("card", cards);
        mav.setViewName("cards");
        return mav;
    }

        @RequestMapping("/add")
        public ModelAndView addCard() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("card_add");
            return mav;
        }
   
        @RequestMapping("/do_add")
        public ModelAndView doAddCard(Card card) {
            cardService.addCard(card);
            ModelAndView mav = new ModelAndView("redirect:/card/list");
            return mav;
        }
        @RequestMapping("/modify/{id}")
        public ModelAndView modifyUser(@PathVariable Long id) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("card", cardService.getCard(id));
            mav.setViewName("card_modify");
            return mav;
        }
    
        @RequestMapping("/do_modify")
        public ModelAndView doModifyUser(Card card) {
            cardService.modifyCard(card);
            ModelAndView mav = new ModelAndView("redirect:/card/list");
            return mav;
        }
    
        @RequestMapping("/search")
        public ModelAndView searchCard() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("card_search");
            return mav;
        }
    }