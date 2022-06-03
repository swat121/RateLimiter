package algorithm.RateLimiter.repo;


import algorithm.RateLimiter.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
    @Query(value = "SELECT * FROM Client where addressIP = ?1",nativeQuery = true)
    Client findByAddressIP(String addressIp);
}
