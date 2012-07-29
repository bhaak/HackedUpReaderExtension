package com.github.bhaak;

import java.io.File;
import java.util.Arrays;

import com.yifanlu.Kindle.LauncherAction;
import com.yifanlu.Kindle.LauncherMenu;

/**
 * A LauncherMenu that dynamically adds ebook documents
 * and sub directories.
 * 
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 */
public class BooksDirectoryMenu extends LauncherMenu {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = 7818993617767718029L;
	/** The directory containing the ebooks. */
	private File directory;
	/** The executable ebook reader binary. */
	private File executable;
	
	/**
	 * Creates a new submenu with its text label and priority
	 *
	 * @param name      The text to show
	 * @param directory The directory this submenu represents
	 * @param priority  The order of this item in comparison to others
	 */
	public BooksDirectoryMenu(String name, File directory, File executable, int priority) {
		this(name, directory, executable, priority, null);
	}

	/**
	 * Creates a new submenu with its text label, priority, and parent menu
	 *
	 * @param name     The text to show
	 * @param directory The directory this submenu represents
	 * @param priority The order of this item in comparison to others
	 * @param parent   The menu to go to when the "Previous" item is selected
	 */
	public BooksDirectoryMenu(
			String name,
			File directory,
			File executable,
			int priority,
			LauncherMenu parent) {
		super(name, priority);
		setHasArrow(true);
		this.directory = directory;
		this.executable = executable;
	}

	@Override
	public void initMenu() {
		addEBooksToMenu();
		super.initMenu();
	}

	private static FileComparator fileComparator = new FileComparator();
	private static BookFilter bookfilter = new BookFilter();
	
	private void addEBooksToMenu() {
		File[] files = directory.listFiles(bookfilter);
		if (files == null) { return; }
		// sort files
		Arrays.sort(files, fileComparator);
		
		int bookCount = 0;
		for (int i=0; i<files.length; i++) {
			if (files[i].isFile()) {
				// add all e-books under folder
				LauncherAction launcherScript = new LauncherExecutable(
						files[i].getName(),
						i,
						executable,
						files[i].getAbsolutePath());
				this.addMenuItem(launcherScript);
				bookCount++;
			} else if (files[i].isDirectory()) {
				if (hasDirectoryBooks(files[i])) {
					// add sub directories as sub menus
					LauncherMenu subMenu = new BooksDirectoryMenu(files[i].getName(), files[i], executable, i);
					this.addMenuItem(subMenu);
				}
			}
		}
	}

	/** Returns true if <code>directory</code> contains at least one ebook. */
	private boolean hasDirectoryBooks(File directory) {
		File[] files = directory.listFiles(bookfilter);
		if (files == null) { return false; }

		for (int i=0; i<files.length; i++) {
			if (files[i].isFile()) {
				return true;
			} else if (files[i].isDirectory()) {
				if (hasDirectoryBooks(files[i])) {
					return true;
				}
			}
		}
		return false;
	}
}
