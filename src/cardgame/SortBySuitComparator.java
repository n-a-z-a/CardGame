package cardgame;

import java.util.Comparator;

public class SortBySuitComparator implements Comparator<Card> {

    @Override
    public int compare(Card firstCard, Card secondCard) {

         int suitCompare = firstCard.getSuit().compareTo((secondCard.getSuit()));

         int valueCompare = Integer.compare(firstCard.getValue(), secondCard.getValue());

         return ( suitCompare == 0) ? valueCompare : suitCompare;
    }
}
