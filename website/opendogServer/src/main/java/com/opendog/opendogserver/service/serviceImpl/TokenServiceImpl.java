package com.opendog.opendogserver.service.serviceImpl;

import com.opendog.opendogserver.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String allocNewToken(int uid) {
        return null;
    }

    @Override
    public boolean checkTokenIsValid(int uid, String token) {
        return false;
    }

    @Override
    public boolean freeToken(int uid, String token) {
        return false;
    }

    @Override
    public String updateToken(int uid) {
        return null;
    }
}
