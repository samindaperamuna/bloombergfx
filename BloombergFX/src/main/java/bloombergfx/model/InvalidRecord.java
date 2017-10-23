package bloombergfx.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invalid_record")
public class InvalidRecord extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	private CSVFile file;
}
