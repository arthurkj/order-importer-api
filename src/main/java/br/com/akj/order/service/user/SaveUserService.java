package br.com.akj.order.service.user;

import br.com.akj.order.builder.user.UserEntityBuilder;
import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.entity.UserEntity;
import br.com.akj.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveUserService {

    private final UserRepository userRepository;

    @Transactional
    public List<UserEntity> saveUser(final Map<Long, UserToImportDTO> usersToImport) {
        log.info("Saving {} users", usersToImport.size());
        final Iterable<UserEntity> savedUsers = userRepository.saveAll(usersToImport.values().stream().map(UserEntityBuilder::userEntityBuild).toList());

        return StreamSupport
                .stream(savedUsers.spliterator(), false)
                .toList();
    }
}
