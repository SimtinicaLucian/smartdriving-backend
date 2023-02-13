package ro.softwarelabz.smartdriving.clients.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ERovinietaResponse {
    private String id;
    private String serie;
    private String nrAuto;
    private String serieSasiu;
    private String categorieAuto;
    private String perioadaValabilitate;
    private String dataStart;
    private String dataStop;
}
