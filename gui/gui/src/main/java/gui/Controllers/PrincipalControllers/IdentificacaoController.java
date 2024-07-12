package gui.Controllers.PrincipalControllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import gui.Dtos.RegimeTributarioDto;
import gui.Dtos.Style;
import gui.Dtos.Uf_Enum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IdentificacaoController {

	Style effects = new Style();

	@FXML
	private Label info;

	@FXML
	private ChoiceBox<String> regime;

	@FXML
	private TextField razao;

	@FXML
	private TextField fantasia;

	@FXML
	private TextField cnpj;

	@FXML
	private TextField ie;

	@FXML
	private CheckBox isento;

	@FXML
	private TextField endereco;

	@FXML
	private TextField num;

	@FXML
	private TextField compl;

	@FXML
	private TextField cidade;

	@FXML
	private TextField cep;

	@FXML
	private ComboBox<Uf_Enum> uf;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	public Label getInfo() {
		return info;
	}

	public void setInfo(Label info) {
		this.info = info;
	}

	public ChoiceBox<String> getRegime() {
		return regime;
	}

	public TextField getRazao() {
		return razao;
	}

	public TextField getFantasia() {
		return fantasia;
	}

	public TextField getCnpj() {
		return cnpj;
	}

	public TextField getIe() {
		return ie;
	}

	public CheckBox getIsento() {
		return isento;
	}

	public TextField getEndereco() {
		return endereco;
	}

	public TextField getNum() {
		return num;
	}

	public TextField getCompl() {
		return compl;
	}

	public TextField getCidade() {
		return cidade;
	}

	public TextField getCep() {
		return cep;
	}

	public ComboBox<Uf_Enum> getUf() {
		return uf;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setRegime(ChoiceBox<String> regime) {
		this.regime = regime;
	}

	public void setRazao(TextField razao) {
		this.razao = razao;
	}

	public void setFantasia(TextField fantasia) {
		this.fantasia = fantasia;
	}

	public void setCnpj(TextField cnpj) {
		this.cnpj = cnpj;
	}

	public void setIe(TextField ie) {
		this.ie = ie;
	}

	public void setIsento(CheckBox isento) {
		this.isento = isento;
	}

	public void setEndereco(TextField endereco) {
		this.endereco = endereco;
	}

	public void setNum(TextField num) {
		this.num = num;
	}

	public void setCompl(TextField compl) {
		this.compl = compl;
	}

	public void setCidade(TextField cidade) {
		this.cidade = cidade;
	}

	public void setCep(TextField cep) {
		this.cep = cep;
	}

	public void setUf(ComboBox<Uf_Enum> uf) {
		this.uf = uf;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void initialize() {
		getUf().getItems().setAll(Uf_Enum.values());
		getRegime().setValue(RegimeTributarioDto.MEI.getDescricao());
		getRegime().getItems().add(RegimeTributarioDto.MEI.getDescricao());
		getRegime().getItems().add(RegimeTributarioDto.RN.getDescricao());
		getRegime().getItems().add(RegimeTributarioDto.SN.getDescricao());

	}

	public void ieIsentoEnabled() {
		if (getIsento().isSelected()) {
			getIe().setDisable(true);
			getIe().setText("Isento");
		} else {
			getIe().setDisable(false);
		}
		getIe().setText(null);

	}

	@FXML
	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {
		// VERIFICA CAMPOS VAZIOS
		/*
		if (getRazao().getText().isEmpty() || getFantasia().getText().isEmpty() || getCnpj().getText().isEmpty()
				|| getIe().getText().isEmpty() || getEndereco().getText().isEmpty() || getNum().getText().isEmpty()
				|| getCidade().getText().isEmpty() || getCep().getText().isEmpty() || getUf().getValue().equals(null)) {
			getInfo().setText("*Campos Obrigatórios!");
			effects.campoObrigatorio(regime);
			effects.campoObrigatorio(getRazao());
			effects.campoObrigatorio(getFantasia());
			effects.campoObrigatorio(getCnpj());
			effects.campoObrigatorio(getIe());
			effects.campoObrigatorio(getEndereco());
			effects.campoObrigatorio(getNum());
			effects.campoObrigatorio(getCidade());
			effects.campoObrigatorio(getCep());
			effects.campoObrigatorio(getUf());
			return;
		}
		else {
			effects.campoObrigatorioRemove(getRegime());
			effects.campoObrigatorioRemove(getFantasia());
			effects.campoObrigatorioRemove(getCnpj());
			effects.campoObrigatorioRemove(getIe());
			effects.campoObrigatorioRemove(getEndereco());
			effects.campoObrigatorioRemove(getNum());
			effects.campoObrigatorioRemove(getCidade());
			effects.campoObrigatorioRemove(getCep());
			effects.campoObrigatorioRemove(getUf());
			getInfo().setText(null);
		
		*/
		  Path path = Path.of(System.getProperty("resources"), "Emissor.yaml");
		  InputStream input = Files.newInputStream(path);
		  Yaml yaml = new Yaml();
		  yaml.load(input);
		    System.out.println(yaml);
		        //result.put("Regime", "DESATIVADO");

		        //(yaml, path, result);
		  
		
	}


		
		private static void write(Yaml yaml, Path path, Map<String, String> content) {
		    try (Writer output = Files.newBufferedWriter(path)) {
		      yaml.dump(content, output);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		
	private static Map<String, String> read(Yaml yaml, Path path) {
	    try (InputStream input = Files.newInputStream(path)) {
	      return yaml.load(input);
	    } catch (IOException e) {
	      e.printStackTrace();
	      return Map.of();
	    }
	  }
	
	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

}
