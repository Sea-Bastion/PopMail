package popMail.classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ScrollBar extends StackPane {

	private StackPane thumb;
	private Pane component;

	public ScrollBar(){
		getStyleClass().clear();
		getStyleClass().add(".scroll-bar");
		setStyle("-fx-background-color: transparent");


		thumb = new StackPane();
		getChildren().add(thumb);
		thumb.getStyleClass().clear();
		thumb.getStyleClass().add(".thumb");

		thumb.setStyle(
				"-fx-background-color: #494949;" +
				"-fx-background-insets: 2, 0, 0;" +
				"-fx-background-radius: 0"
		);

		SimpleDoubleProperty InitTrans = new SimpleDoubleProperty(), InitMouse = new SimpleDoubleProperty();
		thumb.setOnMousePressed(e ->{
			InitTrans.set(thumb.getTranslateY());
			InitMouse.set(e.getY());
		});

		thumb.setOnMouseDragged(e ->{
			thumb.setTranslateY(InitTrans.get() + e.getY() - InitMouse.get());
		});
	}

	public void setComponent(Pane component){
		this.component = component;

		component.sceneProperty().addListener((ob, old_val, new_val) -> {

			new_val.heightProperty().addListener(((ob2, old_val2, new_val2) -> {
				if((double)new_val2 >= component.getHeight()){
					setVisible(false);

				}else{
					setVisible(true);
				}
			}));

			component.heightProperty().addListener((ob2, old_val2, new_val2) -> {
				if((double)new_val2 <= new_val.getHeight()){
					setVisible(false);

				}else{
					if(!isVisible()) setVisible(true);

					
				}
			});
		});

	}


	public Pane getComponent(){
		return component;
	}

}
