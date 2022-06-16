package cardgame;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card firstCard, Card secondCard) {

         int SuitCompare = firstCard.getSuit().compareTo((secondCard.getSuit()));

         int ValueCompare = Integer.compare(firstCard.getValue(), secondCard.getValue());

         return ( SuitCompare == 0) ? ValueCompare : SuitCompare;
    }
}
