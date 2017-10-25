package bloombergfx.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import bloombergfx.data.CSVFileRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

	@Autowired
	CSVFileRepository fileRepository;

	@Autowired
	MockMvc mvc;

	@Test
	public void testUploadFile() throws Exception {
		mvc.perform(get("/uploadFile").accept(MediaType.ALL)).andExpect(status().isOk());
	}

	@Test
	public void testRetrieveFile() {
		fail("Not yet implemented");
	}

}
