package akademia.databaseStorage.repository;

import akademia.databaseStorage.model.DataBaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseFileRepository extends JpaRepository<DataBaseFile, String> {

}
