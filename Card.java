/*
Andrew Paternostro
asp2260
Card.java is the class for building a card
*/

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
    private String suitName; // used for toString method
    private String rankName; // used for toString method
	
    //card constructor 
	public Card(int s, int r){
        
        //make a card with suit s and rank r
        suit = s; 
        rank = r;
            
        //assigns name/value to rank
        switch(r){
               
                case 1:
                    rankName = "Ace";
                    break;
                case 11:
                    rankName = "Jack";
                    break;
                case 12:
                    rankName = "Queen";
                    break;
                case 13:
                    rankName = "King";
                    break;
                default: 
                    rankName = Integer.toString(rank);
                    break;
            }

        //assigns names to suit
        switch (s) {
            case 1:
                suitName = "Clubs";
                break;
            case 2:
                suitName = "Diamonds";
                break;
            case 3:
                suitName = "Hearts";
                break;
            case 4: 
                suitName = "Spades";
                break;
        }

	}
	

    //compare to method for sorting
	public int compareTo(Card c){
        return rank - c.rank;
    }
	
    //toString method for printing cards
	public String toString(){
		// use this method to easily print a Card object
        return rankName + " of " + suitName;
	} 

    //method for getting suit of the card
    public int getSuit() {
        return suit;
    }
    
    //method for getting rank of card
    public int getRank() {
        return rank;
    }

}
  