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
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;
import rest.DishOfTheDay.repository.UserRepository;
import rest.DishOfTheDay.service.mapper.UserMapper;
import org.springframework.data.domain.Sort;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@EnableCaching
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable("users")
    public List<UserRespDTO> getAll() {
        return mapper.fromUsers(repository.findAll(new Sort(Sort.Direction.ASC, "name")));
    }

    @Cacheable("users")
    public UserRespDTO get(int id) {
        return mapper.fromUser(repository.getOne(id));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public UserRespDTO create(UserReqDTO userDTO) {
        Assert.notNull(userDTO, "User must not be null");
        UserRespDTO created = mapper.fromUser(repository.save(mapper.toUser(userDTO)));
        log.info("User created : {}", created);
        return created;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public UserRespDTO update(int id, UserReqDTO userReqDTO) {
        Assert.notNull(userReqDTO, "User must not be null");
        User user = repository.getOne(id);
        mapper.toUpdate(user, userReqDTO);
        log.info("User with id={} updated : {}", id, user);
        return mapper.fromUser(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void delete (int id) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();
        repository.deleteById(id);
    }
}
