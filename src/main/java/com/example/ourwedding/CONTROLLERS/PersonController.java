package com.example.ourwedding.CONTROLLERS;

import com.example.ourwedding.DTO.InvitedPerson;
import com.example.ourwedding.REPOSITORY.PersonRepository;
import com.example.ourwedding.SERVICE.EmailSenderService;
import com.example.ourwedding.SERVICE.InvitedPersonServiceImplementation;
import com.example.ourwedding.SERVICE.SMSsender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/confirma")
public class PersonController {
    private final String subject = "Nunta 9 IULIE 2023, Raluca si Sorin";
    private String body = "Multumim pentru confirmare! Va asteptam pe 9 IULIE 2023 sa ne fiti alaturi! Pentru mai multe detalii, va rugam sa ne contactati la 0740096512.";


    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private SMSsender smSsender;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InvitedPersonServiceImplementation invitedPersonServiceImplementation;


    @PostMapping("/vreauSaDevinMembru")
    public ResponseEntity<String> addPerson(
            @RequestBody InvitedPerson invitedPerson) {
        if (invitedPersonServiceImplementation.checkIfEmailOrNumberExists(invitedPerson.getContact())==true && invitedPersonServiceImplementation.isSMSFlag()==true) {
            try {
                LOGGER.log(Level.INFO, "Numărul de telefon este introdus corect");
                smSsender.sendSMSmessage("ralucasorin", "SorinSiRaluca29", invitedPerson.getContact(),
                        "Dragă " + invitedPerson.getContact() + ",\n" +
                                "Vă mulţumim că aţi confirmat prezenţa la nunta noastră. Suntem onoraţi că veţi fi alături de noi în această zi specială.Vă aşteptăm cu nerăbdare.\n" +
                                "Cu respect, Raluca si Sorin Scintei");
                System.out.println("SMS send successfully.");
            } catch (Exception e) {
                System.out.println("SMS message cound not be sent");
                e.printStackTrace();
            } finally {
                personRepository.save(new InvitedPerson(invitedPerson.getFullName(), invitedPerson.getContact()));
                LOGGER.log(Level.INFO, invitedPerson.getFullName() + " was inserted successfuly");
                invitedPersonServiceImplementation.setSmsFlag(false);
                LOGGER.log(Level.INFO,"The SMSFlag is:"+invitedPersonServiceImplementation.isSMSFlag());


                return new ResponseEntity<>("Mulțumim pentru confirmare, "+invitedPerson.getFullName()+"!"+"\n"+"În scurt timp veți primi un mesaj pe telefon sau email.", HttpStatus.CREATED);


            }
        } else if (invitedPersonServiceImplementation.checkIfEmailOrNumberExists(invitedPerson.getContact())==true && invitedPersonServiceImplementation.isEmailFlag()==true) {
            try {
                LOGGER.log(Level.INFO, "Email-ul este scris într-un format corect");
                emailSenderService.sendEmail(invitedPerson.getContact(), subject,
                        "Dragă "+invitedPerson.getFullName()+",\n" +
                                "\n" +
                                "Vreau să îţi mulţumesc din suflet pentru confirmarea prezenţei tale la nunta noastră. Suntem onoraţi că vei fi alături de noi în această zi specială.\n" +
                                "\n" +
                                "Suntem încântaţi că vom avea ocazia să ne împărtăşim fericirea cu cei dragi nouă.\n" +
                                "\n" +
                                "Ne bucurăm să ştim că vei fi alături de noi în această zi importantă şi te aşteptăm cu nerăbdare.\n" +
                                "\n" +
                                "Cu drag, Raluca și Sorin Scîntei.");
                System.out.println("Email send successfully");
            } catch (Exception e) {
                System.out.println("Email cound not be sent");
                e.printStackTrace();
            } finally {
                InvitedPerson invitedPerson1 = personRepository
                        .save(new InvitedPerson(invitedPerson.getFullName(), invitedPerson.getContact()));
                LOGGER.log(Level.INFO, invitedPerson.getFullName() + " was inserted successfuly");
                invitedPersonServiceImplementation.setEmailFlag(false);
                LOGGER.log(Level.INFO,"The EmailFlag is:"+invitedPersonServiceImplementation.isEmailFlag());

                return new ResponseEntity<>("Mulțumim pentru confirmare, "+invitedPerson.getFullName()+"!"+ "\n"+ "În scurt timp veți primi un mesaj pe telefon sau email.", HttpStatus.CREATED);
            }
        } else {
            LOGGER.log(Level.INFO, "Datele sunt introduse intr-un format gresit");
            LOGGER.log(Level.INFO, invitedPerson.getFullName() + " was not inserted");
            invitedPersonServiceImplementation.setEmailFlag(false);
            invitedPersonServiceImplementation.setSmsFlag(false);
            return new ResponseEntity<>("Datele au fost introduse greșit sau ceva nu a funcționat.Vă rugăm să reîncercați reintroducerea datelor.", HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }

//
//    @GetMapping("/allMembers")
//    public ResponseEntity<List> getMembers() {
//        List<InvitedPerson> membersList;
//        try {
//            membersList = personRepository.findAll();
//            for (InvitedPerson invitedPerson : membersList)
//                System.out.println(invitedPerson.getFullName());
//            LOGGER.log(Level.INFO, "The members were fetch successfuly");
//
//            return new ResponseEntity<>(membersList, HttpStatus.OK);
//        } catch (Exception e) {
//
//            return new ResponseEntity<>(List.new("The members could not be fetched"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



}
