package com.pi2.monity_edu.service;

import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.pi2.monity_edu.model.Certificado;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class CertificadoPdfService {

  private Paragraph addParaghaph(Document document, String text, Font font, boolean addChunk) {
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    document.add(paragraph);

    if (addChunk) {
      document.add(Chunk.NEWLINE);
    }

    return paragraph;
  }

  private String getFinalText(Certificado certificado) {
    Duration duration = Duration.between(certificado.getMonitoria().getHorarioInicio(),
        certificado.getMonitoria().getHorarioFim());

    long hours = duration.toHours();
    long minutes = duration.toMinutesPart();

    String textoCargaHoraria = hours + " horas e " + minutes + " minutos";

    String horarioInicioTermino = certificado.getMonitoria().getHorarioInicio() + " às "
        + certificado.getMonitoria().getHorarioFim();

    String dataFormatada = certificado.getMonitoria().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    String textoFinal = "Certificamos que " + certificado.getMonitoria().getMonitor().getNome()
        + " realizou a monitoria de "
        + certificado.getMonitoria().getTitulo() + " no dia " + dataFormatada + " de " + horarioInicioTermino
        + " na plataforma MonityEdu. Totalizando " + textoCargaHoraria + " de monitoria.";

    return textoFinal;
  }

  public byte[] gerarPdf(Certificado certificado) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Document document = new Document(com.lowagie.text.PageSize.A4.rotate());

    Font tituloFonte = new Font(Font.HELVETICA, 24, Font.BOLD);
    Font textoFonte = new Font(Font.HELVETICA, 14);
    Font monitoriaFonte = new Font(Font.HELVETICA, 20, Font.BOLD);
    monitoriaFonte.setColor(0, 102, 204);
    try {
      document.setMargins(96.0f, 96.0f, 96.0f, 96.0f);

      PdfWriter.getInstance(document, out);
      document.open();

      InputStream backgroundStream = getClass().getResourceAsStream("/images/background.jpg");
      if (backgroundStream != null) {
        byte[] imageBytes = backgroundStream.readAllBytes();
        Image background = Image.getInstance(imageBytes);
        background.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());
        background.setAlignment(Image.MIDDLE);
        background.setAbsolutePosition(0, 0);
        document.add(background);
      }

      InputStream is = getClass().getResourceAsStream("/images/logo.png");
      if (is != null) {
        byte[] imageBytes = is.readAllBytes();
        Image logo = Image.getInstance(imageBytes);
        logo.scaleToFit(100, 100);
        logo.setAlignment(Image.MIDDLE);
        document.add(logo);
      }

      document.add(Chunk.NEWLINE);

      this.addParaghaph(document, "Certificado de Monitoria", tituloFonte, true);

      document.add(Chunk.NEWLINE);

      this.addParaghaph(document, "Certificamos que", textoFonte, false);
      this.addParaghaph(document, certificado.getMonitoria().getMonitor().getNome(), tituloFonte, false);
      this.addParaghaph(document, "Ministrou a monitoria de", textoFonte, false);
      this.addParaghaph(document, certificado.getMonitoria().getTitulo(), monitoriaFonte, true);

      document.add(Chunk.NEWLINE);

      String textoFinal = getFinalText(certificado);
      this.addParaghaph(document, textoFinal, textoFonte, true);

      document.close();
    } catch (DocumentException e) {
      throw new RuntimeException("Erro ao gerar PDF: " + e.getMessage(), e);
    }
    return out.toByteArray();
  }

  public void salvarPdf(Certificado certificado, byte[] pdf) {
    certificado.setId(UUID.randomUUID());
    String caminho = "uploads/certificados/" + certificado.getId() + ".pdf";
    try {
      java.nio.file.Files.write(java.nio.file.Paths.get(caminho), pdf);
    } catch (java.io.IOException e) {
      throw new RuntimeException("Erro ao salvar PDF: " + e.getMessage(), e);
    }
    certificado.setNomeArquivo(certificado.getId() + ".pdf");
  }

}