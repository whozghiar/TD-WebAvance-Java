package fr.unilasalle.tdwebavancejava.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DBException extends RuntimeException{
    private String message;
}
