import java.util.ArrayList;
import java.util.Collections;

public class Maze {

	private int height;
	private int width;
	private int maze[][];
	private int solution[][]= {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
			
	private ArrayList<Integer>liveNodesX=new ArrayList<>();
	private ArrayList<Integer>liveNodesY=new ArrayList<>();
	
	private int arxiX;
	private int arxiY;
	private int telosX;
	private int telosY;
	
	private ArrayList<Diadromi>diadromes=new ArrayList<>();
	
	
	private ArrayList<Integer>nodesWithChildrenX=new ArrayList<>();
	private ArrayList<Integer>nodesWithChildrenY=new ArrayList<>();
    
	 
	
	
	public Maze(int aHeight,int aWidth,int aMaze[][],int anArxiX,int anArxiY,int aTelosX,int aTelosY) {
		height=aHeight;
		width=aWidth;
		maze=aMaze;
		arxiX=anArxiX;
		arxiY=anArxiY;
		telosX=aTelosX;
		telosY=aTelosY;
		
		
	}
	
    public void printEveryThing() {
    	
		System.out.println("LiveNodes");
		for(int i=0;i<liveNodesX.size();i++) {
			System.out.println(liveNodesX.get(i)+","+liveNodesY.get(i));
		}
		
		System.out.println("Κόμβοι με παραπάνω από ένα παιδί");
		for(int i=0;i<nodesWithChildrenX.size();i++) {
			System.out.println(nodesWithChildrenX.get(i)+","+nodesWithChildrenY.get(i));
		}
		
		System.out.println("Τωρινή κατάσταση λαβυρίνθου:");
		for (int i = 0; i < width; i++) { 
            for (int j = 0; j < height; j++) 
                System.out.print(" " + maze[i][j] + " "); 
            System.out.println(); 
        }
		
		System.out.println("Τωρινή κατάσταση λύσης");
		for (int i = 0; i < width; i++) { 
            for (int j = 0; j < height; j++) 
                System.out.print(" " + solution[i][j] + " "); 
            System.out.println(); 
        } 
    }
	
	
	public void hasMoreThanOneChild(int aX,int aY) {
		
		int x=aX;
		int y=aY;
		int counter=0;
		if(this.giveXFromLeftChild(x,y)!=0) 
			 counter++;
		
		if(this.giveXFromRightChild(x,y)!=0) 
			 counter++;
		
		if(this.giveYFromDownChild(x,y)!=0) 
			counter++;
			
		if(this.giveYFromUpChild(x,y)!=0) 
			counter++;
			
			
		if(counter>1) {
			
			nodesWithChildrenX.add(x);
			nodesWithChildrenY.add(y);
		}
	}
	
	
	
	//κίνηση πάνω
	public int giveYFromUpChild(int aX,int anY) {
		
		int x=aX;
		int y=anY;
		
		if((( y+1>=0) && (y+1)<height)==true){
			if(maze[(x)][y+1]==1){
			
			return y+1;
			}
			else return 0;
			
		}else
			return 0;
		
		
	}
	
	//κίνηση κάτω
	public int giveYFromDownChild(int aX,int anY) {
			
		int x=aX;
		int y=anY;
			
		if(( (y-1)>=0 && (y-1)<height)==true){
			if(maze[(x)][y-1]==1) {
				
			return y-1;
			}
			else return 0;
				
		}else
			return 0;
			
	}
	//κίνηση μπροστά
	public int giveXFromRightChild(int aX,int anY) {
		
		int x=aX;
		int y=anY;
		
		if(( (x+1)>=0 && (x+1)<width)==true){
			if( maze[(x+1)][y]==1) {
			
			return x+1;
			
			}else return 0;
			
		}else
			return 0;
		
	}	
	//κίνηση πίσω
	public int giveXFromLeftChild(int aX,int anY) {
		
		int x=aX;
		int y=anY;
		
		if(( (x-1)>=0 && (x-1)<width)==true){
			if(maze[(x-1)][y]==1) {
			
			return x-1;
			}else return 0;
			
		}else
			return 0;
		
	}	
	
	
	
	
	public int findTheMinInLiveNodesX() {
		
		return(liveNodesX.indexOf(Collections.min(liveNodesX)));
	}
	
	
	
	public boolean hasNeighbour(int aX,int aY) {
		
		int counter=0;
		
		if(this.giveYFromDownChild(aX,aY)!=0) {
			counter++;
		}
		if(this.giveYFromUpChild(aX,aY)!=0) {
			counter++;
		}
		if(this.giveXFromRightChild(aX,aY)!=0) {
			counter++;
		}
		if(this.giveXFromLeftChild(aX,aY)!=0) {
			counter++;
		}
		
		if(counter!=0)
			return true;
		else
			return false;
		
	}
	
	
	
	
	public void findASolution() {
		
		System.out.println("καινούρια προσπάθεια διάσχισης");
		
		int maze2[][]=maze;

		int e_nodeX=arxiX;
		int e_nodeY=arxiY;
	
		int costCounter=0;
		
		boolean breaked=false;
		
		while(e_nodeX!=telosX || e_nodeY!=telosY) {
		
		  System.out.println("Είμαστε στο τετράγωνο "+e_nodeX+","+e_nodeY);
			
			if(hasNeighbour(e_nodeX,e_nodeY)==true) {
			   
			if(this.giveYFromDownChild(e_nodeX,e_nodeY)!=0) {
				liveNodesX.add(e_nodeX);
				liveNodesY.add(e_nodeY-1);
				
			}
			if(this.giveYFromUpChild(e_nodeX,e_nodeY)!=0) {
				liveNodesX.add(e_nodeX);
				liveNodesY.add(e_nodeY+1);
			}
			if(this.giveXFromRightChild(e_nodeX,e_nodeY)!=0) {
				liveNodesX.add(e_nodeX+1);
				liveNodesY.add(e_nodeY);
			}
			if(this.giveXFromLeftChild(e_nodeX,e_nodeY)!=0) {
				liveNodesX.add(e_nodeX-1);
				liveNodesY.add(e_nodeY);
			}
			
			solution[e_nodeX][e_nodeY]=1;
			maze[e_nodeX][e_nodeY]=0;
			
			
		     this.hasMoreThanOneChild(e_nodeX,e_nodeY);
			
		    	
			int minIndex=this.findTheMinInLiveNodesX();
			
		    e_nodeX=liveNodesX.get(minIndex);
		    e_nodeY=liveNodesY.get(minIndex);
			
			
		    liveNodesX.clear();
		    liveNodesY.clear();
		    	
			
			this.printEveryThing();
		   
		    	
			System.out.println("αλλάζουμε κόμβο");
			
		    costCounter++;
		    System.out.println("Τωρινό κόστος: "+costCounter);
		    }else if(e_nodeX!=telosX && e_nodeY!=telosY) {
		    	
		    	breaked=true;
		    	break;
		    
		    }else if(e_nodeX==telosX && e_nodeY==telosY) {
		    	solution[e_nodeX][e_nodeY]=1;
		    	
		    }
		    	
		  }
		
		
		
		  if(breaked==false) {
			
			
			  
			 solution[telosX][telosY]=1;
			
			  
			 
			  System.out.println("αποθήκευση λύσης");
			  diadromes.add(new Diadromi(costCounter,solution,height,width));
			 
			  System.out.println("αποθήκευση της παρακάτω λύσης με κόστος: "+costCounter);
			  for (int i = 0; i < width; i++) { 
		            for (int j = 0; j < height; j++) 
		                System.out.print(" " + solution[i][j] + " "); 
		            System.out.println(); 
		        } 
			  
			  System.out.println("Κατάσταση λαβυρίνθου μετά την διάσχιση:");
			
			  for (int i = 0; i < width; i++) { 
		            for (int j = 0; j < height; j++) 
		                System.out.print(" " + maze[i][j] + " "); 
		            System.out.println(); 
		        }
		    
		    
		     
			
		
		 }else {
			
			
			
			
			maze=maze2;
			for (int k = 0; k < width; k++) { 
	            for (int j = 0; j < height; j++) { 
	               solution[k][j]=0;
	              
	               
	           } 
	        }
			 System.out.println("Δεν υπάρχει δυνατότητα μετακίνης,οπότε σταματάμε");
			 System.out.println("Κατάσταση λαβυρίνυου:");
				
			  for (int i = 0; i < width; i++) { 
		            for (int j = 0; j < height; j++) 
		                System.out.print(" " + maze[i][j] + " "); 
		            System.out.println(); 
		        }
			 
			
			
		}
		
		
		
	}
	
	
	
		public void remakeMaze() {
			
				
		        maze[arxiX][arxiY]=1;
				
		
				int i=nodesWithChildrenX.size()-1;
				maze[nodesWithChildrenX.get(i)][nodesWithChildrenY.get(i)]=1;
			
			
				nodesWithChildrenX.remove(i);
				nodesWithChildrenY.remove(i);
			
			
		}
		
	
	
		public boolean telosEpilogwnDiasxisis() {
			return nodesWithChildrenY.isEmpty();
		}
	
	
		
		
		
		
	   public void solveTheMaze() {
		   int bestCost=200000000;
			Diadromi best=null;
			
			findASolution();
          
			while(telosEpilogwnDiasxisis()==false) {
				for (int i = 0; i < width; i++) { 
		            for (int j = 0; j < height; j++) 
		                solution[i][j]=0; 
				}
				this.remakeMaze();
				System.out.println("Τέλος διάσχισης");
				findASolution();
				
			}
			
			for(Diadromi d:diadromes) {
				
				if(d.getCost()<bestCost) {
					bestCost=d.getCost();
					best=d;
					
				}
			
			}
			
			if(best!=null) {
			
				System.out.println("Η καλύτερη λύση είναι: ");
				best.printAll();
			}else {
				System.out.println("Δεν υπάρχει λύση");
			}
	   }
		
		
	}

