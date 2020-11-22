package blackjack;

import java.util.Random;

public class Deck {
    private static final int MAX_CARDS = 52;
    private Card[] cards = new Card[MAX_CARDS];
    private int count;
    
    public Deck() {
        reset();
    }
    
    public Card getCards(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException();
        }
        
        return cards[index];
    }
    
    private void setCards(int index, Card card) {
        if (index < 0 || index > getCount()) {
            throw new IllegalArgumentException();
        }
        
        cards[index] = card;
    }
    
    public int getCount() {
        return count;
    }
    
    private void setCount(int count) {
        if (count < 0 || count > MAX_CARDS) {
            throw new IllegalArgumentException();
        }
        
        this.count = count;
    }
    
    public void reset() {
        setCount(0);
        
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                setCards(getCount(), new Card(value, suit));
                setCount(getCount() + 1);
            }
        }
    }
    
    public void shuffle() {
        Random random = new Random();
        int index;
        Card card;
        
        for (int i = 0; i < MAX_CARDS; i++) {
            index = random.nextInt(MAX_CARDS);
            card = getCards(i);
            setCards(i, getCards(index));
            setCards(index, card);
        }
        
        setCount(MAX_CARDS);
    }
    
    public Card deal() {
        if (getCount() == 0) {
            throw new IllegalStateException();
        }
        
        setCount(getCount() - 1);
        
        return getCards(getCount());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (getCount() == 0) {
            sb.append("(Empty Deck)");
        } else {
            for (int i = 0; i < getCount(); i++) {
                sb.append(getCards(i));
                
                if (i < getCount() - 1) {
                    sb.append(" ");
                }
            }
        }
        
        return sb.toString();
    }
}
