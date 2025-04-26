package io.github.nagpalankit.creditcardprocessor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nagpalankit.creditcardprocessor.model.CreditCardDraft;
import io.github.nagpalankit.creditcardprocessor.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CreditCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreditCardRepository creditCardRepository;

    @Test
    void getAllCardsIsSuccessful() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        verify(creditCardRepository).findAll();
    }

    @Test
    void saveNewCardIsSuccessful() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content(new ObjectMapper()
                        .writeValueAsString(
                                new CreditCardDraft(
                                        "nagpalankit",
                                        "1234-5678-9012-3456",
                                        5000.00
                                )
                        )
                )
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isCreated());
        verify(creditCardRepository).save(any());
    }

    @Test
    void saveNewCardWithInvalidCardNumber() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content(new ObjectMapper()
                        .writeValueAsString(
                                new CreditCardDraft(
                                        "nagpalankit",
                                        "1234-5678-9012",
                                        5000.00
                                )
                        )
                )
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isBadRequest());
        verify(creditCardRepository, never()).save(any());
    }

    @Test
    void saveNewCardWithInvalidUserName() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content(new ObjectMapper()
                        .writeValueAsString(
                                new CreditCardDraft(
                                        "",
                                        "1234-5678-9012-3456",
                                        5000.00
                                )
                        )
                )
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isBadRequest());
        verify(creditCardRepository, never()).save(any());
    }

    // Further validation tests go here
}
