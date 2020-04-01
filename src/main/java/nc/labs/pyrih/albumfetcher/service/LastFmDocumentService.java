package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import nc.labs.pyrih.albumfetcher.model.AbstractTrack;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Primary
public class LastFmDocumentService implements DocumentService {

    private static final String XMLBASE_CURSOR_SCHEMA_PATH = "declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:fldChar/@w:fldCharType";
    private static final String XMLBASE_OBJECT_SCHEMA_PATH = "declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:ffData/w:name/@w:val";
    private static final String PICTURE_TYPE_PNG = "png";
    private RestTemplate restTemplate;

    public LastFmDocumentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static String durationConverter(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    @Override
    public byte[] getDocStream(String templatePath, Optional<AbstractAlbum> albumOptional) throws IOException {
        if (!albumOptional.isPresent()) {
            return null;
        } else {
            AbstractAlbum album = albumOptional.get();
            URL poster = album.getPoster();

            FileInputStream stream = new FileInputStream(templatePath);
            XWPFDocument docx = new XWPFDocument(stream);

            replaceFormField(docx, "albumInputField", album.getName());
            replaceFormField(docx, "artistInputField", album.getArtist());
            replaceFormField(docx, "genreInputField", album.getGenre());
            replaceFormField(docx, "posterInputField", album.getPoster().toString());
            fillTable(docx, album);
            addImage(docx, poster);

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            docx.write(byteArray);
            return byteArray.toByteArray();
        }
    }

    private void replaceFormField(XWPFDocument document, String field, String text) {
        boolean isFound = false;
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                XmlCursor cursor = run.getCTR().newCursor();
                cursor.selectPath(XMLBASE_CURSOR_SCHEMA_PATH);
                while (cursor.hasNextSelection()) {
                    cursor.toNextSelection();
                    XmlObject object = cursor.getObject();
                    if ("begin".equals(((SimpleValue) object).getStringValue())) {
                        cursor.toParent();
                        object = cursor.getObject();
                        object = object.selectPath(XMLBASE_OBJECT_SCHEMA_PATH)[0];
                        isFound = field.equals(((SimpleValue) object).getStringValue());
                    } else if ("end".equals(((SimpleValue) object).getStringValue())) {
                        if (isFound) return;
                        isFound = false;
                    }
                }
                if (isFound && run.getCTR().getTList().size() > 0) {
                    run.getCTR().getTList().get(0).setStringValue(text);
                }
            }
        }
    }

    @Override
    public void fillTable(XWPFDocument document, AbstractAlbum album) {
        createXwpfRun(document, "Album track list:");

        if (album.getTracks().size() > 0) {
            List<AbstractTrack> tracks = album.getTracks();
            XWPFTable table = document.createTable(album.getTracks().size() + 1, 3);

            table.getRow(0).getCell(0).setText("#");
            table.getRow(0).getCell(1).setText("Name");
            table.getRow(0).getCell(2).setText("Duration");

            for (int row = 1; row <= album.getTracks().size(); row++) {
                table.getRow(row).getCell(0).setText(String.valueOf(row));
                table.getRow(row).getCell(1).setText(tracks.get(row - 1).getName());
                table.getRow(row).getCell(2).setText(durationConverter(tracks.get(row - 1).getDuration()));
            }
        }
    }

    @Override
    public void addImage(XWPFDocument document, URL poster) throws IOException {
        XWPFRun run = createXwpfRun(document, "Album cover:");

        ResponseEntity<Resource> entity = restTemplate.getForEntity(poster.toString(), Resource.class);
        InputStream inputStream = Objects.requireNonNull(entity.getBody()).getInputStream();
        BufferedImage picture = ImageIO.read(inputStream);

        int width = picture.getWidth();
        int height = picture.getHeight();
        int type = picture.getType();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(picture, PICTURE_TYPE_PNG, outputStream);
        InputStream arrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());

        try {
            run.addPicture(arrayInputStream, type, "cover.png", Units.toEMU(width), Units.toEMU(height));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        run.addBreak();
    }

    private XWPFRun createXwpfRun(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun xwpfRun = paragraph.createRun();
        xwpfRun.setText(text);
        xwpfRun.setBold(true);
        xwpfRun.setCapitalized(true);
        xwpfRun.addBreak();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        return xwpfRun;
    }
}

