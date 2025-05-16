package br.com.akj.order.service.user;

import static org.mockito.Mockito.*;

import br.com.akj.order.builder.user.UserEntityBuilder;
import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.entity.UserEntity;
import br.com.akj.order.fixture.Fixture;
import br.com.akj.order.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class SaveUserServiceTest {

    @InjectMocks
    private SaveUserService service;

    @Mock
    private UserRepository userRepository;

    @Test
    void saveUser_ok() {
        Long userId = 1L;
        UserToImportDTO userDto = Fixture.make(UserToImportDTO.class);
        Map<Long, UserToImportDTO> usersToImport = new HashMap<>();
        usersToImport.put(userId, userDto);

        UserEntity mockEntity = mock(UserEntity.class);

        try (MockedStatic<UserEntityBuilder> mockedBuilder = mockStatic(UserEntityBuilder.class)) {
            mockedBuilder.when(() -> UserEntityBuilder.userEntityBuild(userDto)).thenReturn(mockEntity);

            service.saveUser(usersToImport);

            mockedBuilder.verify(() -> UserEntityBuilder.userEntityBuild(userDto));
            verify(userRepository).saveAll(List.of(mockEntity));
        }
    }
}
