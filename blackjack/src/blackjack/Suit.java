package blackjack;

public enum Suit {
    SPADES("♠"), HEARTS("♥"), CLUBS("♣"), DIAMONDS("♦");
    
    private String suit;
    
    private Suit(String suit) {
        setSuit(suit);
    }
    
    private void setSuit(String suit) {
        this.suit = suit;
    }
    
    public String toString() {
        return suit;
    }
}
