package se331.project.rest.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.project.rest.security.user.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {
    Long id;
    Long recipeId;
    String name;
    String description;
    String images;
    String keywords;
    String recipeIngredientQuantities;
    String recipeIngredientParts;
    String recipeInstructions;

    List<Folder> folders;
//    Long recipeId;
//    String title;
//    String description;
//    List<String> files;
//    User user;

//    Long recipeId;
//    String name;
//    String authorId;
//    String authorName;
//    String cookTime;
//    String prepTime;
//    String totalTime;
//    String datePublished;
//    String description;
//    String images;
//    String recipeCategory;
//    String keywords;
//    String recipeIngredientQuantities;
//    String recipeIngredientParts;
//    String aggregatedRating;
//    String reviewCount;
//    String calories;
//    String fatContent;
//    String saturatedFatContent;
//    String cholesterolContent;
//    String sodiumContent;
//    String carbohydrateContent;
//    String fiberContent;
//    String sugarContent;
//    String proteinContent;
//    String recipeServings;
//    String recipeYield;
//    String recipeInstructions;
}
