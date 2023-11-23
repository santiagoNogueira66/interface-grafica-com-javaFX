package interfaceFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.PacientesDao;
import model.Pacientes;

public class TelaCadastroPaciente extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage novoPCT) throws Exception {
        novoPCT.setTitle("Cadastro paciente");
        VBox root = new VBox(10);

        // Crie uma cena com todos os elementos, onde a largura é de 300px e a altura de 470px.
        Scene scene = new Scene(root, 300, 470);

        // Crie os campos de entrada para os dados do paciente.

        Label name = new Label("Nome");
        TextField nome = new TextField();

        Label CPF = new Label("Cpf");
        TextField cpf = new TextField();

        Label SEXO = new Label("Sexo");
        TextField sexo = new TextField();

        Label TELEFONE = new Label("Telefone");
        TextField telefone = new TextField();

        Label DATANASCIMENTO = new Label("Data Nascimento");
        TextField dataNascimento = new TextField();

        Label ENDERECO = new Label("Endereço");
        TextField endereco = new TextField();

        Label UF = new Label("UF");
        TextField uf = new TextField();


        // Crie um botão para cadastrar o paciente.
        Button botaoCadastrar = new Button("Cadastrar");

        botaoCadastrar.setOnAction(e -> {
            // Crie um novo objeto Pacientes com os dados do formulário.
            Pacientes novoPaciente = new Pacientes();
            novoPaciente.setNome(nome.getText());
            novoPaciente.setCpf(cpf.getText());
            novoPaciente.setSexo(sexo.getText());
            novoPaciente.setTelefone(telefone.getText());
            novoPaciente.setDataNasc(dataNascimento.getText());
            novoPaciente.setEndereco(endereco.getText());
            novoPaciente.setUf(uf.getText());


            // Crie uma instância do PacientesDao para salvar o novo paciente.
            PacientesDao pacientesDao = new PacientesDao();
            pacientesDao.save(novoPaciente);

            // Limpe os campos do formulário após o cadastro.
            nome.clear();
            cpf.clear();
            sexo.clear();
            telefone.clear();
            dataNascimento.clear();
            endereco.clear();
            uf.clear();
        });

        // Adicione os elementos à cena.
        root.getChildren().addAll(
                name, nome,
                CPF, cpf,
                SEXO, sexo,
                UF, uf,
                TELEFONE, telefone,
                DATANASCIMENTO, dataNascimento,
                ENDERECO, endereco,
                botaoCadastrar
        );

        root.setAlignment(Pos.CENTER);

        // Exiba a cena.
        novoPCT.setScene(scene);
        novoPCT.show();
    }
}
