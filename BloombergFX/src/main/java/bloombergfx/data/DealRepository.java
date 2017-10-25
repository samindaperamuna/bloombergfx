package bloombergfx.data;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bloombergfx.model.Deal;

@Repository
@Transactional
public interface DealRepository extends JpaRepository<Deal, String> {

	Deal findByCurrencyCode(String currencyCode);

	<S extends Deal> S save(S deal);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Deal d SET d.count = d.count + 1 WHERE d.currencyCode = :currency_code")
	int update(@Param("currency_code") String currencyCode);
}
