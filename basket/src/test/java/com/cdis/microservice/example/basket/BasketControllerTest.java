package com.cdis.microservice.example.basket;

import com.cdis.microservice.example.basket.controller.BasketController;
import com.cdis.microservice.example.basket.model.BasketItem;
import com.cdis.microservice.example.basket.model.BasketModel;
import com.cdis.microservice.example.basket.model.BasketUser;
import com.cdis.microservice.example.basket.service.BasketService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(BasketController.class)
@AutoConfigureDataMongo
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    private JacksonTester<List<BasketItem>> jsonBasketItemList;
    private JacksonTester<List<BasketModel>> jsonBasketModelList;
    private JacksonTester<List<BasketUser>> jsonBasketUserList;

    private BasketItem basketItem = new BasketItem(1L, 123.456, 2, true);
    private BasketItem basketItem2 = new BasketItem(1L, 123.456, 2, true);

    private ObjectId id = new ObjectId();

    private BasketModel basketModel = new BasketModel( id, 1L);
    private BasketModel basketModel2 = new BasketModel(id, 2L);
    private BasketUser basketUser = new BasketUser(1L, "testUsername");

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllBaskets() throws Exception {
        String uri = "/basket";
        List<BasketModel> listBasket = new ArrayList<>();
        listBasket.add(basketModel);
        listBasket.add(basketModel2);

        given(basketService.findAllBaskets())
                .willReturn(listBasket);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonBasketModelList.write(listBasket).getJson());
    }

    @Test
    public void getBasketbyUserId() throws Exception {
        String uri = "/basket/1";

        given(basketService.findBasketByUserId(1L))
                .willReturn(basketModel);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(mapToJson(basketModel));
    }

    @Test
    public void createBasket() throws Exception {
        String uri = "/basket";

        MockHttpServletResponse response = mockMvc.perform(
                post(uri).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(basketModel))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void addItemtoUserBasket() throws Exception {
        String uri = "/basket/1/item";

        MockHttpServletResponse response = mockMvc.perform(
                post(uri).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(basketItem))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(mapToJson(basketItem));
    }

    @Test
    public void findItemInUserBasket() throws Exception {
        String uri = "/basket/1/item/1";

        given(basketService.findItemInBasket(1L, 1L))
                .willReturn(basketItem);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(mapToJson(basketItem));
    }

    @Test
    public void deleteBaskets()throws Exception{
        String uri = "/basket";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void deleteBasketbyUserId() throws Exception{
        String uri = "/basket/1";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteBasketItemInBasketById() throws Exception{
        String uri = "/basket/1/item/1";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}