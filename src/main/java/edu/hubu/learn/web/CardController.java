package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Card;
import edu.hubu.learn.service.CardService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
        public ModelAndView searchUser() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("card_search");
            return mav;
        }
   
        @RequestMapping("/do_search")
        public ModelAndView doSearchCard(HttpServletRequest httpRequest) {
            ModelAndView mav = new ModelAndView();
            String keyword = httpRequest.getParameter("keyword");
            List<Card> cards = cardService.searchCards(keyword);
            mav.addObject("card", cards);
            mav.setViewName("cards");
            return mav;
        }
         @RequestMapping("/add_avatar/{id}")
    public ModelAndView addCardAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("card", cardService.getCard(id));
        mav.setViewName("card_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddCardAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            // String filePath = ResourceUtils.getURL("classpath:").getPath();
            String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Card card = cardService.getCard(id);
            card.setAvatar(fileName);
            cardService.modifyCard(card);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/card/list");
    }
    }