package io.codeforall.hackaton.service;

import io.codeforall.hackaton.persistence.dao.ParentDao;
import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImp implements ParentService{

        private ParentDao parentDao;

        @Autowired
        public void setParentDao(ParentDao parentDao) {
            this.parentDao = parentDao;
        }

        @Override
        public Parent get(Integer id) {
            return parentDao.findById(id);
        }
    }


