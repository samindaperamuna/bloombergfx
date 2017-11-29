package bloombergfx.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bloombergfx.data.CSVFileRepository;
import bloombergfx.data.service.UpdateDealsService;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CSVFileRepository fileRepository;

    @MockBean
    private UpdateDealsService updateDealService;

    @Test
    public void testUploadFile() throws Exception {
	mvc.perform(get("/uploadFile")).andExpect(status().isOk());
    }

    @Test
    public void testRetrieveFile() throws Exception {
	File file = new File("data/Dataset 1.csv");

	System.out.println(file.isFile() + "  " + file.getName() + file.exists());

	FileInputStream inputStream = new FileInputStream(file);
	MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "multipart/form-data",
		inputStream);

	mvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile").file(multipartFile)).andExpect(status().isOk());
    }
}
