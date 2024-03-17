import string
import json

import pandas as pd


def get_and_clean_data():
    data = pd.read_csv('resource/recipes.csv', encoding='utf-8')
    description = data[["RecipeId", "Name", "AuthorId", "AuthorName", "CookTime", "PrepTime", "TotalTime", "DatePublished",
        "Description", "Images", "RecipeCategory", "Keywords", "RecipeIngredientQuantities", "RecipeIngredientParts",
        "AggregatedRating", "ReviewCount", "Calories", "FatContent", "SaturatedFatContent", "CholesterolContent",
        "SodiumContent", "CarbohydrateContent", "FiberContent", "SugarContent", "ProteinContent", "RecipeServings",
        "RecipeYield", "RecipeInstructions"]]
    cleaned_description = description.apply(
        lambda s: s.astype(str).str.translate(str.maketrans('', '', string.punctuation + u'\xa0')))
    cleaned_description = cleaned_description.applymap(lambda s: "not found" if s == '' else s)
    cleaned_description['RecipeInstructions'] = cleaned_description['RecipeInstructions'].apply(lambda s: s.split(", "))
    cleaned_description['Images'] = cleaned_description['Images'].apply(lambda s: s.split(", "))
    cleaned_description['Description'] = cleaned_description['Description'].apply(lambda s: [s[i:i + 150] for i in range(0, len(s), 150)] if len(s) > 150 else [s])
    cleaned_description = cleaned_description.apply(lambda s: s.str.lstrip("c"))
    cleaned_description = cleaned_description.drop_duplicates()

    return cleaned_description


if __name__  == '__main__':
    parsed_description = get_and_clean_data()
    limit = parsed_description.head(10)
    # ลบ index ที่ถูกเขียนในไฟล์ json
    limit.reset_index(drop=True, inplace=True)
    # แปลงเป็น json และบันทึกลงไฟล์
    with open('resource/test_recipes.json', 'w', encoding='utf-8') as json_file:
        json.dump(limit.to_dict(orient='records'), json_file, indent=4, ensure_ascii=False)
