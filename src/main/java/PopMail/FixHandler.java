package PopMail;

import javafx.scene.control.Label;

public interface FixHandler {
	/**
	 *
	 * @return true for success and false for fail
	 */
	boolean FixHandle(Label Title, Label Description);
}
