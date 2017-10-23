package bloombergfx.data;

import org.springframework.data.jpa.repository.JpaRepository;

import bloombergfx.model.CSVFile;

public interface CSVFileRepository extends JpaRepository<CSVFile, Long> {

	<S extends CSVFile> S save(S file);
}
