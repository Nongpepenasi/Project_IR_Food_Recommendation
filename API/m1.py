import string
import csv
import json


import numpy as np
import pandas as pd


def get_and_clean_data():
    data = pd.read_csv('resource/recipes.csv', encoding='utf-8')
    description = data[['RecipeId', 'Name', 'Description', 'Keywords', 'RecipeIngredientParts', 'RecipeIngredientQuantities', 'RecipeInstructions']]
    cleaned_description = description.apply(lambda s: s.astype(str).str.translate(str.maketrans('', '', string.punctuation + u'\xa0')))
    cleaned_description['Images'] = data['Images']
    cleaned_description['Description'] = cleaned_description['Description'].apply(lambda s: "not found" if s == 'nan' else s)
    cleaned_description['Keywords'] = cleaned_description['Keywords'].apply(lambda s: "not found" if s == 'nan' else s)
    cleaned_description['RecipeInstructions'] = cleaned_description['RecipeInstructions'].apply(lambda s: "not found" if s == '' else s)
    cleaned_description['RecipeIngredientQuantities'] = cleaned_description['RecipeIngredientQuantities'].apply(lambda s: "not found" if s == 'nan' else s)
    # cleaned_description['Images'] = cleaned_description['Images'].apply(lambda s: "not found" if s == ' ' else s)
    cleaned_description['Images'] = cleaned_description['Images'].fillna("not found")
    cleaned_description = cleaned_description.apply(lambda s: s.str.lstrip("c"))
    # cleaned_description = cleaned_description.apply(lambda s: s.str.lower())
    cleaned_description = cleaned_description.apply(lambda s: s.str.translate(str.maketrans(string.whitespace, ' ' * len(string.whitespace), '')))
    cleaned_description['Images'] = cleaned_description['Images'].apply(lambda s: s.split(', ')[0])
    cleaned_description = cleaned_description.apply(lambda s: s.str.lstrip("("))
    cleaned_description['Images'] = cleaned_description['Images'].str.replace(r'"', '')
    cleaned_description = cleaned_description.drop_duplicates()

    return cleaned_description


if __name__  == '__main__':
    data = pd.read_csv('resource/cleaned_recipes.csv', encoding='utf-8')
    data.rename(columns=lambda x: x[0].lower() + x[1:], inplace=True)
    data.to_csv('resource/cleaned_recipes.csv', index=False)
    # json_data = data.to_json(orient='records', indent=4)
    # with open('resource/cleaned_recipes.json', 'w', encoding='utf-8') as json_file:
    #     json_file.write(json_data)

    # parsed_description = get_and_clean_data()
    # parsed_description.to_csv('resource/cleaned_recipes.csv', index=False)
