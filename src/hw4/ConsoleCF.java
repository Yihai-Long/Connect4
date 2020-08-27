package hw4;
import java.util.Random;
import java.util.Scanner;
public class ConsoleCF extends CFGame {
	 private int order; //1: Ai first,2: human first, 3 ai1 first,4 ai2 first
	 private CFPlayer player1;
	 private CFPlayer player2;
	 private ConsoleCF.HumanPlayer human;
	 private Random rand=new Random(); 
	 
	 public ConsoleCF (CFPlayer ai){
		if(rand.nextInt(2)==0) order=1;
		else order=2;
		human= this.new HumanPlayer();
		player1=ai;
	 }
	 
	 public ConsoleCF (CFPlayer ai1, CFPlayer ai2){
		 if(rand.nextInt(2)==0) order=3;
		 else order=4;
		 player1=ai1;
		 player2=ai2;
			
    }
	 

	 
	 private class HumanPlayer implements CFPlayer{
		Scanner  reader= new Scanner(System.in);
		 @Override
		public  int nextMove(CFGame g) {
			 g.print();
			 System.out.println("Please choose a column to play");
			 int next=reader.nextInt();
			 while(!g.play(next)) { 
				 System.out.println("Please choose a valid column");
				 next=reader.nextInt();}
			 return next; }
		
		
		 
		 @Override
		 public  String getName() {
			 return "Human Player"; }
	}
	
	
	
	 public void playOut () {
		int next;
		if(order==1||order==2) {
			//Ai V.S. Human mode
			if(order==1) {//ai plays first
				System.out.println("AI goes first, marks 1");
				while(!isGameOver()) {
					next=player1.nextMove(this);			
					setState(next);
					if(!isGameOver()) {
					next=human.nextMove(this);
					setState(next);}
					}	
			
				}
			else {//human plays first
				System.out.println("Human go first, marks 1");
				
				while(!isGameOver()) {
			
					next=human.nextMove(this);
					setState(next);
					if(!isGameOver()) {
					next=player1.nextMove(this);
					setState(next);  } 
					}	
			
				}

		}
		//Ai V.S. Ai Mode
		else if(order==3) {//ai1 plays first
			while(!isGameOver()) {
				next=player1.nextMove(this);
				setState(next);
				if(!isGameOver()) {
				next=player2.nextMove(this);
				setState(next);
				}
			
			}
			
		}
		else {//ai2 plays first
			while(!isGameOver()) {
				
				next=player2.nextMove(this);
				setState(next);
				if(!isGameOver()) {
				next=player1.nextMove(this);
				setState(next);
				} 
			
		}
			
}
	 if(winner()==1||winner()==-1) System.out.println(getWinner()+ " wins!");
	 else System.out.println("This is a draw! ");
	 print();
	
}
	
	
			
		public String getWinner () {
		if( winner()==1) {
			if(order==1)
				return player1.getName();
			else if (order==2)
				return human.getName();
			else if (order==3)
				return player1.getName();
			else 
				return player2.getName();}
		else if( winner()==-1) {
			if(order==1)
				return human.getName();
			else if (order==2)
				return player1.getName();
			else if (order==3)
				return player2.getName();
			else 
				return player1.getName();}
		else return "draw";
	
	}
	


}
