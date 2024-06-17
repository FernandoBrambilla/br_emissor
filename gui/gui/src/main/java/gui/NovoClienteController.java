package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import gui.Models.Style;
import gui.Models.Uf_Enum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class NovoClienteController {

	private static String token = PrincipalController.getAccessToken();

	private Style effect = new Style();

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label info;

	@FXML
	private RadioButton pf;

	@FXML
	private RadioButton pj;

	@FXML
	private TextField name;

	@FXML
	private TextField cpf_cnpj;

	@FXML
	private TextField email;

	@FXML
	private TextField rg_ie;

	@FXML
	private TextField phone;

	@FXML
	private DatePicker dataNasc_Const;

	@FXML
	private TextField adress;

	@FXML
	private RadioButton IeIsento;

	@FXML
	private DatePicker dataEmis;

	@FXML
	private TextField num;

	@FXML
	private TextField compl;

	@FXML
	private TextField city;

	@FXML
	private TextField cep;

	@FXML
	private ComboBox<Uf_Enum> uf;

	@FXML
	private Label labelCpf_Cnpj;

	@FXML
	private Label labelRgIe;

	@FXML
	private Label labelDataNascConst;

	@FXML
	private Label labelEmissRg;

	@FXML
	private TextArea obs;

	public String getToken() {
		return token;
	}

	public Style getEffect() {
		return effect;
	}

	@SuppressWarnings("exports")
	public Button getBtnSalvar() {
		return btnSalvar;
	}

	@SuppressWarnings("exports")
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	@SuppressWarnings("exports")
	public Label getInfo() {
		return info;
	}

	@SuppressWarnings("exports")
	public RadioButton getPf() {
		return pf;
	}

	@SuppressWarnings("exports")
	public RadioButton getPj() {
		return pj;
	}

	@SuppressWarnings("exports")
	public TextField getName() {
		return name;
	}

	@SuppressWarnings("exports")
	public TextField getCpf_cnpj() {
		return cpf_cnpj;
	}

	@SuppressWarnings("exports")
	public TextField getEmail() {
		return email;
	}

	@SuppressWarnings("exports")
	public TextField getRg_ie() {
		return rg_ie;
	}

	@SuppressWarnings("exports")
	public TextField getPhone() {
		return phone;
	}

	@SuppressWarnings("exports")
	public DatePicker getDataNasc_Const() {
		return dataNasc_Const;
	}

	@SuppressWarnings("exports")
	public TextField getAdress() {
		return adress;
	}

	@SuppressWarnings("exports")
	public RadioButton getIeIsento() {
		return IeIsento;
	}

	@SuppressWarnings("exports")
	public DatePicker getDataEmis() {
		return dataEmis;
	}

	@SuppressWarnings("exports")
	public TextField getNum() {
		return num;
	}

	@SuppressWarnings("exports")
	public TextField getCompl() {
		return compl;
	}

	@SuppressWarnings("exports")
	public TextField getCity() {
		return city;
	}

	@SuppressWarnings("exports")
	public TextField getCep() {
		return cep;
	}

	public ComboBox<Uf_Enum> getUf() {
		return uf;
	}

	@SuppressWarnings("exports")
	public Label getLabelCpf_Cnpj() {
		return labelCpf_Cnpj;
	}

	@SuppressWarnings("exports")
	public Label getLabelRgIe() {
		return labelRgIe;
	}

	@SuppressWarnings("exports")
	public Label getLabelDataNascConst() {
		return labelDataNascConst;
	}

	@SuppressWarnings("exports")
	public Label getLabelEmissRg() {
		return labelEmissRg;
	}

	@SuppressWarnings("exports")
	public TextArea getObs() {
		return obs;
	}

	@SuppressWarnings("static-access")
	public void setToken(String token) {
		this.token = token;
	}

	public void setEffect(Style effect) {
		this.effect = effect;
	}

	@SuppressWarnings("exports")
	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	@SuppressWarnings("exports")
	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@SuppressWarnings("exports")
	public void setInfo(Label info) {
		this.info = info;
	}

	@SuppressWarnings("exports")
	public void setPf(RadioButton pf) {
		this.pf = pf;
	}

	@SuppressWarnings("exports")
	public void setPj(RadioButton pj) {
		this.pj = pj;
	}

	@SuppressWarnings("exports")
	public void setName(TextField name) {
		this.name = name;
	}

	@SuppressWarnings("exports")
	public void setCpf_cnpj(TextField cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	@SuppressWarnings("exports")
	public void setEmail(TextField email) {
		this.email = email;
	}

	@SuppressWarnings("exports")
	public void setRg_ie(TextField rg_ie) {
		this.rg_ie = rg_ie;
	}

	@SuppressWarnings("exports")
	public void setPhone(TextField phone) {
		this.phone = phone;
	}

	@SuppressWarnings("exports")
	public void setDataNasc_Const(DatePicker dataNasc_Const) {
		this.dataNasc_Const = dataNasc_Const;
	}

	@SuppressWarnings("exports")
	public void setAdress(TextField adress) {
		this.adress = adress;
	}

	@SuppressWarnings("exports")
	public void setIeIsento(RadioButton ieIsento) {
		IeIsento = ieIsento;
	}

	@SuppressWarnings("exports")
	public void setDataEmis(DatePicker dataEmis) {
		this.dataEmis = dataEmis;
	}

	@SuppressWarnings("exports")
	public void setNum(TextField num) {
		this.num = num;
	}

	@SuppressWarnings("exports")
	public void setCompl(TextField compl) {
		this.compl = compl;
	}

	@SuppressWarnings("exports")
	public void setCity(TextField city) {
		this.city = city;
	}

	@SuppressWarnings("exports")
	public void setCep(TextField cep) {
		this.cep = cep;
	}

	public void setUf(ComboBox<Uf_Enum> uf) {
		this.uf = uf;
	}

	@SuppressWarnings("exports")
	public void setLabelCpf_Cnpj(Label labelCpf_Cnpj) {
		this.labelCpf_Cnpj = labelCpf_Cnpj;
	}

	@SuppressWarnings("exports")
	public void setLabelRgIe(Label labelRgIe) {
		this.labelRgIe = labelRgIe;
	}

	@SuppressWarnings("exports")
	public void setLabelDataNascConst(Label labelDataNascConst) {
		this.labelDataNascConst = labelDataNascConst;
	}

	@SuppressWarnings("exports")
	public void setLabelEmissRg(Label labelEmissRg) {
		this.labelEmissRg = labelEmissRg;
	}

	@SuppressWarnings("exports")
	public void setObs(TextArea obs) {
		this.obs = obs;
	}

	@SuppressWarnings("exports")
	public void setPJ(ActionEvent action) {
		getLabelCpf_Cnpj().setText("CNPJ");
		getCpf_cnpj().setPromptText("99.999.999/9999-99");
		getLabelRgIe().setText("IE");
		getRg_ie().setPromptText("Insc. Estadual");
		getLabelDataNascConst().setVisible(false);
		getDataNasc_Const().setVisible(false);
		getIeIsento().setVisible(true);
		getLabelEmissRg().setText("Data de Cadastro");
		getPf().setSelected(false);
	}

	@SuppressWarnings({ "exports" })
	public void setPF(ActionEvent action) {
		getLabelCpf_Cnpj().setText("CPF");
		getCpf_cnpj().setPromptText("999.999.999-99");
		getLabelRgIe().setText("RG");
		getRg_ie().setPromptText("99999999");
		getLabelDataNascConst().setVisible(true);
		getDataNasc_Const().setVisible(true);
		getIeIsento().setVisible(false);
		getLabelEmissRg().setText("Data de Emissão RG");
		getPj().setSelected(false);
	}

	@SuppressWarnings("exports")
	public void ieIsentoAction(ActionEvent action) {
		if (getIeIsento().isSelected()) {
			getRg_ie().setDisable(true);
			getLabelRgIe().setDisable(true);
		} else {
			getRg_ie().setDisable(false);
			getLabelRgIe().setDisable(false);
		}
	}

	public void initialize() {
		getUf().setValue(Uf_Enum.AC);
		getUf().getItems().setAll(Uf_Enum.values());
		ClientesController.novoClient.setUf(uf);
		ClientesController.novoClient.setDataEmis(null);
		ClientesController.novoClient.setDataNasc_Const(null);
	}

	@FXML
	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {
		try {
			// VERIFICA CAMPOS VAZIOS
			if (getName().getText().isEmpty()) {
				getInfo().setText("*Campo Obrigatórios!");
				getEffect().campoObrigatorio(getName());
				return;
			} else {
				getEffect().campoObrigatorioRemove(getName());
				getInfo().setText(null);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

				// SETA DATA ATUAL CASO NÃO SEJA INFORMADA (DATA DEEXPEDIÇÃO E NASCIMENTO)
				LocalDate dateNascConst = getDataNasc_Const().getValue() == null ? LocalDate.now()
						: getDataNasc_Const().getValue();
				LocalDate dateExp = getDataEmis().getValue() == null ? LocalDate.now() : getDataEmis().getValue();
				String tipo = getPf().isSelected() ? "Física" : "Jurídica";

				JSONObject json = new JSONObject();
				json.put("name", getName().getText()); 
				json.put("tipo", tipo);
				json.put("phone", getPhone().getText());
				json.put("email", getEmail().getText());
				json.put("cpf_cnpj", getCpf_cnpj().getText());
				json.put("rg_ie", getRg_ie().getText());
				json.put("dateNasc_const", dateNascConst.format(formatter));
				json.put("dateExp", dateExp.format(formatter));
				json.put("address", getAdress().getText());
				json.put("addressNumber", getNum().getText());
				json.put("addressComplement", getCompl().getText());
				json.put("city", getCity().getText());
				json.put("uf", getUf().getValue());
				json.put("cep", getCep().getText());
				json.put("obs", getObs().getText());

				// REQUIÇÃO
				String url = "http://localhost:8080/clients";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String message = response.body();
				int status = response.statusCode();

				if (status == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Cliente \"" + getName().getText() + "\" cadastrado com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) btnCancelar.getScene().getWindow();
					stage.close();

					// ATUALIZA A TABELA
					ClientesController.popularTabela();
				} else {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Cliente \"" + getName().getText() + "\" não cadastrado! Motivo: " + message);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();

				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Erro. " + e.getMessage() + e.getCause());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		}
	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

}
