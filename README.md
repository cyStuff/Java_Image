# Java_Image
### Simple Image editing tools for java.
The Image class is the main class, allowing the opening, editing, and saving of images.

#### Color
The Color class is the base class for all colors in this library.
Colors have a few basic meathods like `setBlue();`, `getBlue();`, etc.
The Color class is immutable, so every operation to change a value will return a new Color.

#### BaseImage
This is a basic object that allows for more simple image editing.
```Java
BaseImage base = new BaseImage(16,16);
base.fill(new Color(255,255,255))
base.save("Test.png")
```
#### Image
The Image class is an exten
```Java
Image i = new Image(16,16);
i.setChannels(arr -> new int[] {255,255,255});
i.setRedChannel(r -> r/2);
i.save("Test.png");
```
The code `i.setChannels(arr -> new int[] {255,255,255});` fills the image with white. In this case, using fill would be much easier, but this is here for an example.
The code `i.setRedChannel(r -> r/2);` takes every red value and halves it.
