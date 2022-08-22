/**
* Class to calculate the diff between two files
* */

import structure5.*;
import java.util.Scanner;

/**
* Class to calculate the diff between two files */
public class BetterHashDiff extends Diff {
	protected Hashtable<BetterIntPair, Association<Integer, String>> table;

	/**
	* Constructor for diff
	* @param 	 	file1Name is the path to the original file
	* @param 		file2Name is the path to the new version of the file
	*/
	public BetterHashDiff(String file1Name, String file2Name) {
		super(file1Name, file2Name);
		table = new Hashtable<BetterIntPair, Association<Integer, String>>();
	}

	/** The recursive helper method for calulating the diff @pre
	 * remainingFile1, remainingFile2, and table are not null @param
	 * remainingFile1Index the first line of file 1 not yet diffed @param
	 * remainingFile2Index the first line of file 2 not yet diffed @return An
	 * association corresponding to the diff between remainingFile1 and
	 * remainingFile2.  The key is the cost of the diff (number of changes
	 * necessary).  The value is the diff output. */
	public Association<Integer, String> diffHelper(int remainingFile1Index, int
			remainingFile2Index) {

		// initialize result association
		Association<Integer, String> result = new Association<Integer, String>(0, "");
		// initialize BetterIntPair object as key
		BetterIntPair hashPair = new BetterIntPair(remainingFile1Index, remainingFile2Index);

		// if table contains key, return value association as result
		if (table.containsKey(hashPair)) {
			 result = table.get(hashPair);
		// otherwise, call Diff's diffHelper, return value is result
		// put return value in table
		} else {
			 result = super.diffHelper(remainingFile1Index, remainingFile2Index);
			 table.put(hashPair, result);
		}
	 return result;
	}

	/**
	* main method: two command line arguments; the first is the original file,
	* the second is the new version to be compared to. */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java BetterHashDiff <file1> <file2>");
			System.exit(1);
		}
		Diff diff = new BetterHashDiff(args[0], args[1]);
		diff.findDiff();
	}
}
