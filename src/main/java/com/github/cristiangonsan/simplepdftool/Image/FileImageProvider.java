package com.github.cristiangonsan.simplepdftool.Image;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileImageProvider implements ImageProvider, AutoCloseable {

    private File[] files;
    private int index = 0;

    public FileImageProvider(File[] files) {
        this.files = files;
    }

    public FileImageProvider(ArrayList<File> fileList) {
        this.files = fileList.toArray(new File[0]);
    }

    @Override
    public BufferedImage nextImage() throws IOException {
        if (!hasNext()) {
            throw new IOException("No more images available");
        }
        BufferedImage image = ImageIO.read(files[index]);
        index++;
        return image;
    }

    @Override
    public boolean hasNext() {
        return index < files.length;
    }

    @Override
    public void close() throws Exception {
        files = null;
    }

    public File[] getFiles() {
        return files;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getTotal() {
        return files.length;
    }
}
