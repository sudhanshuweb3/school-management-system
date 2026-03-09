package com.fees.management.controller;

import com.fees.management.dto.PaymentRequestDto;
import com.fees.management.dto.PaymentResponseDto;
import com.fees.management.entity.User;
import com.fees.management.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDto createPayment(@Valid @RequestBody PaymentRequestDto request,
                                           @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return paymentService.createPayment(request, schoolId);
    }

    @PutMapping("/{id}")
    public PaymentResponseDto updatePayment(@PathVariable Long id,
                                            @RequestBody PaymentRequestDto dto,
                                            @AuthenticationPrincipal User user) {
        Long schoolId = user.getSchool().getId();
        return paymentService.updatePayment(id, dto, schoolId);
    }

}
