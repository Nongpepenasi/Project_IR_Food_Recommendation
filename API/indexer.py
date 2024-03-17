from elasticsearch import Elasticsearch
from elasticsearch.helpers import parallel_bulk
import pandas as pd
from concurrent.futures import ThreadPoolExecutor
import multiprocessing


class Indexer:
    def __init__(self):
        self.es_client = Elasticsearch("https://localhost:9200", basic_auth=("elastic", "CH4WXIv=zH9mPMf6n0e*"),
                                       ca_certs="~/http_ca.crt")

    def index_documents(self, documents):
        for doc in documents:
            self.es_client.index(index='simple', document=doc)
            print(doc)

    def run_indexer(self):
        df = pd.read_csv("resource/cleaned_recipes.csv")
        documents = df.to_dict(orient='records')

        self.es_client.options(ignore_status=400).indices.create(index='simple')
        self.es_client.options(ignore_status=[400, 404]).indices.delete(index='simple')

        # for doc in documents:
        #     self.es_client.index(index='simple', document=doc)
        #     print(doc)

        with ThreadPoolExecutor(max_workers=multiprocessing.cpu_count() - 1) as executor:
            executor.map(self.index_documents, [documents])


if __name__ == '__main__':
    s = Indexer()
    s.run_indexer()
