package io.github.nagpalankit.creditcardprocessor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreditCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCardsIsSuccessful() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void saveNewCardIsSuccessful() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content("{\"userName\":\"nagpalankit\",\"cardNumber\":\"1234-5678-9012-3456\",\"cardLimit\":5000.00}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void saveNewCardWithInvalidCardNumber() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content("{\"userName\":\"nagpalankit\",\"cardNumber\":\"1234-5678-9012\",\"cardLimit\":5000.00}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveNewCardWithInvalidUserName() throws Exception {
        RequestBuilder saveNewCardRequest = MockMvcRequestBuilders
                .post("/cards")
                .content("{\"userName\":\"\",\"cardNumber\":\"1234-5678-9012-3456\",\"cardLimit\":5000.00}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(saveNewCardRequest)
                .andExpect(status().isBadRequest());
    }

    // Further validation tests go here
}
