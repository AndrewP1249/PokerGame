/*
Andrew Paternostro
asp2260
Player.java is the class for building a player
*/
import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll; //amount player has
    private double bet;
		
	public Player(){		
        //Constructor that creates player hand
        hand = new ArrayList<>();
        bankroll = 50;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
        }
		
        public void bets(double amt){
            // player makes a bet and bet is removed from bankroll
            bet = amt;
            bankroll -= amt;
        }

        public void winnings(double odds){
            //	adjust bankroll if player wins
            bankroll += (bet*odds);
        }

        public double getBankroll(){
            // return current balance of bankroll
            return bankroll;
        }

        //returns hand for display purposes
        public ArrayList<Card> getArrayList(){
            return hand;
        }

        //gets a card in the hand used for the exchange
        public Card getCard(int i){
            return hand.get(i);
        }
}


