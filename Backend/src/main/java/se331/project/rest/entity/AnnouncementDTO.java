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
    Long recipeId;
    String name;
    Long authorId;
    String authorName;
    String cookTime;
    String prepTime;
    String totalTime;
    String datePublished;
    List<String> description;
    List<String>  images;
    String recipeCategory;
    List<String>  keywords;
    List<String>  recipeIngredientQuantities;
    List<String>  recipeIngredientParts;
    Double aggregatedRating;
    Long reviewCount;
    Double calories;
    Double fatContent;
    Double saturatedFatContent;
    Double cholesterolContent;
    Double sodiumContent;
    Double carbohydrateContent;
    Double fiberContent;
    Double sugarContent;
    Double proteinContent;
    String recipeServings;
    String recipeYield;
    List<String>  recipeInstructions;

    List<Folder> folders;
//    Long recipeId;
//    String title;
//    String description;
//    List<String> files;
//    User user;
}
