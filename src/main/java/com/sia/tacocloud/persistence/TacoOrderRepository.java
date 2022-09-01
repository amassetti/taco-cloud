package com.sia.tacocloud.persistence;

import com.sia.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
