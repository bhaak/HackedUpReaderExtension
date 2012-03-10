package com.github.bhaak;

import java.io.File;

import com.yifanlu.Kindle.LauncherAction;
import com.yifanlu.Kindle.LauncherMenu;
import com.yifanlu.Kindle.Menuable;

public class HackedUpReaderExtension implements Menuable {

	@Override
	public void addItemsToMenu(LauncherMenu menu)
	{
		// sub menu in GUI launcher's  
		LauncherMenu topMenu = new LauncherMenu("HackedUpReader", 0);
		menu.addMenuItem(topMenu);

		// HackedUpReader location
		File cr3 = new File("/mnt/us/cr3xcb/bin/cr3");

		// e-books location
		File folder = new File("/mnt/us/documents/");
		File[] files = folder.listFiles(new BookFilter());
		for (int i=0; i<files.length; i++) {
			if (files[i].isFile()) {
				// add all e-books under folder
				LauncherAction launcherScript = new LauncherExecutable(
						files[i].getName(),
						i,
						cr3,
						files[i].getAbsolutePath());
				topMenu.addMenuItem(launcherScript);
			} else if (files[i].isDirectory()) {
				// add sub directories as sub menus
				LauncherAction subMenu = new LauncherMenu(files[i].getName(), i);
				topMenu.addMenuItem(subMenu);
				// TODO rekursiv runter
			}
		}
	}

	class BookFilter implements java.io.FileFilter {
		@Override
		public boolean accept(File file) {
			String filename = file.getName();
			if (filename.toLowerCase().endsWith(".epub") ||
				filename.toLowerCase().endsWith(".fb2") ||
				filename.toLowerCase().endsWith(".txt") ||
				filename.toLowerCase().endsWith(".rtf") ||
				filename.toLowerCase().endsWith(".html") ||
				filename.toLowerCase().endsWith(".htm") ||
				filename.toLowerCase().endsWith(".pdb")) {
				return true;
			} else if (file.isDirectory()) {
				return true;
			}
			return false;
		}
	}
}