package bloombergfx.model;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecordTest {

    private CSVFile file;
    private Record validRecord;
    private Record invalidRecord;

    @Before
    public void setup() {
	file = new CSVFile();
	file.setFileName("test_file");

	validRecord = new Record(1, "USD", "AED", new Date(), 100.00, file);
	invalidRecord = new Record(2, "USD", "USD", new Date(), 100.00, file);
    }

    @Test
    public void testValidate() {
	assertThat(validRecord.validate(), instanceOf(ValidRecord.class));
	assertThat(invalidRecord.validate(), instanceOf(InvalidRecord.class));
    }
}
