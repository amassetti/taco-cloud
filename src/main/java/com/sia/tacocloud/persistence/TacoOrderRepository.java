package com.sia.tacocloud.persistence;

import com.sia.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
}
