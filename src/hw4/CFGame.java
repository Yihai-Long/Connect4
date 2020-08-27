package hw4;

public class CFGame {
  //state[i][j]= 0 means the i,j slot is empty
  //state[i][j]= 1 means the i,j slot has red
  //state[i][j]=-1 means the i,j slot has black
  private final int[][] state;
  private boolean isRedTurn;
  private boolean redwin=false;
  private boolean blackwin=false;
 
  
  {
    state = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        state[i][j] = 0;
    isRedTurn = true; //red goes first
  }
    
  public int[][] getState() {
    int[][] ret_arr = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        ret_arr[i][j] = state[i][j];
    return ret_arr;
  }
  
  
  public boolean isRedTurn() {
    return isRedTurn;
  }
  
  public boolean play(int column) {
      if(column>7 || column <1) return false;
      
      int count=0;
      for(int i=0; i<6;i++){
          if (state[column-1][i]!=0) count++;
      }
      if (count==6) return false;
      else return true;
    	 
  } 
 
  public void setState(int col) {
	  
	  if(isRedTurn) {
	  		
	  		state[col-1][height(col-1)]=1;
	  		isRedTurn=false;
	  		}
	  	else {
	  		
	  		state[col-1][height(col-1)]=-1;
	  		isRedTurn=true;
	  		}
	  
	  
  }
  
  public int getTurn(){
	  if(isRedTurn) return 1;
	  else return -1;
	  
  }
  
  public boolean isGameOver() {
	 //make a copy
	 
      int[][] statecopy = new int[7][6];
      for (int i=0; i<7; i++){
          for (int j=0; j<6; j++){
              statecopy[i][j] = state[i][j];
            }
          }
      
      //check if there is a possible move
      int count=0;
      for (int i=0; i<7; i++){
          for (int j=0; j<6; j++){
              if(statecopy[i][j] == 0)
                  count++;}
      }
      if(count==0) return true;
      
     
    //check horizontally
      for(int i = 0; i < 4; i++){
          for(int j = 0; j<6; j++){
              if(statecopy[i][j] == statecopy[i+1][j] && statecopy[i+1][j] == statecopy[i+2][j] && statecopy[i+2][j] == statecopy[i+3][j]){
                  if(statecopy[i][j] == 1){
                      redwin = true;
                      return true;}
                  else if(statecopy[i][j] == -1){
                      blackwin = true;
                      return true; }
              }
          }
      }
    
      
      //check vertically
      for(int i = 0; i < 7; i++){
          for(int j = 0; j<3; j++){
              if(statecopy[i][j] == statecopy[i][j+1] && statecopy[i][j+1] == statecopy[i][j+2] && statecopy[i][j+2] == statecopy[i][j+3]){
                  if(statecopy[i][j] == 1){
                      redwin = true;
                      return true; }
                  else if(statecopy[i][j] == -1){
                      blackwin = true;
                      return true;}
              }
          }
      }
    
      
      //check diagonal line (/)
      for(int i = 0; i<4; i++){
          for(int j = 0; j<3; j++){
              if(statecopy[i][j] == statecopy[i+1][j+1] && statecopy[i+1][j+1] == statecopy[i+2][j+2] && statecopy[i+2][j+2] == statecopy[i+3][j+3]){
                  if(statecopy[i][j] == 1){
                      redwin = true;
                      return true; }
                  else if(statecopy[i][j] == -1){
                      blackwin = true;
                      return true;}
              }
          }
          
          
      }
     
      //check  diagonal line(\)
      for(int i = 6; i>2; i--){
          for(int j = 0; j<3; j++){
              if(statecopy[i][j] == statecopy[i-1][j+1] && statecopy[i-1][j+1] == statecopy[i-2][j+2] && statecopy[i-2][j+2] == statecopy[i-3][j+3]){
                  if(statecopy[i][j] == 1){
                      redwin = true;
                      return true;}
                  else if(statecopy[i][j] == -1){
                      blackwin = true;
                      return true;}
              }
          }
      }
     
	  
	  return false;
  }
  
