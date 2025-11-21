package com.github.cristiangonsan.simplepdftool.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WebImageProvider implements ImageProvider, AutoCloseable {

    private URL[] urls;
    private int index = 0;

    public WebImageProvider(URL[] urls) {
        this.urls = urls;
    }

    @Override
    public BufferedImage nextImage() throws IOException {
        if (!hasNext()) {
            throw new IOException("No more images available");
        }
        BufferedImage image = ImageIO.read(urls[index]);
        index++;
        return image;
    }

    @Override
    public boolean hasNext() {
        return index < urls.length;
    }

    @Override
    public void close() throws Exception {
        urls = null;
    }

    public URL[] getUrls() {
        return urls;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getTotal() {
        return urls.length;
    }
}
