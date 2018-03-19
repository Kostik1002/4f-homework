package io.fourfinanceit.web;

import io.fourfinanceit.model.to.ClientTO;
import io.fourfinanceit.service.ClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "clients")
public class ClientController {

    @Autowired
    private ClientInfoService clientInfoService;

    @GetMapping(path = "{id}")
    public ResponseEntity<ClientTO> getUserInfo(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(clientInfoService.getClientInfo(id));
    }

}
