package ctlr;

import java.io.IOException;
import java.net.URL;
import java.text.ChoiceFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nasa.NASAClient;
import nasa.NASAData;
import nasa.NASAResponseException;

public class CTLMain implements Initializable {
	final String szLangDestProp = "resources/Lang";
	final String szLangDestClass = "bdl.LangCLASS";
	private Stage rootStage;
	NASAClient client = new NASAClient("tMp1a9VErUrH9oNaeqMjSdghxV57gE5deJH17KpQ");
	@FXML
	ImageView IVEarth;
	@FXML
	TextField TXTLong;
	@FXML
	TextField TXTLat;
	@FXML
	TextField TXTRID;
	@FXML
	TextField TXTRURL;
	@FXML
	TextField TXTScale;
	@FXML
	TextField TXTRDate;
	@FXML
	TextField TXTRDataSet;
	@FXML
	TextField TXTRPlanet;
	@FXML
	TextField TXTRClouds;
	@FXML
	TextField TXTRService;
	@FXML
	CheckBox CHKCloud;
	@FXML
	Button BDownload;
	@FXML
	TextField TXTDate;
	@FXML
	Label LRClouds;
	@FXML
	Label LDate;
	@FXML
	Label LScale;
	@FXML
	Label LLongtitude;
	@FXML
	Label LLatitude;
	@FXML
	Label LRDate;
	@FXML
	Label LRID;
	@FXML
	Label LRDataSet;
	@FXML
	Label LRPlanet;
	@FXML
	Label LRService;
	@FXML
	Label LRURL;
	@FXML
	Menu MNLang;
	@FXML
	RadioMenuItem RMIUseProp;
	@FXML
	Menu MLang;
	@FXML
	MenuItem MISetPL;
	@FXML
	MenuItem MISetEN;
	@FXML
	MenuItem MISetUS;
	@FXML
	Label LDescr;
	@FXML
	Label LCount;
	private int iLaunchCount = 0;
	boolean useClasses = false;
	private String szCountry;
	private String szRegion;
	private String szSourceBundle = szLangDestProp;
	private ChoiceFormat currFormat;
	private String[] getErrorTxt()
	{
		String[] toRet = new String[5];
		Locale newLocale = getLocale();
		try {
			ResourceBundle gBundle = ResourceBundle.getBundle(szSourceBundle, newLocale);
			toRet[0] = gBundle.getString("txtError");
			toRet[1] = gBundle.getString("txtErrorNMB");
			toRet[2] = gBundle.getString("txtErrorPG");
			toRet[3] = gBundle.getString("txtErrorEX");
			toRet[4] = gBundle.getString("txtErrorDT");
		} catch (MissingResourceException e) {
			e.printStackTrace();
			System.err.println("Nie odnaleziono zasobu: " + e.getClassName() + e.getKey());
			throw new IllegalStateException();
		}
		
		return toRet;
	}
	public void setTitle() {
		Locale newLocale = new Locale("pl", "PL");

		try {
			ResourceBundle gBundle = ResourceBundle.getBundle(szSourceBundle, newLocale);
			rootStage.setTitle(gBundle.getString("txtTitle"));
		} catch (MissingResourceException e) {
			e.printStackTrace();
			System.err.println("Nie odnaleziono zasobu: " + e.getClassName() + e.getKey());
			throw new IllegalStateException();
		}
	}

	private void updateLaunchCount() {
		iLaunchCount++;
		Locale newLocale;
		if (szRegion != null)
			newLocale = new Locale(szCountry, szRegion);
		else
			newLocale = new Locale(szCountry);

		try {
			ResourceBundle gBundle = ResourceBundle.getBundle(szSourceBundle, newLocale);
			LCount.setText(gBundle.getString("txtLaunch") + " " + Integer.toString(iLaunchCount)
					+ gBundle.getString("txtLaunchWord") + currFormat.format((double) iLaunchCount));
		} catch (MissingResourceException e) {
			e.printStackTrace();
			System.err.println("Nie odnaleziono zasobu: " + e.getClassName() + e.getKey());
			throw new IllegalStateException();
		}
	}

	private Locale getLocale() {
		Locale newLocale;
		if (szRegion != null)
			newLocale = new Locale(szCountry, szRegion);
		else
			newLocale = new Locale(szCountry);
		return newLocale;
	}

