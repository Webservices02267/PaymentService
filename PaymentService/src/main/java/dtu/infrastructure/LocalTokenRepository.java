package dtu.infrastructure;


import dtu.domain.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LocalTokenRepository  {

	List<Token> tokens = new ArrayList<>();
	HashMap<String, Token> tokenHashMap = new HashMap<>();
	HashMap<String, HashSet<Token>> customerHashMap = new HashMap<>();



	public HashSet<Token> get(String customerId) {
		if(!customerHashMap.containsKey(customerId)) {
			customerHashMap.put(customerId, new HashSet<Token>());
		}
		return customerHashMap.get(customerId);
	}


	public Token create(String customerId) {
		Token token = new Token(customerId);
		tokenHashMap.put(token.getUuid(), token);
		if (customerHashMap.containsKey(customerId)) {
			customerHashMap.get(customerId).add(token);
		}
		else {
			var tokenSet = new HashSet<Token>();
			tokenSet.add(token);
			customerHashMap.put(customerId, tokenSet);
		}
		return token;
	}


	public Token getVerfiedToken(String tokenUuid) {
		try {
			var token = tokenHashMap.remove(tokenUuid);
			customerHashMap.get(token.getCustomerId()).remove(token);
			return token;
		} catch (Exception e) {
			return new Token(false);
		}
	}

}
