/*
Andrew Paternostro
asp2260
Game.java is the class for building a game
*/
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
public class Game {
	
	private Player p; 
	private Deck cards; //make priv
	private Scanner s; //for user input
	private int betamt; //amount user bet
	private int exchangeNum; //the number of exchanges user wants to do
	private String exchangeVal; //the actual card being exchanged
	private ArrayList<Card> prevExchanges; //arraylist of cards that came from an exchange
	private boolean validVal; //used for making sure user card input is valid
	private int payout; //payout to user
	

	//used for constructor when user gives hand
	char suitCharGivenHand; 
	int rankGivenHand;
	int suitGivenHand;
	boolean userInput = false;
	
	public Game(String[] testHand){
	
		p = new Player();
		for (String cardString : testHand) {
            
            suitCharGivenHand = cardString.charAt(0);
            rankGivenHand = Integer.parseInt(cardString.substring(1));

            // Map the suit to an integer (1-4) using classic switch
            switch (suitCharGivenHand) {
                case 'c':
                    suitGivenHand = 1; // Clubs
                    break;
                case 'd':
                    suitGivenHand = 2; // Diamonds
                    break;
                case 'h':
                    suitGivenHand = 3; // Hearts
                    break;
                case 's':
                    suitGivenHand = 4; // Spades
                    break;
            }
			p.addCard(new Card(suitGivenHand, rankGivenHand));
			
		}
		userInput = true;
		s = new Scanner(System.in);
		cards = new Deck(p.getArrayList());
		cards.shuffle();
	}	    
	
	// This no-argument constructor is to actually play a normal game
	public Game(){
		
		//new player, deck, creates hand
		p = new Player();
		cards = new Deck();
		cards.shuffle();
		s = new Scanner(System.in);
		for(int i = 0; i < 5; i++){
			p.addCard(cards.deal());
		}
	}
	