	private void loadNumb() {
		Locale currLocale = getLocale();
		try {
			ResourceBundle gBundle = ResourceBundle.getBundle(szSourceBundle, currLocale);
			String szNumbers = gBundle.getString("bNumb");
			String[] szNumbersArr = szNumbers.split(",");
			double [] countLimits = new double[szNumbersArr.length];
			for(int i = 0 ; i < szNumbersArr.length; i++)
			{
				countLimits[i] = Double.parseDouble(szNumbersArr[i]);
			}
			String temp = gBundle.getString("txtParts");
			String[] szPart = temp.split(",");
			currFormat = new ChoiceFormat(countLimits, szPart);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			System.err.println("Nie odnaleziono zasobu: " + e.getClassName() + e.getKey());
			throw new IllegalStateException();
		}
	}

	private void setTexts() throws IllegalStateException {
		Locale newLocale = getLocale();
		try {
			ResourceBundle gBundle = ResourceBundle.getBundle(szSourceBundle, newLocale);

			LLongtitude.setText(gBundle.getString("txtLongtitude"));
			LLatitude.setText(gBundle.getString("txtLatitude"));
			LScale.setText(gBundle.getString("txtScale"));
			CHKCloud.setText(gBundle.getString("txtDownCloud"));
			MNLang.setText(gBundle.getString("txtMenuLang"));
			MISetEN.setText(gBundle.getString("txtMenuLangGB"));
			MISetUS.setText(gBundle.getString("txtMenuLangUS"));
			MISetPL.setText(gBundle.getString("textMenuLangPL"));
			MLang.setText(gBundle.getString("txtMenuLangChoose"));
			RMIUseProp.setText(gBundle.getString("txtMenuUseClass"));
			LDescr.setText(gBundle.getString("txtDescription"));
			BDownload.setText(gBundle.getString("txtDownloadData"));
			LDate.setText(gBundle.getString("txtDate"));
			LRDate.setText(gBundle.getString("txtRDate"));
			LRClouds.setText(gBundle.getString("txtClouds"));
			LRDataSet.setText(gBundle.getString("txtRDataSet"));
			LRID.setText(gBundle.getString("txtRID"));
			LRPlanet.setText(gBundle.getString("txtRPlanet"));
			LRDataSet.setText(gBundle.getString("txtRDataSet"));
			LRService.setText(gBundle.getString("txtRService"));
			LRURL.setText(gBundle.getString("txtRURL"));
			LCount.setText(gBundle.getString("txtLaunch"));
			if (rootStage != null)
				rootStage.setTitle(gBundle.getString("txtTitle"));
			loadNumb();
		} catch (MissingResourceException e) {
			e.printStackTrace();
			System.err.println("Nie odnaleziono zasobu: " + e.getClassName() + e.getKey());
			throw new IllegalStateException();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		RMIUseProp.setOnAction((arg) -> {
			if (RMIUseProp.isSelected())
				szSourceBundle = szLangDestClass;
			else
				szSourceBundle = szLangDestProp ;
		});

		BDownload.setOnAction((action) -> {
			TXTLat.setEditable(false);
			TXTLong.setEditable(false);
			client.setClouds(CHKCloud.isSelected());
			updateLaunchCount();
			String [] errors = getErrorTxt();
			try {
				double dLongtitude = Double.valueOf(TXTLong.getText());
				double dLatitude = Double.valueOf(TXTLat.getText());
				double dDim = Double.valueOf(TXTScale.getText());
				NASAData data = client.getData(TXTDate.getText(), dLatitude, dLongtitude, dDim);
				IVEarth.setImage(data.NASAImage);
				TXTRClouds.setText(Double.toString(data.dCloudScale * 100));
				TXTRDataSet.setText(data.szDataSet);
				TXTRDate.setText(data.szDate);
				TXTRID.setText(data.szID);
				TXTRPlanet.setText(data.szPlanet);
				TXTRService.setText(data.szService);
				TXTRURL.setText(data.szURL);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(errors[0]);
				alert.setHeaderText(
						errors[1]);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			} catch (IllegalArgumentException | IOException | InterruptedException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(errors[0]);
				alert.setHeaderText(errors[3]);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			} catch (NASAResponseException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(errors[0]);
				alert.setHeaderText(errors[2]);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(errors[0]);
				alert.setHeaderText(errors[4]);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}

			TXTLat.setEditable(true);
			TXTLong.setEditable(true);

		});

		MISetUS.setOnAction((action) -> {
			szCountry = "en";
			szRegion = "US";
			setTexts();
		});
		MISetPL.setOnAction((action) -> {
			szCountry = "pl";
			szRegion = "PL";
			setTexts();
		});

		MISetEN.setOnAction((action) -> {
			szCountry = "en";
			szRegion = "GB";
			setTexts();
		});
		szCountry = "pl";
		szRegion = "PL";

		setTexts();
	}

	public void setStage(Stage s) {
		this.rootStage = s;
	}

}
