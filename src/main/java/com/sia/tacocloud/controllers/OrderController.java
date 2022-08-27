package com.sia.tacocloud.controllers;

import com.sia.tacocloud.model.TacoOrder;
import com.sia.tacocloud.persistence.TacoOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private TacoOrderRepository orderRepository;

    @Autowired
    public OrderController(TacoOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String submitOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            log.error(errors.toString());
            return "orderForm";
        }
        log.info("Submitting order {}", order);
        order.setPlacedAt(new Date());
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
