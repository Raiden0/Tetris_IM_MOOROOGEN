package map;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame{
	private Map mapSpace;
	
	public Window(){
			super("Tetris by IM&MOOROOGEN");
			
			mapSpace= new Map(this);
			mapSpace.setMinimumSize(new Dimension(800,600));
			
			setContentPane(mapSpace);
			setMinimumSize(new Dimension(800,600));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			
			mapSpace.init();
			
	}
	public Map getDraw(){
		return mapSpace;
	}

}	

