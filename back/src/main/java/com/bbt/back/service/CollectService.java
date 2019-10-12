package com.bbt.back.service;

import org.springframework.stereotype.Component;

@Component
public interface CollectService {
    int countByUserId(int userId);
}
