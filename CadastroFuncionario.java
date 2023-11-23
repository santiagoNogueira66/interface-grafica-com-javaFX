package interfaceFX;

import dao.EnfermeiroDao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Enfermeiros;
import model.Medicos;
import dao.MedicoDao;

import static dao.EnfermeiroDao.save;
import static dao.MedicoDao.save;

public class CadastroFuncionario extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage cadastroFuncionario){
        cadastroFuncionario.setTitle("Cadastro Funcionario");
        VBox root = new VBox(10);
        Scene scene = new Scene(root,300,670);


        Label COREN = new Label("Coren");
        TextField coren = new TextField();

        Label CRM = new Label("CRM");
        TextField crm = new TextField();

        Label NOME = new Label("Nome");
        TextField nome = new TextField();

        //caixa SEXO
        Label SEXO = new Label("Sexo");
        TextField sexo = new TextField();

        Label SENHA = new Label("Senha");
        TextField password = new TextField();

        Label ESPECIALIDADE = new Label("Especialidade");
        TextField especialidade = new TextField();

        Label TELEFONE = new Label("Telefone");
        TextField telefone = new TextField();

        Label DATANASCIMENTO = new Label("Data de nascimento");
        TextField dataNascimento = new TextField();

        Label UF  = new Label("UF");
        TextField uf = new TextField();


        Button botaoCadastrar = new Button("Cadastrar");
        botaoCadastrar.setOnAction(event -> {

            if(!coren.getText().isEmpty()){
                Enfermeiros enfermeiros = new Enfermeiros();
                enfermeiros.setCoren(Integer.parseInt(coren.getText()));
                enfermeiros.setNome(nome.getText());
                enfermeiros.setSexo(sexo.getText());
                enfermeiros.setSenha(password.getText());
                enfermeiros.setEspecialidade(especialidade.getText());
                enfermeiros.setTelefone(telefone.getText());
                enfermeiros.setDataNasc(dataNascimento.getText());
                enfermeiros.setUf(uf.getText());

                save(enfermeiros); // Chama o método save para inserir o enfermeiro no banco de dados
            } else if ((!crm.getText().isEmpty())) {
                Medicos medicos = new Medicos();
                medicos.setCrm(Integer.parseInt(crm.getText()));
                medicos.setNome(nome.getText());
                medicos.setSexo(sexo.getText());
                medicos.setSenha(password.getText());
                medicos.setEspecialidade(especialidade.getText());
                medicos.setTelefone(telefone.getText());
                medicos.setDataNasc(dataNascimento.getText());
                medicos.setUf(uf.getText());

                save(medicos);// Chama o método save para inserir o medicos no banco de dados
            } else {
                System.out.println("Crm ou Coren não foram preenchidos!");
            }
        });


        root.getChildren().addAll(COREN, coren, CRM, crm, NOME, nome, SENHA, password, SEXO, sexo, ESPECIALIDADE, especialidade, TELEFONE, telefone, DATANASCIMENTO, dataNascimento, UF, uf, botaoCadastrar);

        root.setAlignment(javafx.geometry.Pos.CENTER);

        root.setPadding(new Insets(10));

        cadastroFuncionario.setScene(scene);
        cadastroFuncionario.show();
    }
}
