package akademia.databaseStorage.service;

import akademia.databaseStorage.model.DataBaseFile;
import akademia.databaseStorage.model.DataBaseFileManage;
import akademia.databaseStorage.repository.DataBaseFileRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataBaseFileService {

  private DataBaseFileRepository dataBaseFileRepository;

  public DataBaseFileService(DataBaseFileRepository dataBaseFileRepository) {
    this.dataBaseFileRepository = dataBaseFileRepository;
  }

  public ResponseEntity<String> storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename()); //cleanPath tutaj nie zrobi nic. 

    if (Strings.isBlank(fileName)) {
      return null; //todo zrobic logger
    }

    long actualDate = new Date().getTime();
    Date date = new Date(actualDate);

    DataBaseFile dataBaseFile = new DataBaseFile();
    dataBaseFile.setFileName(fileName);
    dataBaseFile.setFileType(file.getContentType());
    dataBaseFile.setDate(date);

    try {
      dataBaseFile.setData(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace(); //todo zrobic logger
      return null;
    }

    DataBaseFile result = dataBaseFileRepository.save(dataBaseFile);

    if (result == null) {
      //todo zrobic logger
      return new ResponseEntity<>("Cannot save file to data base!", HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
  }

  public List<DataBaseFileManage> getFiles() {
    List<DataBaseFile> files = dataBaseFileRepository.findAll();
    List<DataBaseFileManage> filesManage = new ArrayList<>();

    files.forEach(f -> {
      String downloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/api/v1/db/files/download/")
          .path(f.getId())
          .toUriString();

      String deleteUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/api/v1/db/files/delete/")
          .path(f.getId())
          .toUriString();

      DataBaseFileManage dataBaseFileManage = new DataBaseFileManage();
      dataBaseFileManage.setFilename(f.getFileName());
      dataBaseFileManage.setDownloadUri(downloadUri);
      dataBaseFileManage.setDeleteUri(deleteUri);
      dataBaseFileManage.setFileType(f.getFileType());
      dataBaseFileManage.setSize((long) f.getData().length);
      filesManage.add(dataBaseFileManage);
    });
    return filesManage;
  }

  public ResponseEntity<String> storeFiles(MultipartFile[] files) {

    Arrays.stream(files)
        .map(this::storeFile)
        .collect(Collectors.toList());
    //todo zamienic na cos sensowniejszego ;)
    // np. dodac metode storeFile bez zwracania ResponseEntity i dodać ją do obecnego stotreFile i sotreFiles

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<Resource> downloadFile(String fileId) {
    DataBaseFile dataBaseFile = dataBaseFileRepository.getOne(fileId);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(dataBaseFile.getFileType()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + dataBaseFile.getFileName() + "\"") // jak zakomentujesz, to nie pobierzesz pliku a tylko wyswietlisz
        .header("Pozdrowienia", "Pozdrowienia z bazy danych")
        .body(new ByteArrayResource(dataBaseFile.getData()));
  }

  public ResponseEntity<String> deleteFile(String fileId) {
    dataBaseFileRepository.deleteById(fileId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
