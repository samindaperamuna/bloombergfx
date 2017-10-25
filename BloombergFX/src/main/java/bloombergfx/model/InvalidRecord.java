package bloombergfx.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "invalid_record")
public class InvalidRecord extends Record {

	public InvalidRecord() {

	}

	public InvalidRecord(Record record) {
		super(record.getId(), record.getFromCurrency(), record.getToCurrency(), record.getTimeStamp(),
				record.getAmount(), record.getFile());
	}

}
