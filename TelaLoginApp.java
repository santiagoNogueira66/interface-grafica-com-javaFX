package com.example.trampodebd;

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
        stage.setTitle("Login");

        //cria um caixa na vertical onde o 10 define o espaços entre os elementos
        VBox root = new VBox(10);

        //cria uma cena com todos os elementos onde a largura e 300px e altura 200px
        Scene scene = new Scene(root, 300, 200);


        //cria a caixa de usuario
        TextField nomeUsuario = new TextField();
        nomeUsuario.setPromptText("Nome de Usuário");

        //cria a caixa de senha
        PasswordField senha = new PasswordField();
        senha.setPromptText("Senha");


        //criar o botão login
        Button botaoLogin = new Button("Login");

        //criar o botão cadastra-se
        Button botaoCadastro = new Button("Cadastra-se");
        botaoCadastro.setOnAction(e->escolherFuncionario());
        //exibe as caixas de texto nomeUsuario, senha e os 2 botões
        root.getChildren().addAll(nomeUsuario,senha,botaoLogin,botaoCadastro);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        //faz com que a tela apareça
        stage.setScene(scene);
        stage.show();

    }
    public void escolherFuncionario(){

        VBox root = new VBox(10);
        Scene scene = new Scene(root, 300, 200);
        Stage enfermeiro = new Stage();
        Stage medico = new Stage();

        TelaCadastroEnfermeiro instanciaEnfermeiro = new TelaCadastroEnfermeiro();
        TelaCadastroMedico instanciamedico = new TelaCadastroMedico();

        Button botaoEnfermeiro = new Button("Enfermeiro");
        botaoEnfermeiro.setOnAction(e->instanciaEnfermeiro.start(enfermeiro));

        Button  botaoMedico = new Button("medico");
        botaoMedico.setOnAction(e->instanciamedico.start(medico));

        root.getChildren().addAll(botaoEnfermeiro,botaoMedico);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        medico.setScene(scene);
        medico.show();

        enfermeiro.setScene(scene);
        enfermeiro.show();

    }
}
