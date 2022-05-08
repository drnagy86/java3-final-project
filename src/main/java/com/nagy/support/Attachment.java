package com.nagy.support;

import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Attachment {
    private String name;
    private byte[] contents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public static Attachment processAttachment(Part part) throws IOException {
        Attachment attachment = new Attachment();
        try(
                InputStream inputStream = part.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(part.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        catch (IOException ex) {
            throw new IOException("Problem processing the file \n" + ex.getMessage());
        }
        return attachment;
    }
}

