package blackjack;

public class Player {
    private static final int MAX_HANDS = 4;
    private Hand[] hands = new Hand[MAX_HANDS];
    private int count, sum;
    
    public Player() {
        reset();
    }
    
    public Hand getHand(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException();
        }
        
        return hands[index];
    }
    
    private void setHand(int index, Hand hand) {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException();
        }
        
        hands[index] = hand;
    }
    
    public int getCount() {
        return count;
    }
    
    private void setCount(int count) {
        if (count < 0 || count > MAX_HANDS) {
            throw new IllegalArgumentException();
        }
        
        this.count = count;
    }
    
    public int getSum() {
        return sum;
    }
    
    private void setSum() {
        int sum, max = 0;
        
        for (Hand hand : hands) {
            sum = hand.getSum();
            
            if (sum <= 21 && sum > max) {
                max = sum;
            }
        }
        
        this.sum = max;
    }
    
    public void reset() {
        setCount(MAX_HANDS);
        
        for (int i = 0; i < MAX_HANDS; i++) {
            setHand(i, new Hand());
        }
        
        setCount(1);
        setSum();
    }
    
    public void take(int index, Card card) {
        if (index < 0 || index >= MAX_HANDS) {
            throw new IllegalArgumentException();
        }
        
        getHand(index).add(card);
        setSum();
    }
    
    public void split(int index) {
        if (index < 0 || index >= MAX_HANDS - 1) {
            throw new IllegalArgumentException();
        }
        
        Hand hand = getHand(index);
        Card firstCard = hand.getCards(0);
        Card secondCard = hand.getCards(1);
        
        if (hand.getCount() == 2 && firstCard.getValue().toString()
                .equals(secondCard.getValue().toString())) {
            setCount(getCount() + 1);
            take(getCount() - 1, secondCard);
            hand.remove(secondCard);
            setSum();
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < getCount(); i++) {
            sb.append(getHand(i));
            
            if (i < getCount() - 1) {
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}
