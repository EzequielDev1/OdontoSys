package controller;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

import dao.ConsultaTratamentoDAO;
import dao.OdontogramaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.ConsultaTratamento;
import model.Odontograma;
import model.Paciente;

public class ControllerTelaOdontograma implements Initializable {

    private Paciente paciente;

    @FXML private TableColumn<Odontograma, String> ColumnElemento;
    @FXML private TableColumn<Odontograma, String> ColumnFaceDente;
    @FXML private TableColumn<Odontograma, String> ColumnTratamento;
    @FXML private Button btLimpar;
    @FXML private Button btSalvar;
    @FXML private GridPane gridInferior;
    @FXML private GridPane gridSuperior;
    @FXML private TableView<Odontograma> tableOdontograma;

    private Map<Integer, StackPane> dentesMap = new HashMap<>();
    private Map<Integer, String> denteFaces = new HashMap<>();
    private Map<Integer, String> denteCondicoes = new HashMap<>();
    private Map<Integer, String> denteTratamentos = new HashMap<>();

    private Map<String, String> tratamentos; // id → nome

    // ================== Inicialização ==================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarOdontogramaSuperior();
        criarOdontogramaInferior();

        // carregar nomes dos tratamentos do banco
        tratamentos = new OdontogramaDAO().listarTratamentos();
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        if (paciente != null) {
            carregarOdontogramaDoBanco(paciente.getIdPaciente());
            carregarTableOdontograma();
        }
    }

    // ================== Criação dos Dentes ==================
    private void criarOdontogramaSuperior() {
        int[] dentes = {18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28};
        for (int i = 0; i < dentes.length; i++) {
            StackPane dente = criarDente(dentes[i]);
            gridSuperior.add(dente, i, 0);
            dentesMap.put(dentes[i], dente);
        }
    }

    private void criarOdontogramaInferior() {
        int[] dentes = {48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38};
        for (int i = 0; i < dentes.length; i++) {
            StackPane dente = criarDente(dentes[i]);
            gridInferior.add(dente, i, 0);
            dentesMap.put(dentes[i], dente);
        }
    }

    private StackPane criarDente(int numero) {
        StackPane stack = new StackPane();
        stack.setPrefSize(50, 60);

        ImageView img = carregarImagemDente(numero);
        if (img != null) {
            img.setFitWidth(40);
            img.setFitHeight(50);
            img.setPreserveRatio(true);
            stack.getChildren().add(img);
        } else {
            Rectangle r = new Rectangle(40, 50);
            r.setFill(Color.WHITE);
            r.setStroke(Color.BLACK);
            stack.getChildren().add(r);
        }

        Text txt = new Text(String.valueOf(numero));
        txt.setStyle("-fx-font-size:10px;-fx-font-weight:bold;");
        Circle condicao = new Circle(5, Color.TRANSPARENT);
        condicao.setStroke(Color.TRANSPARENT);
        condicao.setTranslateY(25);

        stack.getChildren().addAll(txt, condicao);

        stack.setOnMouseClicked(e -> marcarCondicaoDente(numero, condicao));

        return stack;
    }

    private ImageView carregarImagemDente(int numero) {
        try {
            String caminho = "/images/dente" + numero + ".png";
            InputStream is = getClass().getResourceAsStream(caminho);
            if (is != null)
                return new ImageView(new Image(is));
        } catch (Exception e) {
            System.out.println("Erro imagem: " + e.getMessage());
        }
        return null;
    }

    // ================== Marcação ==================
    private void marcarCondicaoDente(int numero, Circle condicao) {
        String[] faces = {"Vestibular","Palatina","Lingual","Oclusal","Mesial","Distal"};
        String[] condicoes = {"Cárie","Restauração","Tratado"};

        ChoiceDialog<String> faceD = new ChoiceDialog<>(faces[0], faces);
        faceD.setTitle("Face do Dente");
        faceD.setHeaderText("Selecione a face do dente " + numero);
        Optional<String> f = faceD.showAndWait();
        if (f.isEmpty()) return;

        ChoiceDialog<String> condD = new ChoiceDialog<>(condicoes[0], condicoes);
        condD.setTitle("Condição do Dente");
        condD.setHeaderText("Selecione a condição do dente " + numero);
        Optional<String> c = condD.showAndWait();
        if (c.isEmpty()) return;

        // Mostra os nomes dos tratamentos vindos do banco
        ChoiceDialog<String> tratD = new ChoiceDialog<>(null, tratamentos.values());
        tratD.setTitle("Tratamento");
        tratD.setHeaderText("Selecione o tratamento para o dente " + numero);
        tratD.setContentText("Tratamento:");
        Optional<String> t = tratD.showAndWait();
        if (t.isEmpty()) return;

        switch (c.get()) {
            case "Cárie" -> condicao.setFill(Color.RED);
            case "Restauração" -> condicao.setFill(Color.BLUE);
            case "Tratado" -> condicao.setFill(Color.GREEN);
        }
        condicao.setStroke(Color.BLACK);

        denteFaces.put(numero, f.get());
        denteCondicoes.put(numero, c.get());
        // converte nome para ID
        String idTratamento = tratamentos.entrySet()
        	    .stream()
        	    .filter(entry -> entry.getValue().equals(t.get()))
        	    .map(Map.Entry::getKey)
        	    .findFirst()
        	    .orElse(null);
        	denteTratamentos.put(numero, idTratamento);
    }

    // ================== Banco e Tabela ==================
    private void carregarOdontogramaDoBanco(String idPaciente) {
        OdontogramaDAO dao = new OdontogramaDAO();
        List<Odontograma> lista = dao.buscarOdontogramaPorPaciente(idPaciente);

        for (Odontograma o : lista) {
            try {
                int numero = Integer.parseInt(o.getNumeroDente());
                StackPane sp = dentesMap.get(numero);
                if (sp == null) continue;

                // Encontra o círculo
                Circle cond = null;
                for (var node : sp.getChildren()) {
                    if (node instanceof Circle c) {
                        cond = c;
                        break;
                    }
                }
                if (cond == null) continue;

                switch (o.getCondicaoDente()) {
                    case "Cárie" -> cond.setFill(Color.RED);
                    case "Restauração" -> cond.setFill(Color.BLUE);
                    case "Tratado" -> cond.setFill(Color.GREEN);
                    default -> cond.setFill(Color.TRANSPARENT);
                }
                cond.setStroke(Color.BLACK);

                denteCondicoes.put(numero, o.getCondicaoDente());
                denteFaces.put(numero, o.getFaceDente());
                denteTratamentos.put(numero, o.getIdTratamento());

            } catch (NumberFormatException e) {
                System.out.println("Erro número dente: " + o.getNumeroDente());
            }
        }
    }

    @FXML
    void salvarOdontograma(ActionEvent e) {
        if (paciente == null) return;
        Map<Integer, Map<String, String>> dentes = getDentesMarcados();
        OdontogramaDAO dao = new OdontogramaDAO();
        dao.deletarOdontogramaPorPaciente(paciente.getIdPaciente());
        boolean ok = dao.salvar(
                paciente.getIdPaciente(),
                ControllerTelaLogin.funcionario.getIdFuncionario(),
                dentes
        );

        Alert alert = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Salvar Odontograma");
        alert.setContentText(ok ? "Odontograma salvo com sucesso!" : "Erro ao salvar odontograma.");
        alert.show();

        carregarTableOdontograma();
    }

    @FXML
    void limparOdontograma(ActionEvent e) {
        if (paciente == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmação");
        confirm.setHeaderText("Tem certeza que deseja limpar o odontograma?");
        confirm.setContentText("Essa ação apagará todas as marcações salvas para o paciente "
                + paciente.getNomePaciente() + ".");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return; // Usuário cancelou
        }

        // Apaga visualmente
        for (StackPane sp : dentesMap.values()) {
            for (var node : sp.getChildren()) {
                if (node instanceof Circle c) {
                    c.setFill(Color.TRANSPARENT);
                    c.setStroke(Color.TRANSPARENT);
                }
            }
        }

        // Limpa os mapas de memória
        denteFaces.clear();
        denteCondicoes.clear();
        denteTratamentos.clear();

        // Apaga do banco de dados
        OdontogramaDAO dao = new OdontogramaDAO();
        dao.deletarOdontogramaPorPaciente(paciente.getIdPaciente());

        // Atualiza a tabela
        carregarTableOdontograma();

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Sucesso");
        info.setHeaderText(null);
        info.setContentText("Odontograma apagado com sucesso!");
        info.showAndWait();
    }


    public Map<Integer, Map<String, String>> getDentesMarcados() {
        Map<Integer, Map<String, String>> marcados = new HashMap<>();
        for (Integer n : denteCondicoes.keySet()) {
            Map<String, String> d = new HashMap<>();
            d.put("face", denteFaces.get(n));
            d.put("condicao", denteCondicoes.get(n));
            d.put("idTratamento", denteTratamentos.get(n));
            marcados.put(n, d);
        }
        return marcados;
    }

    ObservableList<Odontograma> arrayOdontograma;

    public void carregarTableOdontograma() {
        if (paciente == null) return;
        arrayOdontograma = FXCollections.observableArrayList(
                new OdontogramaDAO().buscarOdontogramaPorPaciente(paciente.getIdPaciente())
        );
        ColumnElemento.setCellValueFactory(new PropertyValueFactory<>("numeroDente"));
        ColumnFaceDente.setCellValueFactory(new PropertyValueFactory<>("faceDente"));
        ColumnTratamento.setCellValueFactory(new PropertyValueFactory<>("nomeTratamento"));
        tableOdontograma.setItems(arrayOdontograma);
    }
}
