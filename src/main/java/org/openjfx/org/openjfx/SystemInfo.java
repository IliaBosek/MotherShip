package org.openjfx.org.openjfx;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SystemInfo extends Application {
	ProgressBar progressBar;
	public static void main(String[] args) {
		launch(args);
	}

	private Map<String, String> users = new HashMap<>();

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		Image image =new Image(
				getClass().getResource("/org/openjfx/org/openjfx/4143c9195c25f97acb007abb5abd02ca.gif")
				.toExternalForm());
		
		
		ImageView imageView = new ImageView(image);
		imageView.setLayoutX(202);
		 imageView.setLayoutY(500);

		Button loader = new Button("Loading");
		loader.setOnAction(event -> Loading(primaryStage));
		users.put("u", "p");
		users.put("user2", "password2");
		users.put("user3", "password3");
		Label headerLbl = new Label("MU-TH-UR 6000");
		headerLbl.setStyle("-fx-text-fill: green; -fx-font-size:30; ");
		headerLbl.setPrefSize(400, 200);
		headerLbl.setAlignment(Pos.BASELINE_CENTER);
		StackPane.setAlignment(headerLbl, Pos.TOP_CENTER);
		Label statusLbl = new Label("Start screen");
		StackPane.setAlignment(statusLbl, Pos.BOTTOM_CENTER);

		BorderPane root2 = new BorderPane();
		Button loginBtn = new Button("Login");
		loginBtn.setOnAction(event -> showLoginDialog(primaryStage));
		HBox topMenu = new HBox();
		topMenu.getChildren().addAll(loginBtn);
		root2.setTop(topMenu);
		loginBtn.setPrefSize(100, 100);

		loginBtn.setStyle("-fx-text-fill: green; -fx-background-color: black;-fx-font-size:20;");
		StackPane root = new StackPane(headerLbl, statusLbl, loginBtn, imageView, loader);
		root.setStyle("-fx-text-fill: green; -fx-background-color: black;");
		StackPane.setMargin(imageView, new Insets(200.0, 0.0, 400.0, 0.0));
		StackPane.setMargin(loginBtn, new Insets(150.0, 0.0, 0.0, 0.0));// Говно чтобы двигат елемент
		StackPane.setMargin(loader, new Insets(400, 0.0, 0.0, 0.0));
		;
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MotherShip2563");
		primaryStage.setWidth(600);
		primaryStage.setHeight(700);
		primaryStage.show();

	}

	private void showLoginDialog(Stage primaryStage) {
		
		Image im = new Image(
				getClass().getResource("/org/openjfx/org/openjfx/weyland-yutani-corp-logo-A94A8F69DB-seeklogo.com.png")
						.toExternalForm());
		MainMenu main = new MainMenu();
		ImageView imageView = new ImageView(im);
		imageView.setFitWidth(200); 
		imageView.setFitHeight(100);;
		Stage loginDialogStage = new Stage();
		Label l = new Label("Employee name");
		Label l1 = new Label("Personal access code");
		TextField usernameF = new TextField();
		usernameF.setPromptText("Employee name");
		PasswordField passwordF = new PasswordField();
		passwordF.setPromptText("Personal access code");
		l.setStyle("-fx-text-fill: green;");
		l1.setStyle("-fx-text-fill: green;");
		
		Button bt = new Button("Login");
		StackPane.setMargin(bt, new Insets(100, 0.0, 0.0, 0.0));

		bt.setOnAction(event -> {
			String username = usernameF.getText();
			String password = passwordF.getText();
			if (users.containsKey(username) && users.get(username).equals(password)) {
				System.out.println("Вход выполнен успешно.");
				main.Core(primaryStage);
				 Stage stage = (Stage) bt.getScene().getWindow();  //закритие сцены посе успешного входа 
				 stage.close();           
			} else {
				System.out.println("Неверное имя пользователя или пароль.");

			}
		});
		GridPane grid = new GridPane();
		grid.add(l, 0, 0);
		grid.add(usernameF, 1, 0);
		grid.add(l1, 0, 1);
		grid.add(passwordF, 1, 1);
		grid.setAlignment(Pos.CENTER);
		StackPane root = new StackPane(grid,bt,imageView);
		StackPane.setMargin(imageView, new Insets(200.0, 0.0, 400.0, 0.0));
		
		Scene scene = new Scene(root, 400, 350);
		root.setStyle("-fx-text-fill: green;");
		root.setStyle("-fx-background-color: black;");
		loginDialogStage.setScene(scene);
		loginDialogStage.show();

	}

	double f = 0.0;
	private double anymethod () {
		
		f = f+0.1;
		return f;
	}
	
	
	private void Loading(Stage primaryStage) {
		Stage loginDialogStage = new Stage();
	    progressBar = new ProgressBar(0.0);
		progressBar.setStyle("-fx-background-color: black;");
		Timeline time = new Timeline();
		
        for(int i=0; i<=10;i++) {
        	time.getKeyFrames().add(new KeyFrame(Duration.seconds(i), e -> {
        		progressBar.setProgress(anymethod());
    			
    		}));
        	
        	
        
        }
       
		time.setCycleCount(1);
		StackPane root = new StackPane(progressBar);
		Scene scene = new Scene(root);
		loginDialogStage.setScene(scene);
		loginDialogStage.show();
		time.play();
	}

}

class User {
	private String username;
	private String password;
	private int accessLevel;

	public User(String username, String password, int accessLevel) {
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

}
