package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IRequest;

public class PolynomialCalculateRequest implements IRequest {
    public String P;
    public String Q;
    public String operation;

    public PolynomialCalculateRequest(String P, String Q, String op) {
        this.P = P;
        this.Q = Q;
        this.operation = op;
    }
}
