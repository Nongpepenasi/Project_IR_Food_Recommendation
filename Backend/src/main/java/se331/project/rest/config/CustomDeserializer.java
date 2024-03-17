package se331.project.rest.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import se331.project.rest.entity.Announcement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomDeserializer extends StdDeserializer<Announcement> {

    public CustomDeserializer() {
        this(null);
    }

    public CustomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Announcement deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Announcement announcement = new Announcement();
        announcement.setRecipeId(node.get("RecipeId").asLong());
        announcement.setName(node.get("Name").asText());
        announcement.setDescription(node.get("Description").asText());
        announcement.setImages(node.get("Images").asText());
        announcement.setKeywords(node.get("Keywords").asText());
        announcement.setRecipeIngredientQuantities(node.get("RecipeIngredientQuantities").asText());
        announcement.setRecipeIngredientParts(node.get("RecipeIngredientParts").asText());
        announcement.setRecipeInstructions(node.get("RecipeInstructions").asText());
        return announcement;
    }

    private List<String> getListFromJsonNode(JsonNode jsonNode) {
        List<String> list = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (final JsonNode objNode : jsonNode) {
                list.add(objNode.asText());
            }
        }
        return list;
    }

}
