package ro.softwarelabz.smartdriving.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListResponse<T> {
    private List<T> data;
    private long total;
    private Integer limit;
    private long offset;

    public static <T> ListResponse<T> build(List<T> data, long total, Integer limit, long offset) {
        return new ListResponse<>(data, total, limit, offset);
    }

    public static <T> ListResponse<T> build(List<T> data) {
        return new ListResponse<>(data, data.size(), null, 0);
    }
}