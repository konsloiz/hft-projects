package eg01.runnable;

import java.util.Date;

public class Target implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(new Date());
		}
	}

}
