package cardgame;

public class Card implements Comparable<Card> {

    //constants to define the available suits
    public static final String SPADES ="\u2660";
    public static final String HEARTS ="\u2661";
    public static final String DIAMONDS ="\u2662";
    public static final String CLUBS ="\u2663";


    //instance variables
    private String suit;
    private String symbol;
    private int value;


    //constructor
    public Card(String suit, String symbol, int value) {
        this.suit = suit;
        this.symbol = symbol;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Card otherCard) {

        //2222333344445555

        //-ve if a<b
        //0 if a==b
        //+ve if a>b

        //if value is different then
        // determine which is smaller/larger
        if (this.value != otherCard.value) {
            return this.value - otherCard.value;

        }

        //else if suit different then
        // determine which is smaller/larger
        return this.suit.compareTo(otherCard.suit);

    }

    @Override
    public String toString() {
        return this.symbol + " of " + this.suit;
    }

    public static void main(String[] args) {
        Card aceOfSpades = new Card(SPADES, "A", 14);
//        aceOfSpades.setSuit(SPADES);
//        aceOfSpades.setSymbol("A");
        System.out.println(aceOfSpades);

        Card fourOfHearts = new Card(HEARTS, "4", 4);
        System.out.println(fourOfHearts);
//        System.out.println(SPADES);
//        System.out.println(HEARTS);
//        System.out.println(DIAMONDS);
//        System.out.println(CLUBS);
    }
}
