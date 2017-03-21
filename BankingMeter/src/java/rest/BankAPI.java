/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import banking.InterestCalc;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Balance;
import dto.Customer;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author bepis
 */
@Path("bank")
public class BankAPI {

    Gson gson;
    
    public InterestCalc calc = new InterestCalc();

    /**
     * Creates a new instance of BankAPI
     */
    public BankAPI() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }


    @POST
    @Path("json/discount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscountJson(String cusJson) {
        
        Customer cus = gson.fromJson(cusJson, Customer.class);
        
        double discount = calc.calculateDiscount(cus);
        
        return Response.status(Response.Status.OK).entity("" + discount).build();
    }

    
    @POST
    @Path("json/interest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInterestJson(String m) {
        
        Balance b = gson.fromJson(m, Balance.class);
        
        double discount = calc.calculateInterest(b.getMoney());
        
        return Response.status(Response.Status.OK).entity("" + discount).build();
    }
    
    @POST
    @Path("csv/discount")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscountCsv(String disc) {
        
        Balance b = new Balance(Double.parseDouble(disc));
        
        double discount = calc.calculateInterest(b.getMoney());
        
        return Response.status(Response.Status.OK).entity("" + discount).build();
    }

    
    @POST
    @Path("csv/interest")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInterestCsv(String interest) {
    
        List<String> items = Arrays.asList(interest.split("\\s*,\\s*"));
        Customer cus = new Customer(Boolean.valueOf(items.get(0)),Boolean.valueOf(items.get(1)),Boolean.valueOf(items.get(2)));
        
        
        double discount = calc.calculateDiscount(cus);
        
        return Response.status(Response.Status.OK).entity("" + discount).build();
    }
}
