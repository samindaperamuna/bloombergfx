package bloombergfx.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import bloombergfx.model.CSVFile;
import bloombergfx.model.InvalidRecord;
import bloombergfx.model.Record;
import bloombergfx.model.ValidRecord;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@Transactional
public class CSVFileRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CSVFileRepository csvFileRepository;

    private CSVFile file;

    @Before
    public void setup() {
	file = new CSVFile();
	file.setFileName("test_file");
	file.getValidRecords().add((ValidRecord) new Record(1, "USD", "AED", new Date(), 100.00, file).validate());
	file.getInvalidRecords().add((InvalidRecord) new Record(2, "USD", "USD", new Date(), 100.00, file).validate());
    }

    @Test
    public void testFindByFileName() {
	entityManager.persist(file);
	entityManager.flush();

	CSVFile found = csvFileRepository.findByFileName(file.getFileName());

	assertThat(found).isEqualTo(file);
    }

    @Test
    public void testSaveS() {
	CSVFile saved = csvFileRepository.save(file);
	
	assertThat(saved).isEqualTo(file);
    }
}
