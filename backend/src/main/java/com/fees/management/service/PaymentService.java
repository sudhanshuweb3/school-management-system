package com.fees.management.service;

import com.fees.management.dto.PaymentRequestDto;
import com.fees.management.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto request, Long schoolId);

    PaymentResponseDto updatePayment(Long id, PaymentRequestDto dto, Long schoolId);

}
