package control;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import com.esiea.mooroogen.Main;
import map.Window;

import component.GameBoard;

public class Keylisteners implements KeyEventDispatcher {
	private GameBoard GameBoard;

	public Keylisteners(GameBoard GameBoard){
		this.GameBoard = GameBoard;
	}
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID()!= KeyEvent.KEY_PRESSED)
			return false;
		
	int key = e.getKeyCode();
	
	switch(key){
	case KeyEvent.VK_DOWN:
		GameBoard.moveDown();
		break;
	case KeyEvent.VK_LEFT:
		GameBoard.moveLeft();
		break;
	case KeyEvent.VK_RIGHT:
		GameBoard.moveRight();
		break;
	case KeyEvent.VK_F5:
		Main.getFrame().getDraw().genColors();
		break;
	}
	return true;
	}
}
