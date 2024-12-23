package com.example.eshop.tests.controller;

import com.example.eshop.db.entities.Category;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.services.CategoryService;
import com.example.eshop.db.services.ProductService;
import com.example.eshop.rest.model.ProductDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityManager entityManager;

    private Category testCategory;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        productService.findAll().forEach(product -> productService.deleteById(product.getId()));
        categoryService.findAll().forEach(category -> categoryService.deleteById(category.getId()));

        testCategory = new Category();
        testCategory.setName("Test Category");
        entityManager.persist(testCategory);

        testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setCategory(testCategory);
        testProduct.setPrice(20.0);
        entityManager.persist(testProduct);

        entityManager.flush();
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Test Product")).andExpect(jsonPath("$[0].price").value(20.0));
    }

    @Test
    void shouldGetProductsByCategory() throws Exception {
        mockMvc.perform(get("/api/v1/categories/" + testCategory.getId() + "/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Test Product")).andExpect(jsonPath("$[0].categoryId").value(testCategory.getId()));
    }

    @Test
    void shouldGetProductById() throws Exception {
        mockMvc.perform(get("/api/v1/products/" + testProduct.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Test Product")).andExpect(jsonPath("$.price").value(20.0));
    }

    @Test
    void shouldCreateNewProduct() throws Exception {
        ProductDTO newProductDTO = new ProductDTO();
        newProductDTO.setName("New Product");
        newProductDTO.setCategoryId(testCategory.getId());
        newProductDTO.setPrice(30.0);

        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newProductDTO))).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("New Product")).andExpect(jsonPath("$.price").value(30.0));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setName("Updated Product");
        updatedProductDTO.setCategoryId(testCategory.getId());
        updatedProductDTO.setPrice(25.0);

        mockMvc.perform(put("/api/v1/products/" + testProduct.getId()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatedProductDTO))).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Updated Product")).andExpect(jsonPath("$.price").value(25.0));
    }

    @Test
    void shouldDeleteProductById() throws Exception {
        mockMvc.perform(delete("/api/v1/products/" + testProduct.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}