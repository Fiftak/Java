package console;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import fcheck.FileCheck;
import fcheck.FileCheckDB;

public class ConsoleParser {

	public void compareFolders(String szFirst, String szSec) {
		FileCheck fHandler = new FileCheck();
		try {
			FileCheckDB checkSum1 = fHandler.checkDir(szFirst);
			FileCheckDB checkSum2 = fHandler.checkDir(szSec);
			compareMD5(checkSum1, checkSum2);

		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void storeMD5(String szFrom, String szWhere) {
		FileCheck fHandler = new FileCheck();
		FileCheckDB checkSum1;
		try {
			checkSum1 = fHandler.checkDir(szFrom);
			checkSum1.save(szWhere);
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void compareMD5(FileCheckDB src, FileCheckDB comp)
	{
		if (src.check(comp))
			System.out.println("Folders match.");
		else
			System.out.println("Folders do not match.");
	}
	
	public void compareFromMD5(String szFrom, String szWith)
	{
		try {
			FileCheckDB checkSum1 = FileCheckDB.load(szFrom);
			FileCheckDB checkSum2 = FileCheckDB.load(szWith);
			compareMD5(checkSum1, checkSum2);
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	public void compareFolderMD5(String szDir, String MD5)
	{
		FileCheck fHandler = new FileCheck();
		
		try {
			FileCheckDB checkSum1 = fHandler.checkDir(szDir);
			FileCheckDB checkSum2 = FileCheckDB.load(MD5);
			compareMD5(checkSum1, checkSum2);

		} catch (NoSuchAlgorithmException | IOException | ClassNotFoundException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	public void parseCommands(String... args) throws CommandNotFoundException {

		boolean isFound = false;
		switch (args[0]) {
		case "-compdirs":
			compareFolders(args[1], args[2]);
			break;
		case "-compmd5":
			compareFromMD5(args[1], args[2]);
			break;
		case "-storemd5":
			storeMD5(args[1], args[2]);
			break;
		case "-compdirmd5":
			compareFolderMD5(args[1], args[2]);
			break;
				
		default:
			throw new CommandNotFoundException(args[0]);
		}

	}

	public static void printUsage()
	{
		System.out.println("Usage: md5lib <command> <opt1> <opt2>, a tool for generating and comparing MD5 checksums, used to determine the integrity of folders' contents.");
		System.out.println("-compdirs dir1 dir2 - compare content of dir1 with dir2 using MD5 checksum");
		System.out.println("-storemd5 dir1 loc1 - store MD5 checksum of folder dir1 to loc1");
		System.out.println("-compdirmd5 dir1 loc1 - compare content of dir1 with generated checksum of loc1");
		System.out.println("-compmd5 loc1 loc2 - compare content of 2 checksum located at loc1 and loc2 \n");
		
	}
	public static void main(String[] args) {
		if(args.length < 3) {
			System.err.println("Too few arguments.");
			printUsage();
		} else {
			ConsoleParser parser = new ConsoleParser();
			try {
				parser.parseCommands(args);
			} catch (CommandNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
}
