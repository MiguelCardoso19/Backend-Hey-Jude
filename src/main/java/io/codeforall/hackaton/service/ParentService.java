package io.codeforall.hackaton.service;

import io.codeforall.hackaton.persistence.model.Parent;
import org.springframework.stereotype.Service;


public interface ParentService {

    Parent get(Integer id);
}


