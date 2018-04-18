# Java_Image
### Simple Image editing tools for java.
The Image class is the main class, allowing the opening, editing, and saving of images.
Within the Image class, There is the ability to change color channels with lambda expressions.
Here is an example:
```Java
Image i = new Image(16,16);
i.setChannels(arr -> new int[] {255,255,255});
i.setRedChannel(r -> r/2);
i.save("Test.png");
```
The code `i.setChannels(arr -> new int[] {255,255,255});` fills the image with white. In this case, using fill would be much easier, but this is here for an example.
The code `i.setRedChannel(r -> r/2);` takes every red value and halves it.