package cardgame;
//
//import java.util.Scanner;
//
//public class SnapGame extends CardGame{
//
//    private Scanner scanner = new Scanner (System.in);
//
//    public SnapGame() {
//        super("Snap!");
//    }
//
//    //Creates a player by reading a name from console
//    public Player[] createPlayers() {
//        Player[] players = new Player[2];
//        for (int i = 0; i < 2 ; i++) {
//            System.out.println("Player " + (i+1) + ", please enter your name");
//            String playerName = scanner.nextLine();
//            players[i] = new Player(playerName);
//        }
//        System.out.println("The players are: " + players[0].getName() + " and " + players[1].getName());
//        return players;
//    }
//
//
//
//
//    public void play () {
//
////        //create player 1
////
////        //ask their name
////        System.out.println("Player 1, please enter your name");
////        String player1Name = scanner.nextLine();
////
////        //create a player object
////        Player player1 = new Player(player1Name);
////
////        //create player 2
////        System.out.println("Player 2, please enter your name");
////        String player2Name = scanner.nextLine();
////        Player player2 = new Player(player2Name);
//
//
////        System.out.println("The players are: " + player1.getName() + " and " + player2.getName());
//
//        //create players
//        Player[] players =  createPlayers();
//
//
//        //shuffle deck
//        shuffleDeck();
//
//        //player1 starts
//        System.out.println(players[0].getName() + " press ENTER to deal");
//        scanner.nextLine();
//        //deal top card
//        Card previousCard = dealCard();
//        System.out.println("Card: " + previousCard);
//
//        while (true) {
//
//            //player2's turn
//            System.out.println(players[1].getName() + " press ENTER to deal:");
//            scanner.nextLine();
//
//            //deal a card
//            Card nextCard = dealCard();
//            if (nextCard == null) {
//                System.out.println("No one wins!");
//                break;
//            }
//            System.out.println("Card: " + nextCard);
//
//            // compare values of last two cards
//            //if values are the same then player2 wins
//            if (previousCard.getValue() == nextCard.getValue()) {
//                System.out.println("SNAP!");
//                System.out.println("Congratulations " + players[1].getName() + "! You win!");
//                break;
//            }
//
//            //else player1 deals another card - go around again
//
//            //reassign previous card
//            previousCard = nextCard;
//            System.out.println(players[0].getName() + " press ENTER to deal:");
//            scanner.nextLine();
//            nextCard = dealCard();
//            if (nextCard == null) {
//                System.out.println("No one wins!");
//                break;
//            }
//            System.out.println("Card: " + nextCard);
//
//            // compare values of last two cards
//            //if values are the same then player1 wins
//            if (previousCard.getValue() == nextCard.getValue()) {
//                System.out.println("SNAP!");
//                System.out.println("Congratulations " + players[0].getName() + "! You win!");
//                break;
//            }
//
//            previousCard = nextCard;
//
//        }
//
//        System.out.println("Thanks for playing!");
//
//    }
//
//    public static void main(String[] args) {
//        SnapGame snapGame = new SnapGame();
//        System.out.println(snapGame.getName());
//        snapGame.play();
//    }
//}

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

public class SnapGame extends CardGame {

    private static final String SNAP = "SNAP";
    private Scanner scanner = new Scanner( System.in );

    public SnapGame() {
        super("Snap!");
    }

    /**
     * Creates a player by reading a name from console.
     * @return
     */
    private Player createPlayer(int playerNumber) {
        // ask their name
        System.out.println("Player " + playerNumber + ", please enter your name:");
        String playerName = scanner.nextLine();
        return new Player(playerName);
    }

    private Player[] createPlayers(int numPlayers) {
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = createPlayer(i + 1 );
        }
        return players;
    }

    public void play() {

        // create player 1

        // create a player object
//        Player player1 = createPlayer(1);

        // create a player object
//        Player player2 = createPlayer(2);

        // create players
        Player[] players = createPlayers(2);
        int currentPlayerIndex = 0;

//        System.out.println( String.format( "The players are: %s and %s", player1.getName(), player2.getName() ) );

        // shuffle deck
        shuffleDeck();

//        for (int i = 0; i < 30; i++) {
//            dealCard();
//        }

        // holds previous card
        Card previousCard = null;

        Player previousPlayer = null;
        boolean previousPlayerWon = false;

        // infinite loop
        // while ( condition ) {
        // }
        // can one ZERO or more times!
        boolean justDoIt = true;
        while( justDoIt ) {

            Player currentPlayer = players[currentPlayerIndex];

            if (previousPlayerWon) {
                System.out.println( String.format( "%s enter SNAP in less than two seconds to win: ", previousPlayer.getName() ) );
            } else {
                System.out.println(String.format("Current player is %s, press ENTER to deal: ", currentPlayer.getName()));
            }
            try {
                //try block - protected
                String playerResponse = readResponse();
                if (playerResponse.equals(SNAP) && previousPlayerWon){
                    System.out.println("SNAP!");
                    System.out.println( String.format("Congratulations %s! You win!!", previousPlayer.getName() ) );
                    break;
                }  if (playerResponse.equals(SNAP)){
                    System.out.println("Not SNAP!");
                    System.out.println( String.format("You lose %s - cheater!", previousPlayer.getName() ) );
                    break;
                }
            } catch (Exception e) {
                // if previous player won AND tto slow then they lose!
                if (previousPlayerWon) {
                    System.out.println("Too slow!");
                    System.out.println( String.format("You snooze you lose %s !", previousPlayer.getName() ) );
                    break;
                }
                //handler block
                System.out.println("Exception thrown");
            }

            // deal another card
            Card nextCard = dealCard();
            if( nextCard == null ) {
                System.out.println( String.format("You lose %s!", currentPlayer.getName() ));
                break;
            }

            System.out.println("Card: " + nextCard);

            if( previousCard == null ) {
                previousCard = nextCard;

            } else {

                // compare values of last two cards
                // if values are the same then player wins!

                previousPlayerWon = previousCard.getValue() == nextCard.getValue();
//                if( previousCard.getValue() == nextCard.getValue() ) {
//                    System.out.println("SNAP!");
//                    System.out.println( String.format("Congratulations %s! You win!!", currentPlayer.getName() ) );
//                    break;
//                }

                // else deal another card - go around again

                // reassign previous card
                previousCard = nextCard;

            }


            //save previous player
            previousPlayer = currentPlayer;
            // change the player
            currentPlayerIndex = ( currentPlayerIndex + 1) % players.length;

        }

        System.out.println("Thanks for playing!");

    }

    /**
     * Read response from user
     * If it takes over 2 seconds then throw exception
     * @return
     * @throws Exception
     */

    private String readResponse() throws Exception{
        Instant startTime = Instant.now();
//        System.out.println("start: " + startTime);
        String response = scanner.nextLine();
        Instant endTime = Instant.now();
//        System.out.println("end: " + endTime);
        long elapsed = endTime.getEpochSecond() - startTime.getEpochSecond();

        if( elapsed > 2) {
            throw new Exception("Out of time!");
        }
        return response.toUpperCase();
    }

    public static void main(String[] args) {
        SnapGame snapGame = new SnapGame();
        System.out.println(snapGame.getName());

        snapGame.play();

    }

}
