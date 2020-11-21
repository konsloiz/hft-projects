package eg06.race;
public class Race2Seq {
	
	private int x = 0, y = 0;
	
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void changeY() {
		if(x == 5) {
			sleep(1000);
			y = x * 2; // y should be 10 in the end
		}
	}

	@Override
	public String toString() {
		return "Race [x=" + x + ", y=" + y + "]";
	}

	public static void main(String[] args) {
		
		Race2Seq r = new Race2Seq();
		System.out.println(r);
		
		r.changeY();
		System.out.println(r);
		
		r.setX(5);
		r.changeY();
		System.out.println(r);
		
	}
}
