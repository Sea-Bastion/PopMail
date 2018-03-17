package PopMail;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.URLName;
import java.util.Properties;

public class Mailing {

	public static void Login(String Username, String Password){
		if(Main.session == null){
			Properties props = System.getProperties();
			props.setProperty("main.store.protocol", "imaps");
			Main.session = Session.getDefaultInstance(props, null);

		}

		try {
			Main.store = Main.session.getStore("imaps");
			Main.store.connect("imap.gmail.com", Username, Password);
			System.out.println(Main.store);

			Folder inbox = Main.store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			for(Message message: messages){
				System.out.println(message.getSubject());
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
