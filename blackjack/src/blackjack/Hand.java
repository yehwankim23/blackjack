package blackjack;

public class Hand {
    private static final int MAX_CARDS = 11;
    private Card[] cards = new Card[MAX_CARDS];
    private int count, sum, aces, usedAces;
    private boolean isBlackjack, isBusted;
    
    public Hand() {
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
    
    public int getSum() {
        return sum;
    }
    
    private void setSum(int sum) {
        if (sum < 0) {
            throw new IllegalArgumentException();
        }
        
        if (sum > 21) {
            setBusted(true);
        }
        
        this.sum = sum;
    }
    
    public int getAces() {
        return aces;
    }
    
    private void setAces(int aces) {
        if (aces < 0) {
            throw new IllegalArgumentException();
        }
        
        this.aces = aces;
    }
    
    public int getUsedAces() {
        return usedAces;
    }
    
    private void setUsedAces(int usedAces) {
        if (usedAces < 0) {
            throw new IllegalArgumentException();
        }
        
        this.usedAces = usedAces;
    }
    
    public boolean isBlackjack() {
        return isBlackjack;
    }
    
    private void setBlackjack(boolean isBlackjack) {
        this.isBlackjack = isBlackjack;
    }
    
    public boolean isBusted() {
        return isBusted;
    }
    
    private void setBusted(boolean isBusted) {
        this.isBusted = isBusted;
    }
    
    public void reset() {
        setCount(0);
        setSum(0);
        setAces(0);
        setUsedAces(0);
        setBlackjack(false);
        setBusted(false);
    }
    
    public void add(Card card) {
        if (getCount() == MAX_CARDS || isBusted()) {
            throw new IllegalStateException();
        }
        
        int value = card.getValue().ordinal();
        
        setCards(getCount(), card);
        setCount(getCount() + 1);
        
        if (value == 0) {
            setAces(getAces() + 1);
            setSum(calculateSum(11));
        } else {
            setSum(calculateSum(Math.min(value + 1, 10)));
        }
        
        if (getCount() == 2 && getSum() == 21) {
            setBlackjack(true);
        } else {
            setBlackjack(false);
        }
    }
    
    public void remove(Card card) {
        if (getCount() == 0) {
            throw new IllegalStateException();
        }
        
        int value = card.getValue().ordinal();
        
        setCount(getCount() - 1);
        
        if (value == 0) {
            setAces(getAces() - 1);
            setUsedAces(getUsedAces() - 1);
            setSum(calculateSum(-1));
        } else {
            setSum(calculateSum(-1 * Math.min(value + 1, 10)));
        }
    }
    
    private int calculateSum(int value) {
        int sum = getSum() + value;
        
        for (int i = 0; i < getAces(); i++) {
            if (sum > 21 && getAces() > 0 && getUsedAces() < getAces()) {
                setUsedAces(getUsedAces() + 1);
                sum -= 10;
            } else {
                break;
            }
        }
        
        return sum;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (getCount() == 0) {
            sb.append("(Empty Deck)");
        } else {
            for (int i = 0; i < getCount(); i++) {
                sb.append(getCards(i)).append(" ");
            }
            
            sb.append("= ").append(getSum());
            
            if (isBlackjack()) {
                sb.append(" (Blackjack)");
            } else if (isBusted()) {
                sb.append(" (Busted)");
            }
        }
        
        return sb.toString();
    }
}
