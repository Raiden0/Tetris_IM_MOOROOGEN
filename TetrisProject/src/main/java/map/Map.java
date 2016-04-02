package map;

import java.awt.Graphics;

import javax.swing.JPanel;


public class Map extends JPanel {
	private Window win;
	private MapThread loopMap;

	public volatile boolean started = false;
	
	public Map(Window win){
		this.win= win;
		loopMap = new MapThread(this);
		new Thread(loopMap).run();
		
	}

	public void init() {
		// TODO Auto-generated method stub
		started=true;
	}
	@Override
	public void paintComponent(Graphics g){
		
	}
}