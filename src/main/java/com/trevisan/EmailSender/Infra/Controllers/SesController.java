package com.trevisan.EmailSender.Infra.Controllers;

import com.trevisan.EmailSender.Core.Dtos.EmailSenderDto;
import com.trevisan.EmailSender.Core.Exceptions.Validators.ValidatorSes;
import com.trevisan.EmailSender.Infra.Services.SesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sendEmail")
public class SesController {
    private final SesService sesService;
    private final ValidatorSes validatorSes;

    @Autowired
    public SesController(SesService sesService, ValidatorSes validatorSes) {
        this.sesService = sesService;
        this.validatorSes = validatorSes;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailAws(@RequestBody EmailSenderDto senderDto){
        this.validatorSes.checkEmailSenderDto(senderDto);
        String messageEmail = this.sesService.senderEmail(senderDto.to(), senderDto.subject());
        return new ResponseEntity<>(messageEmail, HttpStatus.OK);
    }
}
