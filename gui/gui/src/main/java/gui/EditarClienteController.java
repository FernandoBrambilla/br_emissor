package gui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import gui.Models.Clients;
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

public class EditarClienteController {

	static Clients cliente = null;

	private String token = PrincipalController.getAccessToken();

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
	private TextArea obs;

	@FXML
	private Label labelCpf_Cnpj;

	@FXML
	private Label labelRgIe;

	@FXML
	private Label labelDataNascConst;

	@FXML
	private Label labelEmissRg;

	public static Clients getCliente() {
		return cliente;
	}

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
	public TextArea getObs() {
		return obs;
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

	public static void setCliente(Clients cliente) {
		EditarClienteController.cliente = cliente;
	}

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
	public void setObs(TextArea obs) {
		this.obs = obs;
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

	public boolean isNull() {
		return ClientesController.getTabelaClientes().getSelectionModel().isEmpty();
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
		getPf().setDisable(true);
		getPj().setDisable(true);
		getUf().getItems().setAll(Uf_Enum.values());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		if (ClientesController.getTabelaClientes().getSelectionModel().getSelectedItem().getTipo().equals("Jurídica")) {
			getPj().setSelected(true);
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
		setCliente(ClientesController.getTabelaClientes().getSelectionModel().getSelectedItem());
		getName().setText(getCliente().getName());
		getCpf_cnpj().setText(getCliente().getCpf_cnpj());
		getEmail().setText(getCliente().getEmail());
		getRg_ie().setText(getCliente().getRg_ie());
		getPhone().setText(getCliente().getPhone());
		getDataNasc_Const().setValue(LocalDate.parse(getCliente().getDateNasc_const(), formatter));
		getAdress().setText(getCliente().getAddress());
		getDataEmis().setValue(LocalDate.parse(getCliente().getDateExp(), formatter));
		getNum().setText(getCliente().getAddressNumber());
		getCompl().setText(getCliente().getAddressComplement());
		getCity().setText(getCliente().getCity());
		getCep().setText(getCliente().getCep());
		getUf().setValue(getCliente().getUf());
		getObs().setText(getCliente().getObs());
	}

	@SuppressWarnings("exports")
	public void salvar(ActionEvent action) throws IOException {

		// VERIFICA SE CAMPOS NOME VAZIO
		if (getName().getText().isEmpty()) {
			getInfo().setText("*Campos Obrigatórios!");
			getEffect().campoObrigatorio(getName());
			return;
		} else {
			getEffect().campoObrigatorioRemove(getName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			// SETA DATA ATUAL CASO NÃO SEJA INFORMADA (DATA DEEXPEDIÇÃO E NASCIMENTO)
			LocalDate dateNascConst = getDataNasc_Const().getValue() == null ? LocalDate.now()
					: getDataNasc_Const().getValue();
			LocalDate dateExp = getDataEmis().getValue() == null ? LocalDate.now() : getDataEmis().getValue();

			try {
				JSONObject json = new JSONObject();
				json.put("id", getCliente().getId());
				json.put("name", getName().getText());
				json.put("tipo", getCliente().getTipo());
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
				
				// REQUIÇÃO PARA ATUALIZAR
				String urlUpdate = "http://localhost:8080/clients";
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlUpdate))
						.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
						.PUT(HttpRequest.BodyPublishers.ofString(json.toString())).build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String message = response.body().substring(83, 152);
				int statusCode = response.statusCode();

				if (statusCode == 200) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Cliente \"" + getName().getText() + "\" atualizado com sucesso! ");
					alert.showAndWait();
					Stage stage = (Stage) btnCancelar.getScene().getWindow();
					stage.close();
					ClientesController.popularTabela();
				}

				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Cliente \"" + getName().getText() + "\" não alterado! Motivo: " + message);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
				}

			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Erro. " + e.getMessage());
				alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert.showAndWait();
			}
		}

	}

	@SuppressWarnings("exports")
	public void cancelar(ActionEvent action) throws IOException {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

}
