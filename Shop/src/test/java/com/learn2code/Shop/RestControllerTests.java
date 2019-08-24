package com.learn2code.Shop;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn2code.Shop.db.service.api.request.UpdateProductRequest;
import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import com.learn2code.Shop.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static Merchant merchant;

    @Before
    public void createMerchant() throws Exception   {
        if(merchant == null){
            merchant = new Merchant("name", "email", "address");

            String id = mockMvc.perform(post("/merchant")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(merchant)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            merchant.setId(objectMapper.readValue(id,Integer.class));
        }
    }

    @Test
    public void customer() throws Exception{
        Customer customer = new Customer("Ferko", "Mrkvicka", "emailtest", "test", 20, "xxx");

        // Add customer
        String id = mockMvc.perform(post("/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();

        customer.setId(objectMapper.readValue(id,Integer.class));

        // Get customer
        String customerJSON = mockMvc.perform(get("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(customerJSON,Customer.class);
        Assert.assertEquals(customer,returnedCustomer);

        // Get all customers
        String listJSON = mockMvc.perform(get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Customer> customers = objectMapper.readValue(listJSON,new TypeReference<List<Customer>>(){});

        assert customers.size() == 1;
        Assert.assertEquals(customer,customers.get(0));

    }

    @Test
    public void merchant() throws Exception{
        // Add merchant
//        Merchant merchant = new Merchant("name", "email", "address");
//
//        String id = mockMvc.perform(post("/merchant")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(merchant)))
//                .andExpect(status().isCreated())
//                .andReturn().getResponse().getContentAsString();
//        merchant.setId(objectMapper.readValue(id,Integer.class));

        // Get merchant
        String merchantJSON = mockMvc.perform(get("/merchant/" + merchant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Merchant returnedMerchant = objectMapper.readValue(merchantJSON,Merchant.class);
        Assert.assertEquals(merchant,returnedMerchant);

        // Get all merchants
        String listJSON = mockMvc.perform(get("/merchant")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Merchant> merchants = objectMapper.readValue(listJSON,new TypeReference<List<Merchant>>(){});
        assert merchants.size() == 1;
        Assert.assertEquals(merchant,merchants.get(0));

    }

    @Test
    public void product() throws Exception{
        // Add producy
        System.out.println(merchant.getId());
        Product product = new Product(merchant.getId(), "name", "description", 5, 1);
        //System.out.println(product.getMerchant_id(), product.getName());

        String id = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        product.setId(objectMapper.readValue(id,Integer.class));

        // Get product
        String productJson = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Product returnedProduct = objectMapper.readValue(productJson,Product.class);
        Assert.assertEquals(product,returnedProduct);

        // Get all products

        String listProduct = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Product> products = objectMapper.readValue(listProduct,new TypeReference<List<Product>> (){});
        assert products.size() == 1;
        Assert.assertEquals(product,products.get(0));

        // Update product
        double updatePrice = product.getPrice() + 1;
        int updateAvailable = product.getAvailable() + 5;
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(),product.getAvailable());


        mockMvc.perform(patch("/product" + product.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updateAvailable)))
        .andExpect(status().isOk());

        String returnedUpdateProduct = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Product updatedProduct = objectMapper.readValue(returnedUpdateProduct,Product.class);
        assert updatePrice == updatedProduct.getPrice();
        assert updateAvailable == updatedProduct.getAvailable();

        // Delete product
        mockMvc.perform(delete("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        String listProductAftetDelete = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Product> productListAfterDelete = objectMapper.readValue(listProductAftetDelete,new TypeReference<List<Product>>(){});

        assert productListAfterDelete.size() == 0;

    }


}
