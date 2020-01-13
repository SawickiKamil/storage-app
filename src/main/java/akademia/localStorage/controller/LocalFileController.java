package akademia.localStorage.controller;

import akademia.localStorage.model.LocalFileManage;
import akademia.localStorage.service.LocalFileService;
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

import java.util.List;

@CrossOrigin //https://sekurak.pl/czym-jest-cors-cross-origin-resource-sharing-i-jak-wplywa-na-bezpieczenstwo/
@RestController //https://stackoverflow.com/questions/25242321/difference-between-spring-controller-and-restcontroller-annotation
@RequestMapping("/api/v1/") //https://www.javappa.com/kurs-spring/spring-mvc-mapowanie-zadan-http
public class LocalFileController {

  private LocalFileService localFileService;

  public LocalFileController(LocalFileService localFileService) {
    this.localFileService = localFileService;
  }

  @GetMapping("/files")
  public List<LocalFileManage> getFiles() {
    return localFileService.getFiles();
  }

  @GetMapping("/files/download/{filename}")
  public ResponseEntity<?> downloadFile(@PathVariable String filename) {
    return localFileService.getFile(filename);
  }

  @PostMapping("/files")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    return localFileService.uploadFile(file);
  }

  @DeleteMapping("/files/delete/{filename}")
  public ResponseEntity<String> deleteFile(@PathVariable String filename) {
     return localFileService.deleteFile(filename);
  }

}
