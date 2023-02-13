package ro.softwarelabz.smartdriving.clients.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.softwarelabz.smartdriving.clients.VignetteService;
import ro.softwarelabz.smartdriving.clients.objects.ERovinietaResponse;
import ro.softwarelabz.smartdriving.clients.objects.VignetteVerifyRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ERovinietaImpl implements VignetteService {
    @Value("${vignette.url}")
    private String url;

    private LocalDateTime expirationDate;

    private String expiryDate;

    @Override
    public boolean hasVignette(VignetteVerifyRequest request) {
        ERovinietaResponse response = getVignette(request);

        if (response == null) {
            return false;
        }

        return true;
    }

    public boolean isExpiredBeforeDays(long daysBeforeExpiration, VignetteVerifyRequest request) {
        ERovinietaResponse response = getVignette(request);
        if (Objects.nonNull(response) && isExpiring(daysBeforeExpiration, response)) {
            return true;
        }

        return false;
    }

    private boolean isExpiring(long daysBeforeToExpire, ERovinietaResponse response) {
        try {
            String dataStop = response.getDataStop();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endData = dateFormat.parse(dataStop);
            Date now = new Date();
            long differenceBetweenDates = endData.getTime() - now.getTime();
            long daysDifference = differenceBetweenDates / (1000*60*60*24);
            // 30.01.2023
            // diffBetweenDates = 60
            // daysBfToExp = 30
            // 60 = 30 - 1
            // 31.01.2023
            // diffBetweenDates = 23
            // daysBfToExp = 24
            // 23 = 24 -1
            // 01.02.2023
            // differenceBetweenDates = 22
            // daysBeforeToExpire = 24
            // 22 = 24 - 1 = false
            // 02.02.2023
            // differencebetweenDates = 21
            // daysBeforeToExpire = 24
            // 21 = 24 -1 = false;
            if(daysDifference == daysBeforeToExpire - 1){
                return true;
            }

        } catch (ParseException e) {
            log.error("Error parsing data stop vignette", e);
            return false;
        }
        return false;
    }

    public ERovinietaResponse getVignette(VignetteVerifyRequest request) {
        var uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("plateNumber", request.getCarNumber())
                .queryParam("vin", request.getVin())
                .build()
                .toUri();
        return rest().exchange(RequestEntity.get(uri).build(), new ParameterizedTypeReference<List<ERovinietaResponse>>() {
                })
                .getBody().stream()
                .findFirst()
                .orElse(null);
    }


    private RestTemplate rest() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        return new RestTemplateBuilder()
                .additionalMessageConverters(messageConverters)
                .additionalMessageConverters(new ByteArrayHttpMessageConverter())
                .build();
    }
}
