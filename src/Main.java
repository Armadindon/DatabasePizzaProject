import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private Parent parentMain;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			parentMain = loadConnect();
			
			initMain(primaryStage, parentMain);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initMain(Stage primaryStage, Parent parent) {
		Scene scene = new Scene(parent);
		
		primaryStage.setTitle("Rapizz");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Parent loadInvoice() {
		Parent p = null;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Invoice.fxml"));
			p = (Parent) loader.load();
		} catch(Exception e) {
			
		}
		
		return p;
	}
	
	public Parent loadMenu() {
		Parent p = null;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Menu.fxml"));
			p = (Parent) loader.load();
		} catch(Exception e) {
			
		}
		
		return p;
	}
	
	public Parent loadConnect() {
		Parent p = null;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Connect.fxml"));
			p = (Parent) loader.load();
		} catch(Exception e) {
			
		}
		
		return p;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
