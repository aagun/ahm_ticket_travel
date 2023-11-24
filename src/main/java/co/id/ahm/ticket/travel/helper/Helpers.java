package co.id.ahm.ticket.travel.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.data.domain.Sort;

public class Helpers {

    public static String setSort(String sort, String defaultSort, Map<String, String> MAPPED_FIELD) {
        String key = Objects.toString(sort, "").trim().toLowerCase();
        return MAPPED_FIELD.containsKey(key) ? MAPPED_FIELD.get(key) : MAPPED_FIELD.get(defaultSort.toLowerCase());
    }

    public static Sort.Direction setOrderBy(String order) {
        final List<String> DIRECTIONS = Arrays.asList("asc", "desc");
        String direction = Objects.toString(order, "").trim();
        return DIRECTIONS.contains(direction.toLowerCase()) ? Sort.Direction.valueOf(direction.toUpperCase()) : Sort.Direction.ASC;
    }

}
