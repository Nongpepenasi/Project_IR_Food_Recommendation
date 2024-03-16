import time

import pandas as pd
from flask import Flask, request
from elasticsearch import Elasticsearch

app = Flask(__name__)
app.es_client = Elasticsearch("https://localhost:9200", basic_auth=("elastic", "CH4WXIv=zH9mPMf6n0e*"), ca_certs="~/http_ca.crt")


@app.route('/search_es', methods=['GET'])
def search_es():
    start = time.time()
    response_object = {'status': 'success'}
    argList = request.args.to_dict(flat=False)
    query_term = argList['query'][0]
    results = app.es_client.search(index='simple', size=100, query={"multi_match": {"query": query_term,
                                                                                    "fields": ["Name", "RecipeIngredientParts", "RecipeInstructions"]}})
    end = time.time()
    total_hit = results['hits']['total']['value']
    results_df = pd.DataFrame([[hit["_source"]['RecipeId'], hit["_source"]['Name'], hit["_source"]['RecipeIngredientParts'], hit["_source"]['RecipeInstructions'][:100], hit["_score"]] for hit in results['hits']['hits']],
                              columns=['RecipeId', 'Name', 'RecipeIngredientParts', 'RecipeInstructions', 'score'])

    response_object['total_hit'] = total_hit
    response_object['results'] = results_df.to_dict('records')
    response_object['elapse'] = end - start

    return response_object


if __name__ == '__main__':
    app.run(debug=False)