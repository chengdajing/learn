package edu.hubu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.CardDao;
import edu.hubu.learn.entity.Card;

@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    public Card getCard (Long id) {
        return cardDao.findById(id).get();
    }
}