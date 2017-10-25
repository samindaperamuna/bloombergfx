package bloombergfx.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class MainControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	public void testIndex() {
		fail("Not yet implemented");
	}

}
