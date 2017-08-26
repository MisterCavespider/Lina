# Lina
This is is a library for jMonkeyEngine3.
It adds support for different kind of lines,
which make it easy to trace objects.

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