package bloombergfx.data;

import org.springframework.data.jpa.repository.JpaRepository;

import bloombergfx.model.Deal;

public interface DealRepository extends JpaRepository<Deal, String> {

	Deal findByCurrencyCode(String currencyCode);

	<S extends Deal> S save(S deal);
}
