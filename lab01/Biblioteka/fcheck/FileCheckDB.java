package fcheck;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileCheckDB implements Serializable {

	private static final long serialVersionUID = 6430516120827789827L;

	ArrayList<byte[]> dbMD5 = new ArrayList<byte[]>();
													
	public void addMD5(byte[] bMD5) {
		dbMD5.add(bMD5); 
	}

	public boolean check(FileCheckDB fdb) {
		boolean isFound = false;

		if (fdb.dbMD5.size() != this.dbMD5.size())
			return false;

		for (byte[] data : fdb.dbMD5) {
			for (byte[] srcdata : this.dbMD5) {
				if (Arrays.equals(data, srcdata))
					isFound = true;
			}

			if (isFound)
				break;
		}
		return isFound;
	}


	// Funkcja do zapisu bazy danych.
	
	public void save(String where) throws IOException {
		try (OutputStream str = Files.newOutputStream(Paths.get(where));
				ObjectOutputStream ostr = new ObjectOutputStream(str)) {
			ostr.writeObject(this);
		}

	}


	 // Załadowanie bazy danych do pamięci

	public static FileCheckDB load(String from) throws ClassNotFoundException, IOException, ClassCastException {
		FileCheckDB toRet = null;

		try (InputStream str = Files.newInputStream(Paths.get(from));
				ObjectInputStream ostr = new ObjectInputStream(str)) {
			toRet = (FileCheckDB) ostr.readObject();
		}

		return toRet;
	}

	public static void main(String[] args) {
		System.out.println("Hello from MAIN, CLASS: " + FileCheckDB.class.getCanonicalName());
	}

}
