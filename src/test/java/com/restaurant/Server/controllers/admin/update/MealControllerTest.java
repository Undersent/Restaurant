package com.restaurant.Server.controllers.admin.update;

import com.restaurant.Server.Repository.MealRepository;
import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
public class MealControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private String mealName = "kurczak";
    private Meal meal = Meal.builder()
            .mealName(mealName)
            .build();

    private List<Meal> mealList = new ArrayList<>();
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc ->hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("JSON message converter cannot be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setUp() throws Exception {
       // Meal meal =
        this.mealRepository.deleteAllInBatch();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.mealList.add(meal);
        this.mealRepository.save(meal);
    }

    @Test
    public void mealNotFoundTest() throws Exception{
        mockMvc.perform(put("http://localhost:8080//admin/update/meal")
                .content(this.json(Meal.builder().mealId(666).build()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateMealTest() throws Exception{
        String mealJson = "{ \"mealId\": 1, \"mealName\":\"kurczak2\", \"available\": true, \"price\": 0 }";//this.json(meal);
        //System.out.println(mealJson);
        mockMvc.perform(put("http://localhost:8080/admin/update/meal")
                .contentType(contentType)
                .content(mealJson))
                .andExpect(status().isNotFound()); //....poprawic....TODO
    }

    @Test
    public void getMeal() throws Exception {
        mockMvc.perform(get("http://localhost:8080/get/meal/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.mealId", is(this.mealList.get(0).getMealId())))
                .andExpect(jsonPath("$.price", is(this.mealList.get(0).getPrice())));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
