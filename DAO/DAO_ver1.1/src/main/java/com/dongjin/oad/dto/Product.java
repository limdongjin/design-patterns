package com.dongjin.oad.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

import static java.util.stream.Collectors.joining;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;

    public String getAttributesToString(){
        Class cl = this.getClass();

        return Arrays.stream(cl.getDeclaredFields())
                                .map(Field::getName)
                                .collect(joining(","));
    }

    @NotNull
    public String getAttributesValueToString() throws IllegalAccessException {
        Class cl = this.getClass();
        StringBuilder result = new StringBuilder();
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);

            if(field.getType().getTypeName().equals("java.lang.String")){
                result.append("\"");
                result.append(field.get(this));
                result.append("\"");
            }else {
                result.append(field.get(this));
            }

            result.append(",");
            field.setAccessible(false);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    @NotNull
    public HashMap<String, HashMap> getAttributes() throws IllegalAccessException {
        Class cl = this.getClass();
        Field[] fields = cl.getDeclaredFields();
        HashMap<String, HashMap> result = new HashMap<>();

        for(Field field : fields){
            HashMap<String, Object> field_info = new HashMap<>();
            field.setAccessible(true);

            field_info.put("type", field.getType().getTypeName());
            field_info.put("value", field.get(this));
            result.put(field.getName(), field_info);

            field.setAccessible(false);
        }

        return result;
    }

}