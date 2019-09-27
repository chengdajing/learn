package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Card;

public interface CardDao extends JpaRepository<Card, Long> {

}