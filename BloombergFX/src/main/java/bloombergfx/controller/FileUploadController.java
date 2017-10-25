package bloombergfx.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bloombergfx.data.CSVFileRepository;
import bloombergfx.model.CSVFile;
import bloombergfx.model.InvalidRecord;
import bloombergfx.model.Record;
import bloombergfx.model.ValidRecord;

@Controller
public class FileUploadController {

	@Autowired
	CSVFileRepository fileRepository;

	@GetMapping("/uploadFile")
	public String uploadFile() {

		return "upload_file";
	}

	@PostMapping("/uploadFile")
	public String retrieveFile(@RequestParam("file") MultipartFile file, Model model) {
		CSVFile csvFile = new CSVFile();
		csvFile.setFileName(file.getOriginalFilename());
		int errorFreeRecords = 0;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			int lineNo = 1;

			while ((line = reader.readLine()) != null) {
				if (lineNo > 1) {
					String[] tokens = line.split(",");

					if (tokens.length == 5) {
						Record record = new Record();
						record.setFile(csvFile);

						try {
							record.setId(Integer.parseInt(tokens[0]));
							record.setFromCurrency(tokens[1]);
							record.setToCurrency(tokens[2]);

							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

							record.setTimeStamp(df.parse(tokens[3]));
							record.setAmount(Double.parseDouble(tokens[4]));

							errorFreeRecords++;
						} catch (Exception e) {
							System.err.println("Cant parse line : " + line + " " + e.getLocalizedMessage());
						}

						Record tempRecord = record.validate();

						if (tempRecord instanceof InvalidRecord) {
							csvFile.getInvalidRecords().add((InvalidRecord) tempRecord);
						} else {
							csvFile.getValidRecords().add((ValidRecord) tempRecord);
						}
					} else {
						System.err.println("Invalid record : " + line);
					}
				}

				lineNo++;
			}
		} catch (IOException e) {
			System.err.println("Cannot read the file : " + e.getLocalizedMessage());
		}

		CSVFile found = fileRepository.findByFileName(csvFile.getFileName());

		if (found == null) {
			if (errorFreeRecords > 0) {
				fileRepository.save(csvFile);
				model.addAttribute("message", "File successfully saved!");

				return "upload_result";
			} else {
				model.addAttribute("message", "Not a single valid record was found!");

				return "upload_file";
			}
		} else {
			model.addAttribute("message", "File already exists!");

			return "upload_file";
		}
	}
}
