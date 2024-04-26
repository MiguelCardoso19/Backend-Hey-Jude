package io.codeforall.hackaton.converter;
import io.codeforall.hackaton.command.ParentDto;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.persistence.model.Parent;
import io.codeforall.hackaton.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

    @Component
    public class ParentDtoToParent implements Converter<ParentDto, Parent> {

        private ParentService parentService;


        @Autowired
        public void setParentService(ParentService parentService) {
            this.parentService = parentService;
        }


        @Override
        public Parent convert(ParentDto parentDto) {

            Parent parent = (parentDto.getId() != null ? parentService.get(parentDto.getId()) : new Parent());

            parent.setName(parentDto.getName());
            parent.setAge(parentDto.getAge());
            parent.setEmail(parentDto.getEmail());
            parent.setPassword(parentDto.getPassword());
            parent.setDateOfBirth(parentDto.getDateOfBirth());

            return parent;
        }
    }
