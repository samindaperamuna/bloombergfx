package bloombergfx.init;

import java.util.Currency;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import bloombergfx.data.DealRepository;
import bloombergfx.model.Deal;

@Component
public class DealDbLoader implements ApplicationRunner {

	@Autowired
	DealRepository dealRespository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createDefaultDeals();
	}

	private void createDefaultDeals() {
		Set<Currency> currencies = Currency.getAvailableCurrencies();

		for (Currency currency : currencies) {
			Deal deal = new Deal();
			deal.setCurrencyCode(currency.getCurrencyCode());
			dealRespository.save(deal);
		}

		dealRespository.flush();
	}
}
