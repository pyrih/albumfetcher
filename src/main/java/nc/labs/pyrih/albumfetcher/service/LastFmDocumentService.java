package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
@Primary
public class LastFmDocumentService implements DocumentService {

    @Override
    public byte[] getDocStream(String templatePath, AbstractAlbum album) throws IOException {
        //create doc
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        fillTable(null, null);
        addImage(null, null);
        //document.write(byteArray);
        return byteArray.toByteArray();
    }

    @Override
    public void fillTable(XWPFTable table, AbstractAlbum album) throws IOException {
        /*if (!movie.getPoster().equals("N/A")) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Resource> responseEntity = restTemplate.getForEntity(movie.getPoster(), Resource.class);
            InputStream inputStream = responseEntity.getBody().getInputStream();
            table.getRow(0).getCell(0).getParagraphs().get(0).removeRun(0);
            table.getRow(0).getCell(0).getParagraphs().get(0).createRun()
                    .addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "",
                            Units.toEMU(180), Units.toEMU(230));
        }
        replaceCell(table, 0, 1, movie.getTitle());
        replaceCell(table, 1, 2, String.valueOf(movie.getYear()));
        replaceCell(table, 2, 2, movie.getCountry());
        replaceCell(table, 3, 2, movie.getDirector());
        replaceCell(table, 4, 2, movie.getActors());
        replaceCell(table, 5, 2, movie.getGenre());
        replaceCell(table, 6, 2, movie.getType());
        replaceCell(table, 7, 2, movie.getReleased());
        replaceCell(table, 8, 2, movie.getRuntime());
        String ratings = movie.getRatings().toString();
        String ratingsOut = ratings.substring(1, ratings.length() - 1);
        replaceCell(table, 9, 2, ratingsOut);
        replaceCell(table, 10, 2, movie.getPlot());*/
    }

    @Override
    public void replaceCell(XWPFTable table, int row, int cell, String text) {
        //table.getRow(row).getCell(cell).getParagraphs().get(0).getRuns().get(0).setText(text, 0);
    }

    @Override
    public void addImage(File imageFile, String documentPath) throws IOException {
        /*XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        BufferedImage image = ImageIO.read(imageFile);
        int width = image.getWidth();
        int height = image.getHeight();

        String fileName = imageFile.getName();
        int imageFormat = getImageFormat(fileName);

        String text = "Album cover:";
        run.setText(text);
        run.setBold(true);
        run.setCapitalized(true);
        run.addBreak();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run.addPicture(new FileInputStream(imageFile), imageFormat, fileName, Units.toEMU(width), Units.toEMU(height));
        run.addBreak();

        FileOutputStream out = new FileOutputStream(documentPath);
        document.write(out);
        out.close();
        document.close();*/
    }
}
