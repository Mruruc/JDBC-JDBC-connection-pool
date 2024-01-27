package com.mruruc.application;


import com.mruruc.db_management.dbconnection.Db;
import com.mruruc.model.client.Address;
import com.mruruc.model.client.Client;
import com.mruruc.model.client.Gender;
import com.mruruc.service.AddressService;
import com.mruruc.service.ClientService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) throws SQLException {
        Db db = null;
        try {
            db = new Db();

            /*
            Address address3 = new Address( "Denmark", "Copenhagen", "Center", "00-001", 555);
            Address address1 = new Address("Poland", "Warsaw", "Praga", "00-001", 555);
            Address address2 = new Address("Sweden", "Stockholm", "North", "40-301", 999);

            */


    Client client =new Client("John",
                                     "Doe",
                                      LocalDate.of(2005,11,1),
                                      Gender.MALE,
                                      List.of("+48654321985","+15625623256"),
                                      "randomm@gmail.com",
                                       2L);

    Client client2 =new Client("Bob",
                "Simpson",
                LocalDate.of(1994,11,1),
                Gender.MALE,
                List.of("+48000000000"),
                "bobesmp@gmail.com",
                3L);
            Client client3 =new Client("Ror",
                    "Simpson",
                    LocalDate.of(1994,11,1),
                    Gender.MALE,
                    List.of("+48000000000","+48566556654"),
                    "rore@gmail.com",
                    2L);





            ClientService service = new ClientService(db);


/*
            //List of All Client
            List<Client> all = service.getAll();
            all.stream().forEach(System.out::println);


*/


/*
            //save Client
            boolean save = service.save(client3);
           // System.out.println(service.save(client));
            System.out.println(save);
*/

/*
            //Find a client By ID
            Optional<Client> byId = service.findById(6L);
            System.out.println(byId.get());




            boolean exists = service.isExists(3L);
            System.out.println(exists);
*/
/*
            //Update Client:
            Client update = service.update(5L, client);
            System.out.println(update);
*/

/*
            //Delete Client
            Long delete = service.delete(10L);
            System.out.println(delete);
*/


            /*
            AddressService adrsService = new AddressService(db);


            Address update = adrsService.update(1L, address1);
            System.out.println(update);
*/

            /*
            // find address by id
            Optional<Address> byId = adrsService.findById(1L);
            Address address = byId.orElse(null);
            System.out.println(address);
*/

/*
            //List of Addresses:
        List<Address> all = adrsService.getAll();
        all.forEach(System.out::println);
*/

        /*

        Long delete = adrsService.delete(2L);
        System.out.println(delete);

         */
/*
        boolean exists = adrsService.isExists(7L);
        if(exists){
            System.out.println("Address Exists!");
        }
        else{
            System.out.println("Address Does Not Exists!");
        }
        */

            /*
       boolean save = adrsService.save(address3);
        if(save){
            System.out.println("Address1 Saved");
        }
        boolean save1 = adrsService.save(address2);
        if(save){
            System.out.println("Address2 Saved");
        }

*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.connection().close();
        }

    }
}
