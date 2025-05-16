package br.com.akj.order.service.order;

import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.errors.Error;
import br.com.akj.order.exception.BusinessErrorException;
import br.com.akj.order.helper.MessageHelper;
import br.com.akj.order.service.user.SaveUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportOrderFileServiceTest {

    @InjectMocks
    private ImportOrderFileService service;

    @Mock
    private SaveUserService saveUserService;

    @Mock
    private MessageHelper messageHelper;

    @Test
    void importFile_ok() {
        final Long expectedId = 1L;
        final String expectedName = "John Doe";
        final String content = "0000000001John Doe                                     000000000100000000020000000050.1520210101";
        final MultipartFile file = new MockMultipartFile("file.txt", content.getBytes());

        service.importFile(file);

        ArgumentCaptor<Map<Long, UserToImportDTO>> captor = ArgumentCaptor.forClass(Map.class);
        verify(saveUserService).saveUser(captor.capture());

        Map<Long, UserToImportDTO> users = captor.getValue();
        UserToImportDTO user = users.get(expectedId);
        assertEquals(expectedName, user.name());
    }

    @Test
    void importFile_ioException_shouldThrowBusinessErrorException() throws Exception {
        final MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("IO error"));
        when(file.getOriginalFilename()).thenReturn("fail.txt");
        when(messageHelper.get(Error.INVALID_FILE_ERROR)).thenReturn("Invalid file");

        assertThrows(BusinessErrorException.class, () -> service.importFile(file));

        verify(messageHelper).get(Error.INVALID_FILE_ERROR);
    }
}
