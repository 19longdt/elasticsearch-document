package sds.easywrite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/api/ckeditor/upload") // Đặt đường dẫn cơ sở cho controller
@CrossOrigin(origins = "*") // CORS cho phép tất cả các domain
public class CKEditorController {
    private final Logger logger = LoggerFactory.getLogger("CKEditorController");

    @PostMapping("/image")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("upload") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("url", saveFile(file, "ckeditor"));
            logger.info("image uploaded url: {}", response.get("url"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "File upload failed");
            logger.info("image uploaded url error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public String saveFile(MultipartFile fileItem, String path) {
        try {
            path = path + "/";
            String pathDomain = "http://10.100.170.56:8080" + "/client/file/";
            String fileName = fileItem.getOriginalFilename();
//            Common.checkFileInvalid(fileName.substring(fileName.lastIndexOf(".")), fileFormats);
            fileName = fileName.replaceAll("[^a-zA-Z0-9.]", "");
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_";
            File compressedImageFile = new File("DATA_SHARE/" + path + fileName);
            if (!compressedImageFile.getParentFile().exists()) {
                compressedImageFile.getParentFile().mkdirs();
            }
            InputStream inputStream = fileItem.getInputStream();
            OutputStream outputStream = new FileOutputStream(compressedImageFile);
            float imageQuality = 0.5f;
            //Create the buffered image
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            //Get image writers
            Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("png");

            if (!imageWriters.hasNext()) throw new IllegalStateException("Writers Not Found!!");

            ImageWriter imageWriter = (ImageWriter) imageWriters.next();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

            //Set the compress quality metrics
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);

            //Created image
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
            // close all streams
            inputStream.close();
            outputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();
            return pathDomain + path + fileName;
        } catch (IOException e) {
            logger.error("save file error {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
