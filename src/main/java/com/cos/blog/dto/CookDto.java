package com.cos.blog.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CookDto {
	private String cook_name;
	private int cook_kind;
	private String material;
	private String gram;
	
	@Builder
    public CookDto(String cook_name, int cook_kind, String material, String gram){
        this.cook_name = cook_name;
        this.cook_kind = cook_kind;
        this.material = material;
        this.gram = gram;
    }
}
