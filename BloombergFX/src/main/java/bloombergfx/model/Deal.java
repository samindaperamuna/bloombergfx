package bloombergfx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deal {

    @Id
    @Column(name = "currency_code")
    private String currencyCode;
    private long count;

    public String getCurrencyCode() {
	return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
    }

    public long getCount() {
	return count;
    }

    public void setCount(long count) {
	this.count = count;
    }
}
