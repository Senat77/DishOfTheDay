package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserRequestDTO;
import rest.DishOfTheDay.domain.dto.UserResponseDTO;
import rest.DishOfTheDay.repository.UserRepository;
import rest.DishOfTheDay.service.mapper.UserMapper;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Cacheable("users")
    public List<UserResponseDTO> getAll() {
        return mapper.fromUsers(repository.findAll(new Sort(Sort.Direction.ASC, "name")));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public UserResponseDTO create(UserRequestDTO userDTO) {
        Assert.notNull(userDTO, "User must not be null");
        UserResponseDTO created = mapper.fromUser(repository.save(mapper.toUser(userDTO)));
        log.info("User created : {}", created);
        return created;
    }
}
