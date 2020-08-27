package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class GUICF extends CFGame{
	//fields
	private int mode;
	private GameBoard this_board;
	private CFPlayer player1;
	private CFPlayer player2;
	//private HumanPlayer human;
	private Random rand=new Random(); 
	private boolean done;
	private boolean start=false;
	JFrame frame= new JFrame("Connect Four");
	Container pane = frame.getContentPane();
	JLabel[][] board= new JLabel[7][6];
	JPanel panel=new JPanel();
	JButton[] buttons= new JButton[7];
	
	
	
	//inner class GameBoard
	private class GameBoard extends JPanel{
	
		
		//inner class constructor
		private GameBoard(){
			frame.setSize(300, 200);
			frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panel.setLayout(new GridLayout(6,7));
			for(int i=0;i<7;i++) {
				for(int j=0;j<6;j++) {
					board[i][j]= new JLabel("");
					board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}
			
			 for(int j = 5; j>=0; j--){
	                for(int i = 0; i<7; i++){

					panel.add(board[i][j]);
				}
			}
			
			pane.add(panel, BorderLayout.CENTER);
			
		}
		
		//method paint
		private void paint(int x, int y, int color) {
		if(color==1) {
			board[x][y].setBackground(Color.red);
			board[x][y].setOpaque(true);}
		else {
			board[x][y].setBackground(Color.black);
		board[x][y].setOpaque(true);}}
		 
	
	
	}
	//constructor 
	public GUICF ( CFPlayer ai ) {
		if(rand.nextInt(2)==0) mode=1;
		else mode=2;
		player1=ai;
		this_board=new GameBoard();
		//add the top panel
		JPanel panel1= new JPanel();
		//add buttons
		for(int i=0;i<7;i++) buttons[i]=new JButton("\u2193");
		panel1.setLayout(new GridLayout(1,7));
		for(int i=0;i<7;i++) panel1.add(buttons[i]);
		//add actionlistners to buttons
		buttons[0].addActionListener(new B1L());
		buttons[1].addActionListener(new B2L());
		buttons[2].addActionListener(new B3L());
		buttons[3].addActionListener(new B4L());
		buttons[4].addActionListener(new B5L());
		buttons[5].addActionListener(new B6L());
		buttons[6].addActionListener(new B7L());
		pane.add(panel1, BorderLayout.NORTH);

		playout();
		
	}
	
	//constructor
	public GUICF ( CFPlayer ai1 , CFPlayer ai2 ) {
		this_board=new GameBoard();
		if(rand.nextInt(2)==0) mode=3;
		 else mode=4;
		 player1=ai1;
		 player2=ai2;
		 JButton b=new JButton("Play");
		 b.setPreferredSize(new Dimension(20,20));
		 b.addActionListener(new play_button());
		 pane.add(b, BorderLayout.NORTH);

		 playout();
	}
	
	
	//GUI method playGUI
	private boolean playGUI (int c) {
		if(play(c)) {
			if(getTurn()==1) {
			//System.out.println("Red paint column "+c+"  height "+height(c-1));
			this_board.paint(c-1,height(c-1),1);
			setState(c);}
			else { 
			this_board.paint(c-1,height(c-1),-1);
			setState(c);
			//System.out.println("black paint column "+c+"  height "+height(c-1));
			}
			return true;
		}
		else 
			return false;}
	
	
	
	private void playout() {
		int next;
	if(mode==1) {//ai first
				System.out.println("ai is red");
				done=true;
				while(!isGameOver()) {
					if(done==true) {
						print();
						//System.out.println("----------------------------");
						next=player1.nextMove(this);	
						playGUI(next);
						print();
						//System.out.println("ai done, human's turn");
						done=false; //ai is done
						if(isGameOver()) {
							if(winner()!=0)
								System.out.println("Game Over. "+ getwinner()+" wins!");
								else System.out.println("Game Over, this is a draw");
							return;}
						
					}
				}
			
		//end of 1
			}
	if(mode==2) {//human first
			System.out.println("human is red");
				done=false;
				while(!isGameOver()) {
					if(done==true) {
						//print();
						//System.out.println("----------------------------");
						next=player1.nextMove(this);	
						playGUI(next);
						
						//print();
						//System.out.println("ai done, human's turn");
						done=false; //ai is done
						if(isGameOver()) {
							if(winner()!=0)
								System.out.println("Game Over. "+ getwinner()+" wins!");
								else System.out.println("Game Over, this is a draw");
							return;}
						
					}
					
					
					}
			//end of 2	
			}
			
		

		
			if(mode==3) {//ai1 first
				System.out.println("ai 1 is red");
				while(!isGameOver()) {
					if(start==true) {
					//System.out.println("ai1 first");
					next=player1.nextMove(this);
					playGUI(next);
					start=false;
					if(isGameOver()) { 
						if(winner()!=0)
							System.out.println("Game Over. "+ getwinner()+" wins!");
							else System.out.println("Game Over, this is a draw");
						return;
					}
					
					if(!isGameOver()) {
						if(start==true) {
					next=player2.nextMove(this);
					playGUI(next);
					start=false;
					//print();
					}}else {
						if(winner()!=0)
							System.out.println("Game Over. "+ getwinner()+" wins!");
							else System.out.println("Game Over, this is a draw");
						return;}
					}
				}
			
			//end of 3	
			}
			
			if(mode==4) {//ai2 first
				System.out.println("ai 2 is red");
				while(!isGameOver()) {
					if(start==true) {
					next=player2.nextMove(this);
					playGUI(next);
					start=false;
					if(isGameOver()) { 
						if(winner()!=0)
							System.out.println("Game Over. "+ getwinner()+" wins!");
							else System.out.println("Game Over, this is a draw");
						return;
					}
				
					if(!isGameOver()) {
						if(start==true) {
					next=player1.nextMove(this);
					playGUI(next);
					start=false;
					}}else {
						if(winner()!=0)
						System.out.println("Game Over. "+ getwinner()+" wins!");
					else System.out.println("Game Over, this is a draw");System.out.println("Game Over "+ getwinner());
						return;}
				
				}
				}
			
			}
			if(isGameOver()) {
				if(winner()!=0)
				System.out.println("Game Over. "+ getwinner()+" wins!");
				else System.out.println("Game Over, this is a draw");
				return;}
		
	
	}
	
	
	public String getwinner () {
		if( winner()==1) {
			if(mode==1)
				return player1.getName();
			else if (mode==2)
				return "Hunam";
			else if (mode==3)
				return player1.getName();
			else 
				return player2.getName();}
		else if( winner()==-1) {
			if(mode==1)
				return "Hunam";
			else if (mode==2)
				return player1.getName();
			else if (mode==3)
				return player2.getName();
			else 
				return player1.getName();}
		else return "draw";
	
	}
	
///////class button action listener/////////////////
	private class B1L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(1)) {
			playGUI(1);
			done=true;}
		}
	}
	private class B2L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(2)) {
				playGUI(2);
				done=true;}
		}
	}
	private class B3L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(3)) {
				playGUI(3);
				done=true;}
		}
	}
	private class B4L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(4)) {
				playGUI(4);
				done=true;}
		}
	}
	private class B5L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(5)) {
				playGUI(5);
				done=true;}
		}
	}

	private class B6L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(6)) {
				playGUI(6);
				done=true;}
		}
	}
	private class B7L implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()&&play(7)) {
				playGUI(7);
				done=true;}
		}
	}
	private class play_button implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(!isGameOver()) {
			start=true;}
		}
	}
	
	/////////////////////////////////////////////////////


}
