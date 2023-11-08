package org.openjfx.org.openjfx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair; // Импорт необходимого класса

public class App extends Application {
    public static void main1(String[] args) {
        launch(args);
    }

    private String loggedInUsername;
    private ArrayList<User1> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MU-TH-UR 6000 Simulation");

        // Создание интерфейса
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // TextArea для вывода команд и информации
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        root.setCenter(textArea);

        // TextField для ввода команд
        TextField commandInput = new TextField();
        commandInput.setStyle("-fx-text-fill: green; -fx-background-color: black;");
        commandInput.setOnAction(event -> processCommand(commandInput.getText(), textArea));
        root.setBottom(commandInput);

        // Добавление пользователей
        users.add(new User1("user1", "password1", "Уровень 1"));
        users.add(new User1("user2", "password2", "Уровень 2"));
        users.add(new User1("user3", "password3", "Уровень 3"));

        // Вход пользователя
        Button loginButton = new Button("Войти");
        loginButton.setOnAction(event -> showLoginDialog());

        HBox topMenu = new HBox();
        topMenu.getChildren().addAll(loginButton);
        root.setTop(topMenu);

        primaryStage.show();
    }

    private void showLoginDialog() {
        Dialog<Pair<String, String>> loginDialog = new Dialog<>();
        loginDialog.setTitle("Вход");
        loginDialog.setHeaderText("Введите имя пользователя и пароль:");

        // Создание полей для ввода имени и пароля
        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");

        // Добавление полей на диалоговое окно
        GridPane loginGrid = new GridPane();
        loginGrid.add(new Label("Имя пользователя:"), 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(new Label("Пароль:"), 0, 1);
        loginGrid.add(passwordField, 1, 1);

        loginDialog.getDialogPane().setContent(loginGrid);

        // Определение кнопок "Войти" и "Отмена"
        ButtonType loginButtonType = new ButtonType("Войти", ButtonData.OK_DONE);
        loginDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Обработка результата диалога
        loginDialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(usernameField.getText(), passwordField.getText());
            }
            return null;
        });

        // Отображение диалога и проверка учетных данных
        loginDialog.showAndWait().ifPresent(usernamePassword -> {
            if (isValidUser(usernamePassword.getKey(), usernamePassword.getValue())) {
                loggedInUsername = usernamePassword.getKey();
                showInfoMessage("Добро пожаловать, " + loggedInUsername + "!");
            } else {
                showErrorMessage("Неверное имя пользователя или пароль.");
            }
        });
    }

    private boolean isValidUser(String username, String password) {
        for (User1 user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void processCommand(String command, TextArea textArea) {
        if (loggedInUsername == null) {
            showErrorMessage("Пожалуйста, войдите, чтобы использовать команды.");
        } else {
            textArea.appendText(loggedInUsername + " выполнил команду: " + command + "\n");
        }
    }

    private void showInfoMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class User1 {
    private String username;
    private String password;
    private String accessLevel;

    public User1(String username, String password, String accessLevel) {
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

    public String getAccessLevel() {
        return accessLevel;
    }
}