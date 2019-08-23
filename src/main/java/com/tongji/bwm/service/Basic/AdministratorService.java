package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.Basic.Administrator;
import com.tongji.bwm.repository.Basic.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService implements  IAdministratorService<Integer>{

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Integer Insert(Administrator administrator) {

        return administratorRepository.save(administrator).getId();
    }

    @Override
    public Administrator GetById(Integer id) {
        Optional<Administrator> optional = administratorRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Administrator GetByLoginName(String loginName) {
        return administratorRepository.findByLoginName(loginName);
    }

    @Override
    public void Update(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    @Override
    public void Delete(Administrator administrator){
        administratorRepository.delete(administrator);
    }

    @Override
    public void Delete(Integer id) {
        administratorRepository.deleteById(id);
    }

    @Override
    public List<Administrator> GetAll() {
        return administratorRepository.findAll();
    }
}
