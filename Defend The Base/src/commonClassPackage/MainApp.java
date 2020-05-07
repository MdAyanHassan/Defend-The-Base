/* This is a representation of the defend the base game
 * It represents the board for the game and put allies and enemies on the board randomly
 * It also puts the missiles randomly in the last row to attack the enemies
 * The game is not yet ready because the move function is getting on my nerves 
 * P.S - The game is finally ready, hurrah!, took me some 3 days to figure out the move function
 * but realized it was not that hard, hehe.
 */
package commonClassPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class MainApp {
   
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int row = 0, col = 0, missiles = 0;

		try {
			System.out.println("Enter board row size");
			row = input.nextInt();
			System.out.println("Enter board column size");
			col = input.nextInt();
			System.out.println("Enter missiles");
			missiles = input.nextInt();
		} catch(Exception e) {
			System.out.println("An error occured " + e);
			input.close();
			return;
		} 
		
		Board gameBoard = new Board(row, col);
		gameBoard.initalizeBoard();
		ArrayList<Integer> rowsNum = new ArrayList<>(row-1);
		ArrayList<Integer> colsNum = new ArrayList<>(col);
		assignRowsCols(rowsNum, row, colsNum, col);
		ArrayList<Missile> missilesList = new ArrayList<>(missiles);
		assignMissiles(missilesList, missiles, gameBoard);
        ArrayList<Object> objectsList = new ArrayList<>();
        int objNums = row-3-1;
        assignObjects(objectsList, objNums, gameBoard, rowsNum, colsNum);
        System.out.println("[] = Enemies\n{} = Allies\n^ = Available missiles\n. = Spaces\n-----------------------");
        gameBoard.displayBoard();
        System.out.println("Get ready for some action\n");
        
        while(true) {
        	Integer enemies = enemyObjs(objectsList);
        	if(isEnemyFinished(enemies)) {
        		System.out.println("Hurrah! You won");
        		break;
        	}
        	else if(isMissilesFinished(missilesList) && !isEnemyFinished(enemies)) {
        		System.out.println("Oh you lost!");
        		break;
        	}
        	else if(isMissilesFinished(missilesList) && isEnemyFinished(enemies)) {
        		System.out.println("Hurrah! You won");
        		break;
        	}
        	
        	System.out.println("Input the coordinates of your missile to use for attack");
        	int oldR = input.nextInt(), oldC = input.nextInt();
        	System.out.println("Input the coordinates for attack");
        	int newR = input.nextInt(), newC = input.nextInt();
        	
        	move(newR, newC, gameBoard, oldR, oldC, objectsList, missilesList);
        	gameBoard.displayBoard();
        	
        	System.out.println("Enemies = " + enemies);
        	System.out.println("Missiles left " + missilesList.size());
        	
        }
        input.close();
	}
	public static void assignMissiles(ArrayList<Missile> missiles, int missilesNum, Board gameBoard) {
		for(int i=0;i<missilesNum;i++) {
        	int power = (int)(Math.round(Math.random() * 8));
        	missiles.add(new Missile(gameBoard, power));
        }
        int a=0;
        while(a<missiles.size()) {
        	Missile misObj = missiles.get(a);
        	int randCol = (int)(Math.random()*gameBoard.board[0].length);
        	if(gameBoard.board[misObj.location][randCol].equals(". ")) {
        		gameBoard.board[misObj.location][randCol] = Missile.missileRepresentation;
        		a++;
        	}
        	
        }
	}
	public static void assignRowsCols(ArrayList<Integer> rowsList, int rows, ArrayList<Integer> colsList, int cols) {
		for(int i=0;i<rows-1;i++) {
			rowsList.add(i);
		}
		for(int j=0;j<cols;j++) {
			colsList.add(j);
		}
		Collections.shuffle(rowsList);
		Collections.shuffle(colsList);
	}
	public static void assignObjects(ArrayList<Object> objectsList, int objNums, Board gameBoard, ArrayList<Integer> rowsNum, ArrayList<Integer> colsNum) {
		for(int b=0;b<objNums;b++) {
            int randNum = (int)(Math.random()*rowsNum.size());
            int rowPos = rowsNum.get(randNum);
            int colPos = colsNum.get(randNum);
        	int colorNum = (int)(Math.random() * 2);
        	String color = null;
        	if(colorNum==1) {
        		color = "blue";
        	}
        	else {
        		color = "green";
        	}
        	objectsList.add(new Object(color, rowPos, colPos));
        	rowsNum.remove(rowsNum.get(randNum));
        	colsNum.remove(colsNum.get(randNum));
        }
        int j=0;
        while(j<objectsList.size()) {
        	Object obj = objectsList.get(j);
            gameBoard.board[obj.rowPos][obj.colPos] = obj.objRepresentation;
        	j++;
        }
	}
	public static void move(int locR, int locC, Board b, int oldR, int oldC, ArrayList<Object> objects, ArrayList<Missile> misslesList) {
		if(locR>b.board.length || locC>b.board[0].length || locR<0 || locC<0) {
			System.out.println("Wrong coordinates.");
			return;
		}
		else if(b.board[locR][locC].equals("{}") || b.board[locR][locC].equals("^ ")) {
			System.out.println("You cannot attack your ally or your own missiles");
			return;
		}
		else {
			b.board[oldR][oldC] = ". ";
			if(b.board[locR][locC].equals("[]")) {
				removeEnemy(objects);
				b.board[locR][locC] = ". ";
				misslesList.remove(0);
				
			}
			else {
				b.board[locR][locC] = "^ ";	
			}
			
		}
		
	}
	public static boolean isEnemyFinished(Integer enemies) {
		if(enemies==0) {
			return true;
		}
		return false;
	}
	public static boolean isMissilesFinished(ArrayList<Missile> missiles) {
		if(missiles.size()==0) {
			return true;
		}
		return false;
	}
	public static Integer enemyObjs(ArrayList<Object> objects) {
		Integer count = 0;
		for(Object o:objects) {
			if(o.objRepresentation.equals("[]")) {
				count++;
			}
			
		}
		return count;
	}
	public static void removeEnemy(ArrayList<Object> objects) {
		for(int i=0;i<objects.size();i++) {
			if(objects.get(i).objRepresentation.equals("[]")) {
				objects.remove(i);
			}
		}
	}

}
