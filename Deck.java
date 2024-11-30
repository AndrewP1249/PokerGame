/*
Andrew Paternostro
asp2260
Deck.java is the class for building a deck of cards
*/
import java.util.ArrayList;
import java.util.Random;
public class Deck {
	
	private Card[] cards; //array of cards for deck
	private int top = 0; // the index of the top of the deck
    private int position = 0; //used for array initalization
	private Card temp; //used for shuffle
	private int randomIndex; //used for shuffle
	private int currentIndex = 0;
	
	//deck constructor for when no command line hand input
	public Deck(){
		
        //creates an array of of 52 cards (deck)
        cards = new Card[52]; 

        //fills deck with respective suits and values
        for(int i = 1 ; i <= 4 ; i++) {
            for(int j = 1; j <= 13; j++) {
                cards[position] = new Card (i, j);
                position++; 
            }
        }

	}
	
	//This constructor is only for when given user input in command line for hand
	public Deck(ArrayList<Card> givenHand){

		// Deck will have exactly 47 cards (52 minus the 5 given in the command line so 47)
		cards = new Card[47]; 

		//populates deck without cards given in hand
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                Card card = new Card(suit, rank);

                if (!isInGivenHand(card, givenHand)) {
                    cards[currentIndex++] = card; 
                }
            }
        }
    }
	
	
	public void shuffle(){
		
		//method to shuffle deck
		Random r = new Random();
		for (int i = cards.length - 1; i > 0; i--) {
			randomIndex = r.nextInt(i + 1); 
			temp = cards[i];
			cards[i] = cards[randomIndex];
			cards[randomIndex] = temp;
		}
	}
	
	
	public Card deal(){
		
		// deal the top card in the deck and then moves top by 1
		return cards[top++];
		
	}

	public Card getLastDealt(){
		return cards[top-1];
	}

	//helper method for deck population method when hand is input
 	private boolean isInGivenHand(Card card, ArrayList<Card> givenHand) {
        for (Card c : givenHand) {
            if (card.toString().equals(c.toString())) {
                return true;
            }
        }
        return false;
    }
	
	// add more methods here if needed

}
