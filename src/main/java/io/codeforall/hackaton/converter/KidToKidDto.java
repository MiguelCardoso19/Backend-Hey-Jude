package io.codeforall.hackaton.converter;
import io.codeforall.hackaton.command.KidDto;
import org.springframework.stereotype.Component;
import io.codeforall.hackaton.persistence.model.Kid;

@Component
public class KidToKidDto extends AbstractConverter<Kid, KidDto>  {

        @Override
        public KidDto convert(Kid kid) {

            KidDto kidDto = new KidDto();
            kidDto.setId(kid.getId());
            kidDto.setName(kid.getName());
            kidDto.setAge(kid.getAge());
            kidDto.setEmail(kid.getEmail());
            kidDto.setPassword(kid.getPassword());
            kidDto.setDateOfBirth(kid.getDateOfBirth());

            return kidDto;
        }
    }
