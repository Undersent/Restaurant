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
@SpringBootTest
@WebAppConfiguration
public class UpdateMealController {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private String mealName = "kurczak";
    private Meal meal;
    private List<Meal> mealList = new ArrayList<>();
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealService mealService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc ->hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("JSON message converter cannot be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.mealList.add(new Meal(19,"kurczak2",true,11));

        //this.mealRepository.deleteAllInBatch();
//        this.meal = mealRepository.save(Meal.builder()
//                .mealName(mealName)
//                .mealId(1)
//                .isAvailable(true)
//                .build());
    }

    @Test
    public void mealNotFoundTest() throws Exception{
        mockMvc.perform(put("/admin/update/meal/19")
                .content(this.json(new Meal()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateMealTest() throws Exception{
        String mealJson = this.json(new Meal(19,"kurczak2",true,11));
       // System.out.println(this.json(new Meal(19,"kurczak2",true,11)));
        mockMvc.perform(put("/admin/update/meal/19")
                .contentType(contentType)
                .content(mealJson))
                .andExpect(status().isNotFound()); //....poprawic....TODO
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
//    @Test
//    public void readSingleBookmark() throws Exception {
//        mockMvc.perform(get("/" + userName + "/bookmarks/"
//                + this.bookmarkList.get(0).getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
//                .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
//                .andExpect(jsonPath("$.description", is("A description")));
//    }
//
//    @Test
//    public void readBookmarks() throws Exception {
//        mockMvc.perform(get("/" + userName + "/bookmarks"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
//                .andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
//                .andExpect(jsonPath("$[0].description", is("A description")))
//                .andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
//                .andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
//                .andExpect(jsonPath("$[1].description", is("A description")));
//    }