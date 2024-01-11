package com.sipibibu.aplhagramms.main.infastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="InterestsClient",url="${services.gateway}"+"/interests")
public interface InterestsClient {


    @GetMapping("/isExist/{id}")
    ResponseEntity<String> isExist(@PathVariable Long id);

    @GetMapping("/isExistMany")
    ResponseEntity<String> isExistMany(@RequestBody Long[] ids);
}
