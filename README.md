# Java_Image
## Simple Image editing tools for Java.
## Docs are not up to date on indev!
#### Full Javadoc can be found [here](https://cystuff.github.io/Java_Image/index.html).
Note:  
Every `IOException` has been abstracted to a `RuntimeException` for my AP class.

#### Color
The Color class is the base class for all colors in this library.
Colors have a few basic meathods like `setBlue();`, `getBlue();`, etc.
The Color class is immutable, so every operation to change a value will return a new Color.

#### BaseImage
This is a basic class that allows for simple image editing.
```Java
BaseImage base = new BaseImage(16,16);
base.fill(new Color(255,255,255))
base.save("Test.png")
```

#### Image
The Image class is an extention of the BaseImage class to allow for things such as bluring, scaling, and changing individual color channels
The Image class also implements lambda expressions to change the image.
```Java
Image image = new Image("Input.png");
image.setChannels(arr -> new int[] {arr[1],arr[0],arr[3]});
image.setRedChannel(r -> r/2);
image.save("Test.png");
```

#### Drawing
The Drawing class is an extention of the Image class that allows for drawing shapes and text.
```Java
Drawing draw = new Drawing(128,128);
draw.fill(new Color(255,255,255));
draw.fillEllipse(0,0,128,128);
```

#### Gif
Gif is a class that enables the creation of gifs from an array or List of Images.
```Java
Image[] images;
Gif.makeGif(images, 10, "out.gif");
```
