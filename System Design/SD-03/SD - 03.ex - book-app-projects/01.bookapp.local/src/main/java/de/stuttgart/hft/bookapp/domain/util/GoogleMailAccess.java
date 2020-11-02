package de.stuttgart.hft.bookapp.domain.util;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.mail.smtp.SMTPTransport;

public class GoogleMailAccess {
	
	// initially by  doraemon, https://stackoverflow.com/questions/3649014/send-email-using-java

	private static final boolean USE_GMAIL_ACCOUNT = true;
	
	private Properties gMailProperties;	
	private String gmailUsername = null;
	private String gmailPassword = null;
	
	
	public GoogleMailAccess() {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		gMailProperties = new Properties();		
		gMailProperties.put("mail.smtps.host", "smtp.gmail.com");
		gMailProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		gMailProperties.put("mail.smtp.socketFactory.fallback", "false");
		gMailProperties.put("mail.smtp.port", "465");
		gMailProperties.put("mail.smtp.socketFactory.port", "465");
		gMailProperties.put("mail.smtps.auth", "true");
		gMailProperties.put("mail.smtps.quitwait", "false");
		
		String[] credentials = null;
		if(USE_GMAIL_ACCOUNT)
			credentials = requestCredentials();
		if(credentials != null) {
			gmailUsername = credentials[0];
			gmailPassword = credentials[1];
		}		
	}
	
	private String[] requestCredentials() {
		
		// see: https://stackoverflow.com/questions/18395615/joptionpane-with-username-and-password-input

	    JPanel panel = new JPanel(new BorderLayout());
	    
	    String message = "Enter some gmail-credentials. The account "
	    				+ "will be used as a mailserver. If you don't enter "
	    				+ "any credentials, mailing will only be simulated.";
	    
	    message = "<html><p width=\"250\">" + message +"</p></html>";
	    
	    JPanel label = new JPanel(new GridLayout(0, 1));
	    label.add(new JLabel("Username ", SwingConstants.RIGHT));
	    label.add(new JLabel("Password ", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);

	    JPanel controls = new JPanel(new GridLayout(0, 1));
	    JTextField usernameField = new JTextField();
	    usernameField.setToolTipText(message);
	    controls.add(usernameField);
	    JPasswordField passwordField = new JPasswordField();
	    passwordField.setToolTipText(message);
	    controls.add(passwordField);
	    panel.add(controls, BorderLayout.CENTER);

	    int result = JOptionPane.showConfirmDialog(null, panel, "GMail-Credentials", JOptionPane.OK_CANCEL_OPTION);

	    if(result == JOptionPane.YES_OPTION){
	    	String username = usernameField.getText();
	    	char[] password = passwordField.getPassword();
	    	if (username.isBlank())
	    		return null;
	    	String[] credentials = {username, new String(password) };
	    	return credentials;
	    } else
	    	return null;
	}

	
	public void send(String recipientEmail, String title, String message) {

		if(gmailPassword == null || gmailPassword.isEmpty() || gmailUsername == null || gmailUsername.isEmpty()) {
			String preparedMessage = "============================================================\n"
					+ "Prepared Mail to: " + recipientEmail + "\n" 
					+ "Title: " + title + "\n\n"
					+ message
					+ "\n============================================================";

			System.out.println("This mail would have been sent.\n" + preparedMessage);
		} else 
			send(gmailUsername, gmailPassword, recipientEmail, title, message);
	}

	public void send(String username, String password, String recipientEmail, String title, String message) {

//	    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
		new Thread(() -> {
				String preparedMessage = "============================================================\n"
					+ "Mail to: " + recipientEmail + "\n" 
					+ "Title: " + title + "\n\n"
					+ message
					+ "\n============================================================";

				Session session = Session.getInstance(gMailProperties, null);
				final MimeMessage msg = new MimeMessage(session);

				try {
					msg.setFrom(new InternetAddress(username + "@gmail.com"));
					msg.setRecipients(Message.RecipientType.TO, 
							InternetAddress.parse(recipientEmail, false));

					msg.setSubject(title);
					msg.setText(message, "utf-8");
					msg.setSentDate(new Date());
				} catch (MessagingException e) {
					System.err.println("Failed to create the following message.\n" + preparedMessage);
					e.printStackTrace();
					return;
				}

		        try (SMTPTransport t = (SMTPTransport)session.getTransport("smtps")){			
					t.connect("smtp.gmail.com", username, password);
					t.sendMessage(msg, msg.getAllRecipients());      
				} catch (MessagingException e) {
					System.err.println("Failed to send the following message.\n" + preparedMessage);
					e.printStackTrace();
				}
		        System.out.println("Successfully sent " + msg);
		}).start();
	}
}
