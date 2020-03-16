package bdl;

import java.util.ListResourceBundle;

public class LangCLASS_pl_PL extends ListResourceBundle {

	Object[][] contents = {
			{"txtLongtitude", "Długość geograficzna"}, 
			{"txtLatitude", "Szerokość geograficzna"},
			{"txtClouds", "% chmur"},
			{"txtScale", "Skala:"}, 
			{"txtDate", "Data:"},
			{"txtDownCloud", "Pobierz % chmur"}, 
			{"txtMenuLang", "Języki"},
			{"txtMenuLangGB", "Brytyjski"}, 
			{"txtMenuLangUS", "Amerykański"},
			{"txtMenuUseClass", "Używaj klas"}, 
			{"textMenuLangPL", "Polski"},
			{"txtMenuLangChoose", "Wybór"}, 
			{"txtDescription", "Program do odczytu zdjęć Ziemi z satelit NASA"},
			{"txtRID", "Ident."},
			{"txtRDataSet", "Zbiór danych"},
			{"txtRPlanet", "Planeta"}, 
			{"txtRService", "Serwis"},
			{"txtRURL", "Odnosnik"},
			{"txtRDate", "Data wyk."},
			{"txtTitle", "NASA: Pobieranie zdjęć"},
			{"txtDownloadData", "Pobierz dane CLASS"},
			{"txtLaunch", "Ilosc wywolan: "},
			{"txtLaunchWord", "wywola"},
			{"bNumb", "0,1,2,5"},
			{"txtParts", "n,nie,nia,n"},
			{"txtErrorNMB", "Blad podczas konwersji liczb. Prosze sprawdzic takie pola "
					+ "jak dl./szer. geogr. oraz skala (musi byc od 0.01 do 1)."},
			{"txtErrorPG", "Blad. Strona zwrocila:"},
			{"txtErrorDT", "Zly format daty. Spodziewany format RRRR-MM-DD."},
			{"txtErrorEX", "Zlapano wyjatek."},
			{"txtError", "Blad."},
			
			
	};
	
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}
}
