package com.learn2code.Shop;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.learn2code.Shop.db.service.api.BankAccountService;
import com.learn2code.Shop.db.service.api.CustomerService;
import com.learn2code.Shop.db.service.api.MerchantService;
import com.learn2code.Shop.db.service.api.ProductService;
import com.learn2code.Shop.db.service.api.request.UpdateBankAccount;
import com.learn2code.Shop.db.service.api.request.UpdateProductRequest;
import com.learn2code.Shop.domain.BankAccount;
import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import com.learn2code.Shop.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTests {

    //dolezita anotacia - bez nej sa mi nevytvori objekt a teda nemozem pristupovat k metodam!!!
    //snazime sa vytvorit objekt z triedy CustomerService - rozhranie ktore nasledne imlementujeme v triede CustomerServiceImpl - tato trieda nam overriduje metody
    //getCustomers, add, get - vracia nam objekty z customerRepository
    // cela logika je taka ze v customerRepository mame vsetky metody ako ukladat citat atd z DB
    //vytvorime si rozhranie ktore potom implementujeme do CustomerService a tuto triedu dalej volame - jej metody ktore nam vracaju objekty

    // anotacia @Autowired -vkladanie komponentov

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    private Merchant merchant;
    private Customer customer;

    @Before
    public void create(){
        createCustomer();
        createMerchant();
    }

   // @Before
    public void createMerchant() {
        if (merchant == null) {
            merchant = new Merchant("name", "email", "address");
            Integer id = merchantService.add(merchant);
            assert id != null;
            merchant.setId(id);
        }
    }

   // @Before
    public void createCustomer() {
        if (customer == null) {
            customer = new Customer("Ferko", "Mrkvicka", "emailtest", "test", 20, "xxx");
            Integer id = customerService.add(customer);
            assert id != null;
            customer.setId(id);
        }
    }

    @Test
    public void customer() {
//        Customer customer = new Customer("Ferko", "Mrkvicka", "emailtest", "test", 20, "xxx");
//        Integer id = customerService.add(customer);
//        assert id != null;
//        customer.setId(id);

        Customer fromDb = customerService.get(customer.getId());
        Assert.assertEquals(customer, fromDb);

        List<Customer> customers = customerService.getCustomers();
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(customer, customers.get(0));
    }

    @Test
    public void merchant() {
        // already created merchant in createMerchant function

        Merchant fromDB = merchantService.get(merchant.getId());
        Assert.assertEquals(merchant, fromDB);

        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(1, merchants.size());
        Assert.assertEquals(merchant, merchants.get(0));
    }

    @Test
    public void product() {
        Product product = new Product(merchant.getId(), "name", "description", 5, 1);
        Integer id = productService.add(product);
        assert id != null;
        product.setId(id);

        Product fromDB = productService.get(id);
        Assert.assertEquals(product, fromDB);

        List<Product> products = productService.getProducts();
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(product, products.get(0));

        product.setAvailable(10);
        UpdateProductRequest productRequest = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(), product.getAvailable());

        productService.update(id, productRequest);
        Product fromDBAfterUpdate = productService.get(id);
        Assert.assertEquals(product, fromDBAfterUpdate);
        Assert.assertNotEquals(fromDB, fromDBAfterUpdate);

        productService.delete(id);
        Assert.assertEquals(0, productService.getProducts().size());
    }
//    @Test
//    public void bankAccount(){
////        BankAccount bankAccount = new BankAccount(customer.getId(),"SB10000-20000-30000",1000d, Timestamp.from(Instant.now()));
//        BankAccount bankAccount = new BankAccount(1,"SB10000-20000-30000",1000d, Timestamp.from(Instant.now()));
//        Integer id = bankAccountService.add(bankAccount);
//        assert id != null;
//        bankAccount.setId(id);
//
//        BankAccount fromDB = bankAccountService.get(id);
//        Assert.assertEquals(bankAccount,fromDB);
//
//        List<BankAccount> bankAccounts = bankAccountService.getBankAccounts();
//        Assert.assertEquals(1,bankAccounts.size());
//        Assert.assertEquals(bankAccount,bankAccounts.get(0));
//
//        UpdateBankAccount request = new UpdateBankAccount(bankAccount.getAccountBalance());
//
//        bankAccountService.update(id,request);
//        BankAccount fromDBAfterUpdate = bankAccountService.get(id);
//        Assert.assertEquals(bankAccount,fromDBAfterUpdate);
//        //Assert.assertNotEquals(fromDB,fromDBAfterUpdate);
//
//    }

}
