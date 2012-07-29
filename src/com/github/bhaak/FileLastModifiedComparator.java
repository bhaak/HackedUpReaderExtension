package com.github.bhaak;

import java.io.File;
import java.util.Comparator;

/**
 * A File comparator comparing file.lastModified.
 * 
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 */
public class FileLastModifiedComparator implements Comparator<File> {

	public int compare(File file1, File file2) {
		long result = file2.lastModified() - file1.lastModified();
		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

