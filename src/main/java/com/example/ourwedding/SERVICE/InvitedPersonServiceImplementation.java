package com.example.ourwedding.SERVICE;

import com.example.ourwedding.DTO.InvitedPerson;
import com.example.ourwedding.REPOSITORY.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class InvitedPersonServiceImplementation implements InvitedPersonService {

    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    PersonRepository personRepository;

    private boolean emailFlag;


    private boolean smsFlag;

    public void setEmailFlag(boolean emailFlag) {
        this.emailFlag = emailFlag;
    }

    public void setSmsFlag(boolean smsFlag) {
        this.smsFlag = smsFlag;
    }

    public List<InvitedPerson> findAll() {
        return personRepository.findAll();
    }

    public InvitedPerson getById(int theId) {
        Optional<InvitedPerson> result = personRepository.findById(theId);
        InvitedPerson thePerson = null;
        if (result.isPresent()) {
            thePerson = result.get();
        }
        return thePerson;
    }

    public void save(InvitedPerson thePerson) {
        personRepository.save(thePerson);
    }

    public void delete(InvitedPerson thePerson) {
        personRepository.delete(thePerson);
    }

    public boolean isEmailFlag() {
        return emailFlag;
    }

    public boolean isSMSFlag() {
        return smsFlag;
    }

    public boolean checkIfEmailOrNumberExists(String contact) {
        if (contact.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            setEmailFlag(true);
            return true;

        } else if (contact.matches("^[0-9]{9}$")) {
            setSmsFlag(true);
            return true;
        } else {
            return false;

        }


    }
}
