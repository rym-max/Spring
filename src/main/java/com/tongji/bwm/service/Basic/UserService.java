package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.Basic.User;
import com.tongji.bwm.repository.Basic.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService<Integer> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer Insert(User user) {

        return userRepository.save(user).getId();

    }

    @Override
    public User GetById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public User GetByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    @Override
    public void Update(User user) {
        //先查询是否存在，但是一般会在调用前查询//jpa底层会自行判断
        //有id是update
        userRepository.save(user);
    }

    @Override
    public void Delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void Delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> GetAll() {
        return userRepository.findAll();
    }
}
