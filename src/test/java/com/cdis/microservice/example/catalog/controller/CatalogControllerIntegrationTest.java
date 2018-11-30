package com.cdis.microservice.example.catalog.controller;

import com.cdis.microservice.example.catalog.model.CatalogBrand;
import com.cdis.microservice.example.catalog.model.CatalogItem;
import com.cdis.microservice.example.catalog.model.CatalogType;
import com.cdis.microservice.example.catalog.service.CatalogBrandService;
import com.cdis.microservice.example.catalog.service.CatalogItemService;
import com.cdis.microservice.example.catalog.service.CatalogTypeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
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

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CatalogController.class)
public class CatalogControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogBrandService catalogBrandService;

    @MockBean
    private CatalogItemService catalogItemService;

    @MockBean
    private CatalogTypeService catalogTypeService;

    private JacksonTester<CatalogBrand> jsonCatalogBrand;
    private JacksonTester<List<CatalogBrand>> jsonCatalogBrandList;

    private JacksonTester<CatalogItem> jsonCatalogItem;
    private JacksonTester<List<CatalogItem>> jsonCatalogItemList;

    private JacksonTester<CatalogType> jsonCatalogType;
    private JacksonTester<List<CatalogType>> jsonCatalogTypeList;

    private CatalogBrand catalogBrand = new CatalogBrand(1L, "testBrand");
    private CatalogType catalogType = new CatalogType(1L, "testType");
    private CatalogItem catalogItem = new CatalogItem(1L, "testItem1", "testDescription",11.00,  "testImageName", "C:/testImageURI.png", new CatalogType(1L, "testType"), new CatalogBrand(1L, "testBrand"), 11);
    private CatalogItem catalogItem2 = new CatalogItem(1L, "testItem2", "testDescription",11.00,  "testImageName", "C:/testImageURI.png", new CatalogType(1L, "testType"), new CatalogBrand(1L, "testBrand"), 11);

    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postCatalogItem() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/item").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(catalogItem))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo("Item creado con exito");

    }

    @Test
    public void postCatalogBrand() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/brand").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(catalogBrand))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void postCatalogType() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/type").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(catalogType))).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    /**
     * Test de DeleteCatalogItem para cuando existe el CatalogItem
     * @throws Exception
     */
    @Test
    public void deleteCatalogItem() throws Exception {
        String uri = "/item/1";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    /**
     * Test de DeleteCatalogBrand para cuando existe el CatalogBrand
     * @throws Exception
     */
    @Test
    public void deleteCatalogBrand() throws Exception {
        String uri = "/brand/2";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    /**
     * Test de DeleteCatalogType para cuando existe el CatalogType
     * @throws Exception
     */
    @Test
    public void deleteCatalogType() throws Exception {
        String uri = "/type/3";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    /**
     * Test de actualizar item para cuando no existe el item
     * @throws Exception
     */
    @Test
    public void updateCatalogItem() throws Exception {
        String uri = "/item/3";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put(uri).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(catalogItem))).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
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
