package bloombergfx.model;

import java.util.Currency;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Record {

	@Id
	@Column(name = "record_id")
	protected long id;

	protected String fromCurrency;
	protected String toCurrency;
	protected Date timeStamp;
	protected Double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id", referencedColumnName = "id")
	private CSVFile file;

	public Record() {
	}

	public Record(long id, String fromCurrency, String toCurrency, Date timeStamp, Double amount, CSVFile file) {
		super();
		this.id = id;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.timeStamp = timeStamp;
		this.amount = amount;
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CSVFile getFile() {
		return file;
	}

	public void setFile(CSVFile file) {
		this.file = file;
	}

	public Record validate() {
		Set<Currency> currencies = Currency.getAvailableCurrencies();

		try {
			if (!currencies.contains(Currency.getInstance(this.fromCurrency))
					|| !currencies.contains(Currency.getInstance(this.toCurrency))) {
				return new InvalidRecord(this);
			}

			if (this.fromCurrency.equals(this.toCurrency)) {
				return new InvalidRecord(this);
			}

			if (this.amount <= 0) {
				return new InvalidRecord(this);
			}
		} catch (Exception e) {
			return new InvalidRecord(this);
		}

		ValidRecord record = new ValidRecord(this);
		return record;
	}
}
