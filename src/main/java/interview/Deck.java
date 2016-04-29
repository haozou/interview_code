package interview;

import java.util.*;

/**
 * Created by Hao on 3/3/16.
 */
public class Deck implements Iterable<Card>{

    final static private int size = 54;
    private List<Card> cards;


    public Deck() {
        this.cards = new ArrayList<>(size);
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                switch (i) {
                    case 0: {
                        this.cards.add(new Card(j, Card.Suit.CLUBS));
                        break;
                    }
                    case 1: {
                        this.cards.add(new Card(j, Card.Suit.SPADES));
                        break;
                    }
                    case 2: {
                        this.cards.add(new Card(j, Card.Suit.HEARTS));
                        break;
                    }
                    case 3: {
                        this.cards.add(new Card(j, Card.Suit.DIAMONDS));
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        this.cards.add(new Card(16, Card.Suit.SMALLBOSS));
        this.cards.add(new Card(17, Card.Suit.BIGBOSS));
    }
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = size - 1; i >= 0; i--) {
            int index = rand.nextInt(i + 1);
            Card tmp = this.cards.get(i);
            this.cards.set(i, this.cards.get(index));
            this.cards.set(index, tmp);
        }
    }
}
