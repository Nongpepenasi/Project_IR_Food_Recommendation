import time
from spellchecker import SpellChecker

import pandas as pd
from flask import Flask, request, jsonify
from elasticsearch import Elasticsearch
from flask_cors import CORS

app = Flask(__name__)
app.es_client = Elasticsearch("https://localhost:9200", basic_auth=("elastic", "CH4WXIv=zH9mPMf6n0e*"), ca_certs="~/http_ca.crt")
CORS(app)



@app.route('/search_es', methods=['GET'])
def search_es():
    start = time.time()
    response_object = {'status': 'success'}
    argList = request.args.to_dict(flat=False)
    query_term = argList['query'][0]
    results = app.es_client.search(index='simple', size=100, query={"multi_match": {"query": query_term,
                                                                                    "fields": ["name", "recipeIngredientParts", "recipeInstructions"]}})
    end = time.time()
    # total_hit = results['hits']['total']['value']
    results_df = pd.DataFrame(
        [[hit["_source"]['recipeId'], hit["_source"]['name'], hit["_source"]['description'], hit["_source"]['keywords'], hit["_source"]['images'],  hit["_source"]['recipeIngredientQuantities'],
          hit["_source"]['recipeIngredientParts'], hit["_source"]['recipeInstructions'], hit["_score"]] for hit in results['hits']['hits']][:10],
                              columns=['recipeId', 'name', 'description', 'keywords', 'images', 'recipeIngredientQuantities', 'recipeIngredientParts', 'recipeInstructions', 'score'])

    # response_object['total_hit'] = total_hit
    response_object = results_df.to_dict('records')
    # response_object['elapse'] = end - start

    return response_object


@app.route('/spell', methods=['GET'])
def spell_correction():
    argList = request.args.to_dict(flat=False)
    query_term = argList['query'][0]
    spell = SpellChecker()
    correction = spell.candidates(query_term)

    if correction is not None:
        correction = list(correction)

    # jsonify({"correction": correction})
    return jsonify(correction)


if __name__ == '__main__':
    app.run(debug=False)