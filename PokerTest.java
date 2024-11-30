/*
Andrew Paternostro
asp2260
PokerTest.java is the class for running the Poker Game
*/
public class PokerTest {

    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        else{
            Game g = new Game(args);
            g.play();
        }
    }

}
