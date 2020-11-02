package de.stuttgart.hft.bookapp.client.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import de.stuttgart.hft.bookapp.domain.application.Application;
import de.stuttgart.hft.bookapp.domain.model.Author;
import de.stuttgart.hft.bookapp.domain.model.Book;
import de.stuttgart.hft.bookapp.domain.model.Genre;
import de.stuttgart.hft.bookapp.domain.model.Order;

@SuppressWarnings("serial")
public class BookView extends JFrame {
	
	private static final int HEIGHT = 600, WIDTH = 900;
	
	private JList<Author> authorList;
	private JList<Genre> genreList;
	private JList<Book> bookList;
	private JTextArea bookInfo;
	private JButton sampleButton, orderButton;
	private Set<Integer> currentGenreIds;
	
	private Application app;

	private String name = "Your Name";
	private String email = "your.name@mail.com";
	private String street = "Street 12";
	private String city = "12345 City";

	public BookView(Application app) {
		super("Books");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.app = app;
									
		initializeWidgets();
		JComponent content = createWidgetLayout();		
		createWidgetInteraction();

		this.setContentPane(content);
		
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private void initializeWidgets() {
		authorList = new JList<>(app.getAuthors().toArray(new Author[0]));
		genreList = new JList<>(app.getGenres().toArray(new Genre[0]));
		bookList = new JList<>(app.getBooks().toArray(new Book[0]));
		bookInfo = new JTextArea();
		sampleButton = new JButton("Sample");
		sampleButton.setEnabled(false);
		orderButton = new JButton("Order");
		orderButton.setEnabled(false);
		
		genreList.setCellRenderer(new GenreListRenderer());
	}
	
	// see: https://stackoverflow.com/questions/6224022/how-to-make-one-item-in-a-jlist-bold
	private class GenreListRenderer extends DefaultListCellRenderer {

	    public GenreListRenderer() {
			super();
			currentGenreIds = new HashSet<>();
			ListModel<Book> model = bookList.getModel();
			for(int i = 0; i < model.getSize(); i++) {
				Book book = model.getElementAt(i);
				currentGenreIds.add(book.getId());
			}
		}

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        Genre genre = (Genre) value;
	        if (currentGenreIds.contains(genre.getId())) // <= put your logic here
	            c.setFont(c.getFont().deriveFont(Font.BOLD));
	        else
	            c.setFont(c.getFont().deriveFont(Font.ITALIC));
	        return c;
	    }
	}

	private JComponent createWidgetLayout() {
		int size = 16;
		authorList.setFont(new Font("Arial", Font.BOLD, size));
		genreList.setFont(new Font("Arial", Font.BOLD, size));
		bookList.setFont(new Font("Arial", Font.BOLD, size));
		bookInfo.setFont(new Font("Arial", Font.BOLD, size));

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(1, 3));
		
		JPanel lists = new JPanel(new GridLayout(2, 1));
		lists.add(new JScrollPane(authorList));
		lists.add(new JScrollPane(genreList));		
		content.add(lists);
		
		content.add(new JScrollPane(bookList));
		
		JPanel bookPanel = new JPanel(new BorderLayout());
		bookPanel.add(bookInfo, BorderLayout.CENTER);
		JPanel control = new JPanel();
		control.add(sampleButton);
		control.add(orderButton);
		bookPanel.add(control, BorderLayout.SOUTH);
		content.add(bookPanel);
		
