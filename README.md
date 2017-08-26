# Lina
This is is a library for jMonkeyEngine3.
It adds support for different kind of lines,
which make it easy to trace objects.

## How to compile your own
1. Download or Clone the repository
2. Use `gradle build` in the root directory
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