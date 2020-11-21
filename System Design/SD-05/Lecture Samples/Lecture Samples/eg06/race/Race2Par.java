package eg06.race;
public class Race2Par {
	
	private int x = 0, y = 0;
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}
	
	public synchronized void setX(int x) {
		this.x = x;
	}
	
	public synchronized void changeY() {
		if(x == 5) {
//			sleep(1);
			y = x * 2; // y should be 10 in the end
		}
	}

	@Override
	public String toString() {
		return "Race [x=" + x + ", y=" + y + "]" + (y == 10 || y == 0 ? "" : " ERROR");
	}

	public static void main(String[] args) {
		
		Race2Par r = new Race2Par();
				
//		System.out.println(r);
		Thread set5 = new Thread(() -> r.setX(5));
		Thread chg  = new Thread(() -> r.changeY());
		Thread set7 = new Thread(() -> r.setX(7));

		set5.start();
		chg.start();
		set7.start();

		sleep(500);
		System.out.println(r);
	}
}
