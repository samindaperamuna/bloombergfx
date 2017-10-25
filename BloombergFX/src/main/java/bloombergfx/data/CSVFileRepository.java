package bloombergfx.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bloombergfx.model.CSVFile;

@Repository
public interface CSVFileRepository extends JpaRepository<CSVFile, Long> {

	CSVFile findByFileName(String fileName);

	<S extends CSVFile> S save(S file);
}
