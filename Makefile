JAVASRC    = Chessmoves.java ChessPiece.java King.java LinkedList.java Node.java Queen.java Rook.java 
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Chessmoves
CLASSES    = Chessmoves.class ChessPiece.class King.class LinkedList.class Node.class Queen.class Rook.class 
JARFILE    = Chessmoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
