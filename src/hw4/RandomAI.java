package hw4;
import java.util.Random;

public class RandomAI implements CFPlayer {

	@Override 
	public int nextMove(CFGame g){
		//System.out.println("reach random ai next move");
	   Random rand=new Random();
	   int next=rand.nextInt(7)+1;
	   while(!g.play(next)) {next=rand.nextInt(7)+1;}
	   //System.out.println("random chosen "+ next);
	   return next;
	}
	@Override 
	  public String getName() {
		return "RandomAI";
	  }
}
