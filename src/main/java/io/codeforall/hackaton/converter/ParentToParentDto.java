package io.codeforall.hackaton.converter;
import io.codeforall.hackaton.command.ParentDto;
import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.stereotype.Component;

    @Component
    public class ParentToParentDto extends AbstractConverter<Parent, ParentDto>  {

        @Override
        public ParentDto convert(Parent parent) {

            ParentDto parentDto = new ParentDto();
            parentDto.setId(parent.getId());
            parentDto.setName(parent.getName());
            parentDto.setAge(parent.getAge());
            parentDto.setEmail(parent.getEmail());
            parentDto.setPassword(parent.getPassword());
            parentDto.setDateOfBirth(parent.getDateOfBirth());

            return parentDto;
        }
    }
