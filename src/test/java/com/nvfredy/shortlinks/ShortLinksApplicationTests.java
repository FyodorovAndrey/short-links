package com.nvfredy.shortlinks;

import com.nvfredy.shortlinks.controller.LinkController;
import com.nvfredy.shortlinks.repository.LinkRepository;
import com.nvfredy.shortlinks.service.LinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShortLinksApplication.class)
class ShortLinksApplicationTests {

    @MockBean
    LinkRepository linkRepository;
    @MockBean
    LinkService personService;
    @MockBean
    LinkController linkController;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateShortLink() throws Exception {
        mockMvc.perform(get("/api/create-link/https://www.google.com/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLongLink() throws Exception {
        String longLinkFromRequest = "https://www.google.com/";
        String shortLink = personService.createShortLink(longLinkFromRequest);
        mockMvc.perform(get("/api/get-long-link/" + shortLink))
                .andExpect(status().isOk());
    }
}
