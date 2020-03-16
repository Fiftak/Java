package fcheck;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * Klasa do generowania bazy danych wpisów MD5/sygnatury.
 */
public class FileCheck {

	private MessageDigest hashHandler;
	private FileCheckDB tempDB;
	
	private BiPredicate<Path, BasicFileAttributes> Predicate = new BiPredicate<Path, BasicFileAttributes>() {
		@Override
		public boolean test(Path t, BasicFileAttributes u) {
			return true;
		}
	};

	private Consumer<? super Path> myAction = new Consumer<>() {
		@Override
		public void accept(Path t) {
			// TODO Auto-generated method stub
			try {
				BasicFileAttributes attr = Files.readAttributes(t, BasicFileAttributes.class);

				if (!attr.isDirectory())
					tempDB.addMD5(digestFile(t));

				tempDB.addMD5(digestFileName(t));
				tempDB.addMD5(digestFileAttr(attr));
			} catch (NullPointerException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};


	 // Funkcja do hashowania atrybutów pliku, takich jak czas utworzenia,
	 // modyfikacji, ostatniego dostępu.

	private byte[] digestFileAttr(BasicFileAttributes attr) {
		byte[] toRet;
		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append(attr.creationTime().toString());
		strBuilder.append(attr.lastModifiedTime().toString());
		strBuilder.append(attr.lastAccessTime().toString());
		toRet = strBuilder.toString().getBytes();
		toRet = hashHandler.digest(toRet);
		return toRet;

	}

	private byte[] digestFileName(Path inputFile) throws NullPointerException {

		if (hashHandler == null)
			throw new NullPointerException("MessageDigest null");
		byte[] toRet = null;

		toRet = inputFile.getFileName().toString().getBytes();
		toRet = hashHandler.digest(toRet);
		return toRet;
	}

	 // Przetwórz zawartość pliku wskazanego w ścieżce.

	private byte[] digestFile(Path inputFile) throws IOException, NullPointerException {
		if (hashHandler == null)
			throw new NullPointerException("MessageDigest null");

		byte[] toRet;

		try (InputStream stream = Files.newInputStream(inputFile);
				DigestInputStream dStream = new DigestInputStream(stream, hashHandler)) {
			dStream.readAllBytes(); 
		}
		toRet = hashHandler.digest();
		return toRet;
	}

	public FileCheck() {
	}

	 // Funkcja do hashownia zawartości folderu.

	public FileCheckDB checkDir(String szPathFirst) throws NoSuchAlgorithmException, IOException {

		tempDB = new FileCheckDB(); 
		FileCheckDB toRet = tempDB;
		Path pathFirst = Path.of(szPathFirst);
		hashHandler = MessageDigest.getInstance("MD5");

		try (Stream<Path> files = Files.find(pathFirst, 999, Predicate)) {
			files.forEach(myAction);
		}

		tempDB = null;
		return toRet;

	}
}
