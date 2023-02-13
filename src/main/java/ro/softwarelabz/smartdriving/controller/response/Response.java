package ro.softwarelabz.smartdriving.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private T data;
    private Object error;
    private Redirect redirect;
    private Paging paging;

    public static <T> Response<List<T>> multiple(ListResponse<T> listResponse) {
        Response<List<T>> customHttpResponse = new Response<>();
        customHttpResponse.setData(listResponse.getData() == null ? new ArrayList<>() : listResponse.getData());
        return customHttpResponse;
    }

    public static Response<Void> empty() {
        return Response.<Void>builder().build();
    }

    public static <T> Response<T> redirect(String url) {
        return Response.<T>builder()
                .redirect(Redirect.builder().url(url).build())
                .build();
    }

    public static <T> Response<T> one(T data) {
        Response<T> customHttpResponse = new Response<>();
        customHttpResponse.setData(data);
        return customHttpResponse;
    }

    public static Response error(Object errorObject) {
        Response customHttpResponse = new Response();
        customHttpResponse.setError(errorObject);
        return customHttpResponse;
    }

    @Data
    @Builder
    public static class Paging {
        private long total;
        private long offset;
        private int limit;
    }

    @Data
    @Builder
    public static class Redirect {
        private String url;
    }
}
