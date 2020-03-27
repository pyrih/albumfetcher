package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public interface DocumentService {

    byte[] getDocStream(String template, AbstractAlbum result) throws IOException;

    void fillTable(XWPFTable table, AbstractAlbum album) throws IOException;

    void replaceCell(XWPFTable table, int row, int cell, String text);

    void addImage(File imageFile, String documentPath) throws IOException;

    default int getImageFormat(String imageFileName) {
        int format;
//        if (imageFileName.endsWith(".emf"))
//            format = XWPFDocument.PICTURE_TYPE_EMF;
//        else if (imageFileName.endsWith(".wmf"))
//            format = XWPFDocument.PICTURE_TYPE_WMF;
//        else if (imageFileName.endsWith(".pict"))
//            format = XWPFDocument.PICTURE_TYPE_PICT;
//        else if (imageFileName.endsWith(".jpeg") || imageFileName.endsWith(".jpg"))
//            format = XWPFDocument.PICTURE_TYPE_JPEG;
//        else if (imageFileName.endsWith(".png"))
//            format = XWPFDocument.PICTURE_TYPE_PNG;
//        else if (imageFileName.endsWith(".dib"))
//            format = XWPFDocument.PICTURE_TYPE_DIB;
//        else if (imageFileName.endsWith(".gif"))
//            format = XWPFDocument.PICTURE_TYPE_GIF;
//        else if (imageFileName.endsWith(".tiff"))
//            format = XWPFDocument.PICTURE_TYPE_TIFF;
//        else if (imageFileName.endsWith(".eps"))
//            format = XWPFDocument.PICTURE_TYPE_EPS;
//        else if (imageFileName.endsWith(".bmp"))
//            format = XWPFDocument.PICTURE_TYPE_BMP;
//        else if (imageFileName.endsWith(".wpg"))
//            format = XWPFDocument.PICTURE_TYPE_WPG;
//        else {
//            return 0;
//        }
        return format = 0; //потом убрать  = 0
    }
}
