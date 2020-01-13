package akademia.localStorage.model;

public class LocalFileManage {

  private String name;
  private String creationTime;
  private String lastModified;
  private Long size;
  private String downloadUri;
  private String deleteUri;
  private String fileType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }

  public String getLastModified() {
    return lastModified;
  }

  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
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
    return "LocalFile{" +
        "name='" + name + '\'' +
        ", creationTime='" + creationTime + '\'' +
        ", lastModified='" + lastModified + '\'' +
        ", size=" + size +
        ", downloadUri='" + downloadUri + '\'' +
        ", fileDeleteUri='" + deleteUri + '\'' +
        ", fileType='" + fileType + '\'' +
        '}';
  }
}
