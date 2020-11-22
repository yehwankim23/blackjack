package blackjack;

public class Card {
    private Value value;
    private Suit suit;
    
    public Card(Value value, Suit suit) {
        setValue(value);
        setSuit(suit);
    }
    
    public Value getValue() {
        return value;
    }
    
    private void setValue(Value value) {
        this.value = value;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    private void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    public String toString() {
        return getValue().toString().concat(getSuit().toString());
    }
}
