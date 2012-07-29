package com.github.bhaak;

import com.yifanlu.Kindle.LauncherMenu;
import com.yifanlu.Kindle.Menuable;

/**
 * A KindleLauncher extension for starting HackedUpReader and
 * selecting specific ebooks.
 * 
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 */
public class HackedUpReaderExtension implements Menuable {

	@Override
	public void addItemsToMenu(LauncherMenu menu)
	{
		// sub menu in GUI launcher's  
		LauncherMenu topMenu = new HackedUpReaderLauncher("HackedUpReader", 0, menu);
		menu.addMenuItem(topMenu);
	}
}