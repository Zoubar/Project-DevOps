package com.example.DevOps_Project.controller;
import com.example.DevOps_Project.dao.ProductDao;
import com.example.DevOps_Project.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDao productDao;

    private ObjectMapper mapper = new ObjectMapper();
    private String resultContent = null;

    @Test
    public void findAllProductsTest() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product("product 1", "product 1 description", 400.0),
                new Product("product 2", "product 2 description", 500.0)
        );

        resultContent = mapper.writeValueAsString(products);
        System.out.println(resultContent);
        when(productDao.findAll()).thenReturn(products);

        // Act
        mvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resultContent)))
                .andDo(print());
    }

    @Test
    public void getProductsByIdTest() throws Exception {
        // Arrange
        Product p = new Product("product 1", "product 1 description", 400.0);
        p.setId(100);

        resultContent = mapper.writeValueAsString(p);
        when(productDao.findById(100)).thenReturn(Optional.of(p));

        // Act
        this.mvc.perform(get("/product/100")  // <-- Updated path here
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resultContent)))
                .andDo(print());
    }

}
