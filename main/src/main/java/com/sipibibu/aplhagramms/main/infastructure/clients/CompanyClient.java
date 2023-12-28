package com.sipibibu.aplhagramms.main.infastructure.clients;

import com.sipibibu.aplhagramms.main.app.dto.CompanyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;


@FeignClient(name="CompanyClient",url="${services.gateway}"+"/company")
public interface CompanyClient {

    @GetMapping("/getByFormIds")
    ResponseEntity<String> getByFormIds(@RequestBody List<Long> formId);


}
