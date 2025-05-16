package br.com.akj.order.service.user;

import br.com.akj.order.builder.UserEntityBuilder;
import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveUserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(final Map<Long, UserToImportDTO> usersToImport) {
        log.info("Saving {} users", usersToImport.size());
        userRepository.saveAll(usersToImport.values().stream().map(UserEntityBuilder::userEntityBuild).toList());
    }
}
