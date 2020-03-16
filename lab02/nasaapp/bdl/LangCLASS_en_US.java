package bdl;

import java.util.ListResourceBundle;

public class LangCLASS_en_US extends ListResourceBundle {
	Object[][] contents = {
			{"txtLongtitude", "Longtitude CL:"}, 
			{"txtLatitude", "Latitude:"},
			{"txtClouds", "% chmur"},
			{"txtScale", "Scale:"}, 
			{"txtDate", "Date CL:"},
			{"txtDownCloud", "Download Clouds"}, 
			{"txtMenuLang", "Language"},
			{"txtMenuLangGB", "British"}, 
			{"txtMenuLangUS", "American"},
			{"txtMenuUseClass", "Use classes"}, 
			{"textMenuLangPL", "Polish"},
			{"txtMenuLangChoose", "Choose"}, 
			{"txtDescription", "A program to preview images of the Earth captured from NASA satelites"},
			{"txtDownloadData", "Download"},
			{"txtRID", "ID."},
			{"txtRPlanet", "Planet"},
			{"txtRDataSet", "Data set"}, 
			{"txtRService", "Service"},
			{"txtDownloadData", "Download"},
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