	public void play(){
		
		System.out.println("           Welcome to the Ultimate Game of POKER. You know the rules.");
		System.out.println("___________________________________________________________________________________");

		//This is just an if to add commentary if user inputs hand in command line and asks for bet
		if(userInput){
			System.out.println("Here is your manually entered hand (I see you like to win): ");
			for (Card c : p.getArrayList()) {
				System.out.println(c);
			}
			System.out.println("You start with " + p.getBankroll() + " tokens, and you may only bet between 1 - 5 at a time.");
			System.out.print("Betting amount: "); 
			betamt = s.nextInt();
			while(betamt>5 || betamt<1) {
				if(betamt>5){
					System.out.println("Silly... silly, you cannot bet more than 5.");
					System.out.print("Please enter a proper value: ");
					betamt = s.nextInt();
				}
				else if(betamt < 1) {
					System.out.println("Silly... silly, you cannot bet less than 1. ");
					System.out.print("Please enter a proper value: ");
					betamt = s.nextInt();
				}
			}
			p.bets((double)betamt); //logs bet
		}

		//Asks for bet and print hand if user does not input hand
		else{
			System.out.println("You start with " + p.getBankroll() + " tokens, and you may only bet between 1 - 5 at a time.");
			System.out.print("Betting amount: "); 
			betamt = s.nextInt();
			while(betamt>5 || betamt<1) {
				if(betamt>5){
					System.out.println("Silly... silly, you cannot bet more than 5.");
					System.out.print("Please enter a proper value: ");
					betamt = s.nextInt();
				}
				else if(betamt < 1) {
					System.out.println("Silly... silly, you cannot bet less than 1. ");
					System.out.print("Please enter a proper value: ");
					betamt = s.nextInt();
				}
			}
			p.bets((double)betamt);
			System.out.println("You are currently left with " + p.getBankroll() + " doubloons.");

			System.out.println("Your hand is as follows: ");
			for(Card c : p.getArrayList()) {
				System.out.println(c);
			}
		}
 
		//Does not allow game to proceed if user inputs exhange number greater than 5 of less than 
		System.out.print("How many cards (0 up to 5) would you like to exchange: ");
		exchangeNum = s.nextInt();
		while(exchangeNum<0 || exchangeNum>5) {
			if(exchangeNum>5){
				System.out.println("Silly... silly, you cannot exchange more than 5.");
				System.out.print("Please enter a proper value: ");
				exchangeNum = s.nextInt();
			}
			else if(exchangeNum < 0) {
				System.out.println("Silly... silly, you cannot exchange less than 0. ");
				System.out.print("Please enter a proper value: ");
				exchangeNum = s.nextInt();
			}
		}

		//creates new arraylist for previous exchange
		prevExchanges = new ArrayList<>();

		//while loop continues until number of desired exchanges becomes zero
		while(exchangeNum>0){
			System.out.print("What card would you like to exchange (use Rank of Suits format E.g. King of Hearts): ");
			
			//clears next line from prev nextInt
			if (s.hasNextLine()) {
				s.nextLine(); 
			}
			exchangeVal = s.nextLine().trim();
			validVal = false;

			//this checks that user exchanges a card in their deck
			while (!validVal) { 
		
				for (int i = 0; i < p.getArrayList().size(); i++) {
					if (exchangeVal.equalsIgnoreCase(p.getCard(i).toString())) {
						validVal = true; 
						p.removeCard(p.getCard(i)); 
						break;
					}
				}
			
				if (!validVal) { 
					System.out.println("You cannot remove those for which you do not have...");
					System.out.print("Please enter a card in your current deck: ");
					exchangeVal = s.nextLine().trim();
				}
			}
			
			//after the exchange and the card is removed, a new card replaces it
			p.addCard(cards.deal());
			prevExchanges.add(cards.getLastDealt());
			exchangeNum--;
			
			System.out.println("You chose to exchange: " + exchangeVal + " and got the " + cards.getLastDealt());
			System.out.println("Your new hand is as follows: ");
				for (Card c : p.getArrayList()) {
					System.out.println(c);
				}
			
			//This second while only occurs if there are 2 or more exchanges
			//It stores cards that replaced others and notes that those cannot be exchanged
			while (exchangeNum > 0) {
				// Inform the user of cards they cannot replace
				System.out.print("What is the next card you would like to remove? Note, it cannot be: ");
				
				for (int i = 0; i < prevExchanges.size();  i++) {
					System.out.print(prevExchanges.get(i));
					if (i < prevExchanges.size() - 1) {
						System.out.print(", ");
					} else {
						System.out.println(".");
					}
				}
					
				System.out.print("Enter here (maintain Rank of Suits format): ");
				exchangeVal = s.nextLine().trim();
				validVal = false;
			
				//Validation check for previously exchanged card
				while (!validVal) {
					boolean found = false;
			
					// Check if the card has already been exchanged
					for (Card card : prevExchanges) {
						if (exchangeVal.equalsIgnoreCase(card.toString())) {
							found = true;
							System.out.print("You already exchanged this card. Please enter a card you have not exchanged: ");
							exchangeVal = s.nextLine().trim();
							break;
						}
					}
					

					if (!found) {
						
						// Check if the card is in the player's current deck
						for (int i = 0; i < p.getArrayList().size(); i++) {
							if (exchangeVal.equalsIgnoreCase(p.getCard(i).toString())) {
								validVal = true;
								p.removeCard(p.getCard(i)); // Remove the card
								break;
							}
						}
			
						//same validation check as before for removing cards not in deck
						if (!validVal) {
							System.out.println("You cannot remove those for which you do not have...");
							System.out.print("Please enter a card in your current deck: ");
							exchangeVal = s.nextLine().trim();
						}
					}
				}
				//replaces exchanged card
				p.addCard(cards.deal());
				prevExchanges.add(cards.getLastDealt());
				exchangeNum--;

				//statements after exchange
				System.out.println("You chose to exchange: " + exchangeVal + " and got " + cards.getLastDealt());
				System.out.println("Your new hand is as follows: ");
				for (Card c : p.getArrayList()) {
					System.out.println(c);
				}

			}
		}
		//final output about winnings
		System.out.println("You got " + checkHand(p.getArrayList()) + " and your payout is " + (payout*betamt) + ".");
		System.out.println("Now you have " + (p.getBankroll()+(payout*betamt)));
		System.out.println("           Thanks for playing!!!");
		System.out.println("___________________________________________");
	}

	/* 
	 * The below methods are helper methods to determine the value of hand
	 * To avoid repeating comments, the methodname returns true if that hand 
	 * type is present. E.g. isHandName returns true if HandName is true in the deck
	*/

