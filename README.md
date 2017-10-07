# Lina
This is is a library for jMonkeyEngine3.
It adds support for different kind of lines,
which make it easy to trace objects.

## Purpose of this project
I really wanted to be able to 'trace' an object, so I could see how it moved. Thus, I created this library. The library's main purpose is to add a new kind of line that can 'curve' (the lines jME features can only go between 2 points), and to add a way to hook up such a line to a moving object (and track the object's movement over time).

The project relies on interfaces, which means (almost) all things are customizable. Because of this, you can have both 2-color gradient lines and transparent gradient lines.

## How to compile your own
1. Download or Clone the repository
2. Use `gradle build` in the root directory (make sure you have Gradle installed)
3. Get the jar from /build/libs/Lina.jar

## Short how-to-use
Here is a small code sample:
``` Java
Geometry g = new Geometry("geometry", new Sphere(8,8,0.5f));
g.setMaterial(lightmat);

TransparentColorControl tcc = new TransparentColorControl();
tcc.setBaseColor(ColorRGBA.Green);
TimeTracer tt = new TimeTracer(unshadedmat, 10, 64, tcc);
g.addControl(tt);

rootNode.attach(g);
```

-----

All files in this repository are licensed under MIT, even if no header is given.
