package br.com.pedrosa.ia.inventory;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Inventory {

    @ApplicationModuleListener
    void onInventoryUpdatedEvent(InventoryUpdatedEvent inventoryUpdatedEvent) throws InterruptedException {
        Thread.sleep(10_000);
        System.out.printf("the invemtory " + inventoryUpdatedEvent);
    }

}