	private boolean isRoyalFlush(ArrayList<Card> hand) { 
		Collections.sort(hand);
		
		// Get the suit of the first card
		int suit = hand.get(0).getSuit();
		
		// Check that all cards have the same suit
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i).getSuit() != suit) {
				return false; // Not the same suit
			}
		}
		
		// Check if the hand starts with an Ace
		 if (hand.get(0).getRank() != 1) {
			return false; // Does not start with an Ace
		}
	
		// Check for Royal Flush ranks: Ace, 10, Jack, Queen, King
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i).getRank() != 9 + i) { // 10, 11, 12, 13 correspond to 9 + index
				return false; // Ranks do not match Royal Flush sequence
			}
		}
		
		// If all checks pass, it's a Royal Flush
		return true;
	}

	private boolean isFourOfAKind(ArrayList<Card> hand) {
		Collections.sort(hand);
		
		for (int i = 0; i < hand.size(); i++) {
			int count = 0;
			int rank = hand.get(i).getRank();
			
			// Count how many cards have the same rank
			for (int j = 0; j < hand.size(); j++) {
				if (hand.get(j).getRank() == rank) {
					count++;
				}
			}
	
			// If 4 cards have the same rank, return true
			if (count == 4) {
				return true;
			}
		}
		return false; // No rank appears 4 times
	}
	
	private boolean isFlush(ArrayList<Card> hand) {
		int suit = hand.get(0).getSuit();

		//checks if all other cards have same suit as card in 0 index
		for(int i = 1; i < hand.size() ; i++){
			if(suit != hand.get(i).getSuit()){
				return false;
			}
		}
		return true;
	}

	private boolean isStraight(ArrayList<Card> hand) {
		Collections.sort(hand);

		//this is case with ace high straight 
		if(hand.get(0).getRank()==1 && hand.get(1).getRank() != 2){
			int count = 0;
			for(int i= 1; i < hand.size() ; i++){
				if(hand.get(i).getRank()  != (10+count)){
					return false;
				}
				count++;
			}
			return true;
		}

		//this is case for any other straight
		else{
			for(int i = 0; i<hand.size()-1; i++){
				if((hand.get(i).getRank()+1) != hand.get(i+1).getRank()){
					return false;
				}
			}
			return true;
		}
	}
 
	private boolean isStraightFlush(ArrayList<Card> hand) {
		if(isStraight(hand) == true && isFlush(hand)== true){
			return true;
		}
		else{
			return false;
		}
	}

	private boolean isFullHouse(ArrayList<Card> hand) {
		Collections.sort(hand);
		
		//got rank value of each hand
		int rank = hand.get(0).getRank();
		int rank1 = hand.get(1).getRank();
		int rank2 = hand.get(2).getRank();
		int rank3 = hand.get(3).getRank();
		int rank4 = hand.get(4).getRank();
		if (rank == rank1 && rank == rank2 && rank3 == rank4) {
			return true;
		}
	
		// Case 2: Two of the same rank, followed by three of another rank
		else if (rank == rank1 && rank2 == rank3 && rank2 == rank4) {
			return true;
		}
	
		else {// Not a Full House
		return false;
		}

	}

	private boolean isThreeOfAKind(ArrayList<Card> hand) {
		Collections.sort(hand);
		
		for (int i = 0; i < hand.size(); i++) {
			int count = 0;
			int rank = hand.get(i).getRank();
			
			// Count how many cards have the same rank
			for (int j = 0; j < hand.size(); j++) {
				if (hand.get(j).getRank() == rank) {
					count++;
				}
			}
	
			// If 3 cards have the same rank, return true
			if (count == 3) {
				return true;
			}
		}
		return false; // No rank appears 3 times
	}

	private boolean isTwoPairs(ArrayList<Card> hand) {
		Collections.sort(hand);
		int count = 0; //used for seeing if 2 pairs
		
		//records count for each pair
		for (int i = 0; i < hand.size() - 1; i++) {
			if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
				count++;
				i++; // Skip the next card since it's part of this pair
			}
		}

		//true if 2 pairs
		if(count==2){
			return true;
		}
		else{
			return false;
		}

	}


	private boolean isOnePair(ArrayList<Card> hand) {
		Collections.sort(hand);
		
		//if pair is present return true
		for(int i = 0; i<hand.size(); i++){
			for(int j = i+1; j<hand.size(); j++){
				if(hand.get(i).getRank()== hand.get(j).getRank()){
					return true;
				}
			}
		}
		return false;
	}
	
	private String checkHand(ArrayList<Card> hand){
	// returns payout and string for card hand type
	
		if (isRoyalFlush(hand)) {
			payout = 250;
			return "a Royal Flush (if you did not cheat, the odds of this are 649,739 : 1),";
		} 
		else if (isStraightFlush(hand)) {
			payout = 50;
			return "a Straight Flush,";
		} 
		else if (isFourOfAKind(hand)) {
			payout = 25;
			return "Four of a Kind,";
		} 
		else if (isFullHouse(hand)) {
			payout = 6;
			return "a Full House (great show),";
		} 
		else if (isFlush(hand)) {
			payout = 5;
			return "a Flush,";
		} 
		else if (isStraight(hand)) {
			payout = 4;
			return "a Straight,";
		} 
		else if (isThreeOfAKind(hand)) {
			payout = 3;
			return "a Three of a Kind,";
		} 
		else if (isTwoPairs(hand)) {
			payout = 2;
			return "Two Pairs,";
		} 
		else if (isOnePair(hand)) {
			payout = 1;
			return "a One Pair,";
		} 
		else {
			payout = 0;
			return "NOTHING :( ,";
		}
	}
}
