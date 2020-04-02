package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Component
public interface DocumentService {

    /**
     * Returns byte array stream of generated document created from template
     *
     * @param template      path to template.docx file
     * @param albumOptional Optional<AbstractAlbum>
     * @return byte array stream of generated document
     * @throws IOException
     */
    byte[] getDocStream(String template, Optional<AbstractAlbum> albumOptional) throws IOException;

    /**
     * Searches and replaces the input fields in the document with information about the album
     *
     * @param document XWPFDocument
     * @param field    input text field that needs to replace
     * @param text     text to replacing
     */
    void replaceFormField(XWPFDocument document, String field, String text);

    /**
     * Creates a paragraph and fill table with album track content
     *
     * @param document XWPFDocument
     * @param album    AbstractAlbum
     * @throws IOException
     */
    void fillTable(XWPFDocument document, AbstractAlbum album) throws IOException;

    /**
     * Adds a paragraph with a picture to document
     *
     * @param document XWPFDocument
     * @param poster   poster URL from AbstractAlbum
     * @throws IOException
     */
    void addImage(XWPFDocument document, URL poster) throws IOException;
}
