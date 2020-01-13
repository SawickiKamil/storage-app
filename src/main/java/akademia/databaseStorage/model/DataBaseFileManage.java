package akademia.databaseStorage.model;

public class DataBaseFileManage {

  private String filename;
  private Long size;
  private String downloadUri;
  private String deleteUri;
  private String fileType;

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public String getDownloadUri() {
    return downloadUri;
  }

  public void setDownloadUri(String downloadUri) {
    this.downloadUri = downloadUri;
  }

  public String getDeleteUri() {
    return deleteUri;
  }

  public void setDeleteUri(String deleteUri) {
    this.deleteUri = deleteUri;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  @Override
  public String toString() {
    return "DataBaseFileManage{" +
        "filename='" + filename + '\'' +
        ", size=" + size +
        ", downloadUri='" + downloadUri + '\'' +
        ", deleteUri='" + deleteUri + '\'' +
        ", fileType='" + fileType + '\'' +
        '}';
  }
}
