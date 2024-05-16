package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;

public record UserUpdateRequest(User user, String name, String email, String phoneNumber) implements IRequest { }
