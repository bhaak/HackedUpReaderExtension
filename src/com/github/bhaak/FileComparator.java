package com.github.bhaak;

import java.io.File;
import java.text.Collator;
import java.util.Comparator;

/**
 * A case-insensitive File comparator.
 * 
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 */
public class FileComparator implements Comparator<File> {
	Collator collator;

	public FileComparator() {
		collator = Collator.getInstance();
		// compare case insensitive
		collator.setStrength(Collator.SECONDARY);
	}
	public int compare(File file1, File file2) {
		return collator.compare(file1.getName(), file2.getName());
	}
}

