import string

import numpy as np
import pandas as pd


def get_and_clean_data():
    data = pd.read_csv('resource/recipes.csv', encoding='utf-8')
    description = data[['RecipeId', 'Name', 'RecipeIngredientParts','RecipeInstructions']]
    cleaned_description = description.apply(lambda s: s.astype(str).str.translate(str.maketrans('', '', string.punctuation + u'\xa0')))
    cleaned_description['RecipeInstructions'] = cleaned_description['RecipeInstructions'].apply(lambda s: "not found" if s == '' else s)
    cleaned_description = cleaned_description.apply(lambda s: s.str.lstrip("c"))
    cleaned_description = cleaned_description.apply(lambda s: s.str.lower())
    cleaned_description = cleaned_description.apply(lambda s: s.str.translate(str.maketrans(string.whitespace, ' ' * len(string.whitespace), '')))
    cleaned_description = cleaned_description.drop_duplicates()

    return cleaned_description


if __name__  == '__main__':
    parsed_description = get_and_clean_data()
    parsed_description.to_csv('resource/cleaned_recipes.csv', index=False)
