package com.example.costume_rental.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenaltyDTO {
    Integer id;
    @NotBlank(message = "Không được bỏ trống tình trạng")
    String status;
    @NotNull(message = "Không được bỏ trống tiền phạt")
    @Min(value = 1)
    Double fine;
}