  public int winner() {
    
	if (redwin) return 1;
    else if (blackwin) return -1;
    else return 0;
  }

       //return the height of a column
		int height(int i){
			int sum=0;
			for(int j=0;j<6;j++) {
				if(state[i][j]!=0) sum++;
			}
			return sum;
			
		}
		
		public void print() {
			for(int j=5;j>=0;j--) {
				System.out.println(state[0][j]+"           "+state[1][j]+"           "+state[2][j]+"           "+state[3][j]+
								"           "+state[4][j]+"           "+state[5][j]+"           "+state[6][j]);
			}
			System.out.println("--------------------------------------------------------------");
			System.out.println();
		
		}
		
		
		
		
		public int check3(int c) {
			int[][] copy=getState();
			//check horizontally
		      for(int i = 0; i < 5; i++){
		          for(int j = 0; j<6; j++){
		        	  if(copy[i][j] == copy[i+1][j] && copy[i+1][j] == copy[i+2][j]&&copy[i][j]==c){
		            	  	if(i<=3) {if(copy[i+3][j]==0 && height(i+3)==j) return i+4;}
		            	  	if(i>=1&&i<=4) {if(copy[i-1][j]==0 && height(i-1)==j) return i;}}
		          }
		      }
		    
		      //check vertically
		      for(int i = 0; i < 7; i++){
		          for(int j = 0; j<3; j++){
		              if(copy[i][j] == copy[i][j+1] && copy[i][j+1] == copy[i][j+2]&&copy[i][j]==c){
		            	  if(copy[i][j+3]==0) return i+1;
		              }
		          }
		      }
		    
		      //check diagonal line (/)
		      for(int i = 0; i<4; i++){
		          for(int j = 0; j<3; j++){
		              if(copy[i][j] == copy[i+1][j+1] && copy[i+1][j+1] == copy[i+2][j+2] && copy[i][j]==c){
		            	  	if(i<=3&&j<=2) {if(copy[i+3][j+3]==0&&height(i+3)==j+3)return i+4;}
		            	  	if(i==4&&j<=3&&j>=1) {if(copy[i-1][j-1]==0&&height(i-1)==j-1) return i;}
		            	  	if(i<=4&&i>=1&&j==3) {if(copy[i-1][j-1]==0&&height(i-1)==j-1) return i;}}
		          }
		          
		          
		      }
		      //check  diagonal line(\)
		      for(int i = 6; i>2; i--){
		          for(int j = 0; j<3; j++){
		              if(copy[i][j] == copy[i-1][j+1] && copy[i-1][j+1] == copy[i-2][j+2] && copy[i][j]==c){
		            	  	if(i>=3&&j<=2) {if(copy[i-3][j+3]==0&&height(i-3)==j+3) return i-2;}
		            	  	if(i==2&&j<=3&&j>=1) {if(copy[i+1][j-1]==0&&height(i+1)==j-1) return i+2;}
		            	  	if(i<=5&&i>=2&&j==3) {if(copy[i+1][j-1]==0&&height(i+1)==j-1) return i+2;}}
		              }
		          
		      }
		     
		      //System.out.println("end of check3");
		      return -2;
			
			
	}
		
		
		public int check13(int c) {
			
		int[][] copy=getState();
		for(int j=0;j<6;j++) {
			for(int i=0;i<4;i++) {
				if(copy[i][j] == copy[i+1][j] && copy[i][j] == copy[i+3][j]&&copy[i][0]==c&&copy[i+2][0]==0&&height(i+2)==j) 
					return i+3;
				if(copy[i][j] == copy[i+2][j] && copy[i][j] == copy[i+3][j]&&copy[i][j]==c&&copy[i+1][0]==0&&height(i+1)==j) 
					return i+2;}
			for(int i=0;i<3;i++) {
				if(copy[i][j] == copy[i+1][j] && copy[i][j] == copy[i+3][j]
					&&copy[i][j] == copy[i+4][j]&&copy[i][j]==c&&copy[i+2][j]==0&&height(i+2)==j) 
					return i+3;}
			}
			
			return -2;
			
			
		}	
		
		
		
		

}



