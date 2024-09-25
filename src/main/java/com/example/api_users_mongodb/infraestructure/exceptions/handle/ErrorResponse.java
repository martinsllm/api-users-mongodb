package com.example.api_users_mongodb.infraestructure.exceptions.handle;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private LocalDateTime data;

    private int status;

    private String path;
    
}
