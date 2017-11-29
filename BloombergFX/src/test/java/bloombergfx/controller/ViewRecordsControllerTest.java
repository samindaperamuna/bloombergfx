package bloombergfx.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import bloombergfx.data.CSVFileRepository;
import bloombergfx.model.CSVFile;
import bloombergfx.model.Record;
import bloombergfx.model.ValidRecord;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewRecordsController.class)
public class ViewRecordsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CSVFileRepository fileRepository;

    private CSVFile file;

    @Before
    public void setup() {
	file = new CSVFile();
	file.setFileName("test_file");
	file.getValidRecords().add((ValidRecord) new Record(1, "USD", "AED", new Date(), 100.00, file).validate());

	Mockito.when(fileRepository.findByFileName("test_file.csv")).thenReturn(file);
    }

    @Test
    public void testViewRecords() throws Exception {
	mvc.perform(get("/viewRecords")).andExpect(status().isOk());
    }

    @Test
    public void testGetRecords() throws Exception {
	mvc.perform(post("/viewRecords").param("file-name", "test_file")).andExpect(status().isOk())
		.andExpect(model().attribute("validRecords", file.getValidRecords()));
    }
}
