package com.hao.interview;

import org.testng.annotations.Test;

/**
 * Created by hzou on 11/3/16.
 */
public class DeckTest {
    @Test
    public void shuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        for (Card card: deck) {

            System.out.println(card);
        }
    }
}
