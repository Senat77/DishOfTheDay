package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.repository.UserRepository;
import rest.DishOfTheDay.service.mapper.UserMapper;

@Service
@Transactional(readOnly = true)
@EnableCaching
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
        this.mapper = UserMapper.INSTANCE;
    }
}
