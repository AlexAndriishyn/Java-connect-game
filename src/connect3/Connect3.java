package connect3;

/**
 *
 * @author JARET
 */
public class Connect3 {

    public static void main(String[] args) {
        GameBoard myGame = new GameBoard();        
        int count = 1;
        
        System.out.print(myGame.boardToString());   
        
        while (myGame.stillPlaying() && count < 25)
        {
            
            if (count % 2 != 0)     //check to see who's turn it is
                myGame.makeMove(1);
            else
                myGame.makeMove(2);
            
            System.out.print(myGame.boardToString());
            myGame.checkWinner();            
            
            count++;            
            
            if (count == 25)
                System.out.print("\nStale mate!");
        }   //end of while loop
    }   //end of main method
}   //end of class