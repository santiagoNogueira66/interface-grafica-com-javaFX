package com.example.trampodebd;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCadastroEnfermeiro extends Application {
    public static void main(String [] args) {

        launch(args);

    }
    public void start(Stage enfermeiro){
        //titulo da minha tela
        enfermeiro.setTitle("cadastro Enfermeiro");

        //caixa vertical com tudo
        VBox root= new VBox(10);

        /*cria uma cena com todos os elementos, onde a largura e 300px e
        altura 200px respctivamente*/
        Scene scene = new Scene(root, 300,350);

        //caixa nome coren
        TextField coren = new TextField();
        coren.setPromptText("Coren");

        //caixa nome
        TextField nome = new TextField();
        nome.setPromptText("Nome");

        //caixa nome senha
        PasswordField senha = new PasswordField();
        senha.setPromptText("Senha");

        //caixa SEXO
        TextField sexo = new TextField();
        sexo.setPromptText("Sexo");

        //caixa especialidade
        TextField especialidade = new TextField();
        especialidade.setPromptText("Especialidade");

        //caixa telefone
        TextField telefone = new TextField();
        telefone.setPromptText("Telefone");

        //caixa data de nascimento
        DatePicker dataNascimento = new DatePicker();
        dataNascimento.setPromptText("Data de Nascimento");

        //caixa UF
        TextField UF = new TextField();
        UF.setPromptText("UF");

        Button botaoCadastrar = new Button("Cadastrar");


        /*mostra as caixas com usuario, senha e o
        demais paramentros*/
        root.getChildren().addAll(coren,nome,senha,sexo,especialidade,
                telefone,dataNascimento,UF,botaoCadastrar);



        //faz com que as caixas sejam exibidas
        enfermeiro.setScene(scene);
        enfermeiro.show();
    }
}