package com.example.trampodebd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCadastroMedico extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage stage2){
        stage2.setTitle("cadastro medico");

        VBox root  = new VBox(10);
        Scene scene = new Scene(root, 300,350);

        TextField crm = new TextField();
        crm.setPromptText("CRM");

        TextField nome = new TextField();
        nome.setPromptText("Nome");

        PasswordField senha = new PasswordField();
        senha.setPromptText("Senha");

        TextField  sexo = new TextField();
        sexo.setPromptText("Sexo");

        TextField especiliade = new TextField();
        especiliade.setPromptText("Especialidade");

        TextField telefone = new TextField();
        telefone.setPromptText("Telefone");

        DatePicker dataNascimento = new DatePicker();
        dataNascimento.setPromptText("Data de nascimento");

        TextField UF = new TextField();
        UF.setPromptText("UF");

        Button botaoCadastrar = new Button("cadastrar");

        root.getChildren().addAll(crm,nome,sexo,senha,especiliade,telefone, UF);
        root.getChildren().add(dataNascimento);
        root.getChildren().add(botaoCadastrar);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        stage2.setScene(scene);
        stage2.show();
    }
}
