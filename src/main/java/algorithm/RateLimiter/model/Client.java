package algorithm.RateLimiter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Client {

    private String addressIP;

    private int maxRequest;

    private int preCount;

    private long curWindowKey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