		return content;
	}


	private void createWidgetInteraction() {		
		authorList.addListSelectionListener(e -> {if(!e.getValueIsAdjusting()) updateList();});		
		genreList.addListSelectionListener(e -> {if(!e.getValueIsAdjusting()) updateList();});
		bookList.addListSelectionListener(e -> {if(!e.getValueIsAdjusting()) updateInfo();});
		sampleButton.addActionListener(e -> displaySample());
		orderButton.addActionListener(e -> order());
	}


	private void updateList() {
		Author selectedAuthor = authorList.getSelectedValue();
		Genre selectedGenre = genreList.getSelectedValue();
		Book[] books = null;

//		System.out.println(selectedAuthor + " " + selectedGenre + " " + selectedBook);
		if(selectedAuthor == null && selectedGenre == null)
			books = app.getBooks().toArray(new Book[0]);
		else if(selectedAuthor != null && selectedGenre == null)
			books = app.getBooksByAuthor(selectedAuthor).toArray(new Book[0]);
		else if(selectedAuthor == null && selectedGenre != null)
			books = app.getBooksByGenre(selectedGenre).toArray(new Book[0]);
		else // (selectedAuthor != null && selectedGenre != null)
			books = app.getBooksByAuthorAndGenre(selectedAuthor, selectedGenre).toArray(new Book[0]);
		
		currentGenreIds = Arrays.asList(books).stream().map(b -> b.getGenre().getId()).collect(Collectors.toSet());

		bookList.setListData(books);
		genreList.revalidate();
		genreList.repaint();
	}


	private void updateInfo() {
		Book selectedBook = bookList.getSelectedValue();
		if(selectedBook == null) {
			bookInfo.setText("");
			sampleButton.setEnabled(false);
			orderButton.setEnabled(false);
		}else {
			Author author = selectedBook.getAuthor();
			StringBuilder text = new StringBuilder();
			text.append("Author:\t").append(author.getFirstname()).append(" ").append(author.getLastname()).append("\n");
			text.append("Title:\t").append(selectedBook.getTitle()).append("\n");
			text.append("Genre:\t").append(selectedBook.getGenre().getGenreName()).append("\n");
			text.append("Year:\t").append(selectedBook.getYear());
			bookInfo.setText(text.toString());
			sampleButton.setEnabled(true);
			orderButton.setEnabled(true);
		}
		
	}
	
	private void displaySample() {
		Book book = bookList.getSelectedValue();
		if(book == null)
			return;
		
		JEditorPane textPane = new JEditorPane();
		textPane.setContentType("text/html");

		Author author = book.getAuthor();
		Genre genre = book.getGenre();

		StringBuilder text = new StringBuilder();

		text.append("<body style=\"font-family:arial\">")
			.append("<div  style=\"text-align:center\">")
			.append("<h2>").append(book.getTitle()).append("</h2>\n")
			.append("<i>by</i><p>\n")
			.append("<i>").append(author.getFirstname()).append(" ")
				.append(author.getLastname()).append(", ")
				.append(book.getYear()).append(" (")
				.append(genre.getGenreName()).append(")</i><p>\n")
			.append("</div>\n")
		.append(book.getSample().replace("\\n", "\n<p>"))
		.append("</body>");

		textPane.setText(text.toString());
		textPane.setEditable(false);
		textPane.setCaretPosition(0);
		
		JDialog dialog = new JDialog(BookView.this, "Sample Content", ModalityType.MODELESS);
		dialog.setPreferredSize(new Dimension(HEIGHT, HEIGHT));
		dialog.getContentPane().add(new JScrollPane(textPane));
		dialog.setLocationByPlatform(true);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setVisible(true);
	}
		
	@FunctionalInterface private static interface FieldAdder{
		void add(String label, JTextField field, int row);
	}

	private void order() {
		
		JPanel orderPanel = new JPanel(new GridBagLayout());
		FieldAdder toPanel = (str, field, row) -> {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(1, 1, 1, 1);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridy = row;
			
			JPanel label = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			label.add(new JLabel(str));
			
			constraints.gridx = 0;
			orderPanel.add(label, constraints);
			constraints.gridx = 1;
			orderPanel.add(field, constraints);
		};
		
		JTextField nameField = new JTextField(20);
		JTextField emailField = new JTextField(20);
		JTextField streetField = new JTextField(20);
		JTextField cityField = new JTextField(20);
		
		nameField.setText(name);
		emailField.setText(email);
		streetField.setText(street);
		cityField.setText(city);

		toPanel.add("Name", nameField, 0);
		toPanel.add("E-Mail", emailField, 1);
		toPanel.add("Street", streetField, 2);
		toPanel.add("City", cityField, 3);

		int result = JOptionPane.showConfirmDialog(null, orderPanel, 
				"Please Enter your Order Info", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			name = nameField.getText();
			email = emailField.getText();
			street = streetField.getText();
			city = cityField.getText();
			Order order = new Order(name, email, street + ", " + city,
					bookList.getSelectedValue().getId());
			app.confirmOrder(order);
//			System.out.println(order);
		}
	}	
}
