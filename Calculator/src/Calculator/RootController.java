package Calculator;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RootController implements Initializable {
	String temp = "";
	String solveTemp = "";
	String result = "";

	Calculator cal = new Calculator();

	Boolean plusBool = false;
	Boolean minusBool = false;
	Boolean multipleBool = false;
	Boolean divideBool = false;

	String display = "";

	@FXML
	private TextArea TextSheet;

	@FXML
	private Button ButtonZero;
	@FXML
	private Button ButtonOne;
	@FXML
	private Button ButtonTwo;
	@FXML
	private Button ButtonThree;
	@FXML
	private Button ButtonFour;
	@FXML
	private Button ButtonFive;
	@FXML
	private Button ButtonSix;
	@FXML
	private Button ButtonSeven;
	@FXML
	private Button ButtonEight;
	@FXML
	private Button ButtonNine;

	@FXML
	private Button ButtonClear;
	@FXML
	private Button ButtonMultiple;
	@FXML
	private Button ButtonDivide;
	@FXML
	private Button ButtonPlus;
	@FXML
	private Button ButtonMinus;
	@FXML
	private Button ButtonSolve;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void handleBtnClear(ActionEvent e) {

		TextSheet.setText("");

		plusBool = false;
		minusBool = false;
		multipleBool = false;
		divideBool = false;

		temp = "";
		solveTemp = "";

	}

	public void handleBtnZero(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "0");
	}

	public void handleBtnOne(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "1");
	}

	public void handleBtnTwo(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "2");
	}

	public void handleBtnThree(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "3");
	}

	public void handleBtnFour(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "4");
	}

	public void handleBtnFive(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "5");
	}

	public void handleBtnSix(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "6");
	}

	public void handleBtnSeven(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "7");
	}

	public void handleBtnEight(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "8");
	}

	public void handleBtnNine(ActionEvent e) {
		display = TextSheet.getText();
		TextSheet.setText(display + "9");
	}

	public void handleBtnPlus(ActionEvent e) {
		temp = TextSheet.getText();
		TextSheet.setText("");
		plusBool = true;
	}

	public void handleBtnMinus(ActionEvent e){
		if(TextSheet.getText().equals("")){
			TextSheet.setText("-");
			return;
		}
		
		temp = TextSheet.getText();
		TextSheet.setText("");
		minusBool = true;
	}

	public void handleBtnMultiple(ActionEvent e) {
		temp = TextSheet.getText();
		TextSheet.setText("");
		multipleBool = true;
	}

	public void handleBtnDivide(ActionEvent e) {
		temp = TextSheet.getText();
		TextSheet.setText("");
		divideBool = true;
	}

	public void handleBtnSolve(ActionEvent e) {

		solveTemp = TextSheet.getText();

		if (plusBool == true) {
			result = cal.plus(temp, solveTemp);
			cal.clear();
		} else if (minusBool == true) {
			result = cal.minus(temp, solveTemp);
			cal.clear();
		} else if (multipleBool == true) {
			result = cal.multiply(temp, solveTemp);
			cal.clear();
		} else if (divideBool == true) {
			result = cal.divide(temp, solveTemp);
			cal.clear();
		}

		TextSheet.setText(result);

		temp = "";
		solveTemp = "";
		result = "";

		plusBool = false;
		minusBool = false;
		multipleBool = false;
		divideBool = false;
		
		cal = new Calculator();

	}
}