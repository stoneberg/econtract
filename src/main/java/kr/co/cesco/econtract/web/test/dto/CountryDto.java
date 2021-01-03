package kr.co.cesco.econtract.web.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CountryDto {
    @NonNull
    private String code;
    @NonNull
    private String name;
    private Long population = 0L;
}
