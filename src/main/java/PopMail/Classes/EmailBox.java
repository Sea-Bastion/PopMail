/*

	FileName: EmailBox.java
	Author(s): Sebastian Cypert
	Jobs: it is the node used for displaying Emails to be selected

 */
package PopMail.Classes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public class EmailBox extends VBox {

	public EmailBox(Message message) throws MessagingException{
		this(message.getSubject(), (InternetAddress[]) message.getFrom());
	}

	public EmailBox(String Subject, InternetAddress... Authors){

		getStyleClass().clear();
		getStyleClass().add("email-box");

		setPadding(new Insets(10));
		setPrefSize(200, 50);

		StringBuilder AuthorText = new StringBuilder();
		for(InternetAddress Author: Authors){
			AuthorText.append(Author.getPersonal() + ", ");
		}
		AuthorText.delete(AuthorText.length()-2, AuthorText.length());

		Subject = Subject == null?"(No subject)":Subject;

		Label SubjectLable = new Label(Subject);
		Label AuthorsLabel = new Label(AuthorText.toString());

		SubjectLable.getStyleClass().add("subject");
		AuthorsLabel.getStyleClass().add("authors");

		getChildren().addAll(SubjectLable, AuthorsLabel);


	}

}
