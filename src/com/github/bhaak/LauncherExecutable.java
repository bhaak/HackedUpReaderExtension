package com.github.bhaak;

import java.io.File;
import java.io.IOException;

import com.yifanlu.Kindle.LauncherAction;

/**
 * A menu item that executes a shell command.
 * 
 * Similar to LauncherScript but LauncherExecutable has the advantage
 * of being able to used parameters with spaces. Unfortunately for this
 * to work, there needs to be appropriate entries in the java.policy
 * file.
 *
 * @author Patric Mueller &lt;bhaak@gmx.net&gt;
 * @version 1.0
 * @see JSONMenu and LauncherScript
 */
public class LauncherExecutable extends LauncherAction {

	private static final long serialVersionUID = 5823843798761456837L;

	private File mScript;
	private String mArgs;

	/**
	 * Creates a new launch script menu item.
	 *
	 * @param name     The text to show
	 * @param priority The order of this item in comparison to others
	 * @param script   The shell script or command to run
	 * @param args     The arguments of the script
	 */
	public LauncherExecutable(String name, int priority, File script, String args) {
		super(name, priority);
		this.mScript = script;
		this.mArgs = args;
	}

	/**
	 * Executes the shell command with parameters.
	 * 
	 * Because Runtime,exec() is used and the SecurityManager is enabled
	 * on the Kindle, commands need an appropriate entry in the
	 * java.policy file.
	 */
	public synchronized void doAction() {
		String[] cmdarray = {mScript.getAbsolutePath(), mArgs};
		try {
			Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
