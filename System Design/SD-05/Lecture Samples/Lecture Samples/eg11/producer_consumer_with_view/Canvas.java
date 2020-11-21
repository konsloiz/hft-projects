package eg11.producer_consumer_with_view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel implements Observer{
	
	public static final int TOP = 0;
	public static final int MIDDLE = 1;
	
	private static final int LENGTH = 25;
	private static final int FRAME = 5;
	
	private int alignment;
	private List<Product> products;
	
	public Canvas(String title, Observable observable, List<Product> products){
		this(title, observable, products, TOP);
	}
	
	public Canvas(String title, Observable observable, List<Product> products, int alignment){
		this.products = products;
		this.alignment = alignment;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setBackground(Color.WHITE);
		observable.addObserver(this);
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int elementsPerRow = (this.getWidth()- FRAME * 2) /(LENGTH + 1);
		int filledRows = products.size() / elementsPerRow + 1;
		
		int x = FRAME;
		int y = FRAME * 3;
		if(alignment == MIDDLE)
			y = Math.max(FRAME * 3, this.getHeight() / 2 - filledRows* (LENGTH + 1) / 2 - LENGTH/2);
		for(int i = 0; i < products.size(); i++){
			g.setColor(products.get(i).getColor());
			g.fillRect(x, y, LENGTH, LENGTH);
			x = x + LENGTH + 1;
			if (x + LENGTH > this.getWidth() - FRAME){
				x = 5;
				y = y + LENGTH + 1;
			}				
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
}
