/*

	FileName: Mailing.java
	Author(s): Sebastian Cypert
	Job: contains static functions need for mailing such as a send function or a login function

 */
package PopMail;

import PopMail.Classes.ErrorWin;

import javax.mail.*;
import java.util.Properties;

public class Mailing {

	public static boolean Login(String Username, String Password){
		if(Main.session == null){
			Properties props = System.getProperties();
			props.setProperty("main.store.protocol", "imaps");
			Main.session = Session.getDefaultInstance(props, null);

		}

		try {
			Main.store = Main.session.getStore("imaps");
			Main.store.connect("imap.gmail.com", Username, Password);
			return true;

		}catch(AuthenticationFailedException e){
			new ErrorWin("Incorrect username or password ", "please try again", true);

		}catch(Exception e){
			new ErrorWin("An unknown error has occurred",
					"reinstall or contact developers if this continues", false);
			e.printStackTrace();
		}

		return false;

	}

}
