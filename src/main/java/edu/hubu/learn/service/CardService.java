package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.CardDao;
import edu.hubu.learn.entity.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    public Card getCard (Long id) {
        return cardDao.findById(id).get();
    }
    public List<Card> getcards() {
        return cardDao.findAll();
    }
    public Card addCard(Card card) {
        return cardDao.save(card);
    }
	public void deleteCard(Long id) {
        cardDao.deleteById(id);
	}
	public void modifyCard(Card card) {
        cardDao.save(card);
    }
    // public List<Card> getCards() {
    //     return cardDao.findAll(new Sort(Direction.DESC,"id"));
    // }
	public List<Card> searchCards(String keyword) {
        log.info(keyword);
        Card card = new Card();
        card.setStudentname(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("studentname", match->match.contains());
        Example<Card> example = Example.of(card, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return cardDao.findAll(example, sort);
    }
	// public List<Card> searchCards(String keyword) {
	// 	return null;
	// }

}