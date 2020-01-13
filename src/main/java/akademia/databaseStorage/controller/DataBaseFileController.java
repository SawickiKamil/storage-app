package akademia.databaseStorage.controller;

import akademia.databaseStorage.model.DataBaseFileManage;
import akademia.databaseStorage.service.DataBaseFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin //https://sekurak.pl/czym-jest-cors-cross-origin-resource-sharing-i-jak-wplywa-na-bezpieczenstwo/
@RestController
//https://stackoverflow.com/questions/25242321/difference-between-spring-controller-and-restcontroller-annotation
@RequestMapping("/api/v1/db/") //https://www.javappa.com/kurs-spring/spring-mvc-mapowanie-zadan-http
public class DataBaseFileController {

  private DataBaseFileService dataBaseFileService;

  public DataBaseFileController(DataBaseFileService dataBaseFileService) {
    this.dataBaseFileService = dataBaseFileService;
  }

  @PostMapping("/files")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    return dataBaseFileService.storeFile(file);
  }

  @PostMapping("/files/many")
  public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
    return dataBaseFileService.storeFiles(files);
  }

  @GetMapping("/files")
  public ResponseEntity<List<DataBaseFileManage>> getFiles() {
    return new ResponseEntity<>(dataBaseFileService.getFiles(), HttpStatus.OK);
  }

  @GetMapping("/files/download/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    return dataBaseFileService.downloadFile(fileId);
  }

  @DeleteMapping("/files/delete/{fileId}")
  public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
    return dataBaseFileService.deleteFile(fileId);
  }

}
