package cardgame;


import java.time.Instant;
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


        // create players
        Player[] players = createPlayers(2);
        int currentPlayerIndex = 0;


        // shuffle deck
        shuffleDeck();



        // holds previous card
        Card previousCard = null;

        Player previousPlayer = null;
        boolean previousPlayerWon = false;

        // infinite loop
        // while ( condition ) {
        // }
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
                // if previous player won AND too slow then they lose!
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
