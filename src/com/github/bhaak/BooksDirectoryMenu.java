package com.github.bhaak;

import java.io.File;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static Logger logger = LoggerFactory.getLogger(HackedUpReaderExtension.class);
	
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
		logger.info("Initialized DynamicEbookLauncherMenu");
	}

	@Override
	public void initMenu() {
		// TODO Auto-generated method stub
		logger.info("DynamicEbookLauncherMenu.initMenu() 1");
		addEBooksToMenu();
		logger.info("DynamicEbookLauncherMenu.initMenu() 2");
		super.initMenu();
		logger.info("DynamicEbookLauncherMenu.initMenu() 3");
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
				// add sub directories as sub menus
				LauncherMenu subMenu = new BooksDirectoryMenu(files[i].getName(), files[i], executable, i);
				//int dirBookCount = addEBooksToMenu(subMenu, files[i], cr3);
				//if (dirBookCount > 0) {
				this.addMenuItem(subMenu);
				//	bookCount += dirBookCount;
				//}
			}
		}
	}
}
