HackedUpReaderExtension for Kindle Touch
========================================

HackedUpReaderExtension is an extension for the Kindle Touch GUI
launcher https://github.com/yifanlu/KindleLauncher
for starting https://github.com/bhaak/HackedUpReader

This KindleLauncher extension will install a submenu called
"HackedUpReader" that contains an entry for starting up
HackedUpReader with the last read book, an entry for showing
the 10 most recently added books on the device, and adding
all books beneath the documents directory to this submenu.


Installation
============
For standard Kindle package installation use the bin file for
installing HackedUpReader. This file includes HackedUpReaderExtension:
https://github.com/bhaak/HackedUpReader/downloads


For manual installation, copy the content of the zip file to your
Kindle Touch and execute the following commands on the command
line:

    mkdir /mnt/us/extension/hackedupreader/
    cp hackedupreader/config.xml /mnt/us/extension/hackedupreader/
    mntroot ro
    cp hackedupreader/HackedUpReaderExtension.jar /opt/amazon/ebook/lib/
    mntroot rw

Kill the cvm process or restart your Kindle and the new Launcher entry will
show up.
