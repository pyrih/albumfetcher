package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Component
public interface DocumentService {

    byte[] getDocStream(String template, Optional<AbstractAlbum> result) throws IOException;

    void fillTable(XWPFDocument document, AbstractAlbum album) throws IOException;

    void addImage(XWPFDocument document, URL poster) throws IOException;
}
