package bloombergfx.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "valid_record")
public class ValidRecord extends Record {

	public ValidRecord() {

	}

	public ValidRecord(Record record) {
		super(record.getId(), record.getFromCurrency(), record.getToCurrency(), record.getTimeStamp(),
				record.getAmount(), record.getFile());
	}
}
