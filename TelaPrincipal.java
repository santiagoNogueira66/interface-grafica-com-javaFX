package interfaceFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.PacientesDao;
import model.Pacientes;

import java.util.List;
import java.util.stream.Collectors;

public class TelaPrincipal extends Application {
    private ListView<String> pacientesListView;
    private List<Pacientes> allPacientes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Listar e Editar Pacientes");

        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label titleLabel = new Label("Lista de Pacientes");

        TelaCadastroPaciente instanciaTelaCadastroPaciente = new TelaCadastroPaciente();

        Stage novoPCT = new Stage();
        
        // Campo de pesquisa
        TextField searchField = new TextField();
        searchField.setPromptText("Pesquisar por nome");

        pacientesListView = new ListView<>();
        Button editButton = new Button("Editar Paciente");
        Button removeButton = new Button("Remover Paciente"); // Novo botão
        Button refreshButton = new Button("Atualizar"); // Botão de atualização
        Button newPacienteButton = new Button("novo paciente");


        // Popule a ListView com os pacientes do banco de dados
        PacientesDao pacientesDao = new PacientesDao();
        allPacientes = pacientesDao.getPacientes();
        atualizarListaPacientes();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Atualize a lista com base na pesquisa
            List<Pacientes> pacientesFiltrados = allPacientes.stream()
                    .filter(paciente -> paciente.getNome().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());

            pacientesListView.getItems().clear();
            for (Pacientes paciente : pacientesFiltrados) {
                pacientesListView.getItems().add("Prontuário: " + paciente.getProntuario() + " - Nome: " + paciente.getNome());
            }
        });

        editButton.setOnAction(e -> {
            String selectedPaciente = pacientesListView.getSelectionModel().getSelectedItem();
            if (selectedPaciente != null) {
                int prontuario = extrairProntuario(selectedPaciente);
                Pacientes paciente = allPacientes.stream()
                        .filter(p -> p.getProntuario() == prontuario)
                        .findFirst()
                        .orElse(null);

                if (paciente != null) {
                    abrirJanelaEdicao(paciente, pacientesDao);
                }
            }
        });

        removeButton.setOnAction(e -> {
            String selectedPaciente = pacientesListView.getSelectionModel().getSelectedItem();
            if (selectedPaciente != null) {
                int prontuario = extrairProntuario(selectedPaciente);
                pacientesDao.deleteByPK(prontuario);
                // Remova o paciente da lista local
                Pacientes pacienteARemover = allPacientes.stream()
                        .filter(p -> p.getProntuario() == prontuario)
                        .findFirst()
                        .orElse(null);

                if (pacienteARemover != null) {
                    allPacientes.remove(pacienteARemover);
                }
                atualizarListaPacientes();
            }
        });

        refreshButton.setOnAction(e -> {
            // Recarregue todos os pacientes do banco de dados
            allPacientes = pacientesDao.getPacientes();
            atualizarListaPacientes();
        });
        
        newPacienteButton.setOnAction(e-> {
            try {
                instanciaTelaCadastroPaciente.start(novoPCT);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        
        root.getChildren().addAll(titleLabel, searchField, pacientesListView, editButton, removeButton, refreshButton,newPacienteButton);

        stage.setScene(scene);
        stage.show();
    }

    private void atualizarListaPacientes() {
        pacientesListView.getItems().clear();
        for (Pacientes paciente : allPacientes) {
            pacientesListView.getItems().add("Prontuário: " + paciente.getProntuario() + " - Nome: " + paciente.getNome());
        }
    }

    private int extrairProntuario(String pacienteString) {
        int prontuarioIndex = pacienteString.indexOf("Prontuário: ") + 12;
        int nomeIndex = pacienteString.indexOf(" - Nome: ");
        String prontuarioStr = pacienteString.substring(prontuarioIndex, nomeIndex);
        return Integer.parseInt(prontuarioStr);
    }

    private void abrirJanelaEdicao(Pacientes paciente, PacientesDao pacientesDao) {
        Stage stage = new Stage();
        stage.setTitle("Editar Paciente");

        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label titleLabel = new Label("Editar Paciente");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        nomeField.setText(paciente.getNome());

        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF");
        cpfField.setText(paciente.getCpf());

        TextField sexoField = new TextField();
        sexoField.setPromptText("Sexo");
        sexoField.setText(paciente.getSexo());

        TextField telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");
        telefoneField.setText(paciente.getTelefone());

        TextField dataNascField = new TextField();
        dataNascField.setPromptText("Data de Nascimento");
        dataNascField.setText(paciente.getDataNasc());

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");
        enderecoField.setText(paciente.getEndereco());

        TextField ufField = new TextField();
        ufField.setPromptText("UF");
        ufField.setText(paciente.getUf());

        Button salvarButton = new Button("Salvar");

        salvarButton.setOnAction(e -> {
            // Atualize os dados do paciente com base nos valores dos campos
            paciente.setNome(nomeField.getText());
            paciente.setCpf(cpfField.getText());
            paciente.setSexo(sexoField.getText());
            paciente.setTelefone(telefoneField.getText());
            paciente.setDataNasc(dataNascField.getText());
            paciente.setEndereco(enderecoField.getText());
            paciente.setUf(ufField.getText());

            // Chame o método de atualização do DAO
            pacientesDao.update(paciente);

            // Feche a janela de edição
            stage.close();

            // Atualize a lista de pacientes na tela principal, se necessário
            // TelaPrincipal.atualizarListaPacientes();
        });

        root.getChildren().addAll(titleLabel, nomeField, cpfField, sexoField, telefoneField, dataNascField, enderecoField, ufField, salvarButton);

        stage.setScene(scene);
        stage.show();
    }
}
