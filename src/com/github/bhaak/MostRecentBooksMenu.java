package com.github.bhaak;

import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;

import com.yifanlu.Kindle.LauncherAction;
import com.yifanlu.Kindle.LauncherMenu;

public class MostRecentBooksMenu extends LauncherMenu {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = 3432997896321432046L;
	/** The directory containing the ebooks. */
	private File directory;
	/** The executable ebook reader binary. */
	private File executable;

	public MostRecentBooksMenu(String name, File directory, File executable, int priority) {
		super(name, priority);
		this.directory = directory;
		this.executable = executable;
	}
	
	private static FileLastModifiedComparator fileComparator = new FileLastModifiedComparator();
	private static BookFilter bookfilter = new BookFilter();
	
	@Override
	public void initMenu() {
		// get all ebooks ordered by lastModified date
		TreeSet<File> files = new TreeSet<File>(fileComparator);
		getMostRecentBooks(directory, files);
		
		// iterate over the 10 newest files
		Iterator<File> it = files.iterator();
		int counter = 0;
		while (it.hasNext() && counter++ < 10) {
			File file = it.next();
			LauncherAction launcherScript = new LauncherExecutable(
					file.getName(),
					counter,
					executable,
					file.getAbsolutePath());
			this.addMenuItem(launcherScript);
		}
		
		super.initMenu();
	}

	private void getMostRecentBooks(File directory, TreeSet<File> f) {
		File[] files = directory.listFiles(bookfilter);

		for (int i=0; i<files.length; i++) {
			if (files[i].isFile()) {
				// add all e-books under folder
				f.add(files[i]);
			} else if (files[i].isDirectory()) {
				getMostRecentBooks(files[i], f);
			}
		}
	}
}
