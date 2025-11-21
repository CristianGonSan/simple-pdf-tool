package com.github.cristiangonsan.simplepdftool.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageProvider {
    BufferedImage nextImage() throws IOException;
    boolean hasNext();
    int getIndex();
    int getTotal();
}
