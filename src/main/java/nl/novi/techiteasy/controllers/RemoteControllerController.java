package nl.novi.techiteasy.controllers;


import nl.novi.techiteasy.models.RemoteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.RemoteServer;

@RequestMapping("/remotecontrollers")
@RestController
public class RemoteControllerController {

    private final RemoteService remoteService;

    public RemoteController (RemoteServer)

}
