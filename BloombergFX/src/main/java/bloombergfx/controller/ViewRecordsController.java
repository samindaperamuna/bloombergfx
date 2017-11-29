package bloombergfx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bloombergfx.data.CSVFileRepository;
import bloombergfx.model.CSVFile;

@Controller
public class ViewRecordsController {

    @Autowired
    CSVFileRepository fileRepository;

    @GetMapping("/viewRecords")
    public String viewRecords() {
	return "view_records";
    }

    @PostMapping("/viewRecords")
    public String getRecords(@RequestParam("file-name") String fileName, Model model) {

	if (!fileName.contains(".csv")) {
	    fileName += ".csv";
	}

	CSVFile found = fileRepository.findByFileName(fileName);

	if (found != null) {
	    model.addAttribute("validRecords", found.getValidRecords());

	    return "records";
	} else {
	    model.addAttribute("message", "File not found");

	    return "view_records";
	}
    }
}
