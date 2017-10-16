/**
 * Created by ryan on 10/9/17.
 */
public class MainWindowController {
	private void inputWindow(ActionEvent e) throws Exception {
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getRescource("/resources/inputWindow.fxml");
			Parent root1  = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
