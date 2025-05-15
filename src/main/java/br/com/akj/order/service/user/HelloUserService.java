package br.com.akj.order.service.user;

import org.springframework.stereotype.Service;

import br.com.akj.order.dto.HelloUserResponse;
import br.com.akj.order.errors.Error;
import br.com.akj.order.exception.BusinessErrorException;
import br.com.akj.order.helper.MessageHelper;
import br.com.akj.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HelloUserService {

    public static final String HELLO_USER_MESSAGE = "Hello %s!";
    private final UserRepository userRepository;
    private final MessageHelper messageHelper;

    public HelloUserResponse hello(final String name) {
        log.info("Say hi to {}!", name);

        userRepository.findByName(name.toUpperCase()).orElseThrow(() -> new BusinessErrorException(Error.USER_NOT_FOUND, messageHelper.get(Error.USER_NOT_FOUND)));

        return new HelloUserResponse(String.format(HELLO_USER_MESSAGE, name));
    }
}
