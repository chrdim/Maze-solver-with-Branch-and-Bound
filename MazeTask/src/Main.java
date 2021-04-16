
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 int maze[][] = {  { 0, 0, 0, 0, 0 }, 
				  		   { 0, 1, 1, 1, 0 }, 
                           { 0, 1, 0, 1, 1 }, 
                           { 0, 1, 0, 1, 0 }, 
                           { 1, 1, 1, 1, 0 }, 
                           { 0, 0, 0, 0, 0 } }; 
		
		
		//πρώτα y και μετά x
		Maze M=new Maze(5,6,maze,4,0,2,4);
		M.solveTheMaze(); 
		
	}

}
