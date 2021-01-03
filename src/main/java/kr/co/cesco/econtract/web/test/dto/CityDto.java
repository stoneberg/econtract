package kr.co.cesco.econtract.web.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CityDto {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String countryCode;

    private String latitude ;
    private String longitude;
    
    public CityDto(@NonNull String name, @NonNull String countryCode, String latitude, String longitude) {
        this.name = name;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
