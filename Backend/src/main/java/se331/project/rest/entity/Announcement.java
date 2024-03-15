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
    @JsonProperty("RecipeId")
    Long recipeId;

    @JsonProperty("Name")
    String name;

    @JsonProperty("AuthorId")
    Long authorId;

    @JsonProperty("AuthorName")
    String authorName;

    @JsonProperty("CookTime")
    String cookTime;

    @JsonProperty("PrepTime")
    String prepTime;

    @JsonProperty("TotalTime")
    String totalTime;

    @JsonProperty("DatePublished")
    String datePublished;

    @ElementCollection
    @JsonProperty("Description")
    List<String> description;

    @ElementCollection
    @JsonProperty("Images")
    List<String> images;

    @JsonProperty("RecipeCategory")
    String recipeCategory;

    @ElementCollection
    @JsonProperty("Keywords")
    List<String> keywords;

    @ElementCollection
    @JsonProperty("RecipeIngredientQuantities")
    List<String> recipeIngredientQuantities;

    @ElementCollection
    @JsonProperty("RecipeIngredientParts")
    List<String> recipeIngredientParts;

    @JsonProperty("AggregatedRating")
    Double aggregatedRating;

    @JsonProperty("ReviewCount")
    Long reviewCount;

    @JsonProperty("Calories")
    Double calories;

    @JsonProperty("FatContent")
    Double fatContent;

    @JsonProperty("SaturatedFatContent")
    Double saturatedFatContent;

    @JsonProperty("CholesterolContent")
    Double cholesterolContent;

    @JsonProperty("SodiumContent")
    Double sodiumContent;

    @JsonProperty("CarbohydrateContent")
    Double carbohydrateContent;

    @JsonProperty("FiberContent")
    Double fiberContent;

    @JsonProperty("SugarContent")
    Double sugarContent;

    @JsonProperty("ProteinContent")
    Double proteinContent;

    @JsonProperty("RecipeServings")
    String recipeServings;

    @JsonProperty("RecipeYield")
    String recipeYield;

    @ElementCollection
    @JsonProperty("RecipeInstructions")
    List<String> recipeInstructions;

//    String title;
//    String description;
//    @ElementCollection
//    List<String> files;
    @ManyToOne
    @JsonIgnore // This annotation may be needed to prevent infinite recursion during serialization
    User user;

    @ManyToMany
    List<Folder> folders;
}
