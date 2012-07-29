package com.github.bhaak;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yifanlu.Kindle.LauncherMenu;
import com.yifanlu.Kindle.Menuable;

/**
 * A KindleLauncher extension for starting HackedUpReader and
 * selecting specific ebooks.
 * 
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 */
public class HackedUpReaderExtension implements Menuable {
	
    private static Logger logger = LoggerFactory.getLogger(HackedUpReaderExtension.class);

	@Override
	public void addItemsToMenu(LauncherMenu menu)
	{
		Date start = new Date();
		// sub menu in GUI launcher's  
		LauncherMenu topMenu = new HackedUpReaderLauncher("HackedUpReader", 0, menu);
		menu.addMenuItem(topMenu);
		Date finished = new Date();
		
		logger.info("Overall startup duration {}", Long.toString(finished.getTime()-start.getTime()));
	}
}