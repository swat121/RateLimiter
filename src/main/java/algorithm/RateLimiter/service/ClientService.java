package algorithm.RateLimiter.service;


import algorithm.RateLimiter.model.Client;
import algorithm.RateLimiter.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepo clientRepo;

    public void saveClient(Client client){
        clientRepo.save(client);
    }

    public Client findByAddressIp(String addressIp){
        return clientRepo.findByAddressIP(addressIp);
    }
}
