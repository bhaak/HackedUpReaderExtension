package com.github.bhaak;

import java.io.File;
import java.util.PriorityQueue;

import com.yifanlu.Kindle.LauncherAction;
import com.yifanlu.Kindle.LauncherMenu;

public class HackedUpReaderLauncher extends LauncherMenu {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = 8897780376920292293L;
	
	/**
	 * Creates a new submenu with its text label and priority
	 *
	 * @param name      The text to show
	 * @param directory The directory this submenu represents
	 * @param priority  The order of this item in comparison to others
	 */
	public HackedUpReaderLauncher(String name, int priority) {
		this(name, priority, null);
	}

	/**
	 * Creates a new submenu with its text label, priority, and parent menu
	 *
	 * @param name     The text to show
	 * @param directory The directory this submenu represents
	 * @param priority The order of this item in comparison to others
	 * @param parent   The menu to go to when the "Previous" item is selected
	 */
	public HackedUpReaderLauncher(
			String name,
			int priority,
			LauncherMenu parent) {
		super(name, priority);
	}
	
	public void initMenu() {
		// HackedUpReader location
		File cr3 = new File("/mnt/us/hackedupreader/bin/cr3");
		if (!cr3.exists()) {
			// path of old version
			cr3 = new File("/mnt/us/cr3xcb/bin/cr3");
		}

		// launch HackedUpReader with last read e-book
		LauncherAction launcherScript = new LauncherExecutable(
				"Continue reading last book",
				-1,
				cr3,
				"--last-book");
		this.addMenuItem(launcherScript);
		
		// e-books location
		File folder = new File("/mnt/us/documents/");
		//addEBooksToMenu(topMenu, folder, cr3);
		LauncherMenu booksMenu = new BooksDirectoryMenu("Books", folder, cr3, 0);
		booksMenu.initMenu();
		LauncherAction[] menuItems = booksMenu.getMenuItems();
		for (int i=0; i<menuItems.length; i++) {
			this.addMenuItem(menuItems[i]);
		}

		super.initMenu();
	}
}
