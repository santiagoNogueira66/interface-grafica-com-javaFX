package com.example.implementacaobd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TelaLoginApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override

    //define um titulo para minha aplicação
    public void start(Stage stage) {
        stage.setTitle("Tela de Login");

        //cria um caixa na vertical onde o 10 define o espaços entre os elementos
        VBox root = new VBox(10);

        //cria uma cena com todos os elementos onde a largura e 300px e altura 200px
        Scene scene = new Scene(root, 300, 200);


        //cria a caixa de usuario
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nome de Usuário");

        //cria a caixa de senha
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Senha");

        //cria o botão de confirmação com a ação do clique
        Button confirmButton = new Button("Confirmar");
        confirmButton.setOnAction(e -> {
            String username = usernameField.getText();
            exibirTelaBemVindo(username);
        });

        root.getChildren().addAll(usernameField, passwordField, confirmButton);

        stage.setScene(scene);
        stage.show();
    }

    private void exibirTelaBemVindo(String username) {

        //cria a nova tela de boa vindas
        Stage stage = new Stage();

        //defini o titulo bem vindo
        stage.setTitle("Bem-Vindo");

        //cria a mensagem de boas vindas
        Label welcomeLabel = new Label("Olá, " + username + " seja bem-vindo!");

        //define a caixa de boas vindas
        StackPane layout = new StackPane(welcomeLabel);

        //cria a cena de boa vinda de largura de 300px e altura de 100px
        Scene scene = new Scene(layout, 300, 100);

        //exibe a mensagem de boa vindas
        stage.setScene(scene);
        stage.show();
    }
}
