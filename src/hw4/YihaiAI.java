package hw4;
import java.util.Random;

public class YihaiAI implements CFPlayer {
	
	

	@Override
	public int nextMove(CFGame g){
		//System.out.println("reach yihai ai next move");
		int next=0;
		int color=g.getTurn();
		
		next=g.check3(color);
		if(next!=-2&&g.play(next))return next;
		next=g.check13(color);
		if(next!=-2) return next;
		next=g.check3(-color);
		if (next!=-2&&g.play(next)) return next;
		next=g.check13(-color);
		if(next!=-2) return next;
	
		
		  //System.out.println("Yihai chooses randomly " );
		   Random rand=new Random();
		   next=rand.nextInt(7)+1;
		   while(!g.play(next)) {next=rand.nextInt(7)+1;}
		   //System.out.println("Yihai chooses " + next);
		   return next;
	
}
	
	
	
	
	
	@Override
	public String getName() {
	return	"Yihai's AI";
	}
	
	
	

}
