package org.experimental_players.debitiApi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.experimental_players.debitiApi.utils.PaginationRequest;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

}
