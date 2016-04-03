package component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.esiea.mooroogen.Main;

public class GameBoard {
	
	public final static int Blocks=1;
	
	public static int COL_NUMBERS=10;
	public static int LINE_NUMBERS=24;

	private int [][] Board;
	private boolean[][] collision;
	
	private List<Point>moving;
	private boolean stillMoving;
	
	public GameBoard(){
		Board = new int[COL_NUMBERS][LINE_NUMBERS];
		collision = new boolean[COL_NUMBERS][LINE_NUMBERS];
		moving = new ArrayList<Point>();
		stillMoving=false;
	}
	
	public void shift(){
		if(stillMoving){
			moveDown();
			return;
		}
		int i = Main.rand(Blocks);
		moving.clear();
		
		switch(i){
		case 1:
			moving.add(new Point(3,2));
			moving.add(new Point(4,2));
			moving.add(new Point(5,2));
			moving.add(new Point(6,2));
			break;
		default:
			return;
		}
		
		for(Point p : moving){
			set(p.x,p.y,i);
		}
	stillMoving = true;
	}
	public void moveLeft(){
		move(1);
	}
	public void moveRight(){
		move(2);
	}
	public void moveDown(){
		move(3);
	}
	public void move(int dir){
		try{
			if(!canMove(dir))
				return;
				
			for(int i=0;i < moving.size();i++){
				Point p = moving.get(i);
				move(p.x,p.y,dir);
				
				switch(dir){
				case 1: //LEFT
					p.x--;
					break;
				case 2: //RIGHT
					p.x++;
					break;
				case 3:
					p.y++;
					break;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			stillMoving=false;
		}
	}
	public void move(int x,int y,int dir){
		int nextX = 0, nextY=0;
		switch(dir){
		case 1: //LEFT
			nextX = x-1;
			nextY= y;
			break;
		case 2: //RIGHT
			nextX = x+1;
			nextY= y;
			break;
		case 3: //DOWN
			nextX = x;
			nextY= y+1;
			break;
		}
		set(nextX,nextY,get(x,y));
		set(x,y,!isMovingBlock(nextX,nextY)?0:get(x,y));
	}
	
	
	public boolean isMovingBlock(int x, int y){
		for(Point p:moving)
			if(p.x==x && p.y==y)
				return true;
			
			return false;
		}
	
	public boolean canMove(int dir){
		int x = -1, y= -1;
		
	switch(dir){
	case 1:
		for(Point p : moving){
			if(x>0 && y>0){
				x=Math.min(p.x,x);
				y=x==p.x? p.y:y;
			}else{
				x=p.x;
				y=p.y;
			}
		}
		break;
	case 2:
		for(Point p : moving){
			if(x>0 && y>0){
				x=Math.max(p.x,x);
				y=x==p.x? p.y:y;
			}else{
				x=p.x;
				y=p.y;
			}
		}
		break;
	case 3:
		for(Point p : moving){
			if(x>0 && y>0){
				x=Math.max(p.y,y);
				x=y==p.y? p.x:x;
			}else{
				x=p.x;
				y=p.y;
			}
		}
		break;
		default:
			return false;
	}
		return canMove(x,y,dir);
	}
	
	public boolean canMove(int x,int y,int dir){
		int nextX = 0, nextY=0;
		switch(dir){
		case 1: //LEFT
			nextX = x-1;
			nextY= y;
			break;
		case 2: //RIGHT
			nextX = x+1;
			nextY= y;
			break;
		case 3: //DOWN
			nextX = x;
			nextY= y+1;
			break;
		}
		return isValid(nextX,nextY);
	}
	public int getSizeX(){
		return Board.length+1;
	}
	public int getSizeY(){
		return Board[0].length+1;
	}
	
	
	
	public int [] getLine(int x){
			return Board[x-1];
	}
	public int[][] get (){
		return Board;
	}
	public void set(int x, int y, int v){
			Board[x - 1][y - 1]=v;
			collision[x-1][y-1] = (v!=0);
	}
	public int get(int x,int y){
		return Board[x-1][y-1];
	}
	public boolean isLineFull(int x){
		int[] line = Board[x-1];
		boolean result = true;
		
		for(int item : line){
			result = result&& item !=0;
		}
		return result;
	}
	public boolean isValid(int x, int y){
		return !(x<1 || x> getSizeX() || y < 1 || y > getSizeY() ||collision[x-1][y-1]);
	}
}
