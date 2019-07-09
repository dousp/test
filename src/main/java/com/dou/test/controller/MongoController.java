package com.dou.test.controller;

import com.dou.test.entity.Address;
import com.dou.test.entity.Cart;
import com.dou.test.entity.Customer;
import com.dou.test.entity.Item;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author dsp
 * @date 2019-07-06
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {
    @Resource
    MongoTemplate mongotemplate;

    @PostMapping("/insert")
    public Customer insert() {

        Item item1 = new Item();
        // item1.setId("xugao");
        item1.setName("雪糕");
        item1.setPrice(5);
        item1.setCount(5);
        Item item2 = new Item();
        // item2.setId("taozi");
        item2.setName("桃子");
        item2.setPrice(6);
        item2.setCount(6);

        Cart cart = new Cart();
        cart.setType("超市");
        cart.setItems( Arrays.asList(item1,item2));

        Customer customer = new Customer();
        // customer.setId("666");
        customer.setName("酒仙");
        customer.setSex(1);
        customer.setCart(cart);
        customer = mongotemplate.insert(customer);
        return customer;
    }

    @PostMapping("/query/{name}")
    public List<Customer> query(@PathVariable String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sex").is(1));
        List<Customer> customers = mongotemplate.find(query, Customer.class);
        return customers;
    }

    @PostMapping("/query")
    public List<Customer> query() {
        Query query = Query.query(Criteria.where("mobile").is("135").and("cart.type").is("超市").and("cart.items.name").is("dd123"));
        List<Customer> customers = mongotemplate.find(query, Customer.class);
        return customers;
    }

    @PostMapping("/update/{mobile}")
    public String update(@PathVariable String mobile) {

        Query query = new Query();
        query.addCriteria(Criteria.where("mobile").is(mobile));

        Update update = new Update();
        // update.addToSet("married",11);
        // update.set("married",11);
        // update.set("cart.type","全球购");
        // update.set("type","全球购1");
        // update.unset("type");
        update.set("cart.items.0.name","全球购1361236");
        UpdateResult result = mongotemplate.updateFirst(query,update,Customer.class);
        return ""+result.getModifiedCount();
    }

    @PostMapping("/addToSet/{name}/{count}")
    public UpdateResult addToSet(@PathVariable String name, @PathVariable String count){
        Query query = Query.query(Criteria.where("mobile").is("135").and("cart.type").is("超市"));
        Item item = new Item();
        item.setCount(Integer.parseInt(count));
        item.setName(name);
        Address address = new Address();
        address.setInfo("朝阳");
        Update update = new Update();
        // push会插入一条一样的数据
        update.push("cart.items", item);
        update.push("addressList", item);
        // addToSet如果数据已经存在，则不做任何操作
        update.addToSet("cart.items", item);
        update.addToSet("addressList", address);
        return mongotemplate.upsert(query, update, Customer.class);
    }

    @PostMapping("/addToSet/{count}")
    public UpdateResult addToSet2(@PathVariable Integer count){
        Query query = Query.query(Criteria.where("mobile").is("135").and("cart.type").is("超市").and("cart.items.name").is("dd123"));
        Update update = new Update();
        update.set("cart.items.$.count", count);
        // update.set("cart.items.count", count);
        System.out.println(query.toString());
        System.out.println(update.toString());
        return mongotemplate.updateMulti(query, update, Customer.class);
    }



}
