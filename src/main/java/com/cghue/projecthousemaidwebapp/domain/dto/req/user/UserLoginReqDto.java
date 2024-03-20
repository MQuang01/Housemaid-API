package com.cghue.projecthousemaidwebapp.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginReqDto (
   @NotBlank
   String username,
   @NotBlank
   String password
){ }
