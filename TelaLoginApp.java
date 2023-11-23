
package interfaceFX;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import factory.ConnectionFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Medicos;
import model.Enfermeiros;
import dao.MedicoDao;
import dao.EnfermeiroDao;
import javax.swing.JOptionPane;

import java.sql.*;


public class TelaLoginApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    //define um titulo para minha aplicação
    public void start(Stage stage) {
        stage.setTitle("Login");

        Stage cadastroFuncionario = new Stage();

        CadastroFuncionario instanciaCadastroFuncionario =
                new CadastroFuncionario();

        //cria um caixa na vertical onde o 10 define o espaços entre os elementos
        VBox root = new VBox(10);

        //cria uma cena com todos os elementos onde a largura
        // e 300px e altura 200px
        Scene scene = new Scene(root, 300, 200);

        Label nomeLabel = new Label("Login");
        TextField nomeUsuario = new TextField();

        Label senhaLabel = new Label("Senha");
        PasswordField senha = new PasswordField();

        Button buttonLogin = new Button("Confirmar");

        buttonLogin.setOnAction(e -> {
            String username = nomeUsuario.getText();
            String password = senha.getText();
            Task<Void> loginTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Medicos medico = MedicoDao.getMedicoByCredentials(username, password);
                    Enfermeiros enfermeiro = EnfermeiroDao.getEnfermeiroByCredentials(username, password);

                    Platform.runLater(() -> {
                        if (medico != null) {
                            exibirTelaBemVindo(medico.getNome());
                            nomeUsuario.setText("");
                            senha.setText("");
                        } else if (enfermeiro != null) {
                            exibirTelaBemVindo(enfermeiro.getNome());
                            nomeUsuario.setText("");
                            senha.setText("");
                        } else {
                            // Exibir mensagem de erro
                            JOptionPane.showMessageDialog(null, "CRM/Coren ou senha incorretos, tente novamente");
                            nomeUsuario.setText("");
                            senha.setText("");
                        }
                    });

                    return null;
                }
            };

            new Thread(loginTask).start();
        });

        Button buttonCadastraSe = new Button("Cadastra-se");
        buttonCadastraSe.setOnAction(e->instanciaCadastroFuncionario.start(cadastroFuncionario));


        root.getChildren().addAll(nomeLabel,nomeUsuario, senhaLabel, senha,
                buttonLogin,buttonCadastraSe);
        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(10));
        stage.setScene(scene);
        stage.show();
    }



    private void exibirTelaBemVindo(String nomeLabel) {
        //cria a nova tela de boa vindas
        Stage stageB = new Stage();
        //defini o titulo bem vindo
        stageB.setTitle("Bem-Vindo");
        //cria a mensagem de boas vindas
        Label welcomeLabel = new Label("Olá, " + nomeLabel + " seja bem-vindo!");
        //define a caixa de boas vindas
        StackPane layout = new StackPane(welcomeLabel);
        //cria a cena de boa vinda de largura de 300px e altura de 100px
        Scene sceneB = new Scene(layout, 300, 100);
        //exibe a mensagem de boa vindas
        stageB.setScene(sceneB);
        stageB.show();
    }
}
