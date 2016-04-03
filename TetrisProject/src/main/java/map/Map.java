package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JPanel;

import com.esiea.mooroogen.Main;

import control.Keylisteners;
import component.GameBoard;


public class Map extends JPanel {

	private Window win;
	private GameBoard GameBoard;

	private long t_started,t_now,t_long;
	private Color[] colors = new Color[GameBoard.Blocks];
	
	
	public volatile boolean started = false;
	
	public Map(Window win){
		this.win= win;
		this.t_started = 0;
		this.t_now = 0;
		this.t_long = 0;
		
		this.win.repaint();
		
	}

	public void init() {
		started=false;
		
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
		}
		FocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
				new Keylisteners(GameBoard));

		started=true;
		t_started = System.currentTimeMillis();
		
	
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	(new Thread(){
		public void run(){
				while (started){
					repaint();
					try{
						Thread.sleep(20);
					}catch (InterruptedException e){
					}
				}
			}
		}).start();
	}
	
	public void genColors(){
		for(int i=0;i<colors.length;i++){
			colors[i]=Main.randomColor();
		}
	}

	@Override
	public void paintComponent(Graphics g){
		setSize(new Dimension(((GameBoard.getSizeX()-1)*16)+1,
				((GameBoard.getSizeY()-1)*16)+1));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		t_now = System.currentTimeMillis();
		t_long= t_now - t_started;
		
		if(t_long >= 1000){
				t_started = System.currentTimeMillis();
				GameBoard.shift();
		}
		
		drawGame(g);
	}
		
		public void drawGame(Graphics g){
			int LengthX = GameBoard.getSizeX(), HeightY = GameBoard.getSizeY(),x=0,y=0;
			
			for (int tx=1;tx<LengthX;tx++){
				for(int ty=1;ty<HeightY;ty++){
					x = (tx-1)*16;
					y = (ty-1)*16;
					int i = GameBoard.get(tx,ty);
					
					if(i != 0 && i<= GameBoard.Blocks){
						g.setColor(colors[i-1]);
						g.fillRect(x, y, 16, 16);
						g.drawLine(x, y, x+16, y);
						g.drawLine(x, y, x, y+16);
						g.drawLine(x+16, y, x+16, y+16);
						g.drawLine(x, y+16, x+16, y+16);
				}		
			}
		}
	}
}