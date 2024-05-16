package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;

public record ProductEditRequest(int productId) implements IRequest { }
