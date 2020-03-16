package bdl;

import java.util.ListResourceBundle;

public class LangCLASS_en_GB extends ListResourceBundle {
	Object[][] contents = {
			{"txtLongtitude", "Longtitude GB CLASS"}, 
			{"txtLatitude", "Latitude GB"}, 
			{"txtClouds", "% cloud GB"},
			{"txtScale", "Scale GB:"}, 
			{"txtDate", "Date:"},
			{"txtDownCloud", "Download CloudsGB"}, 
			{"txtMenuLang", "LanguageGB"},
			{"txtMenuLangGB", "BritishGB"}, 
			{"txtMenuLangUS", "AmericanGB"},
			{"txtMenuUseClass", "Use classesGB"}, 
			{"textMenuLangPL", "PolishGB"},
			{"txtMenuLangChoose", "ChooseGB"}, 
			{"txtDescription", "A program to preview images of the Earth captured from NASA satelitesGB"},
			{"txtDownloadData", "Download CLASS"},
			{"txtRID", "ID."},
			{"txtRPlanet", "Planet"},
			{"txtRDataSet", "Data set"}, 
			{"txtRService", "Service"},
			{"txtDownloadData", "DownloadGB"},
			{"txtRURL", "Link"},
			{"txtRDate", "Shoot date"},
			{"txtTitle", "NASA: Photo Viewer"},
			{"txtLaunch", "Executions:"},
			{"bNumb", "0,1,2"},
			{"txtParts", "s, ,s"},
			{"txtLaunchWord", "time"},
			{"txtErrorNMB", "Error during conversion of "
					+ "numbers. Please check latitude, longtitude, and scale (bt. 0.01 and 1) fields."},
			{"txtErrorPG", "Error. Page returned:"},
			{"txtErrorDT", "Wrong date format. Expected YYYY-MM-DD."},
			{"txtErrorEX", "Exception was caught."},
			{"txtError", "Error."}
	};
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}

}
