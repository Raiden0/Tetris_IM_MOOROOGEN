package map;

public class MapThread implements Runnable {

	private Map map;
	
	public MapThread(Map map){
		this.map = map;
	}
	

	public void run() {
		if(map.started){
			map.repaint();
	}
	}
}
