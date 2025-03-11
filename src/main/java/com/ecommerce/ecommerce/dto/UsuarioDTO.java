package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.model.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{

    private Integer id;
    private String login;
    private String senha;
    private UserRole role;
}