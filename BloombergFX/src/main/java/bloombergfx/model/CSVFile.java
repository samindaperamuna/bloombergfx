package bloombergfx.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "csv_file")
public class CSVFile {

	@Id
	@GeneratedValue
	private long id;

	private String fileName;

	@OneToMany(mappedBy = "file")
	private List<ValidRecord> validRecords;

	@OneToMany(mappedBy = "file")
	private List<InvalidRecord> invalidRecords;

	public CSVFile() {
		this.validRecords = new ArrayList<>();
		this.invalidRecords = new ArrayList<>();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<ValidRecord> getValidRecords() {
		return validRecords;
	}

	public void setValidRecords(List<ValidRecord> validRecords) {
		this.validRecords = validRecords;
	}

	public List<InvalidRecord> getInvalidRecords() {
		return invalidRecords;
	}

	public void setInvalidRecords(List<InvalidRecord> invalidRecords) {
		this.invalidRecords = invalidRecords;
	}
}
