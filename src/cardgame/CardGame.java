package cardgame;

import java.util.*;


public class CardGame {

    private String name;
    private ArrayList<Card> deckOfCards = new ArrayList<>();
    private int[] values = {2,3,4,5,6,7,8,9,10,11,12,13,14};
    private String[] symbols = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public CardGame(String name) {
        this.name = name;
        generateDeckOfCards();
    }

    public String getName() {
        return name;
    }

    private void generateDeckOfCards() {
        for (int i = 0; i < values.length; i++) {
            Card spade = new Card(Card.SPADES, symbols[i], values[i]);
            deckOfCards.add(spade);
            Card heart = new Card(Card.HEARTS, symbols[i], values[i]);
            deckOfCards.add(heart);
            Card diamond = new Card(Card.DIAMONDS, symbols[i], values[i]);
            deckOfCards.add(diamond);
            Card club = new Card(Card.CLUBS, symbols[i], values[i]);
            deckOfCards.add(club);
        }
//        System.out.println(deckOfCards);
//        System.out.println(deckOfCards.size());
    }

    public ArrayList<Card> getDeck() {
        return this.deckOfCards;
    }

    public Card dealCard() {
//        if (deckOfCards.size() == 0) {
//            return null;
//        }
        if (deckOfCards.isEmpty()) {
            return null;
        }
        Card firstCard = this.deckOfCards.remove(0);
        return firstCard;
    }

    public void sortDeckInNumberOrder() {
        Collections.sort(this.deckOfCards);
    }

    public void sortDeckIntoSuits() {
        Comparator<Card> cardComparator = new SortBySuitComparator();
        Collections.sort(this.deckOfCards, cardComparator);
    }
    public void shuffleDeck() {
        Collections.shuffle(this.deckOfCards);
    }


    public static void main(String[] args) {
    CardGame cardGame = new CardGame("Snap");
//    cardGame.generateDeckOfCards();

        System.out.println(cardGame.getDeck());
        System.out.println(cardGame.getDeck().size());
        cardGame.shuffleDeck();
        System.out.println(cardGame.dealCard());
        System.out.println(cardGame.getDeck());
        System.out.println(cardGame.getDeck().size());

        cardGame.sortDeckInNumberOrder();
        System.out.println(cardGame.getDeck());
        System.out.println(cardGame.getDeck().size());

        cardGame.sortDeckIntoSuits();;
        System.out.println(cardGame.getDeck());
        System.out.println(cardGame.getDeck().size());



    }
}

