package dtu.Application;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import dtu.Domain.Token;

public class MockTokenService implements ITokenService {

    IAccountService accountService = new MockAccountService();
    static HashMap<String, HashSet<String>> customerTokens = new HashMap<>();
    //static HashSet<String> tokens = new HashSet<>();

    @Override
    public String getToken(String customerId) {
        if (!accountService.hasCustomer(customerId)) return null;
        var token = new Token(customerId).getUuid().toString();
        if (customerTokens.containsKey(customerId)) {
            customerTokens.get(customerId).add(token);
        } else {
            customerTokens.put(customerId, new HashSet<>());
            customerTokens.get(customerId).add(token);
        }
        return token;
    }

    @Override
    public boolean verifyToken(String customerId, String token) {
        if (!customerTokens.containsKey(customerId)) return false;
        return customerTokens.get(customerId).stream().anyMatch(s -> Objects.equals(s, token));
    }

    @Override
    public void removeToken(String customerId, String token) {
        customerTokens.get(customerId).remove(token);
    }
}