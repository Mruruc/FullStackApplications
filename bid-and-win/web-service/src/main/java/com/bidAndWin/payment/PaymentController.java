package com.bidAndWin.payment;

import com.bidAndWin.dto.BidDto;
import com.bidAndWin.service.BidService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaypalPaymentService paymentService;
    private final BidService bidService;

    public PaymentController(PaypalPaymentService paypalPaymentService, BidService bidService) {
        this.paymentService = paypalPaymentService;
        this.bidService = bidService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<URI> paymentRest(@RequestBody PaymentDto paymentDto) throws PayPalRESTException {

        Payment payment = paymentService.createPayment(paymentDto.getAmount(), paymentDto.getCurrency(),
                paymentDto.getMethod(), "sale", paymentDto.getDescription(),paymentDto.getSuccessUrl(),paymentDto.getCancelUrl());

        for (Links links : payment.getLinks()) {
            if (links.getRel().equals("approval_url")) {
                return ResponseEntity.status(HttpStatus.OK).body(URI.create(links.getHref()));
            }
        }
        throw new PayPalRESTException("No approval_url in payment response");
    }

    @PostMapping(value = "/success", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> paymentSuccessRest(@RequestBody BidDto bidDto) throws PayPalRESTException {
        System.out.println(bidDto);
        Payment payment = paymentService.executePayment(bidDto.getPaymentId(), bidDto.getPayerId());
        if (payment.getState().equals("approved")) {
            Long l = bidService.saveBid(bidDto.getAuctionId(), bidDto);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("auctionId",l));
        }
        throw new PayPalRESTException("Payment not approved");
    }
}
