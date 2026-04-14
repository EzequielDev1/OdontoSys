package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Paciente;
import model.Funcionario;

public class ControllerTelaEspecialidades {

    @FXML
    private ChoiceBox<String> cbEspecialidade;

    @FXML
    private StackPane paneEspecialidades;

    private Paciente paciente;
    private Funcionario funcionario;

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @FXML
    private void initialize() {
        // Opções da ChoiceBox
        ObservableList<String> especialidades = FXCollections.observableArrayList(
                "Endodontia",
                "Prótese",
                "Cirurgia"
        );
        cbEspecialidade.setItems(especialidades);

        // Quando o usuário escolher uma especialidade, carrega a tela
        cbEspecialidade.setOnAction(event -> carregarEspecialidade());
    }

    private void carregarEspecialidade() {
        String selecionada = cbEspecialidade.getValue();
        if (selecionada == null || selecionada.isEmpty()) return;

        String caminhoFXML = switch (selecionada) {
            case "Endodontia" -> "/view/TelaEndodontia.fxml";
            case "Prótese" -> "/view/TelaProtese.fxml";
            case "Cirurgia" -> "/view/TelaCirurgia.fxml";
            default -> null;
        };

        if (caminhoFXML != null) abrirTela(caminhoFXML);
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            AnchorPane pane = loader.load();

            // Passar paciente e funcionário
            Object controller = loader.getController();
            if (controller instanceof ControllerTelaEndodontia c1) {
                c1.setPaciente(paciente);
                c1.setFuncionario(funcionario);
            } else if (controller instanceof ControllerTelaProtese c2) {
                c2.setPaciente(paciente);
                c2.setFuncionario(funcionario);
            } else if (controller instanceof ControllerTelaCirurgia c3) {
                c3.setPaciente(paciente);
                c3.setFuncionario(funcionario);
            }

            paneEspecialidades.getChildren().setAll(pane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
