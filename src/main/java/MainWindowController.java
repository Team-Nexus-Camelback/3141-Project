import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ryan on 10/9/17.
 */
public class MainWindowController {
	@FXML
	private void inputWindow(ActionEvent e) throws Exception {
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/inputWindow.fxml"));
			Parent root1  = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
}
