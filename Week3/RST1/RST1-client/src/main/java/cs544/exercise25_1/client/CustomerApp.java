package cs544.exercise25_1.client;

import cs544.exercise25_1.generated.customer.Customer;
import cs544.exercise25_1.service.ICustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

public class CustomerApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        ICustomerService remoteService = context.getBean("customerServiceProxy", ICustomerService.class);

        // TODO add 3 customers & print customers list
        Customer c1 = new Customer();
        c1.setName("Pacho");
        c1.setCustomerNumber("34");
        remoteService.addCustomer(c1);

        Customer c2 = new Customer();
        c2.setName("Edwin");
        c2.setCustomerNumber("29");
        remoteService.addCustomer(c2);

        Customer c3 = new Customer();
        c3.setName("Ezequiesillo");
        c3.setCustomerNumber("35");
        remoteService.addCustomer(c3);

        remoteService.getCustomers().forEach(x -> System.out.println(x.getName()));
        System.out.println("----");


        // TODO update 1 customer & print customers list
        Customer c4 = remoteService.getCustomer("34");
        c4.setName("Pachito");
        remoteService.updateCustomer(c4);

        remoteService.getCustomers().forEach(x -> System.out.println(x.getName()));
        System.out.println("----");


        // TODO delete 1 customer & print customers list
        remoteService.deleteCustomer("35");

        remoteService.getCustomers().forEach(x -> System.out.println(x.getName()));
        System.out.println("----");

    }

    public static void printCustomers(Collection<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " " + customer.getCustomerNumber());
        }
        System.out.println();
    }
}
