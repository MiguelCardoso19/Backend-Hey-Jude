package io.codeforall.hackaton.converter;

import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KidDtoToKid implements Converter<KidDto, Kid>{

        private KidService kidService;


        @Autowired
        public void setKidService(KidService kidService) {
            this.kidService = kidService;
        }


        @Override
        public Kid convert(KidDto kidDto) {

            Kid kid = (kidDto.getId() != null ? kidService.get(kidDto.getId()) : new Kid());

            kid.setDateOfBirth(kidDto.getDateOfBirth());
            kid.setName(kidDto.getName());
            kid.setAge(kidDto.getAge());
            kid.setEmail(kidDto.getEmail());
            kid.setPassword(kidDto.getPassword());

            return kid;
        }
    }
