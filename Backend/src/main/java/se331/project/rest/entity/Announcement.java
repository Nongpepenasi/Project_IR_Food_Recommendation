package se331.project.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import se331.project.rest.security.user.User;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @JsonProperty("RecipeId")
    Long recipeId;

    @JsonProperty("Name")
    String name;

    @Column(length = 1000)
    @JsonProperty("Description")
    String description;

    @JsonProperty("Images")
    String images;

    @JsonProperty("Keywords")
    String keywords;

    @Column(length = 1000)
    @JsonProperty("RecipeIngredientQuantities")
    String recipeIngredientQuantities;

    @Column(length = 1000)
    @JsonProperty("RecipeIngredientParts")
    String recipeIngredientParts;

    @Column(length = 10000)
    @JsonProperty("RecipeInstructions")
    String recipeInstructions;

//    String title;
//    String description;
//    @ElementCollection
//    List<String> files;
    @ManyToOne
    @JsonIgnore // This annotation may be needed to prevent infinite recursion during serialization
    User user;

    @ManyToMany
    List<Folder> folders;




//    @JsonProperty("RecipeId")
//    Long recipeId;
//
//    @JsonProperty("Name")
//    String name;
//
//    @JsonProperty("AuthorId")
//    String authorId;
//
//    @JsonProperty("AuthorName")
//    String authorName;
//
//    @JsonProperty("CookTime")
//    String cookTime;
//
//    @JsonProperty("PrepTime")
//    String prepTime;
//
//    @JsonProperty("TotalTime")
//    String totalTime;
//
//    @JsonProperty("DatePublished")
//    String datePublished;
//
//    @JsonProperty("Description")
//    String description;
//
//    @JsonProperty("Images")
//    String images;
//
//    @JsonProperty("RecipeCategory")
//    String recipeCategory;
//
//    @JsonProperty("Keywords")
//    String keywords;
//
//    @JsonProperty("RecipeIngredientQuantities")
//    String recipeIngredientQuantities;
//
//    @JsonProperty("RecipeIngredientParts")
//    String recipeIngredientParts;
//
//    @JsonProperty("AggregatedRating")
//    String aggregatedRating;
//
//    @JsonProperty("ReviewCount")
//    String reviewCount;
//
//    @JsonProperty("Calories")
//    String calories;
//
//    @JsonProperty("FatContent")
//    String fatContent;
//
//    @JsonProperty("SaturatedFatContent")
//    String saturatedFatContent;
//
//    @JsonProperty("CholesterolContent")
//    String cholesterolContent;
//
//    @JsonProperty("SodiumContent")
//    String sodiumContent;
//
//    @JsonProperty("CarbohydrateContent")
//    String carbohydrateContent;
//
//    @JsonProperty("FiberContent")
//    String fiberContent;
//
//    @JsonProperty("SugarContent")
//    String sugarContent;
//
//    @JsonProperty("ProteinContent")
//    String proteinContent;
//
//    @JsonProperty("RecipeServings")
//    String recipeServings;
//
//    @JsonProperty("RecipeYield")
//    String recipeYield;
//
//    @JsonProperty("RecipeInstructions")
//    String recipeInstructions;

}
