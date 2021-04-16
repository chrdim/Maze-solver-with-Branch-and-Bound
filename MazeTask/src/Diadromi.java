 
public class Diadromi {

	private  int cost;
	private int solution[][];
	private int width;
	private int height;
	
	public Diadromi(int aCost,int aSolution[][],int aHeigth,int aWidth) {
		cost=aCost;
		solution=aSolution;
		width=aWidth;
		height=aHeigth;
	}
	
	
	
	public int getCost() {
		return cost;
	}
	
	public void printSolution() {
		 
		for (int i = 0; i < width; i++) { 
	            for (int j = 0; j < height; j++) 
	                System.out.print(" " + solution[i][j] + " "); 
	            System.out.println(); 
	        } 
	}
	
	public void printCost() {
		System.out.println("κόστος= "+cost);
	}
	
	public void printAll() {
		printSolution();
		printCost();
	}

}
