package com.sipibibu.aplhagramms.main.infastructure.clients;

import feign.HeaderMap;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "userClient", url="${services.gateway}"+"/account")
public interface UserClient {

    @GetMapping("/getId")
    ResponseEntity<Long> getCurrentId();
}
