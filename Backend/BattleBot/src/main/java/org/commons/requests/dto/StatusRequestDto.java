package org.commons.requests.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.commons.requests.RequestStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StatusRequestDto {
    private final RequestStatus requestStatus;
}
