VERSION=$(shell grep '<version>' config.xml | sed -e 's/.*<version>\(.*\)<\/version>.*/\1/')

package:
	mkdir -p hackedupreader
	cp config.xml hackedupreader/
	cp README.md hackedupreader/README.txt
	cp ChangeLog hackedupreader/ChangeLog.txt
	zip -r hackedupreader_extension_$(VERSION).zip hackedupreader/
