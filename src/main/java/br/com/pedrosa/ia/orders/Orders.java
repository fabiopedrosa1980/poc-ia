package br.com.pedrosa.ia.orders;

import br.com.pedrosa.ia.inventory.InventoryUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

public class Orders {
}

class Loans {
    String displayMesageFor(Loan loan) {
        return switch (loan){
            case UnsecuredLoan(var interest) ->
                "ouch that " + interest;
            case SecuredLoan sl ->
                "Good job, you got a loan";
        };

    }
}

sealed interface Loan permits SecuredLoan, UnsecuredLoan {
}

final class SecuredLoan implements Loan {

}

record UnsecuredLoan(float interest) implements Loan {

}

@RestController
@RequestMapping("/orders")
@Transactional
class OrdersController {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    OrdersController(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @GetMapping
    Collection<Order> orders(){
        return this.orderRepository.findAll();
    }

    @PostMapping
    void create(@RequestBody Order order) {
        var saved =  this.orderRepository.save(order);
        saved.lineItems().forEach(li ->{
            applicationEventPublisher.publishEvent(new InventoryUpdatedEvent(
                    li.product(),
                    li.quantity()));
        });

    }
}

interface OrderRepository extends ListCrudRepository<Order, Integer> {
}

@Table("orders_line_items")
record LineItem(@Id Integer id, int product, int quantity) {
}

@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems) {
}